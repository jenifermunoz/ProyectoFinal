package org.uniquindio.proyectofinalcodigo.model;

public class Atraccion {
    private String id;
    private String nombre;
    private int capacidadMaxima;
    private double alturaMinimaRequerida;
    private int edadMinimaRequerida;
    private int contadorVisitantes;
    private double costoAdicional;
    private int tiempoEstimadoEspera;
    private TipoAtraccion tipo;
    private MotivoCierre motivoCierre;
    private EstadoAtraccion estado;

    public Atraccion(String id,String nombre,int capacidadMaxima,double alturaMinimaRequerida,int edadMinimaRequerida,int contadorVisitantes,double costoAdicional, int tiempoEstimadoEspera, TipoAtraccion tipo, MotivoCierre motivoCierre, EstadoAtraccion estado){
        this. id=id;
        this.nombre=nombre;
        this.capacidadMaxima=capacidadMaxima;
        this.alturaMinimaRequerida=alturaMinimaRequerida;
        this.edadMinimaRequerida=edadMinimaRequerida;
        this.contadorVisitantes=contadorVisitantes;
        this.costoAdicional=costoAdicional;
        this.tiempoEstimadoEspera=tiempoEstimadoEspera;
        this.tipo=tipo;
        this.motivoCierre=motivoCierre;
        this.estado=estado;
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

    public TipoAtraccion getTipo() {
        return tipo;
    }

    public void setTipo(TipoAtraccion tipo) {
        this.tipo = tipo;
    }

    public MotivoCierre getMotivoCierre() {
        return motivoCierre;
    }

    public void setMotivo(MotivoCierre motivoCierre) {
        this.motivoCierre = motivoCierre;
    }

    public EstadoAtraccion getEstado() {
        return estado;
    }

    public void setEstado(EstadoAtraccion estado) {
        this.estado = estado;
    }
}
