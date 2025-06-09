package model;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TransaccionDTO {
    private Timestamp fecha;
    private double monto;
    private String tipoTransaccion;

    public TransaccionDTO(Timestamp fecha, double monto, String tipoTransaccion) {
        this.fecha = fecha;
        this.monto = monto;
        this.tipoTransaccion = tipoTransaccion;
    }

    public Date getFecha() {
        return fecha;
    }

    public double getMonto() {
        return monto;
    }

    public String getTipoTransaccion() {
        return tipoTransaccion;
    }

    public String getFechaFormateada() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd 'de' MMMM 'del' yyyy");
        return sdf.format(this.fecha);
    }

    public String getMontoFormateado() {
        return String.format("$%,.2f MXN", this.monto);
    }
}