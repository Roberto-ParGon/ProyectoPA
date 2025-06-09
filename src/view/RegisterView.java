package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

public class RegisterView extends JDialog {
    private JTextField tfName;
    private JTextField tfLastName1;
    private JTextField tfLastName2;
    private JTextField tfEmail;
    private JPasswordField tfPin;
    private JPasswordField tfConfirmPin;
    private JButton btnAccept;

    private ImageIcon eyeOnIcon;
    private ImageIcon eyeOffIcon;
    private boolean isPin1Visible = false;
    private boolean isPin2Visible = false;

    public RegisterView(JFrame owner) {
        super(owner, "Sistema de Registro", true);
        setSize(520, 850);
        setResizable(false);
        setLayout(new BorderLayout());
        setLocationRelativeTo(owner);
        loadIcons();
        initializeComponents();
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    }

    private void loadIcons() {
        URL eyeOnURL = getClass().getResource("/resources/icon/eye_on.png");
        if (eyeOnURL != null) {
            eyeOnIcon = new ImageIcon(new ImageIcon(eyeOnURL).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
        }
        URL eyeOffURL = getClass().getResource("/resources/icon/eye_off.png");
        if (eyeOffURL != null) {
            eyeOffIcon = new ImageIcon(new ImageIcon(eyeOffURL).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
        }
    }

    private void initializeComponents() {
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(new EmptyBorder(30, 50, 30, 50));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Título
        JLabel title = new JLabel("REGISTRO");
        title.setFont(new Font("SansSerif", Font.BOLD, 36));
        title.setForeground(Color.WHITE);
        title.setHorizontalAlignment(JLabel.CENTER);
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 30, 0);
        mainPanel.add(title, gbc);

        //Nombre
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 0, 8, 0);
        gbc.anchor = GridBagConstraints.WEST;
        mainPanel.add(createLabel("Nombre:"), gbc);
        tfName = createTextField();
        gbc.gridy = 2;
        gbc.insets = new Insets(0, 0, 20, 0);
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(tfName, gbc);

        //Apellido Paterno
        gbc.gridy = 3;
        gbc.insets = new Insets(0, 0, 8, 0);
        gbc.anchor = GridBagConstraints.WEST;
        mainPanel.add(createLabel("Apellido Paterno:"), gbc);
        tfLastName1 = createTextField();
        gbc.gridy = 4;
        gbc.insets = new Insets(0, 0, 20, 0);
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(tfLastName1, gbc);

        //Apellido Materno
        gbc.gridy = 5;
        gbc.insets = new Insets(0, 0, 8, 0);
        gbc.anchor = GridBagConstraints.WEST;
        mainPanel.add(createLabel("Apellido Materno:"), gbc);
        tfLastName2 = createTextField();
        gbc.gridy = 6;
        gbc.insets = new Insets(0, 0, 20, 0);
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(tfLastName2, gbc);

        //Correo Electrónico
        gbc.gridy = 7;
        gbc.insets = new Insets(0, 0, 8, 0);
        gbc.anchor = GridBagConstraints.WEST;
        mainPanel.add(createLabel("Correo Electrónico:"), gbc);
        tfEmail = createTextField();
        gbc.gridy = 8;
        gbc.insets = new Insets(0, 0, 20, 0);
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(tfEmail, gbc);

        //Contraseña
        gbc.gridy = 9;
        gbc.insets = new Insets(0, 0, 8, 0);
        gbc.anchor = GridBagConstraints.WEST;
        mainPanel.add(createLabel("Contraseña:"), gbc);
        tfPin = new JPasswordField();
        gbc.gridy = 10;
        gbc.insets = new Insets(0, 0, 20, 0);
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(createPinPanel(tfPin, 1), gbc);

        //Confirmar Contraseña
        gbc.gridy = 11;
        gbc.insets = new Insets(0, 0, 8, 0);
        gbc.anchor = GridBagConstraints.WEST;
        mainPanel.add(createLabel("Confirmar Contraseña:"), gbc);
        tfConfirmPin = new JPasswordField();
        gbc.gridy = 12;
        gbc.insets = new Insets(0, 0, 20, 0);
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(createPinPanel(tfConfirmPin, 2), gbc);

        //Panel de Botones
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.add(Box.createHorizontalGlue());
        JButton btnCancel = new JButton("CANCELAR");
        styleButton(btnCancel, new Color(120, 120, 120));
        btnCancel.addActionListener(e -> dispose());
        btnAccept = new JButton("ACEPTAR");
        styleButton(btnAccept, new Color(102, 102, 204));

        buttonPanel.add(btnCancel);
        buttonPanel.add(Box.createRigidArea(new Dimension(20, 0)));
        buttonPanel.add(btnAccept);
        buttonPanel.add(Box.createHorizontalGlue());
        gbc.gridy = 13;
        gbc.insets.top = 20;
        mainPanel.add(buttonPanel, gbc);

        add(mainPanel, BorderLayout.CENTER);
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("SansSerif", Font.PLAIN, 16));
        label.setForeground(Color.WHITE);
        return label;
    }

    private JTextField createTextField() {
        JTextField textField = new JTextField();
        textField.setPreferredSize(new Dimension(350, 45));
        textField.setFont(new Font("SansSerif", Font.PLAIN, 16));
        textField.setBackground(new Color(45, 45, 65));
        textField.setForeground(Color.WHITE);
        textField.setCaretColor(Color.WHITE);
        textField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(70, 70, 90), 1),
                new EmptyBorder(8, 15, 8, 15)));
        return textField;
    }

    private JPanel createPinPanel(JPasswordField passwordField, int eyeId) {
        JPanel pinPanel = new JPanel(new BorderLayout());
        pinPanel.setPreferredSize(new Dimension(350, 45));
        pinPanel.setBackground(new Color(45, 45, 65));
        pinPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(70, 70, 90), 1),
                new EmptyBorder(8, 15, 8, 5)));

        passwordField.setFont(new Font("SansSerif", Font.PLAIN, 16));
        passwordField.setBackground(new Color(45, 45, 65));
        passwordField.setForeground(Color.WHITE);
        passwordField.setCaretColor(Color.WHITE);
        passwordField.setBorder(null);
        passwordField.setEchoChar('*');

        JLabel eyeIconLabel = new JLabel(eyeOffIcon);
        eyeIconLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        eyeIconLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (eyeId == 1) {
                    isPin1Visible = !isPin1Visible;
                    passwordField.setEchoChar(isPin1Visible ? (char) 0 : '*');
                    eyeIconLabel.setIcon(isPin1Visible ? eyeOnIcon : eyeOffIcon);
                } else {
                    isPin2Visible = !isPin2Visible;
                    passwordField.setEchoChar(isPin2Visible ? (char) 0 : '*');
                    eyeIconLabel.setIcon(isPin2Visible ? eyeOnIcon : eyeOffIcon);
                }
            }
        });
        pinPanel.add(passwordField, BorderLayout.CENTER);
        pinPanel.add(eyeIconLabel, BorderLayout.EAST);
        return pinPanel;
    }

    private void styleButton(JButton button, Color color) {
        button.setFont(new Font("SansSerif", Font.BOLD, 16));
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setPreferredSize(new Dimension(130, 55));
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    public String getNombre() { return tfName.getText(); }
    public String getApellidoPaterno() { return tfLastName1.getText(); }
    public String getApellidoMaterno() { return tfLastName2.getText(); }
    public String getCorreo() { return tfEmail.getText(); }
    public char[] getContrasena() { return tfPin.getPassword(); }
    public char[] getConfirmarContrasena() { return tfConfirmPin.getPassword(); }

    public void addAceptarListener(ActionListener listener) {
        btnAccept.addActionListener(listener);
    }
}