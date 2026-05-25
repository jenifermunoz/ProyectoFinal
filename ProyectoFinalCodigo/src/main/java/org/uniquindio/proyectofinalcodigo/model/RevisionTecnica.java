package org.uniquindio.proyectofinalcodigo.model;
import java.time.LocalDate;

public record RevisionTecnica(String estado, Atraccion atraccion, LocalDate fechaInicio, String descripcion, LocalDate fechaFinalizacion){

}
