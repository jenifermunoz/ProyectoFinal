package org.uniquindio.proyectofinalcodigo.model;

import java.time.LocalDate;
import java.util.List;

public record ReporteGeneral(LocalDate dia, double ingresosDiarios, List<Atraccion> atracccionesMasVisitadas, double tiemposPromediosDeEspera, int cierresPorClima, int alertasMantenimiento, List<Atraccion> atraccionesMayoresIncidentes){

}
