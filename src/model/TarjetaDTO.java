package model;

public class TarjetaDTO {
    private String numTarjeta;
    private double saldo;
    private String tipoTarjeta;
    private double limiteCredito;
    private double comisionesAcumuladas;

    public TarjetaDTO(String numTarjeta, double saldo, String tipoTarjeta, double limiteCredito, double comisionesAcumuladas) {
        this.numTarjeta = numTarjeta;
        this.saldo = saldo;
        this.tipoTarjeta = tipoTarjeta;
        this.limiteCredito = limiteCredito;
        this.comisionesAcumuladas = comisionesAcumuladas;
    }

    public double getLimiteCredito() { return limiteCredito; }
    public double getComisionesAcumuladas() { return comisionesAcumuladas; }

    public String getNumTarjeta() { return numTarjeta; }
    public double getSaldo() { return saldo; }
    public String getTipoTarjeta() { return tipoTarjeta; }
    @Override
    public String toString() {
        return String.format("Tarjeta %s (%s) - Saldo: $%,.2f", tipoTarjeta, numTarjeta.substring(numTarjeta.length() - 4), saldo);
    }
}