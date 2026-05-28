package org.uniquindio.proyectofinalcodigo.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Operador extends Persona implements IMostrable {
    private int aniosExperiencia;
    private Zona theZona;
    private ArrayList<RevisionTecnica> listRevisionesTecnicas;
    private Parque theParque;

    public Operador(int aniosExperiencia, Parque theParque, Zona theZona, String cedula, String nombre, LocalDate fechaNacimiento, double estatura, String numeroTelefono, String nacionalidad) {
        super(cedula, nombre, fechaNacimiento, estatura, numeroTelefono, nacionalidad);
        this.aniosExperiencia = aniosExperiencia;
        this.theZona = theZona;
        this.listRevisionesTecnicas = new ArrayList<>();
        this.theParque = theParque;
    }

    // Metodos

    // Iniciar Sesion como Operador

	public Operador iniciarSesionOperador(Parque parque, String cedula){
		if(parque.buscarOperadorByCedula(cedula) != -1){
			Operador operadoractivo = parque.getListOperadores(parque.getListPersonas()).get(parque.buscarOperadorByCedula(cedula));
			return operadoractivo;
        }
        return null;
	}

    // Validar el acceso de un visitante a la cola

    public boolean validarAccesoCola(Visitante visitante, Atraccion atraccion){

	    if(atraccion.getEstado()!=EstadoAtraccion.EN_MANTENIMIENTO && atraccion.getEstado()!=EstadoAtraccion.CERRADA){
		    if(visitante.getEdad()>=atraccion.getEdadMinimaRequerida() && visitante.getEstatura()>=atraccion.getAlturaMinimaRequerida() && atraccion.getEstado() != EstadoAtraccion.CERRADA && visitante.getListEntradas().get(visitante.getListEntradas().size()-1).isActiva() != false){
			    if(visitante.getListEntradas().get(visitante.getListEntradas().size()-1).getTipoEntrada() == TipoEntrada.GENERAL){
		    		atraccion.getTheColaVirtual().getListVisitantesGeneralFamiliar().add(visitante);
					atraccion.getTheColaVirtual().setTiempoEspera((atraccion.getTheColaVirtual().getListVisitantesGeneralFamiliar().size()/(atraccion.getCapacidadMaxima()/2))*5);
		    		return true;
		    	}
		    	if(visitante.getListEntradas().get(visitante.getListEntradas().size()-1).getTipoEntrada() == TipoEntrada.FAST_PASS){
		    		atraccion.getTheColaVirtual().getListVisitantesFastPass().add(visitante);
					atraccion.getTheColaVirtual().setTiempoEspera((atraccion.getTheColaVirtual().getListVisitantesFastPass().size()/(atraccion.getCapacidadMaxima()/2))*5);
		    		return true;
		    	}
		    	atraccion.getTheColaVirtual().getListVisitantesGeneralFamiliar().add(visitante);
		    	return true;
		    }
	    	return false;
	    }
	    return false;
    }

    // Permitir el acceso de un visitante a la atraccion

    public boolean permitirAccesoAtraccion(Atraccion atraccion, String descripcion){

		boolean fp = atraccion.getTheColaVirtual().getListVisitantesFastPass().isEmpty() == false;
		boolean gf = atraccion.getTheColaVirtual().getListVisitantesGeneralFamiliar().isEmpty() == false;

	    if(atraccion.getEstado()!=EstadoAtraccion.EN_MANTENIMIENTO && atraccion.getEstado()!=EstadoAtraccion.CERRADA && (fp || gf)){
		    for(int i=0; i<atraccion.getCapacidadMaxima(); i++){

				if (atraccion.getTheColaVirtual().getListVisitantesFastPass().isEmpty() == false) {
			    	atraccion.ingresarVisitante(atraccion.getTheColaVirtual().getListVisitantesFastPass().get(0));
		    		atraccion.getTheColaVirtual().getListVisitantesFastPass().remove(0);

				}else if(atraccion.getTheColaVirtual().getListVisitantesGeneralFamiliar().isEmpty() == false){
					atraccion.ingresarVisitante(atraccion.getTheColaVirtual().getListVisitantesGeneralFamiliar().get(0));
		    		atraccion.getTheColaVirtual().getListVisitantesGeneralFamiliar().remove(0);
				}else{
					break;
				}

		    }

			if(atraccion.getTheZona().getTheParque().getDia()!=LocalDate.now()){
				atraccion.setCiclosDiarios(0);
			}
			
			atraccion.setCiclosDiarios(atraccion.getCiclosDiarios()+1);
		    	
		    atraccion.setContadorVisitantes(atraccion.getContadorVisitantes() + atraccion.getCapacidadMaxima());
		    if(atraccion.getContadorVisitantes()>=500){
		    	atraccion.setEstado(EstadoAtraccion.EN_MANTENIMIENTO);
		    	registrarRevisionTecnica(atraccion, descripcion);		

		    }
		    return true;
	    }
	    return false;

    }

    // Retirar Visitantes de la Atraccion

    public boolean retirarVisitantesAtraccion(Atraccion atraccion){
        if(atraccion.getListVisitantes().isEmpty()==false){
	        atraccion.sacarVisitantes();
            return true;
        }
        return false;
    }

	// Mostrar Lista de Visitantes en Cola Virtual de la Atraccion (General y Familiar)

	public ArrayList<Visitante> mostrarColaVirtualGF(Atraccion atraccion){
		return atraccion.getTheColaVirtual().getListVisitantesGeneralFamiliar();
	}

	// Mostrar Lista de Visitantes en Cola Virtual de la Atraccion (General y Familiar)

	public ArrayList<Visitante> mostrarColaVirtualFF(Atraccion atraccion){
		return atraccion.getTheColaVirtual().getListVisitantesFastPass();
	}

	// Mostrar Lista de Visitantes en una atraccion activa

	public ArrayList<Visitante> mostrarVisitantesAtraccion(Atraccion atraccion){
		return atraccion.getListVisitantes();
	}

    // Cambiar Capacidad Maxima de la Atraccion

    public void cambiarCapacidadAtraccion(int capacidad, Atraccion atraccion){
	    atraccion.setCapacidadMaxima(capacidad);
    }

    // Registrar Estado de la Atraccion

    public boolean registrarEstadoAtraccion(Atraccion atraccion, int estado, String descripcion){
		
	    switch(estado){
		    case 1:
			    if(atraccion.getEstado()==EstadoAtraccion.EN_MANTENIMIENTO){
				    atraccion.setEstado(EstadoAtraccion.ACTIVA);
					int index = atraccion.getListRevisionesTecnicas().size()-1;
        			if(atraccion.getListRevisionesTecnicas().isEmpty()){
            			index = 0;
        			}
			    	if(atraccion.getListRevisionesTecnicas().get(index).estado().equals("INICIADA") || atraccion.getListRevisionesTecnicas().isEmpty()){
			    		finalizarRevisionTecnica(atraccion, descripcion);
			    	}
			    	return true; 
			    }
		    case 2:
			    if(atraccion.getEstado()==EstadoAtraccion.ACTIVA){
			    	atraccion.setEstado(EstadoAtraccion.EN_MANTENIMIENTO);
					int index = atraccion.getListRevisionesTecnicas().size()-1;
        			if(atraccion.getListRevisionesTecnicas().isEmpty()){
            			index = 0;
        			}
					if(atraccion.getTheZona().getTheParque().getDia()!=LocalDate.now()){
						atraccion.setNumMantenimientos(0);
					}
			    	atraccion.setNumMantenimientos(atraccion.getNumMantenimientos()+1);
			    	if(atraccion.getListRevisionesTecnicas().isEmpty() || atraccion.getListRevisionesTecnicas().get(index).estado().equals("FINALIZADA")){
			    		registrarRevisionTecnica(atraccion, descripcion);
			    	}
			    	return true;
			    }
            default:
                return false;
	    }
    }

    // Registrar Revision Tecnica

    public boolean registrarRevisionTecnica(Atraccion atraccion, String descripcion){
		int index = atraccion.getListRevisionesTecnicas().size()-1;
        if(atraccion.getListRevisionesTecnicas().isEmpty()){
        	index = 0;
        }
	    if(atraccion.getListRevisionesTecnicas().isEmpty() || atraccion.getListRevisionesTecnicas().get(index).estado().equals("FINALIZADA")){
		    RevisionTecnica nuevarevision = new RevisionTecnica("INICIADA", this, atraccion, LocalDate.now(), descripcion, null);
	    	atraccion.getListRevisionesTecnicas().add(nuevarevision);
	    	this.getListRevisionesTecnicas().add(nuevarevision);
	    	if(atraccion.getEstado()==EstadoAtraccion.ACTIVA){
	    		registrarEstadoAtraccion(atraccion, 2, descripcion);
	    	}
	    	return true;
	    }
	    return false;
    }

    // Finalizar Revision Tecnica

    public boolean finalizarRevisionTecnica(Atraccion atraccion, String descripcion){
		if(atraccion.getListRevisionesTecnicas().isEmpty() != true){
				int index = atraccion.getListRevisionesTecnicas().size()-1;
        		if(atraccion.getListRevisionesTecnicas().isEmpty()){
        			index = 0;
        		}
	    	if(atraccion.getListRevisionesTecnicas().get(index).estado().equals("INICIADA")){
		    	RevisionTecnica nuevarevision = new RevisionTecnica("FINALIZADA", this, atraccion, atraccion.getListRevisionesTecnicas().get(index).fechaInicio(), descripcion, LocalDate.now());
		    	atraccion.getListRevisionesTecnicas().add(nuevarevision);
		    	this.getListRevisionesTecnicas().add(nuevarevision);
		    	if(atraccion.getEstado()==EstadoAtraccion.CERRADA){
		    		registrarEstadoAtraccion(atraccion, 1, descripcion);
		    	}
		    	return true;
	    	}
	    	return false;
		}
		return false;
    }

	// Mostrar lista de revisiones tecnicas

	public String mostrarRevisionesTecnicas(Parque parque){
		String revisiones="";
		if(this.getListRevisionesTecnicas().isEmpty()==false){
	        for(int i=0; i<this.getListRevisionesTecnicas().size(); i++){
	    	    revisiones += (this.getListRevisionesTecnicas().get(i).toString() + "\n");
	        }
        }else{
        revisiones = "No hay registro de revisiones tecnicas por el momento.";
        }
	    return revisiones;
	}

    @Override
    public String mostrarDatos() {
        String zona = (theZona != null) ? theZona.getNombre() : "Sin zona";
        return "Cedula: " + getCedula() + " | Nombre: " + getNombre() +
               " | Zona Asignada: " + zona +
               " | Experiencia: " + aniosExperiencia + " años" +
               " | Revisiones: " + listRevisionesTecnicas.size();
    }

    public int getAniosExperiencia() {
        return aniosExperiencia;
    }

    public void setAniosExperiencia(int aniosExperiencia) {
        this.aniosExperiencia = aniosExperiencia;
    }

    public Zona getTheZona() {
        return theZona;
    }

    public void setTheZona(Zona theZona) {
        this.theZona = theZona;
    }

    public List<RevisionTecnica> getListRevisionesTecnicas() {
        return listRevisionesTecnicas;
    }

    public void setListRevisionesTecnicas(ArrayList<RevisionTecnica> listRevisionesTecnicas) {
        this.listRevisionesTecnicas = listRevisionesTecnicas;
    }

    public Parque getTheParque() {
        return theParque;
    }

    public void setTheParque(Parque theParque) {
        this.theParque = theParque;
    }

}
