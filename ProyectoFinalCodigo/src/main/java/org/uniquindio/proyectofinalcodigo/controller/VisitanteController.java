package org.uniquindio.proyectofinalcodigo.controller;

import org.uniquindio.proyectofinalcodigo.model.*;
import java.time.LocalDate;
import java.util.List;

public class VisitanteController {

    private Parque theParque;
    private Visitante visitanteActivo;

    public VisitanteController(Parque theParque, Visitante visitanteActivo) {
        this.theParque = theParque;
        this.visitanteActivo = visitanteActivo;
    }

    public boolean actualizarPerfil(String nombre, double estatura, String telefono, String nacionalidad, int edad) {
        visitanteActivo.actualizarPerfil(nombre, estatura, telefono, nacionalidad, edad);
        return true;
    }

    public boolean comprarTicket(int tipo) {
        return visitanteActivo.comprarTickets(theParque, visitanteActivo.getCedula(), tipo);
    }

    public String mostrarListEntradas(){
        return visitanteActivo.mostrarListEntradas();
    }

    public String consultarTiempoEspera(String nombreAtraccion) {
        return visitanteActivo.consultarTiemposEspera(theParque, nombreAtraccion);
    }

    public boolean agregarFavorita(String nombreAtraccion) {
        return visitanteActivo.agregarAtraccionFavorita(theParque, nombreAtraccion);
    }

    public String verFavoritas() {
        return visitanteActivo.mostrarAtraccionesFavoritas(theParque);
    }

    public String verNotificaciones() {
        return visitanteActivo.mostrarNotificaciones(theParque);
    }

    public boolean unirseACola(Atraccion atraccion) {
        return visitanteActivo.unirseAColaVirtualAtraccion(theParque, atraccion);
    }

    public void recargarSaldo(int cantidad) {
        visitanteActivo.RecargarSaldoVirtual(cantidad);
    }

    public List<Zona> getZonas() {
        return theParque.getListZonas();
    }

    public List<Atraccion> getAtracciones() {
        return theParque.getListAtracciones();
    }

    public Visitante getVisitanteActivo() {
        return visitanteActivo;
    }

    public Parque getParque() {
        return theParque;
    }
}
