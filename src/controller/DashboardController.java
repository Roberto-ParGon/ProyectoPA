package controller;

import model.CuentaDAO;
import model.TarjetaDTO;
import model.TransaccionDTO;
import view.DashboardView;
import javax.swing.*;
import java.util.List;

public class DashboardController {

    private final DashboardView view;
    private final CuentaDAO dao;
    private final String numTarjeta;

    public DashboardController(DashboardView view, CuentaDAO dao, String numTarjeta) {
        this.view = view;
        this.dao = dao;
        this.numTarjeta = numTarjeta;

        cargarDatosDeTarjeta();

        this.view.addDepositarListener(e -> procesarTransaccion("DEPOSITO"));
        this.view.addRetirarListener(e -> procesarTransaccion("RETIRO"));
    }

    private void cargarDatosDeTarjeta() {
        TarjetaDTO tarjeta = dao.getTarjeta(numTarjeta);
        if (tarjeta != null) {
            view.setSaldo(tarjeta.getSaldo());
            List<TransaccionDTO> transacciones = dao.getTransaccionesPorTarjeta(numTarjeta);
            view.setTransaccionesData(transacciones);
        }
    }

    private void procesarTransaccion(String tipo) {
        String titulo = tipo.equals("RETIRO") ? "¿Cuánto monto deseas retirar?" : "¿Cuánto monto deseas depositar?";
        Double monto = view.showAmountDialog(titulo, tipo);

        if (monto == null || monto <= 0) {
            return;
        }

        TarjetaDTO tarjeta = dao.getTarjeta(numTarjeta);
        if (tarjeta == null) {
            JOptionPane.showMessageDialog(view, "No se pudo encontrar la información de la tarjeta.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        double comision = 0;

        if (tipo.equals("RETIRO")) {
            if (tarjeta.getTipoTarjeta().equalsIgnoreCase("DEBITO")) {
                if (tarjeta.getSaldo() < monto) {
                    comision = 100.0;
                    JOptionPane.showMessageDialog(view, "No tienes saldo suficiente. Se aplicará una comisión de $100.00.", "Aviso de Comisión", JOptionPane.WARNING_MESSAGE);
                }
            } else { // CRÉDITO
                double creditoDisponible = tarjeta.getSaldo() + tarjeta.getLimiteCredito();
                if (creditoDisponible < monto) {
                    comision = 200.0;
                    JOptionPane.showMessageDialog(view, "No tienes crédito suficiente. Se aplicará una comisión de $200.00.", "Aviso de Comisión", JOptionPane.WARNING_MESSAGE);
                }
            }
        }

        boolean exito = dao.realizarTransaccion(numTarjeta, monto, tipo, comision);

        if (exito) {
            JOptionPane.showMessageDialog(view, "¡Transacción exitosa!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            cargarDatosDeTarjeta();
        } else {
            JOptionPane.showMessageDialog(view, "La transacción no se pudo completar.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}