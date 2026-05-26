package org.uniquindio.proyectofinalcodigo.model;
import java.time.LocalDate;

public record RevisionTecnica(String estado, Operador operador, Atraccion atraccion, LocalDate fechaInicio, String descripcion, LocalDate fechaFinalizacion){

}


