package org.uniquindio.proyectofinalcodigo.controller;

import org.uniquindio.proyectofinalcodigo.model.*;
import java.time.LocalDate;
import java.util.List;

public class AdministradorController {

    private Parque theParque;
    private Administrador adminActivo;

    public AdministradorController(Parque theParque, Administrador adminActivo) {
        this.theParque = theParque;
        this.adminActivo = adminActivo;
    }

    // Operadores
    public boolean registrarOperador(String cedula, String nombre, LocalDate fechaNac, double estatura, String tel, String nac) {
        return adminActivo.registrarOperador(theParque, cedula, nombre, fechaNac, estatura, tel, nac);
    }
    public boolean modificarOperador(String cedula, String nombre, double estatura, String tel, String nac) {
        return adminActivo.modificarOperador(theParque, cedula, nombre, estatura, tel, nac);
    }
    public boolean despedirOperador(String cedula) {
        return adminActivo.despedirOperador(theParque, cedula);
    }
    public boolean asignarOperadorAZona(Operador op, Zona zona) {
        return adminActivo.asignarOperadorAZona(op, zona);
    }
    public boolean quitarOperadorDeZona(Operador op) {
        return adminActivo.quitarOperadorDeZona(op);
    }
    public boolean asignarOperadorAAtraccion(Operador op, Atraccion atraccion) {
        return adminActivo.asignarOperadorAAtraccion(op, atraccion);
    }
    public boolean quitarOperadorDeAtraccion(Operador op, Atraccion atraccion) {
        return adminActivo.quitarOperadorDeAtraccion(op, atraccion);
    }

    // Zonas
    public boolean registrarZona(String nombre, String ubicacion, int visitantesMax) {
        return adminActivo.registrarZona(theParque, nombre, ubicacion, visitantesMax);
    }
    public boolean modificarZona(String id, String nombre, int visitantesMax) {
        return adminActivo.modificarZona(theParque, id, nombre, visitantesMax);
    }
    public boolean eliminarZona(String id) {
        return adminActivo.eliminarZona(theParque, id);
    }

    // Atracciones
    public boolean registrarAtraccion(Zona zona, String nombre, int cap, double altMin, int edadMin, double costo, TipoAtraccion tipo) {
        return adminActivo.registrarAtraccion(theParque, zona, nombre, cap, altMin, edadMin, costo, tipo, null, EstadoAtraccion.ACTIVA);
    }
    public boolean modificarAtraccion(String id, String nombre, int cap, double altMin, int edadMin, double costo) {
        return adminActivo.modificarAtraccion(theParque, id, nombre, cap, altMin, edadMin, costo);
    }
    public boolean eliminarAtraccion(String id) {
        return adminActivo.eliminarAtraccion(theParque, id);
    }

    // Clima
    public boolean activarAlerta(int tipo) {
        return adminActivo.activarAlertaClimatica(theParque, tipo);
    }
    public boolean desactivarAlerta() {
        return adminActivo.desactivarAlertaClimatica(theParque);
    }

    // Jornada y reporte
    public void terminarJornada() {
        adminActivo.terminarJornada(theParque);
    }

    public String consultarReporte() {
        return adminActivo.consultarReporteGeneral(theParque);
    }
    
    public String consultarDatos(int tipo, String dato) {
        return adminActivo.consultarDatos(theParque, tipo, dato);
    }

    public List<Zona> getZonas() { return theParque.getListZonas(); }
    public List<Atraccion> getAtracciones() { return theParque.getListAtracciones(); }
    public List<Operador> getOperadores() { return theParque.getListOperadores(theParque.getListPersonas()); }
    public Parque getParque() { return theParque; }
    public Administrador getAdminActivo() { return adminActivo; }
}
