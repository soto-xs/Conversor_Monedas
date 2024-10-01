package drr.aluradesafio.conversormonedas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class Convertidor {
    private Map<String, Double> tasasDeCambio;

    public Convertidor() {
        tasasDeCambio = new HashMap<>();
        inicializarTasasDeCambio();
        crearInterfazGrafica();
    }

    private void inicializarTasasDeCambio() {
        // Aquí puedes agregar las tasas de cambio de las monedas
        tasasDeCambio.put("EUR", 0.85); // Euro
        tasasDeCambio.put("JPY", 110.53); // Yen japonés
        tasasDeCambio.put("GBP", 0.75); // Libra esterlina
        tasasDeCambio.put("AUD", 1.35); // Dólar australiano
        tasasDeCambio.put("CAD", 1.25); // Dólar canadiense
        tasasDeCambio.put("CNY", 6.45); // Yuan chino
        tasasDeCambio.put("KRW", 1176.00); // Won surcoreano
        tasasDeCambio.put("THB", 32.73); // Baht tailandés
        tasasDeCambio.put("CRC", 610.00); // Colón costarricense
        // Agregar más monedas según sea necesario
    }

    private void crearInterfazGrafica() {
        // Crear la ventana principal
        JFrame ventana = new JFrame("Conversor de Monedas");
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setSize(400, 300);
        ventana.getContentPane().setBackground(Color.decode("#778da9"));
        ventana.setLayout(new FlowLayout());

        // Crear componentes
        JLabel etiqueta = new JLabel("Conversor de Monedas");
        etiqueta.setForeground(Color.BLACK);
        etiqueta.setFont(new Font("Arial", Font.BOLD, 20));

        JTextField montoField = new JTextField(10);
        JComboBox<String> monedaDestinoCombo = new JComboBox<>(obtenerMonedasSoportadas());
        JButton convertirButton = new JButton("Convertir");
        JButton mostrarTasasButton = new JButton("Mostrar Tasas de Cambio");

        convertirButton.setBackground(Color.decode("#e0e1dd"));
        convertirButton.setForeground(Color.BLACK);
        mostrarTasasButton.setBackground(Color.decode("#e0e1dd"));
        mostrarTasasButton.setForeground(Color.BLACK);

        // Agregar componentes a la ventana
        ventana.add(etiqueta);
        ventana.add(new JLabel("Monto en USD: "));
        ventana.add(montoField);
        ventana.add(new JLabel("Convertir a: "));
        ventana.add(monedaDestinoCombo);
        ventana.add(convertirButton);
        ventana.add(mostrarTasasButton);

        // Acción del botón de conversión
        convertirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double monto = Double.parseDouble(montoField.getText());
                    String monedaDestino = (String) monedaDestinoCombo.getSelectedItem();
                    double resultado = convertir("USD", monedaDestino, monto);
                    JOptionPane.showMessageDialog(ventana, "Resultado: " + resultado + " " + monedaDestino);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(ventana, "Por favor, ingrese un monto válido.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Acción del botón para mostrar tasas de cambio
        mostrarTasasButton.addActionListener(e -> {
            StringBuilder sb = new StringBuilder("Tasas de Cambio:\n");
            for (Map.Entry<String, Double> entry : tasasDeCambio.entrySet()) {
                sb.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
            }
            JOptionPane.showMessageDialog(ventana, sb.toString(), "Tasas de Cambio", JOptionPane.INFORMATION_MESSAGE);
        });

        // Mostrar la ventana
        ventana.setVisible(true);
    }

    public String[] obtenerMonedasSoportadas() {
        return tasasDeCambio.keySet().toArray(new String[0]);
    }

    public double convertir(String monedaOrigen, String monedaDestino, double monto) {
        if (!monedaOrigen.equals("USD")) {
            throw new IllegalArgumentException("Solo se permite la conversión desde USD.");
        }
        double tasaDestino = tasasDeCambio.get(monedaDestino);
        return monto * tasaDestino;
    }

    public static void main(String[] args) {
        new Convertidor();
    }
}

