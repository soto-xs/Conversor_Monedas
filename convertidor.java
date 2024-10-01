package drr.aluradesafio.conversormonedas;

import java.util.HashMap;
import java.util.Map;

public class Convertidor {
    private Map<String, Double> tasasDeCambio;

    public Convertidor() {
        tasasDeCambio = new HashMap<>();
        inicializarTasasDeCambio();
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
}
