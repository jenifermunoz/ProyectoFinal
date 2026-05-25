package org.uniquindio.proyectofinalcodigo.model;

public class Parque {
    private String nombre;
    private String direccion;
    private String clima;
    private String nit;
    private String paginaWeb;
    private String mapa;
    private int capacidadMax;

    public Parque(String nombre, String direccion, String clima, String nit, String paginaWeb,String mapa, int capacidadMax){

        this.nombre=nombre;
        this.direccion=direccion;
        this.clima=clima;
        this.nit=nit;
        this.paginaWeb=paginaWeb;
        this.mapa=mapa;
        this.capacidadMax=capacidadMax;
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
}
