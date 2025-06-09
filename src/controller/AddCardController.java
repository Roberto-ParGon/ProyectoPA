package controller;

import model.CuentaDAO;
import view.AddCardView;
import javax.swing.JOptionPane;
import java.util.Arrays;
import java.util.Random;

public class AddCardController {

    private final AddCardView view;
    private final CuentaDAO dao;
    private final int idCliente;

    public AddCardController(AddCardView view, CuentaDAO dao, int idCliente) {
        this.view = view;
        this.dao = dao;
        this.idCliente = idCliente;

        this.view.addAceptarListener(e -> crearTarjeta());
        this.view.addCancelarListener(e -> view.dispose());
    }

    private void crearTarjeta() {
        String tipoCuenta = view.getTipoCuenta();
        char[] pin = view.getPin();
        char[] confirmarPin = view.getConfirmarPin();

        if (pin.length != 4) {
            JOptionPane.showMessageDialog(view, "El PIN debe tener exactamente 4 dígitos.", "Error de Validación", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!Arrays.equals(pin, confirmarPin)) {
            JOptionPane.showMessageDialog(view, "Los PIN no coinciden.", "Error de Validación", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String numeroTarjeta = generarNumeroTarjeta();

        boolean exito = dao.crearNuevaTarjeta(idCliente, numeroTarjeta, new String(pin), tipoCuenta);

        if (exito) {
            JOptionPane.showMessageDialog(view, "¡Tarjeta creada exitosamente!\nNúmero: " + numeroTarjeta, "Éxito", JOptionPane.INFORMATION_MESSAGE);
            view.dispose();
        } else {
            JOptionPane.showMessageDialog(view, "Ocurrió un error al crear la tarjeta.", "Error de Base de Datos", JOptionPane.ERROR_MESSAGE);
        }
    }

    private String generarNumeroTarjeta() {
        Random rand = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 16; i++) {
            sb.append(rand.nextInt(10));
        }
        return sb.toString();
    }
}