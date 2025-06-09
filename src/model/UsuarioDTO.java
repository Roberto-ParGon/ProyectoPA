package model;

public class UsuarioDTO {
    private final int idCliente;
    private String nombreCompleto;
    private double saldo;

    public UsuarioDTO(int idCliente, String nombreCompleto, double saldo) {
        this.idCliente = idCliente;
        this.nombreCompleto = nombreCompleto;
        this.saldo = saldo;
    }

    public int getIdCliente() { return idCliente; }
    public String getNombreCompleto() { return nombreCompleto; }
    public double getSaldo() { return saldo; }


    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }
}