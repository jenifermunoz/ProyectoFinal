package org.uniquindio.proyectofinalcodigo.model;

import java.util.ArrayList;

public class Atraccion {
    private String id;
    private String nombre;
    private int capacidadMaxima;
    private double alturaMinimaRequerida;
    private int edadMinimaRequerida;
    private int contadorVisitantes;
    private double costoAdicional;
    private int tiempoEstimadoEspera;
    private int numMantenimientos;
    private int ciclosDiarios;
    private TipoAtraccion tipo;
    private MotivoCierre motivoCierre;
    private EstadoAtraccion estado;
    private Zona theZona;
    private ColaVirtual theColaVirtual;
    private ArrayList<RevisionTecnica> listRevisionesTecnicas;
    private ArrayList<Visitante> listVisitantes;
    private ArrayList<Operador> listOperadores;

    public Atraccion(String id,String nombre,int capacidadMaxima,double alturaMinimaRequerida,int edadMinimaRequerida,int contadorVisitantes, double costoAdicional, int tiempoEstimadoEspera, int numMantenimientos, int ciclosDiarios, TipoAtraccion tipo, MotivoCierre motivoCierre, EstadoAtraccion estado, Zona theZona, ColaVirtual theColaVirtual){
        this. id=id;
        this.nombre=nombre;
        this.capacidadMaxima=capacidadMaxima;
        this.alturaMinimaRequerida=alturaMinimaRequerida;
        this.edadMinimaRequerida=edadMinimaRequerida;
        this.contadorVisitantes=contadorVisitantes;
        this.costoAdicional=costoAdicional;
        this.tiempoEstimadoEspera=tiempoEstimadoEspera;
        this.numMantenimientos=numMantenimientos;
        this.ciclosDiarios=ciclosDiarios;
        this.tipo=tipo;
        this.motivoCierre=motivoCierre;
        this.estado=estado;
        this.theZona = theZona;
        this.theColaVirtual=theColaVirtual;
        this.listRevisionesTecnicas = new ArrayList<>();
        this.listVisitantes = new ArrayList<>();
        this.listOperadores = new ArrayList<>();
    }

    //Metodos

    // Ingresar Visitantes de la Atraccion

    public void ingresarVisitante(Visitante visitante){
	    getListVisitantes().add(visitante);
    }

    // Sacar Visitantes de la Atraccion

    public void sacarVisitantes(){
	   getListVisitantes().clear();
    }

    // Procesar solicitud de entrada de un Visitante a la Atraccion

    public boolean procesarSolicitudCola(Visitante visitante, Atraccion atraccion){
        Operador operador = listOperadores.get(0);
        if(operador.validarAccesoCola(visitante, this)){
            operador.permitirAccesoAtraccion(visitante, atraccion, null);
            return true;
        }
        return false;
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

    public int getCapacidadMaxima() {
        return capacidadMaxima;
    }

    public void setCapacidadMaxima(int capacidadMaxima) {
        this.capacidadMaxima = capacidadMaxima;
    }

    public double getAlturaMinimaRequerida() {
        return alturaMinimaRequerida;
    }

    public void setAlturaMinimaRequerida(double alturaMinimaRequerida) {
        this.alturaMinimaRequerida = alturaMinimaRequerida;
    }

    public int getEdadMinimaRequerida() {
        return edadMinimaRequerida;
    }

    public void setEdadMinimaRequerida(int edadMinimaRequerida) {
        this.edadMinimaRequerida = edadMinimaRequerida;
    }

    public int getContadorVisitantes() {
        return contadorVisitantes;
    }

    public void setContadorVisitantes(int contadorVisitantes) {
        this.contadorVisitantes = contadorVisitantes;
    }

    public double getCostoAdicional() {
        return costoAdicional;
    }

    public void setCostoAdicional(double costoAdicional) {
        this.costoAdicional = costoAdicional;
    }

    public int getTiempoEstimadoEspera() {
        return tiempoEstimadoEspera;
    }

    public void setTiempoEstimadoEspera(int tiempoEstimadoEspera) {
        this.tiempoEstimadoEspera = tiempoEstimadoEspera;
    }

    public int getNumMantenimientos() {
        return numMantenimientos;
    }

    public void setNumMantenimientos(int numMantenimientos) {
        this.numMantenimientos = numMantenimientos;
    }

    public int getCiclosDiarios() {
        return ciclosDiarios;
    }

    public void setCiclosDiarios(int ciclosDiarios) {
        this.ciclosDiarios = ciclosDiarios;
    }

    public TipoAtraccion getTipo() {
        return tipo;
    }

    public void setTipo(TipoAtraccion tipo) {
        this.tipo = tipo;
    }

    public MotivoCierre getMotivoCierre() {
        return motivoCierre;
    }

    public void setMotivoCierre(MotivoCierre motivoCierre) {
        this.motivoCierre = motivoCierre;
    }

    public EstadoAtraccion getEstado() {
        return estado;
    }

    public void setEstado(EstadoAtraccion estado) {
        this.estado = estado;
    }

    public Zona getTheZona() {
        return theZona;
    }

    public void setTheZona(Zona theZona) {
        this.theZona = theZona;
    }

    public ColaVirtual getTheColaVirtual() {
        return theColaVirtual;
    }

    public void setTheColaVirtual(ColaVirtual theColaVirtual) {
        this.theColaVirtual = theColaVirtual;
    }

    public ArrayList<RevisionTecnica> getListRevisionesTecnicas() {
        return listRevisionesTecnicas;
    }

    public void setListRevisionesTecnicas(ArrayList<RevisionTecnica> listRevisionesTecnicas) {
        this.listRevisionesTecnicas = listRevisionesTecnicas;
    }

    public ArrayList<Visitante> getListVisitantes() {
        return listVisitantes;
    }

    public void setListVisitantes(ArrayList<Visitante> listVisitantes) {
        this.listVisitantes = listVisitantes;
    }

    public ArrayList<Operador> getListOperadores() {
        return listOperadores;
    }

    public void setListOperadores(ArrayList<Operador> listOperadores) {
        this.listOperadores = listOperadores;
    }


}
