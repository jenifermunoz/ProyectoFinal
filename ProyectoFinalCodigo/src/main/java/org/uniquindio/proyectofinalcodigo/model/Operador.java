package org.uniquindio.proyectofinalcodigo.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Operador extends Persona {
    private int aniosExperiencia;
    private ArrayList<Object> zonaAsignada;
    private List<RevisionTecnica> listaRevisionesTecnicas;

    public Operador(int aniosExperiencia, String cedula, String nombre, LocalDate fechaNacimiento, double estatura, String numeroTelefono, String nacionalidad) {
        super(cedula, nombre, fechaNacimiento, estatura, numeroTelefono, nacionalidad);
        this.aniosExperiencia = aniosExperiencia;
        this.zonaAsignada = new ArrayList<>();
        this.listaRevisionesTecnicas = new ArrayList<>();
    }

    public int getAniosExperiencia() {
        return aniosExperiencia;
    }

    public void setAniosExperiencia(int aniosExperiencia) {
        this.aniosExperiencia = aniosExperiencia;
    }

    public ArrayList<Object> getZonaAsignada() {
        return zonaAsignada;
    }

    public void setZonaAsignada(ArrayList<Object> zonaAsignada) {
        this.zonaAsignada = zonaAsignada;
    }

    public List<RevisionTecnica> getListaRevisionesTecnicas() {
        return listaRevisionesTecnicas;
    }

    public void setListaRevisionesTecnicas(List<RevisionTecnica> listaRevisionesTecnicas) {
        this.listaRevisionesTecnicas = listaRevisionesTecnicas;
    }
}
