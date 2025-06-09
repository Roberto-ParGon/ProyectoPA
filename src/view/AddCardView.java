package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AddCardView extends JDialog {

    private static final Color COLOR_FONDO = new Color(44, 44, 58);
    private static final Color COLOR_CAMPO = new Color(58, 58, 79);
    private static final Color COLOR_TEXTO = Color.WHITE;
    private static final Color COLOR_TEXTO_SECUNDARIO = new Color(180, 180, 180);
    private static final Color COLOR_BOTON_ACEPTAR = new Color(91, 75, 142);
    private static final Color COLOR_BOTON_CANCELAR = new Color(97, 97, 97);

    private JRadioButton rbDebit, rbCredit;
    private JPasswordField pfPin, pfConfirmPin;
    private JButton btnAccept, btnCancel;
    private JLabel eyeIcon1, eyeIcon2;

    private ImageIcon eyeOnIcon, eyeOffIcon;
    private boolean isPinVisible = false;
    private boolean isConfirmPinVisible = false;

    public AddCardView(Frame owner) {
        super(owner, "Añadir Tarjeta", true);
        loadIcons();

        setSize(420, 580);
        setResizable(false);
        setLocationRelativeTo(owner);
        getContentPane().setBackground(COLOR_FONDO);
        setLayout(new GridBagLayout());

        initializeComponents();
    }

    private void loadIcons() {
        eyeOnIcon = createIcon("/resources/icon/eye_on.png", 20, 20);
        eyeOffIcon = createIcon("/resources/icon/eye_off.png", 20, 20);
    }

    private void initializeComponents() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 20, 10, 20);
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Título
        JLabel lblTitle = new JLabel("Añadir tarjeta");
        lblTitle.setFont(new Font("SansSerif", Font.BOLD, 36));
        lblTitle.setForeground(COLOR_TEXTO);
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblTitle, gbc);

        // Icono de tarjeta
        JLabel lblCardIcon = new JLabel(createIcon("/resources/icon/card.png", 64, 64));
        lblCardIcon.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.insets.bottom = 20;
        add(lblCardIcon, gbc);

        // Tipo de cuenta
        add(createLabel("Tipo de cuenta:"), gbc);
        setupRadioButtons(gbc);

        // PIN
        add(createLabel("PIN (4 dígitos):"), gbc);
        add(createPinPanel(1), gbc);

        // Confirmar PIN
        add(createLabel("Confirmar PIN:"), gbc);
        add(createPinPanel(2), gbc);

        // Botones
        gbc.insets.top = 20;
        add(createButtonPanel(), gbc);
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("SansSerif", Font.PLAIN, 16));
        label.setForeground(COLOR_TEXTO_SECUNDARIO);
        return label;
    }

    private void setupRadioButtons(GridBagConstraints gbc) {
        rbDebit = new JRadioButton("Débito", true);
        rbCredit = new JRadioButton("Crédito");
        styleRadioButton(rbDebit);
        styleRadioButton(rbCredit);

        ButtonGroup group = new ButtonGroup();
        group.add(rbDebit);
        group.add(rbCredit);

        JPanel radioPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 0));
        radioPanel.setOpaque(false);
        radioPanel.add(rbDebit);
        radioPanel.add(rbCredit);

        gbc.insets.bottom = 10;
        add(radioPanel, gbc);
    }

    private void styleRadioButton(JRadioButton rb) {
        rb.setFont(new Font("SansSerif", Font.PLAIN, 16));
        rb.setForeground(COLOR_TEXTO);
        rb.setOpaque(false);
        rb.setFocusPainted(false);
        rb.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    private JPanel createPinPanel(int fieldId) {
        JPanel pinPanel = new JPanel(new BorderLayout());
        pinPanel.setBackground(COLOR_CAMPO);
        pinPanel.setBorder(new EmptyBorder(5, 15, 5, 10));

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBackground(COLOR_CAMPO);
        passwordField.setForeground(COLOR_TEXTO);
        passwordField.setCaretColor(COLOR_TEXTO);
        passwordField.setFont(new Font("SansSerif", Font.PLAIN, 16));
        passwordField.setBorder(null);

        JLabel eyeLabel = new JLabel(eyeOffIcon);
        eyeLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));

        if (fieldId == 1) {
            pfPin = passwordField;
            eyeIcon1 = eyeLabel;
            eyeIcon1.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    isPinVisible = !isPinVisible;
                    togglePinVisibility(pfPin, eyeIcon1, isPinVisible);
                }
            });
        } else {
            pfConfirmPin = passwordField;
            eyeIcon2 = eyeLabel;
            eyeIcon2.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    isConfirmPinVisible = !isConfirmPinVisible;
                    togglePinVisibility(pfConfirmPin, eyeIcon2, isConfirmPinVisible);
                }
            });
        }

        pinPanel.add(passwordField, BorderLayout.CENTER);
        pinPanel.add(eyeLabel, BorderLayout.EAST);
        return pinPanel;
    }

    private void togglePinVisibility(JPasswordField pf, JLabel iconLabel, boolean isVisible) {
        pf.setEchoChar(isVisible ? (char) 0 : '●');
        iconLabel.setIcon(isVisible ? eyeOnIcon : eyeOffIcon);
    }

    private JPanel createButtonPanel() {
        JPanel panel = new JPanel(new GridLayout(1, 2, 15, 0));
        panel.setOpaque(false);

        btnCancel = new JButton("Cancelar");
        btnAccept = new JButton("Aceptar");

        styleButton(btnCancel, COLOR_BOTON_CANCELAR);
        styleButton(btnAccept, COLOR_BOTON_ACEPTAR);

        panel.add(btnCancel);
        panel.add(btnAccept);
        return panel;
    }

    private void styleButton(JButton button, Color color) {
        button.setFont(new Font("SansSerif", Font.BOLD, 16));
        button.setBackground(color);
        button.setForeground(COLOR_TEXTO);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setPreferredSize(new Dimension(150, 50));
    }

    private ImageIcon createIcon(String path, int width, int height) {
        java.net.URL imgURL = getClass().getResource(path);
        if (imgURL != null) {
            ImageIcon icon = new ImageIcon(imgURL);
            Image img = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
            return new ImageIcon(img);
        } else {
            System.err.println("No se pudo encontrar el icono: " + path);
            return null;
        }
    }

    public String getTipoCuenta() {
        return rbDebit.isSelected() ? "DEBITO" : "CREDITO";
    }

    public char[] getPin() {
        return pfPin.getPassword();
    }

    public char[] getConfirmarPin() {
        return pfConfirmPin.getPassword();
    }

    public void addAceptarListener(java.awt.event.ActionListener listener) {
        btnAccept.addActionListener(listener);
    }

    public void addCancelarListener(java.awt.event.ActionListener listener) {
        btnCancel.addActionListener(listener);
    }
}