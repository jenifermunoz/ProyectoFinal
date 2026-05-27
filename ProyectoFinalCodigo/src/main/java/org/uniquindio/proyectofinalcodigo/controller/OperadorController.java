package org.uniquindio.proyectofinalcodigo.controller;

import org.uniquindio.proyectofinalcodigo.model.*;
import java.util.List;

public class OperadorController {

    private Parque theParque;
    private Operador operadorActivo;

    public OperadorController(Parque theParque, Operador operadorActivo) {
        this.theParque = theParque;
        this.operadorActivo = operadorActivo;
    }

    public boolean validarAcceso(Visitante visitante, Atraccion atraccion) {
        return operadorActivo.validarAccesoCola(visitante, atraccion);
    }

    public boolean permitirAcceso(Visitante visitante, Atraccion atraccion) {
        return operadorActivo.permitirAccesoAtraccion(visitante, atraccion, "");
    }

    public boolean retirarVisitantes(Atraccion atraccion) {
        return operadorActivo.retirarVisitantesAtraccion(atraccion);
    }

    public void cambiarCapacidad(int capacidad, Atraccion atraccion) {
        operadorActivo.cambiarCapacidadAtraccion(capacidad, atraccion);
    }

    public boolean registrarEstado(Atraccion atraccion, int estado, String descripcion) {
        return operadorActivo.registrarEstadoAtraccion(atraccion, estado, descripcion);
    }

    public boolean registrarRevision(Atraccion atraccion, String descripcion) {
        return operadorActivo.registrarRevisionTecnica(atraccion, descripcion);
    }

    public boolean finalizarRevision(Atraccion atraccion, String descripcion) {
        return operadorActivo.finalizarRevisionTecnica(atraccion, descripcion);
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
