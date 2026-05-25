package org.uniquindio.proyectofinalcodigo.model;

public class NotificacionAsignada {
    private Notificacion theNotificacion;
    private Visitante theVisitante;

    public NotificacionAsignada(Notificacion theNotificacion, Visitante theVisitante) {
        this.theNotificacion = theNotificacion;
        this.theVisitante = theVisitante;
    }

    public Notificacion getTheNotificacion() {
        return theNotificacion;
    }

    public void setTheNotificacion(Notificacion theNotificacion) {
        this.theNotificacion = theNotificacion;
    }

    public Visitante getTheVisitante() {
        return theVisitante;
    }

    public void setTheVisitante(Visitante theVisitante) {
        this.theVisitante = theVisitante;
    }
}
