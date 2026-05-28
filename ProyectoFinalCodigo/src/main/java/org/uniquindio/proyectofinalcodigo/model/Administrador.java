package org.uniquindio.proyectofinalcodigo.model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Administrador extends Persona implements INotificacionGenerable {

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

	int index = -1;

    public boolean despedirOperador(Parque parque, String cedula){
		for(int i = 0; i<parque.listPersonas.size(); i++){
			if(parque.getListPersonas().get(i) instanceof Operador){
				if(parque.getListPersonas().get(i).getCedula().equals(cedula)){
					index = i;
				}
			}
		}

	    if(index != -1){
			parque.getListPersonas().remove(index);
	    	return true;
	    }
	    return false;
    }

    // Registrar Zona

    public boolean registrarZona(Parque parque, String nombre, String ubicacion, int visitantesmax){
	    int i = parque.buscarZonaByNombre(nombre);
	    if(i == -1){
			int maxId = 0;
        	for (Zona zona : parque.getListZonas()) {
				String nums = zona.getId().replaceAll("[^0-9]", "");
            	int idActual = Integer.parseInt(nums);
            	if (idActual > maxId) {
                	maxId = idActual;
            	}
        	}
	    	Zona nuevazona = new Zona(String.format("Z%03d",maxId+1), nombre, parque, ubicacion, visitantesmax);
			parque.getListZonas().add(nuevazona);
	    	return true;
	    }
	    return false;
    }

    // Modificar Zona

    public boolean modificarZona(Parque parque, String id, String nombre, int visitantesmax){
	    int i = parque.buscarZonaById(id);
	    if(i != -1){
		    parque.getListZonas().get(i).setNombre(nombre);
		    parque.getListZonas().get(i).setVisitantesMax(visitantesmax);
		    return true;
	    }
	    return false;
    }

    // Eliminar Zona

    public boolean eliminarZona(Parque parque, String id){
	    int i = parque.buscarZonaById(id);
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
				int maxId = 0;
        		for (Atraccion atraccion : parque.getListAtracciones()) {
					String nums = atraccion.getId().replaceAll("[^0-9]", "");
            		int idActual = Integer.parseInt(nums);
            		if (idActual > maxId){
                		maxId = idActual;
            		}
        		}

	    		Atraccion nuevaatraccion = new Atraccion(String.format("A%03d",maxId+1), nombre, capacidadMaxima, alturaMinimaRequerida, edadMinimaRequerida, 0, costoAdicional, 0, 0, 0, tipo, motivoCierre, estadoAtraccion, zona, null);
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

    public boolean modificarAtraccion(Parque parque, String id, String nombre, int capacidadMaxima, double alturaMinimaRequerida, int edadMinimaRequerida, double costoAdicional){
	    int i = parque.buscarAtraccionById(id);
	    if(i != -1){
		    parque.getListAtracciones().get(i).setNombre(nombre);
		    parque.getListAtracciones().get(i).setCapacidadMaxima(capacidadMaxima);
		    parque.getListAtracciones().get(i).setAlturaMinimaRequerida(alturaMinimaRequerida);
		    parque.getListAtracciones().get(i).setEdadMinimaRequerida(edadMinimaRequerida);
		    parque.getListAtracciones().get(i).setCostoAdicional(costoAdicional);
		    return true;
	    }
	    return false;
    }

    // Eliminar Atraccion

    public boolean eliminarAtraccion(Parque parque, String id){
	    int i = parque.buscarAtraccionById(id);
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
	    			for(int j=0; j<parque.getListZonas().get(i).getListAtracciones().size(); j++){
	    				if(parque.getListZonas().get(i).getListAtracciones().get(j).getTipo() == TipoAtraccion.MECANICA_DE_ALTURA || parque.getListZonas().get(i).getListAtracciones().get(j).getTipo() == TipoAtraccion.ACUATICA){
	    					parque.getListZonas().get(i).getListAtracciones().get(j).setEstado(EstadoAtraccion.CERRADA);
	    					parque.getListZonas().get(i).getListAtracciones().get(j).setMotivoCierre(MotivoCierre.CLIMA);
	    				}
	    			}
	    		}
	    		parque.notificarTodosConTicket(generarNotificacion("Alerta de Lluvia Fuerte: algunas atracciones han sido cerradas por seguridad."));
	    		return true;
	    	case 2:
	    		parque.setAlertaClima(AlertaClima.TORMENTA_ELECTRICA);
	    		parque.setNumCierresClima(parque.getNumCierresClima()+1);
	    		for(int i=0; i<parque.getListZonas().size(); i++){
	    			for(int j=0; j<parque.getListZonas().get(i).getListAtracciones().size(); j++){
	    				if(parque.getListZonas().get(i).getListAtracciones().get(j).getTipo() == TipoAtraccion.MECANICA_DE_ALTURA || parque.getListZonas().get(i).getListAtracciones().get(j).getTipo() == TipoAtraccion.ACUATICA){
	    					parque.getListZonas().get(i).getListAtracciones().get(j).setEstado(EstadoAtraccion.CERRADA);
	    					parque.getListZonas().get(i).getListAtracciones().get(j).setMotivoCierre(MotivoCierre.CLIMA);
	    				}
	    			}
	    		}
	    		parque.notificarTodosConTicket(generarNotificacion("Alerta de Tormenta Electrica: algunas atracciones han sido cerradas por seguridad."));
	    		return true;
	    	default:
	    		return false;
	    }
    }

    // Desactivar alerta sistemica (y todo lo que conlleva)

    public boolean desactivarAlertaClimatica(Parque parque){
	    parque.setAlertaClima(null);
	    for(int i=0; i<parque.getListZonas().size(); i++){
		    for(int j=0; j<parque.getListZonas().get(i).getListAtracciones().size(); j++){
				Atraccion atr = parque.getListZonas().get(i).getListAtracciones().get(j);
			    if(atr.getTipo() == TipoAtraccion.MECANICA_DE_ALTURA || atr.getTipo() == TipoAtraccion.ACUATICA){
	
					boolean tieneRevisionActiva = !atr.getListRevisionesTecnicas().isEmpty() &&
						atr.getListRevisionesTecnicas().get(atr.getListRevisionesTecnicas().size()-1).estado().equals("INICIADA");
					if (tieneRevisionActiva) {
						atr.setEstado(EstadoAtraccion.EN_MANTENIMIENTO);
					} else if (!parque.isAbierto()) {
						atr.setEstado(EstadoAtraccion.CERRADA);
						atr.setMotivoCierre(MotivoCierre.FIN_JORNADA);
					} else {

						atr.setEstado(EstadoAtraccion.ACTIVA);
						atr.setMotivoCierre(null);
						parque.notificarTodosConTicket(generarNotificacion(
							"La alerta climatica ha terminado. La atraccion '"
							+ atr.getNombre() + "' esta nuevamente ACTIVA."));
					}
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
		    	} return "Operador no encontrado";
		    case 2:
		    	i = parque.buscarZonaById(datobuscar);
		    	if(i != -1){
		    		return parque.getListZonas().get(i).mostrarDatos();
		    	} return "Zona no encontrada";
		    case 3:
		    	i = parque.buscarAtraccionById(datobuscar);
		    	if(i != -1){
		    		return parque.getListAtracciones().get(i).mostrarDatos();
		    	} return "Atraccion no encontrada";
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

			for(Atraccion atraccion : parque.getListAtracciones()){
				if (listAtraccionesVisitadas.isEmpty()) {
        			listAtraccionesVisitadas.add(atraccion);
    			} else {
					boolean insertado = false;

			    		for(int j=0; j<listAtraccionesVisitadas.size(); j++){
			    			if(atraccion.getCiclosDiarios() < listAtraccionesVisitadas.get(j).getCiclosDiarios()){
			    				listAtraccionesVisitadas.add( j , atraccion );
								insertado = true;
								break;
			    			}
			    		}

						if(!insertado){
							listAtraccionesVisitadas.add(atraccion);
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


			for(int i = 0; i < parque.getListAtracciones().size(); i++){
				Atraccion atraccion = parque.getListAtracciones().get(i);

				if(listAtraccionesIncidentes.isEmpty()){
					listAtraccionesIncidentes.add(atraccion);
				}else{
					boolean insertado = false;

					for(int j = 0; j < listAtraccionesIncidentes.size(); j++){
						if(atraccion.getNumMantenimientos() < listAtraccionesIncidentes.get(j).getNumMantenimientos()){
							listAtraccionesIncidentes.add(j, atraccion);
							insertado = true;
							break;
						}
					}

					if(!insertado){
						listAtraccionesIncidentes.add(atraccion);
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


    @Override
    public Notificacion generarNotificacion(String mensaje) {
        return new Notificacion("Alerta Administrativa", mensaje, java.time.LocalDate.now());
    }

}

