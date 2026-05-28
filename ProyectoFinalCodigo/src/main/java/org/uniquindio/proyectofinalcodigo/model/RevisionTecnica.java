package org.uniquindio.proyectofinalcodigo.model;
import java.time.LocalDate;

public record RevisionTecnica(String estado, Operador operador, Atraccion atraccion, LocalDate fechaInicio, String descripcion, LocalDate fechaFinalizacion){

@Override
    public String toString(){
    return "Revision tecnica [ Operador = " + operador.getCedula() + " - "+ operador.getNombre() + ", Atraccion = " + atraccion.getId() +" - "+ atraccion.getNombre() + " - "+ atraccion.getTheZona().getNombre() + ", Estado = " + estado + ", Fecha de Inicio = " + fechaInicio + ", Descripcion = " + descripcion + ", Fecha de Finalizacion " + fechaFinalizacion + "]";
}

}



