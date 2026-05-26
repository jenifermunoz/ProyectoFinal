package org.uniquindio.proyectofinalcodigo.model;
import java.time.LocalDate;

public class Entrada {
    private String id;
    private LocalDate fechaCompra;
    private LocalDate fechaVigencia;
    private boolean activa;
    private TipoEntrada tipoEntrada;

    public Entrada(String id, LocalDate fechaCompra, LocalDate fechaVigencia, boolean activa, TipoEntrada tipoEntrada){
        this.id=id;
        this.fechaCompra=fechaCompra;
        this.fechaVigencia=fechaVigencia;
        this.activa=activa;
        this.tipoEntrada = tipoEntrada;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(LocalDate fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public LocalDate getFechaVigencia() {
        return fechaVigencia;
    }

    public void setFechaVigencia(LocalDate fechaVigencia) {
        this.fechaVigencia = fechaVigencia;
    }

    public boolean isActiva() {
        return activa;
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
    }

    public TipoEntrada getTipoEntrada() {
        return tipoEntrada;
    }

    public void setTipoEntrada(TipoEntrada tipoEntrada) {
        this.tipoEntrada = tipoEntrada;
    }
}
