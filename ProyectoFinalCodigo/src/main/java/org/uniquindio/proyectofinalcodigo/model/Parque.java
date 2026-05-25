package org.uniquindio.proyectofinalcodigo.model;

import java.util.ArrayList;

public class Parque {
    private String nombre;
    private String direccion;
    private String clima;
    private String nit;
    private String paginaWeb;
    private String mapa;
    private int capacidadMax;
    private int numCierresClima;
    private boolean abierto;
    private AlertaClima alertaClima;
    public ArrayList<Persona> listPersonas;
    public ArrayList<Zona> listZonas;
    public ArrayList<Atraccion> listAtracciones;

    public Parque(String nombre, String direccion, String clima, String nit, String paginaWeb,String mapa, int capacidadMax, int numCierresClima, boolean abierto, AlertaClima alertaClima){

        this.nombre=nombre;
        this.direccion=direccion;
        this.clima=clima;
        this.nit=nit;
        this.paginaWeb=paginaWeb;
        this.mapa=mapa;
        this.capacidadMax=capacidadMax;
        this.numCierresClima=numCierresClima;
        this.abierto=abierto;
        this.alertaClima=alertaClima;
        this.listPersonas = new ArrayList<>();
        this.listZonas = new ArrayList<>();
        this.listAtracciones = new ArrayList<>();

    }

    // Filtrar Visitantes de una lista de Personas

    public ArrayList<Visitante> getListVisitantes(ArrayList<Persona> listPersonas){
        ArrayList<Visitante> listaVisitantes = new ArrayList<>();
        for(int i = 0; i<listPersonas.size(); i++){
	        if(listPersonas.get(i) instanceof Visitante){
		        listaVisitantes.add((Visitante) listPersonas.get(i));
	        }
        }
        return listaVisitantes;
    }

    // Filtrar Operadores de una lista de Personas

    public ArrayList<Operador> getListOperadores(ArrayList<Persona> listPersonas){
        ArrayList<Operador> listaOperadores = new ArrayList<>();
        for(int i = 0; i<listPersonas.size(); i++){
	        if(listPersonas.get(i) instanceof Operador){
		        listaOperadores.add((Operador) listPersonas.get(i));
	        }
        }
        return listaOperadores;
    }

    // Filtrar Administradores de una lista de Personas

    public ArrayList<Administrador> getListAdministradores(ArrayList<Persona> listPersonas){
        ArrayList<Administrador> listaAdministradores = new ArrayList<>();
        for(int i = 0; i<listPersonas.size(); i++){
	        if(listPersonas.get(i) instanceof Administrador){
		        listaAdministradores.add((Administrador) listPersonas.get(i));
	        }
        }
        return listaAdministradores;
    }

    // Obtener todas las entradas existentes de los Visitantes 

    public ArrayList<Entrada> getListEntradas(ArrayList<Persona> listPersonas){
        ArrayList<Entrada> listEntradas = new ArrayList<>();
        ArrayList<Visitante> listVisitantes = getListVisitantes(listPersonas);
        for(int i = 0; i<listVisitantes.size(); i++){
	        for(int j = 0; i<listVisitantes.get(i).getListEntradas().size(); j++){
		        listEntradas.add(listVisitantes.get(i).getListEntradas().get(j));
            }
        }
        return listEntradas;
    }

    //Buscar Visitante con cedula

    public int buscarVisitantebyCedula(String cedula){
	    for(int i=0; i<getListVisitantes(getListPersonas()).size() ;i++){
		    if(getListVisitantes(getListPersonas()).get(i).getCedula().equals(cedula)){
			    return i;
		    }
	    }
        return -1;
    }

    //Buscar Operador con cedula

    public int buscarOperadorByCedula(String cedula){
	    for(int i=0; i<getListOperadores(getListPersonas()).size() ;i++){
		    if(getListOperadores(getListPersonas()).get(i).getCedula().equals(cedula)){
			    return i;
		    }
	    }
        return -1;
    }

    //Buscar Administrador con cedula

    public int buscarAdministradorByCedula(String cedula){
	    for(int i=0; i<getListAdministradores(getListPersonas()).size() ;i++){
		    if(getListAdministradores(getListPersonas()).get(i).getCedula().equals(cedula)){
			    return i;
		    }
	    }
        return -1;
    }

    //Buscar Atraccion con nombre

    public int buscarAtraccionByNombre(String nombre){
	    for(int i=0; i<getListZonas().size(); i++){
		    for(int j=0; j<getListZonas().get(i).getListAtracciones().size(); j++){
			    if(getListZonas().get(i).getListAtracciones().get(j).getNombre().equalsIgnoreCase(nombre)){
			        return j;
			    }
		    }
    	}
        return -1;
    }

    //Buscar Zona con nombre

    public int buscarZonaByNombre(String nombre){
	    for(int i=0;i<listZonas.size();i++){
	    	if(listZonas.get(i).getNombre() == nombre){
		    	return i;
		    }
	    }
        return -1;
    }

    //Buscar Zona con nombre de una Atraccion

    public int buscarZonaByNombreAtraccion(String nombre){
    	for(int i=0; i<getListZonas().size(); i++){
	    	for(int j=0; j<getListZonas().get(i).getListAtracciones().size(); j++){
	    		if(getListZonas().get(i).getListAtracciones().get(j).getNombre().equalsIgnoreCase(nombre)){
		    	    return i;
	    		}
		    }
	    }
        return -1;
    }   

        
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getClima() {
        return clima;
    }

    public void setClima(String clima) {
        this.clima = clima;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getPaginaWeb() {
        return paginaWeb;
    }

    public void setPaginaWeb(String paginaWeb) {
        this.paginaWeb = paginaWeb;
    }

    public String getMapa() {
        return mapa;
    }

    public void setMapa(String mapa) {
        this.mapa = mapa;
    }

    public int getCapacidadMax() {
        return capacidadMax;
    }

    public void setCapacidadMax(int capacidadMax) {
        this.capacidadMax = capacidadMax;
    }
    
    public int getNumCierresClima() {
        return numCierresClima;
    }

    public void setNumCierresClima(int numCierresClima) {
        this.numCierresClima = numCierresClima;
    }

    public boolean isAbierto() {
        return abierto;
    }

    public void setAbierto(boolean abierto) {
        this.abierto = abierto;
    }
    
    public AlertaClima getAlertaClima() {
        return alertaClima;
    }

    public void setAlertaClima(AlertaClima alertaClima) {
        this.alertaClima = alertaClima;
    }

    public ArrayList<Persona> getListPersonas() {
        return listPersonas;
    }

    public void setListPersonas(ArrayList<Persona> listPersonas) {
        this.listPersonas = listPersonas;
    }

    public ArrayList<Zona> getListZonas() {
        return listZonas;
    }

    public void setListZonas(ArrayList<Zona> listZonas) {
        this.listZonas = listZonas;
    }

    public ArrayList<Atraccion> getListAtracciones() {
        return listAtracciones;
    }

    public void setListAtracciones(ArrayList<Atraccion> listAtracciones) {
        this.listAtracciones = listAtracciones;
    }

}
