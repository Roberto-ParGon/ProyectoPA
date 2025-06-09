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
        String sql = "SELECT Fecha, Monto, Tipo_Transaccion FROM Transaccion WHERE Num_Tarjeta = ? ORDER BY Fecha DESC";

        try (java.sql.PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, numTarjeta);
            try (java.sql.ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    Timestamp fecha = rs.getTimestamp("Fecha");
                    double monto = rs.getDouble("Monto");
                    String tipo = rs.getString("Tipo_Transaccion");
                    transacciones.add(new TransaccionDTO(fecha, monto, tipo));
                }
            }
        } catch (java.sql.SQLException e) {
            System.err.println("Error al obtener las transacciones: " + e.getMessage());
            e.printStackTrace();
        }
        return transacciones;
    }
}