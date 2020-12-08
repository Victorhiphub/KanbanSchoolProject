package flujodetrabajo;

import javax.swing.*;
import java.io.Serializable;

public class Tarea implements Serializable {

    private String nombre;
    private Actividad actividad;
    private FlujoDeTrabajo flujoDeTrabajo;

    public Tarea(String nombre, Actividad actividad, FlujoDeTrabajo flujoDeTrabajo) {
        this.nombre = nombre;
        this.actividad = actividad;
        this.flujoDeTrabajo = flujoDeTrabajo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Actividad getActividad() {
        return actividad;
    }

    public void setActividad(Actividad actividad) {
        this.actividad = actividad;
    }


    @Override
    public String toString() {
        return "Tarea{" +
                "nombre='" + nombre + '\'' +
                '}';
    }
}