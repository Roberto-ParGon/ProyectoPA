package view;

import model.TarjetaDTO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class DashboardAccountView extends JFrame {

    private static final Color COLOR_FONDO = new Color(44, 44, 58);
    private static final Color COLOR_FONDO_TABLA_HEADER = new Color(50, 50, 65);
    private static final Color COLOR_TEXTO_PRINCIPAL = new Color(230, 230, 230);
    private static final Color COLOR_TEXTO_SECUNDARIO = new Color(180, 180, 180);
    private static final Color COLOR_SELECCION_FILA = new Color(91, 75, 142);
    private static final Color COLOR_BOTON = new Color(91, 75, 142);

    private static final Font FONT_BIENVENIDA = new Font("SansSerif", Font.BOLD, 24);
    private static final Font FONT_TITULO = new Font("SansSerif", Font.BOLD, 16);
    private static final Font FONT_TEXTO_TABLA = new Font("SansSerif", Font.PLAIN, 15);

    private JTable tablaTarjetas;
    private DefaultTableModel tableModel;
    private JButton btnVer;
    private JLabel lblBienvenido;

    private JButton btnLogout;
    private JButton btnSettings;
    private JButton btnAddCard;

    private List<TarjetaDTO> listaDeTarjetas;

    public DashboardAccountView(String nombreCliente) {
        this.listaDeTarjetas = new ArrayList<>();
        setTitle("Mis Cuentas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 500);
        setMinimumSize(new Dimension(700, 450));
        setLocationRelativeTo(null);
        getContentPane().setBackground(COLOR_FONDO);
        setLayout(new BorderLayout(0, 15));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(20, 25, 20, 25));

        setupTopPanel(nombreCliente);
        setupCenterPanel();
        setupBottomPanel();
    }

    private void setupTopPanel(String nombreCliente) {
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setOpaque(false);

        JPanel welcomePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 0));
        welcomePanel.setOpaque(false);

        btnLogout = createIconButton("/resources/icon/logout.png");

        lblBienvenido = new JLabel("Bienvenido, " + nombreCliente);
        lblBienvenido.setFont(FONT_BIENVENIDA);
        lblBienvenido.setForeground(COLOR_TEXTO_PRINCIPAL);

        welcomePanel.add(btnLogout);
        welcomePanel.add(lblBienvenido);
        topPanel.add(welcomePanel, BorderLayout.WEST);

        JPanel actionButtonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        actionButtonsPanel.setOpaque(false);

        btnSettings = createIconButton("/resources/icon/manage_account.png");
        btnAddCard = createIconButton("/resources/icon/add_card.png");

        actionButtonsPanel.add(btnSettings);
        actionButtonsPanel.add(btnAddCard);
        topPanel.add(actionButtonsPanel, BorderLayout.EAST);

        add(topPanel, BorderLayout.NORTH);
    }

    private void setupCenterPanel() {
        JPanel centerPanel = new JPanel(new BorderLayout(0, 10));
        centerPanel.setOpaque(false);

        JLabel lblTituloTabla = new JLabel("Tarjetas disponibles");
        lblTituloTabla.setFont(FONT_TITULO);
        lblTituloTabla.setForeground(COLOR_TEXTO_SECUNDARIO);
        lblTituloTabla.setBorder(new EmptyBorder(10, 5, 0, 0));
        centerPanel.add(lblTituloTabla, BorderLayout.NORTH);

        String[] columnNames = {"Número de tarjeta", "Tipo"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };

        tablaTarjetas = new JTable(tableModel);
        styleTable(tablaTarjetas);

        JScrollPane scrollPane = new JScrollPane(tablaTarjetas);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getViewport().setBackground(COLOR_FONDO);
        centerPanel.add(scrollPane, BorderLayout.CENTER);

        add(centerPanel, BorderLayout.CENTER);
    }

    private void styleTable(JTable table) {
        table.setBackground(COLOR_FONDO);
        table.setForeground(COLOR_TEXTO_PRINCIPAL);
        table.setSelectionBackground(COLOR_SELECCION_FILA);
        table.setSelectionForeground(COLOR_TEXTO_PRINCIPAL);
        table.setFont(FONT_TEXTO_TABLA);
        table.setRowHeight(40);
        table.setShowGrid(false);
        table.setIntercellSpacing(new Dimension(0, 0));
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setFillsViewportHeight(true);

        JTableHeader header = table.getTableHeader();
        header.setBackground(COLOR_FONDO_TABLA_HEADER);
        header.setForeground(COLOR_TEXTO_SECUNDARIO);
        header.setFont(FONT_TITULO);
        header.setPreferredSize(new Dimension(0, 40));
        header.setReorderingAllowed(false);
    }

    private void setupBottomPanel() {
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 10));
        bottomPanel.setOpaque(false);

        btnVer = new JButton("Ver");
        btnVer.setFont(FONT_TITULO);
        btnVer.setBackground(COLOR_BOTON);
        btnVer.setForeground(COLOR_TEXTO_PRINCIPAL);
        btnVer.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnVer.setPreferredSize(new Dimension(180, 50));
        btnVer.setFocusPainted(false);
        btnVer.setBorder(new EmptyBorder(10,10,10,10));

        bottomPanel.add(btnVer);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private ImageIcon createIcon(String path) {
        java.net.URL imgURL = getClass().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("No se pudo encontrar el icono: " + path);
            return null;
        }
    }
    private JButton createIconButton(String path) {
        JButton button = new JButton(createIcon(path));
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return button;
    }

    public void setTableData(List<TarjetaDTO> tarjetas) {
        this.listaDeTarjetas = tarjetas;
        tableModel.setRowCount(0);

        for (TarjetaDTO tarjeta : tarjetas) {
            tableModel.addRow(new Object[]{tarjeta.getNumTarjeta(), tarjeta.getTipoTarjeta()});
        }
    }
    public TarjetaDTO getTarjetaSeleccionada() {
        int selectedRow = tablaTarjetas.getSelectedRow();
        if (selectedRow >= 0) {
            return listaDeTarjetas.get(selectedRow);
        }
        return null;
    }

    public void addLogoutListener(ActionListener listener) {
        btnLogout.addActionListener(listener);
    }

    public void addSettingsListener(ActionListener listener) {
        btnSettings.addActionListener(listener);
    }

    public void addAddCardListener(ActionListener listener) {
        btnAddCard.addActionListener(listener);
    }

    public void addVerListener(ActionListener listener) {
        btnVer.addActionListener(listener);
    }

    public void setNombreCliente(String nombreCliente) {
        if (lblBienvenido != null) {
            lblBienvenido.setText("Bienvenido, " + nombreCliente);
        }
    }

}