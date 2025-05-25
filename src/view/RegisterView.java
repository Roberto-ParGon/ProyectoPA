package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class RegisterView extends JDialog {
    public RegisterView(JFrame owner) {
        super(owner, "Sistema de Registro", true);
        setSize(520, 780);
        setResizable(false);
        setLayout(new BorderLayout());
        setLocationRelativeTo(owner);
        initializeComponents();
    }

    private void initializeComponents() {
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(new EmptyBorder(30, 50, 30, 50));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 0, 0, 0);

        // Título
        JLabel title = new JLabel("REGISTRO");
        title.setFont(new Font("SansSerif", Font.BOLD, 36));
        title.setForeground(Color.WHITE);
        title.setHorizontalAlignment(JLabel.CENTER);
        gbc.gridy = 0;
        gbc.insets.bottom = 40;
        mainPanel.add(title, gbc);

        // Campo de Nombre
        JLabel lblName = new JLabel("Nombre:");
        lblName.setFont(new Font("SansSerif", Font.PLAIN, 16));
        lblName.setForeground(Color.WHITE);
        gbc.gridy = 1;
        gbc.insets.bottom = 8;
        gbc.anchor = GridBagConstraints.WEST;
        mainPanel.add(lblName, gbc);

        JTextField tfName = new JTextField();
        tfName.setPreferredSize(new Dimension(350, 45));
        tfName.setFont(new Font("SansSerif", Font.PLAIN, 15));
        tfName.setBackground(new Color(45, 45, 65));
        tfName.setForeground(Color.WHITE);
        tfName.setCaretColor(Color.WHITE);
        tfName.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(70, 70, 90), 1),
                new EmptyBorder(8, 15, 8, 15)));
        gbc.gridy = 2;
        gbc.insets.bottom = 20;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(tfName, gbc);

        // Campo de Apellido Paterno
        JLabel lblLastName1 = new JLabel("Apellido Paterno:");
        lblLastName1.setFont(new Font("SansSerif", Font.PLAIN, 16));
        lblLastName1.setForeground(Color.WHITE);
        gbc.gridy = 3;
        gbc.insets.bottom = 8;
        gbc.anchor = GridBagConstraints.WEST;
        mainPanel.add(lblLastName1, gbc);

        JTextField tfLastName1 = new JTextField();
        tfLastName1.setPreferredSize(new Dimension(350, 45));
        tfLastName1.setFont(new Font("SansSerif", Font.PLAIN, 15));
        tfLastName1.setBackground(new Color(45, 45, 65));
        tfLastName1.setForeground(Color.WHITE);
        tfLastName1.setCaretColor(Color.WHITE);
        tfLastName1.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(70, 70, 90), 1),
                new EmptyBorder(8, 15, 8, 15)));
        gbc.gridy = 4;
        gbc.insets.bottom = 20;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(tfLastName1, gbc);

        // Campo de Apellido Materno
        JLabel lblLastName2 = new JLabel("Apellido Materno:");
        lblLastName2.setFont(new Font("SansSerif", Font.PLAIN, 16));
        lblLastName2.setForeground(Color.WHITE);
        gbc.gridy = 5;
        gbc.insets.bottom = 8;
        gbc.anchor = GridBagConstraints.WEST;
        mainPanel.add(lblLastName2, gbc);

        JTextField tfLastName2 = new JTextField();
        tfLastName2.setPreferredSize(new Dimension(350, 45));
        tfLastName2.setFont(new Font("SansSerif", Font.PLAIN, 15));
        tfLastName2.setBackground(new Color(45, 45, 65));
        tfLastName2.setForeground(Color.WHITE);
        tfLastName2.setCaretColor(Color.WHITE);
        tfLastName2.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(70, 70, 90), 1),
                new EmptyBorder(8, 15, 8, 15)));
        gbc.gridy = 6;
        gbc.insets.bottom = 20;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(tfLastName2, gbc);

        // Primer campo PIN
        JLabel lblPin = new JLabel("PIN (4 dígitos):");
        lblPin.setFont(new Font("SansSerif", Font.PLAIN, 16));
        lblPin.setForeground(Color.WHITE);
        gbc.gridy = 7;
        gbc.insets.bottom = 8;
        gbc.anchor = GridBagConstraints.WEST;
        mainPanel.add(lblPin, gbc);

        JPasswordField tfPin = new JPasswordField();
        tfPin.setPreferredSize(new Dimension(350, 45));
        tfPin.setFont(new Font("SansSerif", Font.PLAIN, 20));
        tfPin.setBackground(new Color(45, 45, 65));
        tfPin.setForeground(Color.WHITE);
        tfPin.setCaretColor(Color.WHITE);
        tfPin.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(70, 70, 90), 1),
                new EmptyBorder(8, 15, 8, 15)));
        gbc.gridy = 8;
        gbc.insets.bottom = 20;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(tfPin, gbc);

        // Segundo campo PIN
        JLabel lblConfirmPin = new JLabel("Confirmar PIN:");
        lblConfirmPin.setFont(new Font("SansSerif", Font.PLAIN, 16));
        lblConfirmPin.setForeground(Color.WHITE);
        gbc.gridy = 9;
        gbc.insets.bottom = 8;
        gbc.anchor = GridBagConstraints.WEST;
        mainPanel.add(lblConfirmPin, gbc);

        JPasswordField tfConfirmPin = new JPasswordField();
        tfConfirmPin.setPreferredSize(new Dimension(350, 45));
        tfConfirmPin.setFont(new Font("SansSerif", Font.PLAIN, 20));
        tfConfirmPin.setBackground(new Color(45, 45, 65));
        tfConfirmPin.setForeground(Color.WHITE);
        tfConfirmPin.setCaretColor(Color.WHITE);
        tfConfirmPin.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(70, 70, 90), 1),
                new EmptyBorder(8, 15, 8, 15)));
        gbc.gridy = 10;
        gbc.insets.bottom = 22;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(tfConfirmPin, gbc);

        //Selección de tipo de tarjeta
        JLabel lblCardType = new JLabel("Tipo de tarjeta:");
        lblCardType.setFont(new Font("SansSerif", Font.PLAIN, 16));
        lblCardType.setForeground(Color.WHITE);
        gbc.gridy = 11;
        gbc.insets.bottom = 16;
        gbc.anchor = GridBagConstraints.WEST;
        mainPanel.add(lblCardType, gbc);

        //Panel de RadioButtons
        JPanel cardTypePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 48, 0));
        ButtonGroup cardTypeGroup = new ButtonGroup();

        // RadioButton para Débito
        JRadioButton rbDebit = new JRadioButton("Débito", true);
        rbDebit.setFont(new Font("SansSerif", Font.PLAIN, 16));
        rbDebit.setForeground(Color.WHITE);
        rbDebit.setFocusPainted(false);
        rbDebit.setCursor(new Cursor(Cursor.HAND_CURSOR));
        cardTypeGroup.add(rbDebit);
        cardTypePanel.add(rbDebit);

        // RadioButton para Crédito
        JRadioButton rbCredit = new JRadioButton("Crédito");
        rbCredit.setFont(new Font("SansSerif", Font.PLAIN, 16));
        rbCredit.setForeground(Color.WHITE);
        rbCredit.setFocusPainted(false);
        rbCredit.setCursor(new Cursor(Cursor.HAND_CURSOR));
        cardTypeGroup.add(rbCredit);
        cardTypePanel.add(rbCredit);

        gbc.gridy = 12;
        gbc.insets.bottom = 40;
        gbc.anchor = GridBagConstraints.WEST;
        mainPanel.add(cardTypePanel, gbc);

        // Panel de botones
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.add(Box.createHorizontalGlue());

        // Botón Cancelar
        JButton btnCancel = new JButton("CANCELAR");
        btnCancel.setFont(new Font("SansSerif", Font.BOLD, 16));
        btnCancel.setBackground(new Color(120, 120, 120));
        btnCancel.setForeground(Color.WHITE);
        btnCancel.setPreferredSize(new Dimension(130, 55));
        btnCancel.setBorderPainted(false);
        btnCancel.setFocusPainted(false);
        btnCancel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnCancel.addActionListener(e -> dispose());

        // Botón Aceptar
        JButton btnAccept = new JButton("ACEPTAR");
        btnAccept.setFont(new Font("SansSerif", Font.BOLD, 16));
        btnAccept.setBackground(new Color(102, 102, 204));
        btnAccept.setForeground(Color.WHITE);
        btnAccept.setPreferredSize(new Dimension(130, 55));
        btnAccept.setBorderPainted(false);
        btnAccept.setFocusPainted(false);
        btnAccept.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnAccept.addActionListener(e -> dispose());

        buttonPanel.add(btnCancel);
        buttonPanel.add(Box.createRigidArea(new Dimension(20, 0)));
        buttonPanel.add(btnAccept);
        buttonPanel.add(Box.createHorizontalGlue());

        gbc.gridy = 13;
        gbc.insets.bottom = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(buttonPanel, gbc);

        add(mainPanel, BorderLayout.CENTER);
    }
}