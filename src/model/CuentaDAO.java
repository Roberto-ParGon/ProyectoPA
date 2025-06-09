package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.Timestamp;


public class CuentaDAO {
    public boolean registrarCliente(String nombre, String apPaterno, String apMaterno, String correo, String contrasena) {
        java.sql.Connection con = model.Connection.getInstance().getDatabaseConnection();
        String sql = "INSERT INTO Cliente (Nombre, Apellido_Paterno, Apellido_Materno, Correo, Contrasena) VALUES (?, ?, ?, ?, ?)";

        try (java.sql.PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, nombre);
            pst.setString(2, apPaterno);
            pst.setString(3, apMaterno);
            pst.setString(4, correo);
            pst.setString(5, contrasena);

            int filasAfectadas = pst.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException e) {
            System.err.println("Error al registrar el cliente: " + e.getMessage());
            return false;
        }
    }


    public UsuarioDTO validarLoginPorCorreo(String correo, String contrasena) {
        java.sql.Connection con = model.Connection.getInstance().getDatabaseConnection();
        UsuarioDTO usuario = null;
        String sql = "SELECT ID_Cliente, Nombre, Apellido_Paterno FROM Cliente WHERE Correo = ? AND Contrasena = ?";

        try (PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, correo);
            pst.setString(2, contrasena);

            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    int idCliente = rs.getInt("ID_Cliente");
                    String nombre = rs.getString("Nombre");
                    String apellido = rs.getString("Apellido_Paterno");
                    usuario = new UsuarioDTO(idCliente, nombre + " " + apellido, 0.0);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al validar credenciales por correo: " + e.getMessage());
            e.printStackTrace();
        }
        return usuario;
    }

    public List<TarjetaDTO> getTarjetasPorCliente(int idCliente) {
        Connection con = model.Connection.getInstance().getDatabaseConnection();
        List<TarjetaDTO> tarjetas = new ArrayList<>();
        String sql = "SELECT Num_Tarjeta, Saldo, Tipo_Tarjeta, Limite_Credito, Comisiones_Acumuladas FROM Tarjeta WHERE ID_Cliente = ?";

        try (java.sql.PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, idCliente);
            try (java.sql.ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    tarjetas.add(new TarjetaDTO(
                            rs.getString("Num_Tarjeta"),
                            rs.getDouble("Saldo"),
                            rs.getString("Tipo_Tarjeta"),
                            rs.getDouble("Limite_Credito"),
                            rs.getDouble("Comisiones_Acumuladas")
                    ));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener tarjetas por cliente: " + e.getMessage());
            e.printStackTrace();
        }
        return tarjetas;
    }
    public TarjetaDTO getTarjeta(String numTarjeta) {
        Connection con = model.Connection.getInstance().getDatabaseConnection();
        TarjetaDTO tarjeta = null;
        String sql = "SELECT * FROM Tarjeta WHERE Num_Tarjeta = ?";
        try (java.sql.PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, numTarjeta);
            try (java.sql.ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    tarjeta = new TarjetaDTO(
                            rs.getString("Num_Tarjeta"),
                            rs.getDouble("Saldo"),
                            rs.getString("Tipo_Tarjeta"),
                            rs.getDouble("Limite_Credito"),
                            rs.getDouble("Comisiones_Acumuladas")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener tarjeta: " + e.getMessage());
            e.printStackTrace();
        }
        return tarjeta;
    }

    public boolean validarPinTarjeta(String numTarjeta, String pin) {
        Connection con = model.Connection.getInstance().getDatabaseConnection();
        String sql = "SELECT COUNT(*) FROM Tarjeta WHERE Num_Tarjeta = ? AND PIN = ?";
        try (java.sql.PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, numTarjeta);
            pst.setString(2, pin);
            try (java.sql.ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) == 1;
                }
            }
        } catch (java.sql.SQLException e) {
            System.err.println("Error al validar PIN: " + e.getMessage());
        }
        return false;
    }

    public boolean actualizarMontoTransaccion(int idTransaccion, double montoOriginal, String tipo, double montoNuevo, String numTarjeta) {
        Connection con = model.Connection.getInstance().getDatabaseConnection();

        double ajusteSaldo = tipo.equalsIgnoreCase("RETIRO") ? (montoOriginal - montoNuevo) : (montoNuevo - montoOriginal);

        String sqlUpdateSaldo = "UPDATE Tarjeta SET Saldo = Saldo + ? WHERE Num_Tarjeta = ?";
        String sqlUpdateTransaccion = "UPDATE Transaccion SET Monto = ? WHERE ID_Transaccion = ?";

        try {
            con.setAutoCommit(false);

            try (java.sql.PreparedStatement pst = con.prepareStatement(sqlUpdateSaldo)) {
                pst.setDouble(1, ajusteSaldo);
                pst.setString(2, numTarjeta);
                pst.executeUpdate();
            }
            try (java.sql.PreparedStatement pst = con.prepareStatement(sqlUpdateTransaccion)) {
                pst.setDouble(1, montoNuevo);
                pst.setInt(2, idTransaccion);
                pst.executeUpdate();
            }

            con.commit(); // Confirmar cambios
            return true;
        } catch (java.sql.SQLException e) {
            System.err.println("Error al actualizar transacción, revirtiendo: " + e.getMessage());
            try { con.rollback(); } catch (java.sql.SQLException ex) {}
            return false;
        } finally {
            try { con.setAutoCommit(true); } catch (java.sql.SQLException ex) {}
        }
    }

    public boolean realizarTransaccion(String numTarjeta, double monto, String tipo, double comision) {
        Connection con = model.Connection.getInstance().getDatabaseConnection();
        String sqlUpdate = "UPDATE Tarjeta SET Saldo = Saldo - ?, Comisiones_Acumuladas = Comisiones_Acumuladas + ? WHERE Num_Tarjeta = ?";
        String sqlInsert = "INSERT INTO Transaccion (Num_Tarjeta, Monto, Fecha, Tipo_Transaccion) VALUES (?, ?, NOW(), ?)";

        try {
            con.setAutoCommit(false);

            try (java.sql.PreparedStatement pstUpdate = con.prepareStatement(sqlUpdate)) {
                double montoTotalARestar = (tipo.equals("RETIRO") ? monto : -monto) + comision;
                pstUpdate.setDouble(1, montoTotalARestar);
                pstUpdate.setDouble(2, comision);
                pstUpdate.setString(3, numTarjeta);
                pstUpdate.executeUpdate();
            }

            try (java.sql.PreparedStatement pstInsert = con.prepareStatement(sqlInsert)) {
                pstInsert.setString(1, numTarjeta);
                pstInsert.setDouble(2, monto);
                pstInsert.setString(3, tipo);
                pstInsert.executeUpdate();
            }

            con.commit();
            return true;

        } catch (SQLException e) {
            System.err.println("Error en la transacción, revirtiendo cambios: " + e.getMessage());
            try {
                con.rollback();
            } catch (SQLException rollbackEx) {
                System.err.println("Error al revertir la transacción: " + rollbackEx.getMessage());
            }
            return false;
        } finally {
            try {
                con.setAutoCommit(true);
            } catch (SQLException finalEx) {
                System.err.println("Error al restaurar auto-commit: " + finalEx.getMessage());
            }
        }
    }

    public boolean crearNuevaTarjeta(int idCliente, String numeroTarjeta, String pin, String tipoCuenta) {
        java.sql.Connection con = model.Connection.getInstance().getDatabaseConnection();
        String sql = "INSERT INTO Tarjeta (Num_Tarjeta, ID_Cliente, PIN, Tipo_Tarjeta, Saldo) VALUES (?, ?, ?, ?, 0.00)";

        try (java.sql.PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, numeroTarjeta);
            pst.setInt(2, idCliente);
            pst.setString(3, pin);
            pst.setString(4, tipoCuenta);

            int filasAfectadas = pst.executeUpdate();
            return filasAfectadas > 0;

        } catch (java.sql.SQLException e) {
            System.err.println("Error al crear nueva tarjeta: " + e.getMessage());
            e.printStackTrace();
            return false;
        }

    }


    public List<TransaccionDTO> getTransaccionesPorTarjeta(String numTarjeta) {
        java.sql.Connection con = model.Connection.getInstance().getDatabaseConnection();
        List<TransaccionDTO> transacciones = new ArrayList<>();
        String sql = "SELECT ID_Transaccion, Fecha, Monto, Tipo_Transaccion FROM Transaccion WHERE Num_Tarjeta = ? ORDER BY Fecha DESC";

        try (java.sql.PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, numTarjeta);
            try (java.sql.ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("ID_Transaccion");

                    Timestamp fecha = rs.getTimestamp("Fecha");
                    double monto = rs.getDouble("Monto");
                    String tipo = rs.getString("Tipo_Transaccion");

                    transacciones.add(new TransaccionDTO(id, fecha, monto, tipo));
                }
            }
        } catch (java.sql.SQLException e) {
            System.err.println("Error al obtener las transacciones: " + e.getMessage());
            e.printStackTrace();
        }
        return transacciones;
    }

    public boolean eliminarTransaccion(int idTransaccion, String numTarjeta, double monto, String tipo) {
        Connection con = model.Connection.getInstance().getDatabaseConnection();
        String sqlDelete = "DELETE FROM Transaccion WHERE ID_Transaccion = ?";
        double montoAReajustar = tipo.equalsIgnoreCase("RETIRO") ? monto : -monto;
        String sqlUpdate = "UPDATE Tarjeta SET Saldo = Saldo + ? WHERE Num_Tarjeta = ?";

        try {
            con.setAutoCommit(false);

            try(PreparedStatement pstDelete = con.prepareStatement(sqlDelete)) {
                pstDelete.setInt(1, idTransaccion);
                pstDelete.executeUpdate();
            }

            try(PreparedStatement pstUpdate = con.prepareStatement(sqlUpdate)) {
                pstUpdate.setDouble(1, montoAReajustar);
                pstUpdate.setString(2, numTarjeta);
                pstUpdate.executeUpdate();
            }

            con.commit();
            return true;
        } catch (SQLException e) {
            try { con.rollback(); } catch (SQLException ex) { }
            return false;
        } finally {
            try { con.setAutoCommit(true); } catch (SQLException ex) { }
        }
    }

    public ClienteDTO getCliente(int idCliente) {
        Connection con = model.Connection.getInstance().getDatabaseConnection();
        ClienteDTO cliente = null;
        String sql = "SELECT ID_Cliente, Nombre, Apellido_Paterno, Apellido_Materno, Correo FROM Cliente WHERE ID_Cliente = ?";
        try (java.sql.PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, idCliente);
            try (java.sql.ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    cliente = new ClienteDTO(
                            rs.getInt("ID_Cliente"),
                            rs.getString("Nombre"),
                            rs.getString("Apellido_Paterno"),
                            rs.getString("Apellido_Materno"),
                            rs.getString("Correo")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener cliente: " + e.getMessage());
        }
        return cliente;
    }

    public boolean actualizarCliente(ClienteDTO cliente, String nuevaContrasena) {
        Connection con = model.Connection.getInstance().getDatabaseConnection();
        StringBuilder sql = new StringBuilder("UPDATE Cliente SET Nombre = ?, Apellido_Paterno = ?, Apellido_Materno = ?");
        if (nuevaContrasena != null && !nuevaContrasena.isEmpty()) {
            sql.append(", Contrasena = ?");
        }
        sql.append(" WHERE ID_Cliente = ?");

        try (java.sql.PreparedStatement pst = con.prepareStatement(sql.toString())) {
            pst.setString(1, cliente.getNombre());
            pst.setString(2, cliente.getApellidoPaterno());
            pst.setString(3, cliente.getApellidoMaterno());

            int parameterIndex = 4;
            if (nuevaContrasena != null && !nuevaContrasena.isEmpty()) {
                pst.setString(parameterIndex++, nuevaContrasena);
            }
            pst.setInt(parameterIndex, cliente.getId());

            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al actualizar cliente: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminarCliente(int idCliente) {
        Connection con = model.Connection.getInstance().getDatabaseConnection();
        String sqlDeleteTransacciones = "DELETE FROM Transaccion WHERE Num_Tarjeta IN (SELECT Num_Tarjeta FROM Tarjeta WHERE ID_Cliente = ?)";
        String sqlDeleteTarjetas = "DELETE FROM Tarjeta WHERE ID_Cliente = ?";
        String sqlDeleteCliente = "DELETE FROM Cliente WHERE ID_Cliente = ?";

        try {
            con.setAutoCommit(false);

            try (java.sql.PreparedStatement pst = con.prepareStatement(sqlDeleteTransacciones)) {
                pst.setInt(1, idCliente);
                pst.executeUpdate();
            }
            try (java.sql.PreparedStatement pst = con.prepareStatement(sqlDeleteTarjetas)) {
                pst.setInt(1, idCliente);
                pst.executeUpdate();
            }
            try (java.sql.PreparedStatement pst = con.prepareStatement(sqlDeleteCliente)) {
                pst.setInt(1, idCliente);
                pst.executeUpdate();
            }

            con.commit();
            return true;
        } catch (SQLException e) {
            System.err.println("Error al eliminar cliente, revirtiendo cambios: " + e.getMessage());
            try { con.rollback(); } catch (SQLException rollbackEx) { }
            return false;
        } finally {
            try { con.setAutoCommit(true); } catch (SQLException finalEx) { }
        }
    }

}