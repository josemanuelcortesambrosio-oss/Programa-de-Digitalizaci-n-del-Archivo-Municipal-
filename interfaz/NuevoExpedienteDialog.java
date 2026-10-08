package archivo.municipal.interfaz;

import archivo.municipal.modelo.Expediente;
import archivo.municipal.servicio.ExpedienteService;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class NuevoExpedienteDialog extends JDialog {
    private static final long serialVersionUID = 1L;

    // Componentes del formulario
    private JTextField txtNumero;
    private JTextField txtAsunto;
    private JTextField txtResponsable;
    private JTextField txtUbicacion;
    private JComboBox<String> cbArea;
    private JComboBox<String> cbEstado;

    // Conexión con la capa de servicio
    private final ExpedienteService service = new ExpedienteService();

    // Constructor
    public NuevoExpedienteDialog(JFrame padre) {
        super(padre, "Nuevo expediente", true);
        setSize(500, 500);
        setLocationRelativeTo(padre);
        crearInterfaz();
    }

    // Diseño de la interfaz visual
    private void crearInterfaz() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(25, 30, 25, 30));
        panel.setLayout(new GridLayout(0, 1, 10, 8));

        JLabel titulo = new JLabel("Nuevo expediente");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titulo.setForeground(new Color(31, 51, 72));
        panel.add(titulo);

        // 1. Campo Número de expediente
        panel.add(new JLabel("Número de expediente"));
        txtNumero = new JTextField();
        panel.add(txtNumero);

        // 2. Selección de Área
        panel.add(new JLabel("Área"));
        cbArea = new JComboBox<>(new String[]{
            "Presidencia", "Tesorería", "Obras Públicas", "Desarrollo Social", "Contraloría"
        });
        panel.add(cbArea);

        // 3. Campo Asunto
        panel.add(new JLabel("Asunto"));
        txtAsunto = new JTextField();
        panel.add(txtAsunto);

        // 4. Campo Responsable
        panel.add(new JLabel("Responsable"));
        txtResponsable = new JTextField("Administrador del Sistema");
        panel.add(txtResponsable);

        // 5. Fecha de Apertura (bloqueada con la fecha de hoy)
        panel.add(new JLabel("Fecha de apertura"));
        JTextField txtFecha = new JTextField(LocalDate.now().toString());
        txtFecha.setEditable(false);
        panel.add(txtFecha);

        // 6. Selección de Estado
        panel.add(new JLabel("Estado"));
        cbEstado = new JComboBox<>(new String[]{"Abierto", "En proceso", "Cerrado"});
        panel.add(cbEstado);

        // 7. Campo Ubicación
        panel.add(new JLabel("Ubicación"));
        txtUbicacion = new JTextField("Archivo de trámite");
        panel.add(txtUbicacion);

        // Botones
        JPanel botones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        
        JButton cancelar = new JButton("Cancelar");
        cancelar.addActionListener(e -> dispose());

        JButton guardar = new JButton("Guardar");
        guardar.setBackground(new Color(28, 110, 169));
        guardar.setForeground(Color.WHITE);
        guardar.setFocusPainted(false);
        guardar.addActionListener(e -> guardar());

        botones.add(cancelar);
        botones.add(guardar);
        panel.add(botones);

        add(panel);
    }

    // Lógica para enviar los datos a MySQL
    private void guardar() {
        String numero = txtNumero.getText().trim();
        String asunto = txtAsunto.getText().trim();
        String responsable = txtResponsable.getText().trim();
        String ubicacion = txtUbicacion.getText().trim();

        if (numero.isEmpty() || asunto.isEmpty() || responsable.isEmpty() || ubicacion.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Complete todos los campos.", "Datos incompletos", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int idArea = cbArea.getSelectedIndex() + 1;
        String estado = cbEstado.getSelectedItem().toString();

        Expediente expediente = new Expediente(numero, idArea, asunto, responsable, LocalDate.now(), estado, ubicacion);
        boolean guardado = service.guardar(expediente);

        if (guardado) {
            JOptionPane.showMessageDialog(this, "El expediente fue registrado correctamente.", "Registro exitoso", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "No fue posible registrar el expediente.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}