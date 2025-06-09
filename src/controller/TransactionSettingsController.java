package controller;

import model.CuentaDAO;
import model.TransaccionDTO;
import view.TransactionSettingsView;
import javax.swing.JOptionPane;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class TransactionSettingsController {
    private final TransactionSettingsView view;
    private final CuentaDAO dao;
    private final TransaccionDTO transaccionOriginal;
    private final String numTarjeta;
    private final Runnable onUpdateCallback;

    public TransactionSettingsController(TransactionSettingsView view, CuentaDAO dao, TransaccionDTO transaccion, String numTarjeta, Runnable onUpdateCallback) {
        this.view = view;
        this.dao = dao;
        this.transaccionOriginal = transaccion;
        this.numTarjeta = numTarjeta;
        this.onUpdateCallback = onUpdateCallback;

        this.view.setMontoInicial(transaccion.getMonto());

        this.view.addConfirmarListener(e -> confirmarCambio());
        this.view.addEliminarListener(e -> eliminar());
        this.view.addCancelarListener(e -> view.dispose());
    }

    private boolean haPasadoMasDeUnaHora() {
        long diffInMillis = new Date().getTime() - transaccionOriginal.getFecha().getTime();
        long diffInMinutes = TimeUnit.MILLISECONDS.toMinutes(diffInMillis);
        return diffInMinutes > 60;
    }

    private void confirmarCambio() {
        if (haPasadoMasDeUnaHora()) {
            JOptionPane.showMessageDialog(view, "Ha pasado más de una hora. Ya no puedes modificar esta transacción.", "Tiempo Excedido", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String pin = view.getPin();
        if (!dao.validarPinTarjeta(numTarjeta, pin)) {
            JOptionPane.showMessageDialog(view, "PIN incorrecto.", "Error de Autenticación", JOptionPane.ERROR_MESSAGE);
            return;
        }

        double nuevoMonto = view.getNuevoMonto();
        boolean exito = dao.actualizarMontoTransaccion(transaccionOriginal.getId(), transaccionOriginal.getMonto(), transaccionOriginal.getTipoTransaccion(), nuevoMonto, numTarjeta);

        if (exito) {
            JOptionPane.showMessageDialog(view, "Transacción actualizada correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            onUpdateCallback.run();
            view.dispose();
        } else {
            JOptionPane.showMessageDialog(view, "No se pudo actualizar la transacción.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminar() {
        if (haPasadoMasDeUnaHora()) {
            JOptionPane.showMessageDialog(view, "Ha pasado más de una hora. Ya no puedes eliminar esta transacción.", "Tiempo Excedido", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int respuesta = JOptionPane.showConfirmDialog(view, "¿Estás seguro de que deseas eliminar esta transacción?", "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);
        if (respuesta != JOptionPane.YES_OPTION) {
            return;
        }

        String pin = view.getPin();
        if (!dao.validarPinTarjeta(numTarjeta, pin)) {
            JOptionPane.showMessageDialog(view, "PIN incorrecto.", "Error de Autenticación", JOptionPane.ERROR_MESSAGE);
            return;
        }

        boolean exito = dao.eliminarTransaccion(transaccionOriginal.getId(), numTarjeta, transaccionOriginal.getMonto(), transaccionOriginal.getTipoTransaccion());

        if (exito) {
            JOptionPane.showMessageDialog(view, "Transacción eliminada correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            onUpdateCallback.run();
            view.dispose();
        } else {
            JOptionPane.showMessageDialog(view, "No se pudo eliminar la transacción.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}