package Beans;

public class BPais {
 private int idPais;
 private BContinente continente;
 private String nombre;
 private int poblacion;
 private float tamanio;

    public BPais(){}

    public BPais(int idPais) {
        this.idPais = idPais;
    }

    public int getIdPais() {
        return idPais;
    }

    public void setIdPais(int idPais) {
        this.idPais = idPais;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPoblacion() {
        return poblacion;
    }

    public void setPoblacion(int poblacion) {
        this.poblacion = poblacion;
    }

    public float getTamanio() {
        return tamanio;
    }

    public void setTamanio(float tamanio) {
        this.tamanio = tamanio;
    }

    public BContinente getContinente() {
        return continente;
    }

    public void setContinente(BContinente continente) {
        this.continente = continente;
    }

}
