package controller;

import model.ClienteDTO;
import model.CuentaDAO;
import view.AccountSettingsView;
import view.LoginView;
import javax.swing.JOptionPane;
import java.awt.Window;

public class AccountSettingsController {
    private final AccountSettingsView view;
    private final CuentaDAO dao;
    private final int idCliente;
    private ClienteDTO clienteActual;
    private final Runnable onUpdateCallback;

    public AccountSettingsController(AccountSettingsView view, CuentaDAO dao, int idCliente, Runnable onUpdateCallback) {
        this.view = view;
        this.dao = dao;
        this.idCliente = idCliente;
        this.onUpdateCallback = onUpdateCallback;

        cargarDatosIniciales();

        this.view.addAceptarListener(e -> actualizarDatos());
        this.view.addEliminarListener(e -> eliminarCuenta());
        this.view.addCancelarListener(e -> view.dispose());
    }

    private void cargarDatosIniciales() {
        clienteActual = dao.getCliente(idCliente);
        if (clienteActual != null) {
            view.setDatosCliente(clienteActual);
        }
    }

    private void actualizarDatos() {
        clienteActual.setNombre(view.getNombre());
        clienteActual.setApellidoPaterno(view.getApellidoPaterno());
        clienteActual.setApellidoMaterno(view.getApellidoMaterno());
        String nuevaContrasena = new String(view.getNuevaContrasena());

        boolean exito = dao.actualizarCliente(clienteActual, nuevaContrasena);

        if (exito) {
            JOptionPane.showMessageDialog(view, "Datos actualizados correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

            if (onUpdateCallback != null) {
                onUpdateCallback.run();
            }

            view.dispose();
        } else {
            JOptionPane.showMessageDialog(view, "No se pudieron actualizar los datos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarCuenta() {
        int respuesta = JOptionPane.showConfirmDialog(view,
                "¿Estás seguro de que deseas eliminar tu cuenta?\nEsta acción es irreversible y borrará todas tus tarjetas.",
                "Confirmar Eliminación", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

        if (respuesta == JOptionPane.YES_OPTION) {
            boolean exito = dao.eliminarCliente(idCliente);
            if (exito) {
                JOptionPane.showMessageDialog(null, "Cuenta eliminada exitosamente.");
                Window[] windows = Window.getWindows();
                for (Window window : windows) {
                    window.dispose();
                }
                new LoginView().setVisible(true);
            } else {
                JOptionPane.showMessageDialog(view, "Error al eliminar la cuenta.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}