package Beans;

import java.lang.reflect.Constructor;

public class BAlumno extends BParticipante {

    private String codigo;
    private float promedio;
    private boolean condicion;
    private BUniversidad universidad;

    public BAlumno(){}

    public BAlumno(String codigo, float promedio, boolean condicion, BUniversidad universidad) {
        this.codigo = codigo;
        this.promedio = promedio;
        this.condicion = condicion;
        this.universidad = universidad;
    }


    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public float getPromedio() {
        return promedio;
    }

    public void setPromedio(float promedio) {
        this.promedio = promedio;
    }

    public boolean isCondicion() {
        return condicion;
    }

    public void setCondicion(boolean condicion) {
        this.condicion = condicion;
    }

    public BUniversidad getUniversidad() {
        return universidad;
    }

    public void setUniversidad(BUniversidad universidad) {
        this.universidad = universidad;
    }
}
