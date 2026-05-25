package org.uniquindio.proyectofinalcodigo.model;
import java.util.ArrayList;
import java.util.List;

public class ColaVirtual {
    private Atraccion atraccion;
    private List<Visitante> listVisitantesGeneralFamiliar;
    private List<Visitante> listVisitantesFastPass;
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

    public List<Visitante> getListVisitantesGeneralFamiliar() {
        return listVisitantesGeneralFamiliar;
    }

    public void setListVisitantesGeneralFamiliar(List<Visitante> listVisitantesGeneralFamiliar) {
        this.listVisitantesGeneralFamiliar = listVisitantesGeneralFamiliar;
    }

    public List<Visitante> getListVisitantesFastPass() {
        return listVisitantesFastPass;
    }

    public void setListVisitantesFastPass(List<Visitante> listVisitantesFastPass) {
        this.listVisitantesFastPass = listVisitantesFastPass;
    }

    public int getTiempoEspera() {
        return tiempoEspera;
    }

    public void setTiempoEspera(int tiempoEspera) {
        this.tiempoEspera = tiempoEspera;
    }
}
