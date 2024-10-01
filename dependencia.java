package drr.aluradesafio.conversormonedas.dominio;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Convertidor {
    private static final String API_KEY = "YOUR-API-KEY"; // Reemplaza con tu API Key
    private static final String API_URL = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/latest/USD";

    private Map<String, Double> tasasDeCambio;

    public Convertidor() {
        tasasDeCambio = new HashMap<>();
        cargarTasasDeCambio();
    }

    private void cargarTasasDeCambio() {
        try {
            // Hacer la petición HTTP a la API
            URL url = new URL(API_URL);
            HttpURLConnection request = (HttpURLConnection) url.openConnection();
            request.connect();

            // Convertir la respuesta en JSON
            JsonParser jp = new JsonParser();
            JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
            JsonObject jsonobj = root.getAsJsonObject();

            // Obtener las tasas de cambio del objeto JSON
            JsonObject conversionRates = jsonobj.getAsJsonObject("conversion_rates");
            for (String currency : conversionRates.keySet()) {
                tasasDeCambio.put(currency, conversionRates.get(currency).getAsDouble());
            }

        } catch (Exception e) {
            System.out.println("Error al cargar las tasas de cambio: " + e.getMessage());
        }
    }

    public String[] obtenerMonedasSoportadas() {
        return tasasDeCambio.keySet().toArray(new String[0]);
    }

    public double convertir(String monedaOrigen, String monedaDestino, double monto) {
        if (tasasDeCambio.containsKey(monedaDestino)) {
            double tasaDestino = tasasDeCambio.get(monedaDestino);
            return monto * tasaDestino;
        } else {
            throw new IllegalArgumentException("Moneda no soportada");
        }
    }
}


