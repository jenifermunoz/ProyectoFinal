package org.uniquindio.proyectofinalcodigo.model;
import java.util.ArrayList;

public class ColaVirtual {
    private Atraccion atraccion;
    private ArrayList<Visitante> listVisitantesGeneralFamiliar;
    private ArrayList<Visitante> listVisitantesFastPass;
    private int tiempoEspera;

    public ColaVirtual(Atraccion atraccion, int tiempoEspera) {
        this.atraccion = atraccion;
        this.tiempoEspera = tiempoEspera;
        this.listVisitantesGeneralFamiliar = new ArrayList<>();
        this.listVisitantesFastPass = new ArrayList<>();
    }

    public Atraccion getAtraccion() {
        return atraccion;
    }

    public void setAtraccion(Atraccion atraccion) {
        this.atraccion = atraccion;
    }

    public ArrayList<Visitante> getListVisitantesGeneralFamiliar() {
        return listVisitantesGeneralFamiliar;
    }

    public void setListVisitantesGeneralFamiliar(ArrayList<Visitante> listVisitantesGeneralFamiliar) {
        this.listVisitantesGeneralFamiliar = listVisitantesGeneralFamiliar;
    }

    public ArrayList<Visitante> getListVisitantesFastPass() {
        return listVisitantesFastPass;
    }

    public void setListVisitantesFastPass(ArrayList<Visitante> listVisitantesFastPass) {
        this.listVisitantesFastPass = listVisitantesFastPass;
    }

    public int getTiempoEspera() {
        return tiempoEspera;
    }

    public void setTiempoEspera(int tiempoEspera) {
        this.tiempoEspera = tiempoEspera;
    }
}
