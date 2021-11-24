package Beans;

public class BContinente {

    private int idContinente;
    private String nombreC;

    public BContinente(int idContinente, String nombreC) {
        this.idContinente = idContinente;
        this.nombreC = nombreC;
    }

    public int getIdContinente() {
        return idContinente;
    }

    public void setIdContinente(int idContinente) {
        this.idContinente = idContinente;
    }

    public String getNombreC() {
        return nombreC;
    }

    public void setNombreC(String nombreC) {
        this.nombreC = nombreC;
    }
}
