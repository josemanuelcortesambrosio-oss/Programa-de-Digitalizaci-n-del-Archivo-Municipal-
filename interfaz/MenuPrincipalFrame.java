package archivo.municipal.interfaz;

import archivo.municipal.modelo.Expediente;
import archivo.municipal.servicio.ExpedienteService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class MenuPrincipalFrame extends JFrame {
    private static final long serialVersionUID = 1L;
    private final Color AZUL_OSCURO = new Color(29, 55, 79);
    private final Color AZUL_PRINCIPAL = new Color(39, 111, 170);
    private final Color AZUL_BOTON = new Color(28, 110, 169);
    private final Color FONDO = new Color(245, 248, 251);
    private final Color TEXTO = new Color(31, 51, 72);
    private final Color BORDE = new Color(220, 227, 234);

    private JTable tablaExpedientes;
    private DefaultTableModel modeloTabla;
    private JTextField txtBuscar;
    private final ExpedienteService service = new ExpedienteService();

    public MenuPrincipalFrame() {
        configurarVentana();
        crearInterfaz();
        cargarExpedientes();
    }

    private void configurarVentana() {
        setTitle("Sistema Integral de Gestión Documental y Archivo Municipal");
        setSize(1250, 750);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(FONDO);
    }

    private void crearInterfaz() {
        setLayout(new BorderLayout());
        crearMenuLateral();
        crearContenido();
    }

    private void crearMenuLateral() {
        JPanel menu = new JPanel();
        menu.setPreferredSize(new Dimension(230, 0));
        menu.setBackground(AZUL_OSCURO);
        menu.setLayout(new BoxLayout(menu, BoxLayout.Y_AXIS));

        JLabel lblTitulo = new JLabel("<html><center><b>H. Ayuntamiento de</b><br>Villa Aldama</center></html>");
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblTitulo.setBorder(new EmptyBorder(30, 10, 30, 10));
        menu.add(lblTitulo);

        menu.add(crearBotonMenu("Inicio"));
        JButton btnExpedientes = crearBotonMenu("Expedientes");
        btnExpedientes.setBackground(AZUL_PRINCIPAL);
        menu.add(btnExpedientes);

        menu.add(crearBotonMenu("Documentos"));
        menu.add(crearBotonMenu("Consulta"));
        menu.add(crearBotonMenu("Administración"));

        menu.add(Box.createVerticalGlue());

        JLabel version = new JLabel("Versión 0.1");
        version.setForeground(new Color(210, 220, 230));
        version.setBorder(new EmptyBorder(0, 20, 20, 0));
        version.setAlignmentX(Component.LEFT_ALIGNMENT);
        menu.add(version);

        add(menu, BorderLayout.WEST);
    }

    private JButton crearBotonMenu(String texto) {
        JButton boton = new JButton(texto);
        boton.setMaximumSize(new Dimension(Integer.MAX_VALUE, 55));
        boton.setAlignmentX(Component.CENTER_ALIGNMENT);
        boton.setHorizontalAlignment(SwingConstants.LEFT);
        boton.setForeground(Color.WHITE);
        boton.setBackground(AZUL_OSCURO);
        boton.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        boton.setBorder(new EmptyBorder(0, 25, 0, 10));
        boton.setFocusPainted(false);
        return boton;
    }

    private void crearContenido() {
        JPanel contenido = new JPanel(new BorderLayout());
        contenido.setBackground(FONDO);
        contenido.setBorder(new EmptyBorder(25, 25, 25, 25));

        contenido.add(crearEncabezado(), BorderLayout.NORTH);
        contenido.add(crearPanelExpedientes(), BorderLayout.CENTER);

        add(contenido, BorderLayout.CENTER);
    }

    private JPanel crearEncabezado() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setOpaque(false);

        JLabel titulo = new JLabel("Expedientes");
        titulo.setForeground(TEXTO);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 28));

        JLabel usuario = new JLabel("Usuario: admin ▼");
        usuario.setForeground(TEXTO);
        usuario.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        panel.add(titulo, BorderLayout.WEST);
        panel.add(usuario, BorderLayout.EAST);
        panel.setBorder(new EmptyBorder(5, 10, 25, 10));
        return panel;
    }

    private JPanel crearPanelExpedientes() {
        JPanel panel = new JPanel(new BorderLayout(0, 15));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createLineBorder(BORDE));

        panel.add(crearBarraHerramientas(), BorderLayout.NORTH);
        crearTabla();
        panel.add(new JScrollPane(tablaExpedientes), BorderLayout.CENTER);

        return panel;
    }

    private JPanel crearBarraHerramientas() {
        JPanel barra = new JPanel(new BorderLayout(15, 0));
        barra.setBackground(Color.WHITE);
        barra.setBorder(new EmptyBorder(15, 15, 0, 15));

        JPanel botones = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        botones.setOpaque(false);

        JButton btnNuevo = new JButton("+ Nuevo expediente");
        btnNuevo.setBackground(AZUL_BOTON);
        btnNuevo.setForeground(Color.WHITE);
        btnNuevo.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnNuevo.setFocusPainted(false);
        btnNuevo.setBorder(new EmptyBorder(12, 18, 12, 18));
        btnNuevo.addActionListener(e -> {
            NuevoExpedienteDialog dialog = new NuevoExpedienteDialog(this);
            dialog.setVisible(true);
            cargarExpedientes();
        });

        JButton btnActualizar = new JButton("Actualizar");
        btnActualizar.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        btnActualizar.setFocusPainted(false);
        btnActualizar.addActionListener(e -> cargarExpedientes());

        botones.add(btnNuevo);
        botones.add(btnActualizar);

        JPanel busqueda = new JPanel(new BorderLayout(5, 0));
        busqueda.setOpaque(false);

        txtBuscar = new JTextField();
        txtBuscar.setPreferredSize(new Dimension(280, 38));
        txtBuscar.setToolTipText("Buscar por número, asunto o área");

        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.setBackground(AZUL_BOTON);
        btnBuscar.setForeground(Color.WHITE);
        btnBuscar.setFocusPainted(false);
        btnBuscar.addActionListener(e -> buscar());

        busqueda.add(txtBuscar, BorderLayout.CENTER);
        busqueda.add(btnBuscar, BorderLayout.EAST);

        barra.add(botones, BorderLayout.WEST);
        barra.add(busqueda, BorderLayout.EAST);

        return barra;
    }

    private void crearTabla() {
        String[] columnas = {"Número", "Área", "Asunto", "Responsable", "Fecha de apertura", "Estado", "Ubicación", "Acciones"};

        modeloTabla = new DefaultTableModel(columnas, 0) {
            private static final long serialVersionUID = 1L;
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tablaExpedientes = new JTable(modeloTabla);
        tablaExpedientes.setRowHeight(45);
        tablaExpedientes.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        tablaExpedientes.setForeground(TEXTO);
        tablaExpedientes.setGridColor(BORDE);
        tablaExpedientes.setSelectionBackground(new Color(225, 239, 250));
        tablaExpedientes.setSelectionForeground(TEXTO);

        tablaExpedientes.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        tablaExpedientes.getTableHeader().setForeground(TEXTO);
        tablaExpedientes.getTableHeader().setBackground(new Color(238, 243, 248));
        tablaExpedientes.getTableHeader().setPreferredSize(new Dimension(0, 42));

        tablaExpedientes.getColumnModel().getColumn(0).setPreferredWidth(110);
        tablaExpedientes.getColumnModel().getColumn(1).setPreferredWidth(120);
        tablaExpedientes.getColumnModel().getColumn(2).setPreferredWidth(220);
        tablaExpedientes.getColumnModel().getColumn(5).setCellRenderer(new EstadoRenderer());

        tablaExpedientes.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int fila = tablaExpedientes.getSelectedRow();
                    if (fila >= 0) {
                        mostrarDetalle(fila);
                    }
                }
            }
        });
    }

    private void cargarExpedientes() {
        List<Expediente> lista = service.listar();
        llenarTabla(lista);
    }

    private void buscar() {
        String texto = txtBuscar.getText().trim();
        if (texto.isEmpty()) {
            cargarExpedientes();
            return;
        }
        List<Expediente> lista = service.buscar(texto);
        llenarTabla(lista);
    }

    private void llenarTabla(List<Expediente> lista) {
        modeloTabla.setRowCount(0);
        for (Expediente e : lista) {
            modeloTabla.addRow(new Object[]{
                e.getNumeroExpediente(),
                e.getArea(),
                e.getAsunto(),
                e.getResponsable(),
                e.getFechaApertura(),
                e.getEstado(),
                e.getUbicacion(),
                "Ver"
            });
        }
    }

    private void mostrarDetalle(int fila) {
        JOptionPane.showMessageDialog(this,
            "Número: " + modeloTabla.getValueAt(fila, 0) +
            "\nÁrea: " + modeloTabla.getValueAt(fila, 1) +
            "\nAsunto: " + modeloTabla.getValueAt(fila, 2) +
            "\nResponsable: " + modeloTabla.getValueAt(fila, 3) +
            "\nFecha: " + modeloTabla.getValueAt(fila, 4) +
            "\nEstado: " + modeloTabla.getValueAt(fila, 5) +
            "\nUbicación: " + modeloTabla.getValueAt(fila, 6),
            "Información del expediente",
            JOptionPane.INFORMATION_MESSAGE);
    }

    private static class EstadoRenderer extends DefaultTableCellRenderer {
        private static final long serialVersionUID = 1L;
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            label.setHorizontalAlignment(SwingConstants.CENTER);
            label.setOpaque(true);
            label.setBorder(new EmptyBorder(5, 10, 5, 10));

            if ("Abierto".equals(value)) {
                label.setBackground(new Color(55, 180, 125));
                label.setForeground(Color.WHITE);
            } else if ("En proceso".equals(value)) {
                label.setBackground(new Color(245, 175, 30));
                label.setForeground(Color.WHITE);
            } else {
                label.setBackground(new Color(120, 140, 155));
                label.setForeground(Color.WHITE);
            }
            return label;
        }
    }
}