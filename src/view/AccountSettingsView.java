package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
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

    private JPasswordField pinField;
    private JLabel eyeIconLabel;
    private boolean isPinVisible = false;
    private ImageIcon userIconImg;
    private ImageIcon trashIconImg;
    private ImageIcon eyeOpenIconImg;
    private ImageIcon eyeSlashedIconImg;

    public AccountSettingsView(JFrame owner) {

        super(owner, "Ajustes de cuenta", true);
        URL userImgURL = getClass().getResource("/resources/icon/user.png");
        if (userImgURL != null) {
            ImageIcon icon = new ImageIcon(userImgURL);
            Image img = icon.getImage();
            Image resizedImg = img.getScaledInstance(60, 60, Image.SCALE_SMOOTH);
            userIconImg = new ImageIcon(resizedImg);
        } else {
            System.err.println("No se pudo encontrar el recurso de imagen: user_icon.png");
            userIconImg = null;
        }

        URL trashImgURL = getClass().getResource("/resources/icon/trash.png");
        if (trashImgURL != null) {
            ImageIcon icon = new ImageIcon(trashImgURL);
            Image img = icon.getImage();
            Image resizedImg = img.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
            trashIconImg = new ImageIcon(resizedImg);
        } else {
            System.err.println("No se pudo encontrar el recurso de imagen:trash_icon.png");
            trashIconImg = null;
        }

        URL eyeOpenImgURL = getClass().getResource("/resources/icon/eye_on.png");
        if (eyeOpenImgURL != null) {
            ImageIcon icon = new ImageIcon(eyeOpenImgURL);
            Image img = icon.getImage();
            Image resizedImg = img.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
            eyeOpenIconImg = new ImageIcon(resizedImg);
        } else {
            System.err.println("No se pudo encontrar el recurso de imagen:eye_open.png");
            eyeOpenIconImg = null;
        }

        URL eyeSlashedImgURL = getClass().getResource("/resources/icon/eye_off.png");
        if (eyeSlashedImgURL != null) {
            ImageIcon icon = new ImageIcon(eyeSlashedImgURL);
            Image img = icon.getImage();
            Image resizedImg = img.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
            eyeSlashedIconImg = new ImageIcon(resizedImg);
        } else {
            System.err.println("No se pudo encontrar el recurso de imagen:eye_slashed.png");
            eyeSlashedIconImg = null;
        }

        setSize(420, 700);
        setResizable(false);
        setLayout(new BorderLayout());
        setLocationRelativeTo(owner);
        getContentPane().setBackground(COLOR_FONDO_PRINCIPAL);
        initializeComponents();
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

    }

    private void initializeComponents() {
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(new EmptyBorder(30, 40, 30, 40));
        mainPanel.setBackground(COLOR_FONDO_PRINCIPAL);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 0, 5, 0);

        JLabel title = new JLabel("Ajustes de cuenta");
        title.setFont(new Font("SansSerif", Font.BOLD, 28));
        title.setForeground(COLOR_TEXTO);
        title.setHorizontalAlignment(JLabel.CENTER);
        gbc.gridy = 0;
        gbc.insets.bottom = 15;
        mainPanel.add(title, gbc);

        JLabel userIconLabel = new JLabel();
        userIconLabel.setIcon(userIconImg);
        userIconLabel.setHorizontalAlignment(JLabel.CENTER);
        gbc.gridy = 1;
        gbc.insets.bottom = 25;
        mainPanel.add(userIconLabel, gbc);

        JLabel lblName = new JLabel("Cambiar Nombre:");
        lblName.setFont(new Font("SansSerif", Font.PLAIN, 14));
        lblName.setForeground(COLOR_TEXTO);
        gbc.gridy = 2;
        gbc.insets.bottom = 3;
        gbc.anchor = GridBagConstraints.WEST;
        mainPanel.add(lblName, gbc);

        JTextField tfName = new JTextField();
        tfName.setPreferredSize(new Dimension(300, 40));
        tfName.setFont(new Font("SansSerif", Font.PLAIN, 14));
        tfName.setBackground(COLOR_CAMPO_FONDO);
        tfName.setForeground(COLOR_TEXTO);
        tfName.setCaretColor(COLOR_TEXTO);
        tfName.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(COLOR_BORDE_CAMPO, 1), new EmptyBorder(5, 10, 5, 10)));
        gbc.gridy = 3;
        gbc.insets.bottom = 15;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(tfName, gbc);

        JLabel lblLastName1 = new JLabel("Cambiar Apellido Paterno:");
        lblLastName1.setFont(new Font("SansSerif", Font.PLAIN, 14));
        lblLastName1.setForeground(COLOR_TEXTO);
        gbc.gridy = 4;
        gbc.insets.bottom = 3;
        gbc.anchor = GridBagConstraints.WEST;
        mainPanel.add(lblLastName1, gbc);

        JTextField tfLastName1 = new JTextField();
        tfLastName1.setPreferredSize(new Dimension(300, 40));
        tfLastName1.setFont(new Font("SansSerif", Font.PLAIN, 14));
        tfLastName1.setBackground(COLOR_CAMPO_FONDO);
        tfLastName1.setForeground(COLOR_TEXTO);
        tfLastName1.setCaretColor(COLOR_TEXTO);
        tfLastName1.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(COLOR_BORDE_CAMPO, 1), new EmptyBorder(5, 10, 5, 10)));
        gbc.gridy = 5;
        gbc.insets.bottom = 15;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(tfLastName1, gbc);

        JLabel lblLastName2 = new JLabel("Cambiar Apellido Materno:");
        lblLastName2.setFont(new Font("SansSerif", Font.PLAIN, 14));
        lblLastName2.setForeground(COLOR_TEXTO);
        gbc.gridy = 6;
        gbc.insets.bottom = 3;
        gbc.anchor = GridBagConstraints.WEST;
        mainPanel.add(lblLastName2, gbc);
        JTextField tfLastName2 = new JTextField();
        tfLastName2.setPreferredSize(new Dimension(300, 40));
        tfLastName2.setFont(new Font("SansSerif", Font.PLAIN, 14));
        tfLastName2.setBackground(COLOR_CAMPO_FONDO);
        tfLastName2.setForeground(COLOR_TEXTO);
        tfLastName2.setCaretColor(COLOR_TEXTO);
        tfLastName2.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(COLOR_BORDE_CAMPO, 1), new EmptyBorder(5, 10, 5, 10)));
        gbc.gridy = 7;
        gbc.insets.bottom = 15;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(tfLastName2, gbc);

        JLabel lblPin = new JLabel("Cambiar PIN (4 dígitos):");
        lblPin.setFont(new Font("SansSerif", Font.PLAIN, 14));
        lblPin.setForeground(COLOR_TEXTO);
        gbc.gridy = 8;
        gbc.insets.bottom = 3;
        gbc.anchor = GridBagConstraints.WEST;
        mainPanel.add(lblPin, gbc);
        JPanel pinPanel = new JPanel(new BorderLayout(5, 0));
        pinPanel.setBackground(COLOR_CAMPO_FONDO);
        pinPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(COLOR_BORDE_CAMPO, 1),
                new EmptyBorder(5, 10, 5, 5)));

        pinField = new JPasswordField();
        pinField.setFont(new Font("SansSerif", Font.PLAIN, 14));
        pinField.setBackground(COLOR_CAMPO_FONDO);
        pinField.setForeground(COLOR_TEXTO);
        pinField.setCaretColor(COLOR_TEXTO);
        pinField.setBorder(null);
        pinField.setEchoChar('●');

        eyeIconLabel = new JLabel();
        eyeIconLabel.setIcon(eyeOpenIconImg); // Ícono inicial
        eyeIconLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        eyeIconLabel.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                isPinVisible = !isPinVisible;
                if (isPinVisible) {
                    pinField.setEchoChar((char) 0);
                    eyeIconLabel.setIcon(eyeSlashedIconImg);
                } else {
                    pinField.setEchoChar('*');
                    eyeIconLabel.setIcon(eyeOpenIconImg);
                }
            }

        });

        pinPanel.add(pinField, BorderLayout.CENTER);
        JPanel eyePanel = new JPanel(new GridBagLayout());
        eyePanel.setBackground(COLOR_CAMPO_FONDO);
        eyePanel.add(eyeIconLabel);
        pinPanel.add(eyePanel, BorderLayout.EAST);

        gbc.gridy = 9;
        gbc.insets.bottom = 15;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(pinPanel, gbc);

        JButton btnDelete = new JButton("Eliminar cuenta");
        btnDelete.setIcon(trashIconImg);
        btnDelete.setFont(new Font("SansSerif", Font.BOLD, 15));
        btnDelete.setBackground(COLOR_BOTON_ELIMINAR);
        btnDelete.setForeground(COLOR_TEXTO);
        btnDelete.setBorderPainted(false);
        btnDelete.setFocusPainted(false);
        btnDelete.setOpaque(true);
        btnDelete.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btnDelete.addActionListener(e -> {
            String mensaje = "¿Estás seguro de que deseas eliminar tu cuenta?\nEsta acción es irreversible.";
            String titulo = "Confirmar Eliminación";

            int respuesta = JOptionPane.showConfirmDialog(
                    this,
                    mensaje,
                    titulo,
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE
            );

            if (respuesta == JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(this, "Cuenta eliminada exitosamente.");

                new LoginView().setVisible(true);

                Window owner = getOwner();
                if (owner != null) {
                    owner.dispose();
                }

                dispose();
            }
        });

        gbc.gridy = 10;
        gbc.insets.top = 15;
        gbc.insets.bottom = 15;
        gbc.ipady = 10;
        mainPanel.add(btnDelete, gbc);

        JPanel bottomButtonPanel = new JPanel(new GridLayout(1, 2, 15, 0));
        bottomButtonPanel.setBackground(COLOR_FONDO_PRINCIPAL);
        JButton btnCancel = new JButton("Cancelar");
        btnCancel.setFont(new Font("SansSerif", Font.BOLD, 14));
        btnCancel.setBackground(COLOR_BOTON_CANCELAR);
        btnCancel.setForeground(COLOR_TEXTO);
        btnCancel.setPreferredSize(new Dimension(140, 45));
        btnCancel.setBorderPainted(false);
        btnCancel.setFocusPainted(false);
        btnCancel.setOpaque(true);
        btnCancel.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btnCancel.addActionListener(e -> dispose());
        JButton btnAccept = new JButton("Aceptar");
        btnAccept.setFont(new Font("SansSerif", Font.BOLD, 14));
        btnAccept.setBackground(COLOR_BOTON_ACEPTAR);
        btnAccept.setForeground(COLOR_TEXTO);
        btnAccept.setPreferredSize(new Dimension(140, 45));
        btnAccept.setBorderPainted(false);
        btnAccept.setFocusPainted(false);
        btnAccept.setOpaque(true);
        btnAccept.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btnAccept.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Cambios guardados.");
            dispose();
        });

        bottomButtonPanel.add(btnCancel);
        bottomButtonPanel.add(btnAccept);

        gbc.gridy = 11;
        gbc.insets.top = 10;
        gbc.insets.bottom = 0;
        mainPanel.add(bottomButtonPanel, gbc);
        add(mainPanel, BorderLayout.CENTER);
    }

}