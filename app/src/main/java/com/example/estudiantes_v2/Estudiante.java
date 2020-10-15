package com.example.estudiantes_v2;

        import java.io.Serializable;

public class Estudiante implements Serializable {
    private String  CUI;
    private String nombre;
    private boolean sex;

    public Estudiante(String nombre, String cui, boolean sex) {
        this.CUI = CUI;
        this.nombre = nombre;
        this.sex = sex;
    }

    public String getCUI() {
        return CUI;
    }

    public String getNombre() {
        return nombre;
    }

    public boolean isSex() {
        return sex;
    }

    public void setCUI(String CUI) {
        this.CUI = CUI;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }
}
