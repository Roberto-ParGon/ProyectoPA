package view;

import model.ClienteDTO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

public class AccountSettingsView extends JDialog {

    private static final Color COLOR_FONDO_PRINCIPAL = new Color(44, 44, 58);
    private static final Color COLOR_CAMPO_FONDO = new Color(58, 58, 79);
    private static final Color COLOR_TEXTO = Color.WHITE;
    private static final Color COLOR_BORDE_CAMPO = new Color(70, 70, 90);
    private static final Color COLOR_BOTON_ELIMINAR = new Color(229, 57, 53);
    private static final Color COLOR_BOTON_CANCELAR = new Color(97, 97, 97);
    private static final Color COLOR_BOTON_ACEPTAR = new Color(126, 87, 194);

    private JTextField tfName;
    private JTextField tfLastName1;
    private JTextField tfLastName2;
    private JPasswordField passwordField;
    private JButton btnAccept;
    private JButton btnCancel;
    private JButton btnDelete;

    private JLabel eyeIconLabel;
    private boolean isPinVisible = false;
    private ImageIcon userIconImg;
    private ImageIcon trashIconImg;
    private ImageIcon eyeOpenIconImg;
    private ImageIcon eyeSlashedIconImg;

    public AccountSettingsView(JFrame owner) {
        super(owner, "Ajustes de cuenta", true);
        loadIcons();
        setSize(420, 700);
        setResizable(false);
        setLayout(new BorderLayout());
        setLocationRelativeTo(owner);
        getContentPane().setBackground(COLOR_FONDO_PRINCIPAL);
        initializeComponents();
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    }

    private void loadIcons() {
        userIconImg = createIcon("/resources/icon/user.png", 60, 60);
        trashIconImg = createIcon("/resources/icon/trash.png", 20, 20);
        eyeOpenIconImg = createIcon("/resources/icon/eye_on.png", 20, 20);
        eyeSlashedIconImg = createIcon("/resources/icon/eye_off.png", 20, 20);
    }

    private void initializeComponents() {
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(new EmptyBorder(30, 40, 30, 40));
        mainPanel.setBackground(COLOR_FONDO_PRINCIPAL);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        // Título
        JLabel title = new JLabel("Ajustes de cuenta");
        title.setFont(new Font("SansSerif", Font.BOLD, 28));
        title.setForeground(COLOR_TEXTO);
        title.setHorizontalAlignment(JLabel.CENTER);
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 0, 15, 0);
        mainPanel.add(title, gbc);

        // Icono de Usuario
        JLabel userIconLabel = new JLabel(userIconImg);
        userIconLabel.setHorizontalAlignment(JLabel.CENTER);
        gbc.gridy = 1;
        gbc.insets = new Insets(5, 0, 25, 0);
        mainPanel.add(userIconLabel, gbc);

        //Nombre
        gbc.gridy = 2;
        gbc.insets = new Insets(5, 0, 3, 0);
        mainPanel.add(createLabel("Cambiar Nombre:"), gbc);
        tfName = createTextField();
        gbc.gridy = 3;
        gbc.insets = new Insets(0, 0, 15, 0);
        mainPanel.add(tfName, gbc);

        //Campo Apellido Paterno
        gbc.gridy = 4;
        gbc.insets = new Insets(5, 0, 3, 0);
        mainPanel.add(createLabel("Cambiar Apellido Paterno:"), gbc);
        tfLastName1 = createTextField();
        gbc.gridy = 5;
        gbc.insets = new Insets(0, 0, 15, 0);
        mainPanel.add(tfLastName1, gbc);

        //Campo Apellido Materno
        gbc.gridy = 6;
        gbc.insets = new Insets(5, 0, 3, 0);
        mainPanel.add(createLabel("Cambiar Apellido Materno:"), gbc);
        tfLastName2 = createTextField();
        gbc.gridy = 7;
        gbc.insets = new Insets(0, 0, 15, 0);
        mainPanel.add(tfLastName2, gbc);

        //Campo Contraseña
        gbc.gridy = 8;
        gbc.insets = new Insets(5, 0, 3, 0);
        mainPanel.add(createLabel("Cambiar Contraseña:"), gbc);
        passwordField = new JPasswordField();
        gbc.gridy = 9;
        gbc.insets = new Insets(0, 0, 15, 0);
        mainPanel.add(createPinPanel(), gbc);

        //Eliminar Cuenta
        btnDelete = new JButton("Eliminar cuenta");
        btnDelete.setIcon(trashIconImg);
        styleButton(btnDelete, COLOR_BOTON_ELIMINAR, 15, 10);
        gbc.gridy = 10;
        gbc.insets = new Insets(15, 0, 15, 0);
        mainPanel.add(btnDelete, gbc);

        //Panel botones inferior
        JPanel bottomButtonPanel = new JPanel(new GridLayout(1, 2, 15, 0));
        bottomButtonPanel.setBackground(COLOR_FONDO_PRINCIPAL);
        btnCancel = new JButton("Cancelar");
        styleButton(btnCancel, COLOR_BOTON_CANCELAR, 14, 0);
        btnAccept = new JButton("Aceptar");
        styleButton(btnAccept, COLOR_BOTON_ACEPTAR, 14, 0);
        bottomButtonPanel.add(btnCancel);
        bottomButtonPanel.add(btnAccept);
        gbc.gridy = 11;
        gbc.insets = new Insets(10, 0, 0, 0);
        mainPanel.add(bottomButtonPanel, gbc);

        add(mainPanel, BorderLayout.CENTER);
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("SansSerif", Font.PLAIN, 14));
        label.setForeground(COLOR_TEXTO);
        label.setHorizontalAlignment(SwingConstants.LEFT);
        return label;
    }

    private JTextField createTextField() {
        JTextField tf = new JTextField();
        tf.setPreferredSize(new Dimension(300, 40));
        tf.setFont(new Font("SansSerif", Font.PLAIN, 14));
        tf.setBackground(COLOR_CAMPO_FONDO);
        tf.setForeground(COLOR_TEXTO);
        tf.setCaretColor(COLOR_TEXTO);
        tf.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(COLOR_BORDE_CAMPO, 1), new EmptyBorder(5, 10, 5, 10)));
        return tf;
    }

    private JPanel createPinPanel() {
        JPanel pinPanel = new JPanel(new BorderLayout(5, 0));
        pinPanel.setBackground(COLOR_CAMPO_FONDO);
        pinPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(COLOR_BORDE_CAMPO, 1),
                new EmptyBorder(5, 10, 5, 5)));

        passwordField.setFont(new Font("SansSerif", Font.PLAIN, 14));
        passwordField.setBackground(COLOR_CAMPO_FONDO);
        passwordField.setForeground(COLOR_TEXTO);
        passwordField.setCaretColor(COLOR_TEXTO);
        passwordField.setBorder(null);
        passwordField.setEchoChar('●');

        eyeIconLabel = new JLabel(eyeOpenIconImg);
        eyeIconLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        eyeIconLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                isPinVisible = !isPinVisible;
                passwordField.setEchoChar(isPinVisible ? (char) 0 : '●');
                eyeIconLabel.setIcon(isPinVisible ? eyeSlashedIconImg : eyeOpenIconImg);
            }
        });

        pinPanel.add(passwordField, BorderLayout.CENTER);
        JPanel eyePanel = new JPanel(new GridBagLayout());
        eyePanel.setBackground(COLOR_CAMPO_FONDO);
        eyePanel.add(eyeIconLabel);
        pinPanel.add(eyePanel, BorderLayout.EAST);
        return pinPanel;
    }

    private void styleButton(JButton button, Color color, int fontSize, int verticalPadding) {
        button.setFont(new Font("SansSerif", Font.BOLD, fontSize));
        button.setBackground(color);
        button.setForeground(COLOR_TEXTO);
        button.setFocusPainted(false);
        button.setOpaque(true);
        button.setBorderPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        if (verticalPadding > 0) {
            button.setBorder(new EmptyBorder(verticalPadding, 0, verticalPadding, 0));
        }
    }

    private ImageIcon createIcon(String path, int width, int height) {
        URL imgURL = getClass().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(new ImageIcon(imgURL).getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
        } else {
            System.err.println("No se pudo encontrar el icono: " + path);
            return null;
        }
    }

    public void setDatosCliente(ClienteDTO cliente) {
        tfName.setText(cliente.getNombre());
        tfLastName1.setText(cliente.getApellidoPaterno());
        tfLastName2.setText(cliente.getApellidoMaterno());
    }

    public String getNombre() { return tfName.getText(); }
    public String getApellidoPaterno() { return tfLastName1.getText(); }
    public String getApellidoMaterno() { return tfLastName2.getText(); }
    public char[] getNuevaContrasena() { return passwordField.getPassword(); }

    public void addAceptarListener(ActionListener listener) {
        btnAccept.addActionListener(listener);
    }
    public void addCancelarListener(ActionListener listener) {
        btnCancel.addActionListener(listener);
    }
    public void addEliminarListener(ActionListener listener) {
        btnDelete.addActionListener(listener);
    }
}