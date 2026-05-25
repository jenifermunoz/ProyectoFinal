package org.uniquindio.proyectofinalcodigo.model;

import java.util.List;

public class Zona {
    private String id;
    private String nombre;
    private String ubicacion;
    private int visitantesMax;
    private List<Operador> listaOperadores;
    private List<Atraccion> listaAtracciones;

    public Zona(String id, String nombre, String ubicacion, int visitantesMax) {
        this.id = id;
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.visitantesMax = visitantesMax;
        this.listaAtracciones = listaAtracciones;
        this.listaOperadores=listaOperadores;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public int getVisitantesMax() {
        return visitantesMax;
    }

    public void setVisitantesMax(int visitantesMax) {
        this.visitantesMax = visitantesMax;
    }

    public List<Operador> getListaOperadores() {
        return listaOperadores;
    }

    public void setListaOperadores(List<Operador> listaOperadores) {
        this.listaOperadores = listaOperadores;
    }

    public List<Atraccion> getListaAtracciones() {
        return listaAtracciones;
    }

    public void setListaAtracciones(List<Atraccion> listaAtracciones) {
        this.listaAtracciones = listaAtracciones;
    }
}
