package org.uniquindio.proyectofinalcodigo.model;

import java.util.List;

public record ReporteGeneral(double ingresosDiarios, List<Atraccion> atracccionesMasVisitadas, double tiemposPromediosDeEspera, int cierresPorClima, int alertasMantenimiento, List<Atraccion> atraccionesMayoresIncidentes){

}
