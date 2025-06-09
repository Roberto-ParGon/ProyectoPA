package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

public class TransactionSettingsView extends JDialog {

    private static final Color COLOR_FONDO = new Color(44, 44, 58);
    private static final Color COLOR_CAMPO_FONDO = new Color(58, 58, 79);
    private static final Color COLOR_TEXTO = Color.WHITE;
    private static final Color COLOR_TEXTO_SECUNDARIO = new Color(180, 180, 180);
    private static final Color COLOR_BOTON_ROJO = new Color(229, 57, 53);
    private static final Color COLOR_BOTON_GRIS = new Color(97, 97, 97);
    private static final Color COLOR_BOTON_MORADO = new Color(126, 87, 194);

    private JSpinner spinnerMonto;
    private JPasswordField pfPin;
    private JButton btnEliminar, btnCancelar, btnConfirmar;

    private JLabel eyeIconLabel;
    private ImageIcon eyeOnIcon, eyeOffIcon;
    private boolean isPinVisible = false;


    public TransactionSettingsView(Frame owner) {
        super(owner, "Configurar Transacción", true);
        loadIcons();
        setSize(400, 550);
        setResizable(false);
        setLocationRelativeTo(owner);
        getContentPane().setBackground(COLOR_FONDO);
        setLayout(new GridBagLayout());
        initializeComponents();
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    }

    private void loadIcons() {
        eyeOnIcon = createIcon("/resources/icon/eye_on.png", 20, 20);
        eyeOffIcon = createIcon("/resources/icon/eye_off.png", 20, 20);
    }

    private void initializeComponents() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 25, 10, 25);
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Título
        JLabel lblTitle = new JLabel("Cambiar monto");
        lblTitle.setFont(new Font("SansSerif", Font.BOLD, 32));
        lblTitle.setForeground(COLOR_TEXTO);
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblTitle, gbc);

        add(createInfoPanel(), gbc);

        gbc.insets.top = 0;
        add(createLabel("Monto:"), gbc);
        spinnerMonto = new JSpinner(new SpinnerNumberModel(100.0, 0.0, 50000.0, 50.0));
        styleSpinner(spinnerMonto);
        gbc.insets.bottom = 15;
        add(spinnerMonto, gbc);

        gbc.insets.bottom = 10;
        add(createLabel("PIN:"), gbc);
        pfPin = new JPasswordField();
        add(createPinPanel(), gbc);

        btnEliminar = new JButton("Eliminar transacción");
        styleButton(btnEliminar, COLOR_BOTON_ROJO, 16);
        gbc.insets.top = 25;
        add(btnEliminar, gbc);
        gbc.insets.top = 10;

        add(createBottomButtonPanel(), gbc);
    }

    private JPanel createPinPanel() {
        JPanel pinPanel = new JPanel(new BorderLayout());
        pinPanel.setBackground(COLOR_CAMPO_FONDO);
        pinPanel.setBorder(new EmptyBorder(5, 15, 5, 10));

        pfPin.setBackground(COLOR_CAMPO_FONDO);
        pfPin.setForeground(COLOR_TEXTO);
        pfPin.setCaretColor(COLOR_TEXTO);
        pfPin.setFont(new Font("SansSerif", Font.PLAIN, 16));
        pfPin.setBorder(null);
        pfPin.setEchoChar('●');

        eyeIconLabel = new JLabel(eyeOffIcon);
        eyeIconLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        eyeIconLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                isPinVisible = !isPinVisible;
                togglePinVisibility(pfPin, eyeIconLabel, isPinVisible);
            }
        });

        pinPanel.add(pfPin, BorderLayout.CENTER);
        pinPanel.add(eyeIconLabel, BorderLayout.EAST);
        return pinPanel;
    }

    private void togglePinVisibility(JPasswordField pf, JLabel iconLabel, boolean isVisible) {
        pf.setEchoChar(isVisible ? (char) 0 : '●');
        iconLabel.setIcon(isVisible ? eyeOnIcon : eyeOffIcon);
    }

    private JPanel createInfoPanel() {
        JPanel infoPanel = new JPanel(new BorderLayout(15, 0));
        infoPanel.setOpaque(false);
        infoPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        JLabel iconInfo = new JLabel(createIcon("/resources/icon/info.png", 24, 24));
        JTextArea txtInfo = new JTextArea("Solo se pueden hacer cambios en la transacción cuando no haya pasado más de 1 hora de realizada.");
        txtInfo.setLineWrap(true);
        txtInfo.setWrapStyleWord(true);
        txtInfo.setEditable(false);
        txtInfo.setOpaque(false);
        txtInfo.setFont(new Font("SansSerif", Font.PLAIN, 14));
        txtInfo.setForeground(COLOR_TEXTO_SECUNDARIO);
        infoPanel.add(iconInfo, BorderLayout.WEST);
        infoPanel.add(txtInfo, BorderLayout.CENTER);
        return infoPanel;
    }
    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("SansSerif", Font.PLAIN, 16));
        label.setForeground(COLOR_TEXTO_SECUNDARIO);
        return label;
    }
    private void styleSpinner(JSpinner spinner) {
        spinner.setFont(new Font("SansSerif", Font.BOLD, 18));
        spinner.setPreferredSize(new Dimension(150, 40));
        JComponent editor = spinner.getEditor();
        if (editor instanceof JSpinner.DefaultEditor) {
            JFormattedTextField tf = ((JSpinner.DefaultEditor) editor).getTextField();
            tf.setForeground(COLOR_TEXTO);
            tf.setBackground(COLOR_CAMPO_FONDO);
            tf.setHorizontalAlignment(SwingConstants.CENTER);
            tf.setBorder(new EmptyBorder(5, 5, 5, 5));
        }
    }
    private JPanel createBottomButtonPanel() {
        JPanel panel = new JPanel(new GridLayout(1, 2, 20, 0));
        panel.setOpaque(false);
        btnCancelar = new JButton("Cancelar");
        btnConfirmar = new JButton("Confirmar");
        styleButton(btnCancelar, COLOR_BOTON_GRIS, 16);
        styleButton(btnConfirmar, COLOR_BOTON_MORADO, 16);
        panel.add(btnCancelar);
        panel.add(btnConfirmar);
        return panel;
    }
    private void styleButton(JButton button, Color color, int fontSize) {
        button.setFont(new Font("SansSerif", Font.BOLD, fontSize));
        button.setBackground(color);
        button.setForeground(COLOR_TEXTO);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setOpaque(true);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setPreferredSize(new Dimension(150, 50));
    }
    private ImageIcon createIcon(String path, int w, int h) {
        URL url = getClass().getResource(path);
        if (url != null) {
            return new ImageIcon(new ImageIcon(url).getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH));
        }
        System.err.println("No se encontró el icono: " + path);
        return null;
    }

    public void setMontoInicial(double monto) { spinnerMonto.setValue(monto); }
    public double getNuevoMonto() { return (double) spinnerMonto.getValue(); }
    public String getPin() { return new String(pfPin.getPassword()); }
    public void addConfirmarListener(ActionListener listener) { btnConfirmar.addActionListener(listener); }
    public void addCancelarListener(ActionListener listener) { btnCancelar.addActionListener(listener); }
    public void addEliminarListener(ActionListener listener) { btnEliminar.addActionListener(listener); }
}