package org.uniquindio.proyectofinalcodigo.model;

import java.util.ArrayList;
import java.util.List;

public class Zona implements IMostrable {
    private String id;
    private String nombre;
    private String ubicacion;
    private int visitantesMax;
    private Parque theParque;
    private ArrayList<Operador> listOperadores;
    private ArrayList<Atraccion> listAtracciones;

    public Zona(String id, String nombre, Parque theParque, String ubicacion, int visitantesMax) {
        this.id = id;
        this.nombre = nombre;
        this.theParque = theParque;
        this.ubicacion = ubicacion;
        this.visitantesMax = visitantesMax;
        this.listAtracciones = new ArrayList<>();
        this.listOperadores = new ArrayList<>();
    }

    @Override
    public String mostrarDatos() {
        return "ID: " + id + " | Nombre: " + nombre + " | Ubicacion: " + ubicacion +
               " | Visitantes Max: " + visitantesMax +
               " | Operadores: " + listOperadores.size() +
               " | Atracciones: " + listAtracciones.size();
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getUbicacion() { return ubicacion; }
    public void setUbicacion(String ubicacion) { this.ubicacion = ubicacion; }
    public int getVisitantesMax() { return visitantesMax; }
    public void setVisitantesMax(int visitantesMax) { this.visitantesMax = visitantesMax; }
    public List<Operador> getListOperadores() { return listOperadores; }
    public void setListOperadores(ArrayList<Operador> listOperadores) { this.listOperadores = listOperadores; }
    public List<Atraccion> getListAtracciones() { return listAtracciones; }
    public void setListAtracciones(ArrayList<Atraccion> listAtracciones) { this.listAtracciones = listAtracciones; }
    public Parque getTheParque() { return theParque; }
    public void setTheParque(Parque theParque) { this.theParque = theParque; }
}
