package org.uniquindio.proyectofinalcodigo.model;

import java.util.List;

public class Zona {
    private String id;
    private String nombre;
    private String ubicacion;
    private int visitantesMax;
    private List<Operador> listOperadores;
    private List<Atraccion> listAtracciones;

    public Zona(String id, String nombre, String ubicacion, int visitantesMax) {
        this.id = id;
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.visitantesMax = visitantesMax;
        this.listAtracciones = listAtracciones;
        this.listOperadores=listOperadores;
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

    public List<Operador> getListOperadores() {
        return listOperadores;
    }

    public void setListOperadores(List<Operador> listOperadores) {
        this.listOperadores = listOperadores;
    }

    public List<Atraccion> getListAtracciones() {
        return listAtracciones;
    }

    public void setListAtracciones(List<Atraccion> listAtracciones) {
        this.listAtracciones = listAtracciones;
    }
}
