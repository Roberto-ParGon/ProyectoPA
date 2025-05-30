package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter; // Import añadido
import java.awt.event.MouseEvent;  // Import añadido
import java.net.URL;               // Import añadido

public class RegisterView extends JDialog {

    private JPasswordField tfPin;
    private JPasswordField tfConfirmPin;
    private ImageIcon eyeOnIcon;
    private ImageIcon eyeOffIcon;
    private boolean isPin1Visible = false;
    private boolean isPin2Visible = false;

    public RegisterView(JFrame owner) {
        super(owner, "Sistema de Registro", true);
        setSize(520, 780);
        setResizable(false);
        setLayout(new BorderLayout());
        setLocationRelativeTo(owner);

        URL eyeOnURL = getClass().getResource("/resources/icon/eye_on.png");
        if (eyeOnURL != null) {
            eyeOnIcon = new ImageIcon(new ImageIcon(eyeOnURL).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
        } else {
            System.err.println("RegisterView: No se pudo encontrar el recurso eye_on.png");
        }

        URL eyeOffURL = getClass().getResource("/resources/icon/eye_off.png");
        if (eyeOffURL != null) {
            eyeOffIcon = new ImageIcon(new ImageIcon(eyeOffURL).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
        } else {
            System.err.println("RegisterView: No se pudo encontrar el recurso eye_off.png");
        }

        initializeComponents();
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
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
        gbc.insets = new Insets(0, 0, 8, 0);
        gbc.anchor = GridBagConstraints.WEST;
        mainPanel.add(lblName, gbc);

        JTextField tfName = new JTextField();
        tfName.setPreferredSize(new Dimension(350, 45));
        tfName.setFont(new Font("SansSerif", Font.PLAIN, 16));
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
        tfLastName1.setFont(new Font("SansSerif", Font.PLAIN, 16));
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
        tfLastName2.setFont(new Font("SansSerif", Font.PLAIN, 16));
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

        //Input de PIN
        JLabel lblPin = new JLabel("PIN (4 dígitos):");
        lblPin.setFont(new Font("SansSerif", Font.PLAIN, 16));
        lblPin.setForeground(Color.WHITE);
        gbc.gridy = 7;
        gbc.insets.bottom = 8;
        gbc.anchor = GridBagConstraints.WEST;
        mainPanel.add(lblPin, gbc);

        JPanel pinPanel1 = new JPanel(new BorderLayout());
        pinPanel1.setPreferredSize(new Dimension(350, 45));
        pinPanel1.setBackground(new Color(45, 45, 65));
        pinPanel1.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(70, 70, 90), 1),
                new EmptyBorder(8, 15, 8, 5)));

        tfPin = new JPasswordField();
        tfPin.setFont(new Font("SansSerif", Font.PLAIN, 16));
        tfPin.setBackground(new Color(45, 45, 65));
        tfPin.setForeground(Color.WHITE);
        tfPin.setCaretColor(Color.WHITE);
        tfPin.setBorder(null);
        tfPin.setEchoChar('*');

        JLabel eyeIconLabel1 = new JLabel(eyeOffIcon);
        eyeIconLabel1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        eyeIconLabel1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                isPin1Visible = !isPin1Visible;
                if (isPin1Visible) {
                    tfPin.setEchoChar((char) 0);
                    eyeIconLabel1.setIcon(eyeOnIcon);
                } else {
                    tfPin.setEchoChar('*');
                    eyeIconLabel1.setIcon(eyeOffIcon);
                }
            }
        });

        pinPanel1.add(tfPin, BorderLayout.CENTER);
        pinPanel1.add(eyeIconLabel1, BorderLayout.EAST);

        gbc.gridy = 8;
        gbc.insets.bottom = 20;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(pinPanel1, gbc);

        //Input de confirmar PIN
        JLabel lblConfirmPin = new JLabel("Confirmar PIN:");
        lblConfirmPin.setFont(new Font("SansSerif", Font.PLAIN, 16));
        lblConfirmPin.setForeground(Color.WHITE);
        gbc.gridy = 9;
        gbc.insets.bottom = 8;
        gbc.anchor = GridBagConstraints.WEST;
        mainPanel.add(lblConfirmPin, gbc);

        JPanel pinPanel2 = new JPanel(new BorderLayout());
        pinPanel2.setPreferredSize(new Dimension(350, 45));
        pinPanel2.setBackground(new Color(45, 45, 65));
        pinPanel2.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(70, 70, 90), 1),
                new EmptyBorder(8, 15, 8, 5)));

        tfConfirmPin = new JPasswordField();
        tfConfirmPin.setFont(new Font("SansSerif", Font.PLAIN, 16));
        tfConfirmPin.setBackground(new Color(45, 45, 65));
        tfConfirmPin.setForeground(Color.WHITE);
        tfConfirmPin.setCaretColor(Color.WHITE);
        tfConfirmPin.setBorder(null);
        tfConfirmPin.setEchoChar('*');

        JLabel eyeIconLabel2 = new JLabel(eyeOffIcon);
        eyeIconLabel2.setCursor(new Cursor(Cursor.HAND_CURSOR));
        eyeIconLabel2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                isPin2Visible = !isPin2Visible;
                if (isPin2Visible) {
                    tfConfirmPin.setEchoChar((char) 0);
                    eyeIconLabel2.setIcon(eyeOnIcon);
                } else {
                    tfConfirmPin.setEchoChar('*');
                    eyeIconLabel2.setIcon(eyeOffIcon);
                }
            }
        });

        pinPanel2.add(tfConfirmPin, BorderLayout.CENTER);
        pinPanel2.add(eyeIconLabel2, BorderLayout.EAST);

        gbc.gridy = 10;
        gbc.insets.bottom = 22;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(pinPanel2, gbc);

        //RadioButtons
        JLabel lblCardType = new JLabel("Tipo de tarjeta:");
        lblCardType.setFont(new Font("SansSerif", Font.PLAIN, 16));
        lblCardType.setForeground(Color.WHITE);
        gbc.gridy = 11;
        gbc.insets = new Insets(0,0,16,0);
        gbc.anchor = GridBagConstraints.WEST;
        mainPanel.add(lblCardType, gbc);

        JPanel cardTypePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 48, 0));
        ButtonGroup cardTypeGroup = new ButtonGroup();

        JRadioButton rbDebit = new JRadioButton("Débito", true);
        rbDebit.setFont(new Font("SansSerif", Font.PLAIN, 16));
        rbDebit.setForeground(Color.WHITE);
        rbDebit.setFocusPainted(false);
        rbDebit.setCursor(new Cursor(Cursor.HAND_CURSOR));
        cardTypeGroup.add(rbDebit);
        cardTypePanel.add(rbDebit);

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

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.add(Box.createHorizontalGlue());

        //Botón cancelar
        JButton btnCancel = new JButton("CANCELAR");
        btnCancel.setFont(new Font("SansSerif", Font.BOLD, 16));
        btnCancel.setBackground(new Color(120, 120, 120));
        btnCancel.setForeground(Color.WHITE);
        btnCancel.setPreferredSize(new Dimension(130, 55));
        btnCancel.setBorderPainted(false);
        btnCancel.setFocusPainted(false);
        btnCancel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnCancel.addActionListener(e -> dispose());

        //Botón aceptar
        JButton btnAccept = new JButton("ACEPTAR");
        btnAccept.setFont(new Font("SansSerif", Font.BOLD, 16));
        btnAccept.setBackground(new Color(102, 102, 204));
        btnAccept.setForeground(Color.WHITE);
        btnAccept.setPreferredSize(new Dimension(130, 55));
        btnAccept.setBorderPainted(false);
        btnAccept.setFocusPainted(false);
        btnAccept.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnAccept.addActionListener(e -> {
            //Registrar cuenta
            dispose();
        });

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