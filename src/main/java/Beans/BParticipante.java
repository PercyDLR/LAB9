package Beans;

public class BParticipante {

    private int idParticipante;
    private String nombreP;
    private String apellidP;
    private int edad;
    private String genero;
    private BPais pais;

    public BParticipante(){}

    public BParticipante(int idParticipante, String nombreP, String apellidP, int edad, String genero, BPais pais) {
        this.idParticipante = idParticipante;
        this.nombreP = nombreP;
        this.apellidP = apellidP;
        this.edad = edad;
        this.genero = genero;
        this.pais = pais;
    }

    public int getIdParticipante() {
        return idParticipante;
    }

    public void setIdParticipante(int idParticipante) {
        this.idParticipante = idParticipante;
    }

    public String getNombreP() {
        return nombreP;
    }

    public void setNombreP(String nombreP) {
        this.nombreP = nombreP;
    }

    public String getApellidP() {
        return apellidP;
    }

    public void setApellidP(String apellidP) {
        this.apellidP = apellidP;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public BPais getPais() {
        return pais;
    }

    public void setPais(BPais pais) {
        this.pais = pais;
    }
}

