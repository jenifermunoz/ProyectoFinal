package org.uniquindio.proyectofinalcodigo.model;

import java.time.LocalDate;
import java.util.List;

public record ReporteGeneral(LocalDate dia, double ingresosDiarios, List<Atraccion> atraccionesMasVisitadas, double tiemposPromediosDeEspera, int cierresPorClima, int alertasMantenimiento, List<Atraccion> atraccionesMayoresIncidentes){

    public String toString(){
        return "Reporte General [ Dia = " + dia + ", Ingresos Diarios del parque = " + ingresosDiarios + ", Atraccciones mas Visitadas del parque = " + atraccionesMasVisitadas.get(atraccionesMasVisitadas.size()-1).getNombre() + " , " +  atraccionesMasVisitadas.get(atraccionesMasVisitadas.size()-2).getNombre() + " , " + atraccionesMasVisitadas.get(atraccionesMasVisitadas.size()-3).getNombre() + ", Tiempos Promedio de espera en atracciones: " + tiemposPromediosDeEspera + " Minutos" + ", Cierres por clima en el parque: " + cierresPorClima + ", Alertas y mantenimientos en el parque: " + alertasMantenimiento + ", Atracciones con la mayor cantidad de Incidentes: " + atraccionesMayoresIncidentes.get(atraccionesMayoresIncidentes.size()-1).getNombre() + " , "+ atraccionesMayoresIncidentes.get(atraccionesMayoresIncidentes.size()-2).getNombre() +  " , " + atraccionesMayoresIncidentes.get(atraccionesMayoresIncidentes.size()-3).getNombre() + " ]";
    }
    
}
