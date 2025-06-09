package controller;

import model.CuentaDAO;
import view.RegisterView;
import javax.swing.JOptionPane;
import java.util.Arrays;

public class RegisterController {
    private final RegisterView view;
    private final CuentaDAO dao;

    public RegisterController(RegisterView view, CuentaDAO dao) {
        this.view = view;
        this.dao = dao;
        this.view.addAceptarListener(e -> registrarNuevoUsuario());
    }

    private void registrarNuevoUsuario() {
        String nombre = view.getNombre();
        String apPaterno = view.getApellidoPaterno();
        String apMaterno = view.getApellidoMaterno();
        String correo = view.getCorreo();
        char[] contrasena = view.getContrasena();
        char[] confirmarContrasena = view.getConfirmarContrasena();

        if (nombre.isEmpty() || apPaterno.isEmpty() || apMaterno.isEmpty()|| correo.isEmpty() || contrasena.length == 0) {
            JOptionPane.showMessageDialog(view, "Por favor, complete todos los campos requeridos.", "Campos Vacíos", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!Arrays.equals(contrasena, confirmarContrasena)) {
            JOptionPane.showMessageDialog(view, "Las contraseñas no coinciden.", "Error de Contraseña", JOptionPane.ERROR_MESSAGE);
            return;
        }

        boolean exito = dao.registrarCliente(nombre, apPaterno, apMaterno, correo, new String(contrasena));

        if (exito) {
            JOptionPane.showMessageDialog(view, "¡Cuenta creada exitosamente!", "Registro Completo", JOptionPane.INFORMATION_MESSAGE);
            view.dispose();
        } else {
            JOptionPane.showMessageDialog(view, "No se pudo crear la cuenta. Es posible que el correo ya esté en uso.", "Error de Registro", JOptionPane.ERROR_MESSAGE);
        }
    }
}