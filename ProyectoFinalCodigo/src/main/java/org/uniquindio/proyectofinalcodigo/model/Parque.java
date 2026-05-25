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
    public ArrayList<Persona> listPersonas;
    public ArrayList<Zona> listZonas;
    public ArrayList<Atraccion> listAtracciones;

    public Parque(String nombre, String direccion, String clima, String nit, String paginaWeb,String mapa, int capacidadMax){

        this.nombre=nombre;
        this.direccion=direccion;
        this.clima=clima;
        this.nit=nit;
        this.paginaWeb=paginaWeb;
        this.mapa=mapa;
        this.capacidadMax=capacidadMax;
        this.listPersonas = new ArrayList<>();
        this.listZonas = new ArrayList<>();
        this.listAtracciones = new ArrayList<>();

    }

    public ArrayList<Visitante> getListVisitantes(ArrayList<Persona> listPersonas){
        ArrayList<Visitante> listaVisitantes = new ArrayList<>();
        for(int i = 0; i<listPersonas.size(); i++){
	        if(listPersonas.get(i) instanceof Visitante){
		        listaVisitantes.add((Visitante) listPersonas.get(i));
	        }
        }
        return listaVisitantes;
    }

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

    //Buscar Atraccion con nombre

    public int buscarAtraccionByNombre(String nombre){
	    for(int i=0; i<getListZonas().size(); i++){
		    for(int j=0; j<getListZonas().get(i).getListAtracciones.size(); j++){
			    if(getListZonas().get(i).getListAtracciones.get(j).getNombre().equalsIgnoreCase(nombre)){
			        return j;
			    }
		    	return -1;
		    }
    	}
    }

    //Buscar Zona con nombre de una Atraccion

    public int buscarZonaByNombreAtraccion(String nombre){
    	for(int i=0; i<getListZonas().size(); i++){
	    	for(int j=0; j<getListZonas().get(i).getListAtracciones.size(); j++){
	    		if(getListZonas().get(i).getListAtracciones.get(j).getNombre().equalsIgnoreCase(nombre)){
		    	    return i;
	    		}
			    return -1;
		    }
	    }
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
