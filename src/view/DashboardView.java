package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;
import model.TransaccionDTO;

public class DashboardView extends JFrame {
    private JFrame owner;
    private DefaultTableModel modeloRetiros;
    private DefaultTableModel modeloDepositos;
    private JLabel lblBienvenido;
    private JLabel lblSaldo;

    private JButton btnRetirar;
    private JButton btnDepositar;
    private JButton btnManage;

    public DashboardView(String clienteNombre, double saldoActual, JFrame owner) {
        this.owner = owner;
        setTitle("Panel de Usuario");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1120, 700);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setResizable(false);
        initializeComponents(clienteNombre, saldoActual);
    }

    private void initializeComponents(String clienteNombre, double saldoActual) {
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
        topPanel.setBorder(new EmptyBorder(20, 30, 20, 30));
        topPanel.setBackground(new Color(44, 44, 58));
        JButton btnLogout = new JButton(new ImageIcon(getClass().getResource("/resources/icon/logout.png")));
        btnLogout.setBorderPainted(false);
        btnLogout.setFocusPainted(false);
        btnLogout.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnLogout.setContentAreaFilled(false);
        btnLogout.addActionListener(e -> {
            owner.setVisible(true);
            this.dispose();
        });
        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textPanel.setOpaque(false);
        lblBienvenido = new JLabel("Bienvenido, " + clienteNombre);
        lblBienvenido.setFont(new Font("SansSerif", Font.PLAIN, 24));
        lblBienvenido.setForeground(Color.LIGHT_GRAY);
        lblSaldo = new JLabel(String.format("Tu saldo es: $%,.2f MXN", saldoActual));
        lblSaldo.setFont(new Font("SansSerif", Font.BOLD, 32));
        lblSaldo.setForeground(Color.WHITE);
        textPanel.add(lblBienvenido);
        textPanel.add(Box.createVerticalStrut(3));
        textPanel.add(lblSaldo);
        JPanel contentPanel = new JPanel(new GridBagLayout());
        contentPanel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        contentPanel.add(textPanel, gbc);
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 35, 0));
        buttonPanel.setOpaque(false);

        btnRetirar = new JButton("RETIRAR");
        btnRetirar.setFont(new Font("SansSerif", Font.BOLD, 18));
        btnRetirar.setBackground(new Color(244, 67, 54));
        btnRetirar.setForeground(Color.WHITE);
        btnRetirar.setFocusPainted(false);
        btnRetirar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnRetirar.setPreferredSize(new Dimension(150, 50));
        btnRetirar.setBorderPainted(false);


        btnDepositar = new JButton("DEPOSITAR");
        btnDepositar.setFont(new Font("SansSerif", Font.BOLD, 18));
        btnDepositar.setBackground(new Color(102, 187, 106));
        btnDepositar.setForeground(Color.WHITE);
        btnDepositar.setFocusPainted(false);
        btnDepositar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnDepositar.setPreferredSize(new Dimension(150, 50));
        btnDepositar.setBorderPainted(false);

        btnManage = new JButton(new ImageIcon(getClass().getResource("/resources/icon/card_config.png")));
        btnManage.setContentAreaFilled(false);
        btnManage.setBorderPainted(false);
        btnManage.setFocusPainted(false);
        btnManage.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnManage.setPreferredSize(new Dimension(50, 50));

        btnManage.addActionListener( e ->{
            AccountSettingsView settings = new AccountSettingsView(this);
            settings.setVisible(true);
        });

        buttonPanel.add(btnRetirar);
        buttonPanel.add(btnDepositar);
        buttonPanel.add(btnManage);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.insets = new Insets(3, 30, 0, 0);
        contentPanel.add(buttonPanel, gbc);
        topPanel.add(btnLogout);
        topPanel.add(Box.createHorizontalStrut(15));
        topPanel.add(contentPanel);
        add(topPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new GridLayout(1, 2, 20, 0));
        centerPanel.setBorder(new EmptyBorder(20, 30, 30, 30));
        centerPanel.setBackground(new Color(44, 44, 58));
        modeloRetiros = new DefaultTableModel(new Object[]{"Fecha de retiro", "Monto de retiro"}, 0) {
            public boolean isCellEditable(int row, int column) { return false; }
        };
        JTable tablaRetiros = new JTable(modeloRetiros);
        tablaRetiros.setRowHeight(30);
        tablaRetiros.setFont(new Font("SansSerif", Font.PLAIN, 14));
        tablaRetiros.getTableHeader().setReorderingAllowed(false);
        JScrollPane scrollRetiros = new JScrollPane(tablaRetiros);
        JPanel panelRetiros = new JPanel(new BorderLayout());
        panelRetiros.setOpaque(false);
        JLabel lblRetiros = new JLabel("Historial de retiros");
        lblRetiros.setFont(new Font("SansSerif", Font.BOLD, 18));
        lblRetiros.setForeground(Color.WHITE);
        lblRetiros.setBorder(new EmptyBorder(0, 0, 10, 0));
        panelRetiros.add(lblRetiros, BorderLayout.NORTH);
        panelRetiros.add(scrollRetiros, BorderLayout.CENTER);
        modeloDepositos = new DefaultTableModel(new Object[]{"Fecha de depósito", "Monto de depósito"}, 0) {
            public boolean isCellEditable(int row, int column) { return false; }
        };
        JTable tablaDepositos = new JTable(modeloDepositos);
        tablaDepositos.setRowHeight(30);
        tablaDepositos.setFont(new Font("SansSerif", Font.PLAIN, 14));
        tablaDepositos.getTableHeader().setReorderingAllowed(false);
        JScrollPane scrollDepositos = new JScrollPane(tablaDepositos);
        JPanel panelDepositos = new JPanel(new BorderLayout());
        panelDepositos.setOpaque(false);
        JLabel lblDepositos = new JLabel("Historial de depósitos");
        lblDepositos.setFont(new Font("SansSerif", Font.BOLD, 18));
        lblDepositos.setForeground(Color.WHITE);
        lblDepositos.setBorder(new EmptyBorder(0, 0, 10, 0));
        panelDepositos.add(lblDepositos, BorderLayout.NORTH);
        panelDepositos.add(scrollDepositos, BorderLayout.CENTER);
        centerPanel.add(panelRetiros);
        centerPanel.add(panelDepositos);
        add(centerPanel, BorderLayout.CENTER);
    }

    public Double showAmountDialog(String dialogTitle, String action) {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel label = new JLabel(dialogTitle);
        label.setFont(new Font("SansSerif", Font.PLAIN, 18));
        label.setForeground(Color.WHITE);
        panel.add(label, gbc);
        SpinnerNumberModel spinnerModel = new SpinnerNumberModel(100.0, 0.0, 50000.0, 100.0);
        JSpinner spinner = new JSpinner(spinnerModel);
        spinner.setFont(new Font("SansSerif", Font.BOLD, 22));
        spinner.setPreferredSize(new Dimension(150, 40));
        JSpinner.NumberEditor editor = (JSpinner.NumberEditor) spinner.getEditor();
        JFormattedTextField textField = editor.getTextField();
        textField.setForeground(Color.WHITE);
        textField.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridy = 1;
        panel.add(spinner, gbc);
        Object[] options = {"Cancelar", "Aceptar"};

        int result = JOptionPane.showOptionDialog(this, panel,
                "Transacción",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                options,
                options[0]);

        if (result == 1) {
            return (double) spinner.getValue();
        }
        return null;
    }

    public void setSaldo(double nuevoSaldo) {
        if (lblSaldo != null) {
            lblSaldo.setText(String.format("Tu saldo es: $%,.2f MXN", nuevoSaldo));
        }
    }

    public void setTransaccionesData(List<TransaccionDTO> transacciones) {
        modeloRetiros.setRowCount(0);
        modeloDepositos.setRowCount(0);

        for (TransaccionDTO transaccion : transacciones) {
            Object[] rowData = {
                    transaccion.getFechaFormateada(),
                    transaccion.getMontoFormateado()
            };
            if ("RETIRO".equalsIgnoreCase(transaccion.getTipoTransaccion())) {
                modeloRetiros.addRow(rowData);
            } else {
                modeloDepositos.addRow(rowData);
            }
        }
    }

    public void addDepositarListener(ActionListener listener) {
        btnDepositar.addActionListener(listener);
    }

    public void addRetirarListener(ActionListener listener) {
        btnRetirar.addActionListener(listener);
    }
}