package org.uniquindio.proyectofinalcodigo.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Operador extends Persona {
    private int aniosExperiencia;
    private Zona theZona;
    private ArrayList<RevisionTecnica> listRevisionesTecnicas;
    private Parque theParque;

    public Operador(int aniosExperiencia, Parque parque, String cedula, String nombre, LocalDate fechaNacimiento, double estatura, String numeroTelefono, String nacionalidad) {
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

    // Operador Activo

    Operador operadoractivo = iniciarSesionOperador(theParque, getCedula());
    Parque parqueactivo = operadoractivo.getTheParque();

    // Validar el acceso de un visitante a la cola

    public boolean validarAccesoCola(Visitante visitante, Atraccion atraccion){
	    if(atraccion.getEstado()!=EstadoAtraccion.EN_MANTENIMIENTO && atraccion.getEstado()!=EstadoAtraccion.CERRADA){
		    if(visitante.getEdad()>=atraccion.getEdadMinimaRequerida() && visitante.getEstatura()>=atraccion.getAlturaMinimaRequerida() && atraccion.getEstado() != EstadoAtraccion.CERRADA && visitante.getListEntradas().get(visitante.getListEntradas().size()-1).isActiva() != false){
			    if(visitante.getListEntradas().get(visitante.getListEntradas().size()-1).getTipoEntrada() == TipoEntrada.GENERAL){
	    			if(visitante.getSaldoVirtual() < atraccion.getCostoAdicional()){
		    			return false;
		    		}
		    		visitante.setSaldoVirtual(visitante.getSaldoVirtual()-atraccion.getCostoAdicional());
		    		atraccion.getTheColaVirtual().getListVisitantesGeneralFamiliar().add(visitante);
		    		return true;
		    	}
		    	if(visitante.getListEntradas().get(visitante.getListEntradas().size()-1).getTipoEntrada() == TipoEntrada.FAST_PASS){
		    		atraccion.getTheColaVirtual().getListVisitantesFastPass().add(visitante);
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

    public boolean permitirAccesoAtraccion(Visitante visitante, Atraccion atraccion, String descripcion){
	
	    if(atraccion.getEstado()!=EstadoAtraccion.EN_MANTENIMIENTO && atraccion.getEstado()!=EstadoAtraccion.CERRADA && atraccion.getListVisitantes()==null){
		    for(int i=0; i<atraccion.getCapacidadMaxima(); i++){
                
			    atraccion.ingresarVisitante(atraccion.getTheColaVirtual().getListVisitantesFastPass().get(0));
		    	atraccion.getTheColaVirtual().getListVisitantesFastPass().remove(0);
		    	i++;
                
		    	if(i<atraccion.getCapacidadMaxima()){
		    		atraccion.ingresarVisitante(atraccion.getTheColaVirtual().getListVisitantesGeneralFamiliar().get(0));
		    		atraccion.getTheColaVirtual().getListVisitantesGeneralFamiliar().remove(0);
		    	}
                
		    }
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
        if(atraccion.getListVisitantes().get(0)!=null){
	        atraccion.sacarVisitantes();
            return true;
        }
        return false;
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
			    	if(atraccion.getListRevisionesTecnicas().get(atraccion.getListRevisionesTecnicas().size()-1).estado().equals("INICIADA")){
			    		finalizarRevisionTecnica(atraccion, descripcion);
			    	}
			    	return true; 
			    }
		    case 2:
			    if(atraccion.getEstado()==EstadoAtraccion.ACTIVA){
			    	atraccion.setEstado(EstadoAtraccion.EN_MANTENIMIENTO);
			    	atraccion.setNumMantenimientos(atraccion.getNumMantenimientos()+1);
			    	if(atraccion.getListRevisionesTecnicas().get(atraccion.getListRevisionesTecnicas().size()-1).estado().equals("FINALIZADA")){
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
	    if(atraccion.getListRevisionesTecnicas().get(atraccion.getListRevisionesTecnicas().size()-1).estado().equals("FINALIZADA")){
		    RevisionTecnica nuevarevision = new RevisionTecnica("INICIADA", operadoractivo, atraccion, LocalDate.now(), descripcion, null);
	    	atraccion.getListRevisionesTecnicas().add(nuevarevision);
	    	operadoractivo.getListRevisionesTecnicas().add(nuevarevision);
	    	if(atraccion.getEstado()==EstadoAtraccion.ACTIVA){
	    		registrarEstadoAtraccion(atraccion, 2, descripcion);
	    	}
	    	return true;
	    }
	    return false;
    }

    // Finalizar Revision Tecnica

    public boolean finalizarRevisionTecnica(Atraccion atraccion, String descripcion){
	    if(atraccion.getListRevisionesTecnicas().get(atraccion.getListRevisionesTecnicas().size()-1).estado().equals("INICIADA")){
		    RevisionTecnica nuevarevision = new RevisionTecnica("FINALIZADA", operadoractivo, atraccion, atraccion.getListRevisionesTecnicas().get(atraccion.getListRevisionesTecnicas().size()-1).fechaInicio(), descripcion, LocalDate.now());
		    atraccion.getListRevisionesTecnicas().add(nuevarevision);
		    operadoractivo.getListRevisionesTecnicas().add(nuevarevision);
		    if(atraccion.getEstado()==EstadoAtraccion.CERRADA){
		    	registrarEstadoAtraccion(atraccion, 1, descripcion);
		    }
		    return true;
	    }
	    return false;
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
