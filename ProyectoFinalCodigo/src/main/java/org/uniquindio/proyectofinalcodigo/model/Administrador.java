package org.uniquindio.proyectofinalcodigo.model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Administrador extends Persona {

    private Parque theParque;
	private ArrayList<ReporteGeneral> listReporteGeneral;

    public Administrador(Parque theParque, String cedula, String nombre, LocalDate fechaNacimiento, double estatura, String numeroTelefono, String nacionalidad) {
        super(cedula, nombre, fechaNacimiento, estatura, numeroTelefono, nacionalidad);
        this.theParque = theParque;
		this.listReporteGeneral = new ArrayList<>();

    }

    // Metodos

    // Iniciar Sesion como Administrador

    public Administrador iniciarSesionAdministrador(Parque parque, String cedula){
	    if(parque.buscarAdministradorByCedula(cedula) != -1){
		        Administrador adminactivo = parque.getListAdministradores(parque.getListPersonas()).get(parque.buscarAdministradorByCedula(cedula));
		    return adminactivo;
        }
        return null;
    }






    // Registrar Operador

    public boolean registrarOperador(Parque parque, String cedula, String nombre, LocalDate fechaNacimiento, double estatura, String numeroTelefono, String nacionalidad){
	    if(parque.buscarOperadorByCedula(cedula) == -1){
	    	Operador nuevooperador = new Operador(0, parque, null, cedula, nombre, fechaNacimiento, estatura, numeroTelefono, nacionalidad);
			parque.getListPersonas().add(nuevooperador);
		    return true;
	    }
	    return false;
    }

    // Modificar Operador

    public boolean modificarOperador(Parque parque, String cedula, String nombre, double estatura, String numeroTelefono, String nacionalidad){
	    int i = parque.buscarOperadorByCedula(cedula); 
	    if(i != -1){ 
		    parque.getListOperadores(parque.getListPersonas()).get(i).setNombre(nombre); 
		    parque.getListOperadores(parque.getListPersonas()).get(i).setEstatura(estatura); 
		    parque.getListOperadores(parque.getListPersonas()).get(i).setNumeroTelefono(numeroTelefono); 
		    parque.getListOperadores(parque.getListPersonas()).get(i).setNacionalidad(nacionalidad); 
		    return true; 
	    } 
	    return false; 
    }

    // Despedir Operador

    public boolean despedirOperador(Parque parque, String cedula){
	    int i = parque.buscarOperadorByCedula(cedula); 
	    if(i != -1){
	    	parque.getListOperadores(parque.getListPersonas()).remove(i);
	    	return true;
	    }
	    return false;
    }

    // Registrar Zona

    public boolean registrarZona(Parque parque, String nombre, String ubicacion, int visitantesmax){
	    int i = parque.buscarZonaByNombre(nombre);
	    if(i == -1){
	    	Zona nuevazona = new Zona(Integer.toString(parque.getListZonas().size()+001), nombre, parque, ubicacion, visitantesmax);
			parque.getListZonas().add(nuevazona);
	    	return true;
	    }
	    return false;
    }

    // Modificar Zona

    public boolean modificarZona(Parque parque, String nombre, int visitantesmax){
	    int i = parque.buscarZonaByNombre(nombre);
	    if(i != -1){
		    parque.getListZonas().get(i).setNombre(nombre);
		    parque.getListZonas().get(i).setVisitantesMax(visitantesmax);
		    return true;
	    }
	    return false;
    }

    // Eliminar Zona

    public boolean eliminarZona(Parque parque, String nombre){
	    int i = parque.buscarZonaByNombre(nombre);
	    if(i != -1){
		    parque.getListZonas().remove(i);
		    return true;
	    }
	    return false;
    }

    // Registrar Atraccion

    public boolean registrarAtraccion(Parque parque, Zona zona, String nombre, int capacidadMaxima, double alturaMinimaRequerida, int edadMinimaRequerida, double costoAdicional, TipoAtraccion tipo, MotivoCierre motivoCierre, EstadoAtraccion estadoAtraccion){
	    if(zona.getListOperadores().size()!=zona.getListAtracciones().size()){
	    	int i = parque.buscarAtraccionByNombre(nombre);
	    	if(i == -1){
	    		Atraccion nuevaatraccion = new Atraccion(Integer.toString(parque.getListAtracciones().size()+001), nombre, capacidadMaxima, alturaMinimaRequerida, edadMinimaRequerida, 0, costoAdicional, 0, 0, 0, tipo, motivoCierre, estadoAtraccion, zona, null);
	    		ColaVirtual nuevacola = new ColaVirtual(nuevaatraccion, 0);
	    		nuevaatraccion.setTheColaVirtual(nuevacola);
				parque.getListAtracciones().add(nuevaatraccion);
	    		return true;
	    	}
	    	return false;
	    }
	    return false;
    }

    // Modificar Atraccion

    public boolean modificarAtraccion(Parque parque, Zona zona, String nombre, int capacidadMaxima, double alturaMinimaRequerida, int edadMinimaRequerida, double costoAdicional){
	    int i = parque.buscarAtraccionByNombre(nombre);
	    if(i != -1){
		    parque.getListAtracciones().get(i).setNombre(nombre);
		    parque.getListAtracciones().get(i).setCapacidadMaxima(capacidadMaxima);
		    parque.getListAtracciones().get(i).setAlturaMinimaRequerida(alturaMinimaRequerida);
		    parque.getListAtracciones().get(i).setEdadMinimaRequerida(edadMinimaRequerida);
		    parque.getListAtracciones().get(i).setCostoAdicional(costoAdicional);
            parque.getListAtracciones().get(i).setTheZona(zona);
		    return true;
	    }
	    return false;
    }

    // Eliminar Atraccion

    public boolean eliminarAtraccion(Parque parque, String nombre){
	    int i = parque.buscarAtraccionByNombre(nombre);
	    if(i != -1){
		    parque.getListAtracciones().remove(i);
		    return true;
	    }
	    return false;
    }

    // Asignar Operador a una Zona

    public boolean asignarOperadorAZona(Operador operador, Zona zona){
	    if(operador.getTheZona()==null){
	    	zona.getListOperadores().add(operador);
	    	return true;
	    }
	    return false;
    }

    // Quitar Operador de una Zona

    public boolean quitarOperadorDeZona(Operador operador){
	    if(operador.getTheZona()!=null){
		    operador.setTheZona(null);
		    return true;
	    }
	    return false;
    }

    // Asignar Operador a una Atraccion

    public boolean asignarOperadorAAtraccion(Operador operador, Atraccion atraccion){
	    if( atraccion.getTheZona() == operador.getTheZona() ){
		    atraccion.getListOperadores().add(operador);
		    return true;
	    }
	    return false;
    }

    // Quitar Operador de una Atraccion

    public boolean quitarOperadorDeAtraccion(Operador operador, Atraccion atraccion){
	    if(atraccion.getTheZona()==operador.getTheZona()){
		    for(int i=0; i<atraccion.getListOperadores().size(); i++){
			    if(operador.getCedula()==atraccion.getListOperadores().get(i).getCedula()){
			    	atraccion.getListOperadores().remove(i);
			    	return true;
			    }
		    }
		    return false;
	    }
	    return false;
    }

    // Activar alerta sistemica (y todo lo que conlleva)

    public boolean activarAlertaClimatica(Parque parque, int tipoalerta){
	    switch(tipoalerta){
	    	case 1: 
	    		parque.setAlertaClima(AlertaClima.LLUVIA_FUERTE);
	    		parque.setNumCierresClima(parque.getNumCierresClima()+1);
	    		for(int i=0; i<parque.getListZonas().size(); i++){
	    			for(int j=0; i<parque.getListZonas().get(i).getListAtracciones().size(); j++){
	    				if(parque.getListZonas().get(i).getListAtracciones().get(j).getTipo() == TipoAtraccion.MECANICA_DE_ALTURA || parque.getListZonas().get(i).getListAtracciones().get(j).getTipo() == TipoAtraccion.ACUATICA){
	    					parque.getListZonas().get(i).getListAtracciones().get(j).setEstado(EstadoAtraccion.CERRADA);
	    					parque.getListZonas().get(i).getListAtracciones().get(j).setMotivoCierre(MotivoCierre.CLIMA);
	    				}
	    			}
	    		}
	    		return true;
	    	case 2:
	    		parque.setAlertaClima(AlertaClima.TORMENTA_ELECTRICA);
	    		parque.setNumCierresClima(parque.getNumCierresClima()+1);
	    		for(int i=0; i<parque.getListZonas().size(); i++){
	    			for(int j=0; i<parque.getListZonas().get(i).getListAtracciones().size(); j++){
	    				if(parque.getListZonas().get(i).getListAtracciones().get(j).getTipo() == TipoAtraccion.MECANICA_DE_ALTURA || parque.getListZonas().get(i).getListAtracciones().get(j).getTipo() == TipoAtraccion.ACUATICA){
	    					parque.getListZonas().get(i).getListAtracciones().get(j).setEstado(EstadoAtraccion.CERRADA);
	    					parque.getListZonas().get(i).getListAtracciones().get(j).setMotivoCierre(MotivoCierre.CLIMA);
	    				}
	    			}
	    		}
	    		return true;
	    	default:
	    		return false;
	    }
    }

    // Desactivar alerta sistemica (y todo lo que conlleva)

    public boolean desactivarAlertaClimatica(Parque parque){
	    parque.setAlertaClima(null);
	    for(int i=0; i<parque.getListZonas().size(); i++){
		    for(int j=0; i<parque.getListZonas().get(i).getListAtracciones().size(); j++){
			    if(parque.getListZonas().get(i).getListAtracciones().get(j).getTipo() == TipoAtraccion.MECANICA_DE_ALTURA || parque.getListZonas().get(i).getListAtracciones().get(j).getTipo() == TipoAtraccion.ACUATICA){
				    if(parque.getListZonas().get(i).getListAtracciones().get(j).getListRevisionesTecnicas().get(parque.getListZonas().get(i).getListAtracciones().get(j).getListRevisionesTecnicas().size()-1).estado() == "INICIADA"){
					    parque.getListZonas().get(i).getListAtracciones().get(j).setEstado(EstadoAtraccion.EN_MANTENIMIENTO);
				    }
				    if(parque.isAbierto() == false && parque.getListZonas().get(i).getListAtracciones().get(j).getEstado()!=EstadoAtraccion.EN_MANTENIMIENTO){
					    parque.getListZonas().get(i).getListAtracciones().get(j).setEstado(EstadoAtraccion.CERRADA);
					    parque.getListZonas().get(i).getListAtracciones().get(j).setMotivoCierre(MotivoCierre.FIN_JORNADA);
				    }
				    parque.getListZonas().get(i).getListAtracciones().get(j).setEstado(EstadoAtraccion.ACTIVA);

			    }
		    }
	    }
	    return true;
    }

    // Consultar Datos de Atracciones , Zonas o Operadores

    public String consultarDatos(Parque parque, int tipodato, String datobuscar){
	    int i = 0;
	    switch(tipodato){
		    case 1:
		    	i = parque.buscarOperadorByCedula(datobuscar);
		    	if(i != -1){
		    		return parque.getListOperadores(parque.getListPersonas()).get(i).mostrarDatos();
		    	}
		    case 2:
		    	i = parque.buscarZonaByNombre(datobuscar);
		    	if(i != -1){
		    		return parque.getListZonas().get(i).mostrarDatos();
		    	}
		    case 3:
		    	i = parque.buscarAtraccionByNombre(datobuscar);
		    	if(i != -1){
		    		return parque.getListAtracciones().get(i).mostrarDatos();
		    	}
		    default:
			    return "Opcion Invalida";
	    }
    }

    // Terminar Jornada (Necesario para general el Reporte General del dia)

    public void terminarJornada(Parque parque){

	    for(Atraccion atraccion : parque.getListAtracciones()){
		    atraccion.setEstado(EstadoAtraccion.CERRADA);
		    atraccion.setMotivoCierre(MotivoCierre.FIN_JORNADA);
	    }

	    parque.setAbierto(false);
    }

    // Generar Reporte General de el dia

    public String consultarReporteGeneral(Parque parque){
	    if(parque.isAbierto() == false){

            LocalDate dia = LocalDate.now();
		    double ingresos = 0;
		    ArrayList<Atraccion> listAtraccionesVisitadas = new ArrayList<>();
		    int tiempopromedio = 0;
		    int alertasclimas = parque.getNumCierresClima();
		    int alertasmantenimientos = 0;
		    ArrayList<Atraccion> listAtraccionesIncidentes = new ArrayList<>();

		    for(int i = 0; i<parque.getListEntradas(parque.getListPersonas()).size(); i++){
                if(parque.getListEntradas((parque.getListPersonas())).get(i).getFechaCompra() == dia){
		    		if(parque.getListEntradas((parque.getListPersonas())).get(i).getTipoEntrada() == TipoEntrada.GENERAL){
			    		ingresos += 100000;
			    	}
			    	if(parque.getListEntradas((parque.getListPersonas())).get(i).getTipoEntrada() == TipoEntrada.FAMILIAR){
			    		ingresos += 200000;
			    	}
			    	if(parque.getListEntradas((parque.getListPersonas())).get(i).getTipoEntrada() == TipoEntrada.FAST_PASS){
			    		ingresos += 170000;
			    	}
			    }
		    }

		    for(int i=0; i<parque.getListAtracciones().size(); i++){
			    for(int j=0; j<=listAtraccionesVisitadas.size(); j++){
			    	if(parque.getListAtracciones().get(i).getCiclosDiarios() < listAtraccionesVisitadas.get(j).getCiclosDiarios()){
			    		listAtraccionesVisitadas.add( j , parque.getListAtracciones().get(i) );
			    	}
			    }
		    }

		    int sum = 0;
		    for(int i=0; i<parque.getListAtracciones().size(); i++){
		    	sum += parque.getListAtracciones().get(i).getTheColaVirtual().getTiempoEspera();
		    }
		    tiempopromedio = sum/parque.getListAtracciones().size();

		    for(int i=0; i<parque.getListAtracciones().size(); i++){
		    	alertasmantenimientos += parque.getListAtracciones().get(i).getNumMantenimientos();
		    }

		    for(int i=0; i<parque.getListAtracciones().size(); i++){
		    	for(int j=0; i<=listAtraccionesIncidentes.size(); j++){
		    		if(parque.getListAtracciones().get(i).getNumMantenimientos() < listAtraccionesIncidentes.get(j).getCiclosDiarios()){
		    			listAtraccionesIncidentes.add( j , parque.getListAtracciones().get(i) );
		    		}
		    	}
		    }

		    ReporteGeneral nuevoreportegeneral = new ReporteGeneral(dia, ingresos, listAtraccionesVisitadas, tiempopromedio, alertasclimas, alertasmantenimientos, listAtraccionesIncidentes);
			this.getListReporteGeneral().add(nuevoreportegeneral);
		    return nuevoreportegeneral.toString();


	    }		

	    return "Invalido, termine jornada del Parque primero";

    }

	// Pasar dia

	public void pasarDia(Parque parque){	
		parque.setDia(LocalDate.now());
	}

    public Parque getTheParque() {
        return theParque;
    }

    public void setTheParque(Parque theParque) {
        this.theParque = theParque;
    }

	public ArrayList<ReporteGeneral> getListReporteGeneral() {
		return listReporteGeneral;
	}

	public void setListReporteGeneral(ArrayList<ReporteGeneral> listReporteGeneral) {
		this.listReporteGeneral = listReporteGeneral;
	}

}

