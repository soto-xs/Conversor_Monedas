package drr.aluradesafio.conversormonedas;

import drr.aluradesafio.conversormonedas.dominio.Convertidor;
import drr.aluradesafio.conversormonedas.dominio.Historial;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;

public class ConversorMonedasGUI extends JFrame {
    private Convertidor convertidor;
    private JTextField montoField;
    private JComboBox<String> monedaDestinoCombo;
    private JTextArea resultadoArea;

    public ConversorMonedasGUI() {
        convertidor = new Convertidor();
        configurarVentana();
        inicializarComponentes();
    }

    private void configurarVentana() {
        setTitle("Conversor de Monedas");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Colores
        JPanel panel = new JPanel();
        panel.setBackground(Color.decode("#778da9"));
        add(panel, BorderLayout.CENTER);
    }

    private void inicializarComponentes() {
        // Crear panel para los componentes
        JPanel panel = new JPanel();
        panel.setBackground(Color.decode("#778da9"));
        panel.setLayout(new GridLayout(5, 2));

        // Campo de monto
        panel.add(new JLabel("Ingrese el monto (USD):", SwingConstants.RIGHT));
        montoField = new JTextField();
        panel.add(montoField);

        // ComboBox para seleccionar moneda destino
        panel.add(new JLabel("Seleccione la moneda de destino:", SwingConstants.RIGHT));
        monedaDestinoCombo = new JComboBox<>(convertidor.obtenerMonedasSoportadas());
        panel.add(monedaDestinoCombo);

        // Botón para convertir
        JButton convertirButton = new JButton("Convertir");
        panel.add(convertirButton);
        resultadoArea = new JTextArea();
        resultadoArea.setEditable(false);
        resultadoArea.setBackground(Color.decode("#e0e1dd"));
        resultadoArea.setForeground(Color.BLACK);
        panel.add(new JScrollPane(resultadoArea));

        // Agregar acción al botón
        convertirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                convertirMoneda();
            }
        });

        add(panel, BorderLayout.CENTER);
    }

    private void convertirMoneda() {
        try {
            double monto = Double.parseDouble(montoField.getText());
            String monedaDestino = (String) monedaDestinoCombo.getSelectedItem();
            double resultado = convertidor.convertir("USD", monedaDestino, monto);
            resultadoArea.setText(String.format("Resultado: %.2f %s", resultado, monedaDestino));

            // Guardar historial
            Historial historial = new Historial(LocalDateTime.now().toString(), resultado);
            resultadoArea.append(String.format("\nHistorial: %s - %s", historial.getFechaStringFormato(), historial.getMontoCambioFormato()));
        } catch (Exception e) {
            resultadoArea.setText("Error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ConversorMonedasGUI gui = new ConversorMonedasGUI();
            gui.setVisible(true);
        });
    }
}
