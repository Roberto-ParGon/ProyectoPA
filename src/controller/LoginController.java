package controller;

import model.CuentaDAO;
import model.UsuarioDTO;
import view.DashboardAccountView;
import view.LoginView;
import javax.swing.JOptionPane;

public class LoginController {

    private final LoginView loginView;
    private final CuentaDAO cuentaDAO;

    public LoginController(LoginView loginView) {
        this.loginView = loginView;
        this.cuentaDAO = new CuentaDAO();
        this.loginView.addLoginListener(e -> iniciarSesion());
    }

    private void iniciarSesion() {
        String correo = loginView.getCorreo();
        String contrasena = loginView.getContrasena();

        if (correo.isEmpty() || contrasena.isEmpty()) {
            JOptionPane.showMessageDialog(loginView, "Por favor, ingrese correo y contraseña.", "Campos vacíos", JOptionPane.WARNING_MESSAGE);
            return;
        }

        UsuarioDTO usuario = cuentaDAO.validarLoginPorCorreo(correo, contrasena);

        if (usuario != null) {
            loginView.dispose();
            DashboardAccountView principalView = new DashboardAccountView(usuario.getNombreCompleto());
            new DashboardAccountController(principalView, usuario, cuentaDAO);
            principalView.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(loginView, "Correo o contraseña incorrectos.", "Error de autenticación", JOptionPane.ERROR_MESSAGE);
        }
    }
}