package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;

public class LoginView extends JFrame {
    public LoginView() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 750);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        initializeComponents();
    }

    private void initializeComponents() {
        //Panel izquierdo
        JPanel leftPanel = new JPanel(new GridBagLayout());
        leftPanel.setPreferredSize(new Dimension(500, 750));
        //leftPanel.setBackground(new Color(32, 32, 50));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0; gbc.insets = new Insets(0, 60, 0, 60);

        //Título
        JLabel title = new JLabel("Iniciar sesión");
        title.setFont(new Font("SansSerif", Font.BOLD, 32));
        title.setForeground(Color.WHITE);
        gbc.gridy = 0; gbc.insets.bottom = 85;
        leftPanel.add(title, gbc);

        //Número de tarjeta
        JLabel lblCard = new JLabel("Núm. de tarjeta:");
        lblCard.setFont(new Font("SansSerif", Font.PLAIN, 16));
        lblCard.setForeground(Color.WHITE);
        gbc.gridy = 1; gbc.insets.bottom = 8; gbc.anchor = GridBagConstraints.WEST;
        leftPanel.add(lblCard, gbc);

        JTextField tfCard = new JTextField();
        tfCard.setPreferredSize(new Dimension(300, 45));
        tfCard.setFont(new Font("SansSerif", Font.PLAIN, 14));
        tfCard.setBackground(new Color(45, 45, 65));
        tfCard.setForeground(Color.WHITE);
        tfCard.setCaretColor(Color.WHITE);
        tfCard.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(70, 70, 90), 1),
                new EmptyBorder(8, 15, 8, 15)));
        gbc.gridy = 2; gbc.insets.bottom = 25; gbc.anchor = GridBagConstraints.CENTER;
        leftPanel.add(tfCard, gbc);

        //PIN de tarjeta
        JLabel lblPin = new JLabel("PIN de tarjeta:");
        lblPin.setFont(new Font("SansSerif", Font.PLAIN, 16));
        lblPin.setForeground(Color.WHITE);
        gbc.gridy = 3; gbc.insets.bottom = 8; gbc.anchor = GridBagConstraints.WEST;
        leftPanel.add(lblPin, gbc);

        JPasswordField tfPin = new JPasswordField();
        tfPin.setPreferredSize(new Dimension(300, 45));
        tfPin.setFont(new Font("SansSerif", Font.PLAIN, 20));
        tfPin.setBackground(new Color(45, 45, 65));
        tfPin.setForeground(Color.WHITE);
        tfPin.setCaretColor(Color.WHITE);
        tfPin.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(70, 70, 90), 1),
                new EmptyBorder(8, 15, 8, 15)));
        gbc.gridy = 4; gbc.insets.bottom = 50; gbc.anchor = GridBagConstraints.CENTER;
        leftPanel.add(tfPin, gbc);

        //Botón
        JButton btnLogin = new JButton("Ingresar");
        btnLogin.setFont(new Font("SansSerif", Font.BOLD, 20));
        btnLogin.setBackground(new Color(102, 102, 204));
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setPreferredSize(new Dimension(120, 40));
        btnLogin.setBorderPainted(false);
        btnLogin.setFocusPainted(false);
        btnLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));
        gbc.gridy = 5; gbc.insets.bottom = 50;
        leftPanel.add(btnLogin, gbc);

        //Texto para registrarse
        JLabel lblRegister = new JLabel("<html><center><font color='#999999'>¿Aún no tienes cuenta? </font><font color='#00ccff'>Crea una.</font></center></html>");
        lblRegister.setFont(new Font("SansSerif", Font.PLAIN, 18));
        lblRegister.setCursor(new Cursor(Cursor.HAND_CURSOR));
        gbc.gridy = 6; gbc.insets.bottom = 0;
        leftPanel.add(lblRegister, gbc);

        // Panel derecho
        JPanel rightPanel = new JPanel(new BorderLayout());
        ImageIcon originalIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/resources/img/atm_image.jpg")));
        Image scaledImage = originalIcon.getImage().getScaledInstance(700, 750, Image.SCALE_SMOOTH);
        rightPanel.add(new JLabel(new ImageIcon(scaledImage)), BorderLayout.CENTER);

        //Asignar paneles
        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.CENTER);

        lblRegister.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                RegisterView registerView = new RegisterView(LoginView.this); // Pasar this como owner
                registerView.setVisible(true);
            }
        });


    }

}