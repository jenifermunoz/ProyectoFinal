package org.uniquindio.proyectofinalcodigo.controller;

import org.uniquindio.proyectofinalcodigo.model.*;

import java.util.ArrayList;
import java.util.List;

public class OperadorController {

    private Parque theParque;
    private Operador operadorActivo;

    public OperadorController(Parque theParque, Operador operadorActivo) {
        this.theParque = theParque;
        this.operadorActivo = operadorActivo;
    }

    // Pestaña de Gestion

    public boolean validarAcceso(Visitante visitante, Atraccion atraccion) {
        return operadorActivo.validarAccesoCola(visitante, atraccion);
    }

    public boolean permitirAcceso(Atraccion atraccion) {
        return operadorActivo.permitirAccesoAtraccion(atraccion, "");
    }

    public boolean retirarVisitantes(Atraccion atraccion) {
        return operadorActivo.retirarVisitantesAtraccion(atraccion);
    }

    public void cambiarCapacidad(int capacidad, Atraccion atraccion) {
        operadorActivo.cambiarCapacidadAtraccion(capacidad, atraccion);
    }

    public ArrayList<Visitante> mostrarColaVirtualGF(Atraccion atraccion){
        return operadorActivo.mostrarColaVirtualGF(atraccion);
    }

    public ArrayList<Visitante> mostrarColaVirtualFF(Atraccion atraccion){
        return operadorActivo.mostrarColaVirtualFF(atraccion);
    }

    public ArrayList<Visitante> mostrarVisitantesAtraccion(Atraccion atraccion){
        return operadorActivo.mostrarVisitantesAtraccion(atraccion);
    }

    // Pestaña de Estado

    public boolean registrarEstado(Atraccion atraccion, int estado, String descripcion) {
        return operadorActivo.registrarEstadoAtraccion(atraccion, estado, descripcion);
    }

    public boolean registrarRevision(Atraccion atraccion, String descripcion) {
        return operadorActivo.registrarRevisionTecnica(atraccion, descripcion);
    }

    public boolean finalizarRevision(Atraccion atraccion, String descripcion) {
        return operadorActivo.finalizarRevisionTecnica(atraccion, descripcion);
    }

    public String mostrarRevisionesTecnicas(){
        return operadorActivo.mostrarRevisionesTecnicas(theParque);
    }

    // Devuelve solo las atracciones de la zona del operador

    public List<Atraccion> getAtraccionesDeZona() {
        if (operadorActivo.getTheZona() != null) {
            return operadorActivo.getTheZona().getListAtracciones();
        }
        return theParque.getListAtracciones();
    }

    public Operador getOperadorActivo() {
        return operadorActivo;
    }

    public Parque getParque() {
        return theParque;
    }
}
