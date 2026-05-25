package org.uniquindio.proyectofinalcodigo.model;
import java.util.ArrayList;
import java.util.List;

public class ColaVirtual {
    private Atraccion atraccion;
    private List<Visitante> listaVisitantesGeneralFamiliar;
    private List<Visitante> listaVisitantesFastPass;
    private int tiempoEspera;

    public ColaVirtual(Atraccion atraccion, int tiempoEspera) {
        this.atraccion = atraccion;
        this.tiempoEspera = tiempoEspera;
        this.listaVisitantesGeneralFamiliar = new ArrayList<>();
        this.listaVisitantesFastPass = new ArrayList<>();
    }

    public Atraccion getAtraccion() {
        return atraccion;
    }

    public void setAtraccion(Atraccion atraccion) {
        this.atraccion = atraccion;
    }

    public List<Visitante> getListaVisitantesGeneralFamiliar() {
        return listaVisitantesGeneralFamiliar;
    }

    public void setListaVisitantesGeneralFamiliar(List<Visitante> listaVisitantesGeneralFamiliar) {
        this.listaVisitantesGeneralFamiliar = listaVisitantesGeneralFamiliar;
    }

    public List<Visitante> getListaVisitantesFastPass() {
        return listaVisitantesFastPass;
    }

    public void setListaVisitantesFastPass(List<Visitante> listaVisitantesFastPass) {
        this.listaVisitantesFastPass = listaVisitantesFastPass;
    }

    public int getTiempoEspera() {
        return tiempoEspera;
    }

    public void setTiempoEspera(int tiempoEspera) {
        this.tiempoEspera = tiempoEspera;
    }
}
