package controller;

import model.ClienteDTO;
import model.CuentaDAO;
import model.TarjetaDTO;
import model.UsuarioDTO;
import view.AccountSettingsView;
import view.AddCardView;
import view.DashboardAccountView;
import view.DashboardView;
import view.LoginView;
import javax.swing.JOptionPane;
import java.util.List;

public class DashboardAccountController {

    private final DashboardAccountView view;
    private final UsuarioDTO usuario;
    private final CuentaDAO cuentaDAO;

    public DashboardAccountController(DashboardAccountView view, UsuarioDTO usuario, CuentaDAO cuentaDAO) {
        this.view = view;
        this.usuario = usuario;
        this.cuentaDAO = cuentaDAO;

        this.view.addLogoutListener(e -> logout());
        this.view.addSettingsListener(e -> openSettings());
        this.view.addAddCardListener(e -> openAddCardDialog());
        this.view.addVerListener(e -> verDetallesTarjeta());

        this.view.setNombreCliente(usuario.getNombreCompleto());
        cargarTarjetas();
    }

    private void cargarTarjetas() {
        List<TarjetaDTO> tarjetas = cuentaDAO.getTarjetasPorCliente(usuario.getIdCliente());
        view.setTableData(tarjetas);
    }


    private void logout() {
        view.dispose();
        new LoginView().setVisible(true);
    }


    private void openSettings() {
        AccountSettingsView settingsView = new AccountSettingsView(view);

        Runnable callbackDeRefresco = this::refrescarDatosDashboard;

        new AccountSettingsController(settingsView, cuentaDAO, usuario.getIdCliente(), callbackDeRefresco);
        settingsView.setVisible(true);
    }


    private void openAddCardDialog() {
        AddCardView addCardView = new AddCardView(view);

        new AddCardController(addCardView, cuentaDAO, usuario.getIdCliente());
        addCardView.setVisible(true);

        cargarTarjetas();
    }

    private void verDetallesTarjeta() {
        TarjetaDTO tarjetaSeleccionada = view.getTarjetaSeleccionada();

        if (tarjetaSeleccionada != null) {
            DashboardView detalleView = new DashboardView(
                    usuario.getNombreCompleto(),
                    tarjetaSeleccionada.getSaldo(),
                    view
            );

            new DashboardController(detalleView, cuentaDAO, tarjetaSeleccionada.getNumTarjeta());

            detalleView.setVisible(true);
            view.dispose();

        } else {
            JOptionPane.showMessageDialog(view, "Por favor, selecciona una tarjeta de la lista.", "Sin selección", JOptionPane.WARNING_MESSAGE);
        }
    }


    private void refrescarDatosDashboard() {
        ClienteDTO clienteActualizado = cuentaDAO.getCliente(usuario.getIdCliente());

        if (clienteActualizado != null) {
            String nuevoNombreCompleto = clienteActualizado.getNombre() + " " + clienteActualizado.getApellidoPaterno();
            usuario.setNombreCompleto(nuevoNombreCompleto);

            view.setNombreCliente(nuevoNombreCompleto);
        }
    }
}