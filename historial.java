package drr.aluradesafio.conversormonedas;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Historial {
    private String fecha;
    private double monto;

    public Historial(String fecha, double monto) {
        this.fecha = fecha;
        this.monto = monto;
    }

    public String getFechaStringFormato() {
        SimpleDateFormat sdf = new SimpleDateFormat("01/10/2024 01:55:30");
        return sdf.format(new Date());
    }

    public String getMontoCambioFormato() {
        return String.format("%.2f USD", monto);
    }
}
