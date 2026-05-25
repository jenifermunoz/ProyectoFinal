package org.uniquindio.proyectofinalcodigo.model;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Visitante extends Persona {
    private double saldoVirtual;
    private int edad;
    private boolean fotografia;
    private Parque theParque;
    private List<Atraccion> AtraccionesFavoritas;
    private List<Entrada> listEntradas;
    private List<NotificacionAsignada> listNotificacionesAsignadas;

    public Visitante(double saldoVirtual, int edad, boolean fotografia, Parque parque, String cedula, String nombre, LocalDate fechaNacimiento, double estatura, String numeroTelefono, String nacionalidad){

        super(cedula,nombre,fechaNacimiento,estatura,numeroTelefono,nacionalidad);
        this.saldoVirtual=saldoVirtual;
        this.edad=edad;
        this.fotografia=fotografia;
        this.theParque = theParque;
        this.AtraccionesFavoritas = new ArrayList<>();
        this.listEntradas = new ArrayList<>();
        this.listNotificacionesAsignadas = new ArrayList<>();

    }

    //Metodos

    // Inicio de Sesion - Visitante

    public Visitante iniciarSesionVisitante(Parque parque, String cedula){
	    if(parque.buscarVisitantebyCedula(cedula) != -1){
		    Visitante visitanteactivo = parque.getListVisitantes(parque.getListPersonas()).get(parque.buscarVisitantebyCedula(cedula));
		    return visitanteactivo;
        }
        return null;
    }

    // Visitante Activo

    Visitante visitanteactivo = iniciarSesionVisitante(theParque, getCedula());
    Parque parqueactivo = visitanteactivo.getTheParque();

    // Registrar perfil de un Visitante

    public boolean registrarPerfil(Parque parque, String cedula, String nombre, LocalDate fechaNacimiento, double estatura, String numeroTelefono, String nacionalidad, int edad){
	    if(parque.buscarVisitantebyCedula(cedula) == -1){
		    Visitante nuevovisitante = new Visitante(0, edad, false, parque, cedula, nombre, fechaNacimiento, estatura, numeroTelefono, nacionalidad);
            parque.getListPersonas().add(nuevovisitante);
		    return true;
	    }
	    return false;
    }

    // Actualizar perfil de un visitante

    public void actualizarPerfil(String nombre, double estatura, String numeroTelefono, String nacionalidad, int edad){
		visitanteactivo.setNombre(nombre);
		visitanteactivo.setEstatura(estatura);
		visitanteactivo.setNumeroTelefono(numeroTelefono);
		visitanteactivo.setNacionalidad(nacionalidad);
		visitanteactivo.setEdad(edad);
    }

    // Comprar tiquetes

    public boolean comprarTickets(Parque parque, String cedula, int tipo){
	    if(visitanteactivo.getListEntradas().get(visitanteactivo.getListEntradas().size()-1).isActiva() != true && parque.getCapacidadMax() > parque.getListVisitantes(parque.getListPersonas()).size()){
		    Entrada nuevaentrada = new Entrada(null, LocalDate.now(), LocalDate.now().plusDays(5), false, null);
		    switch(tipo){
		    	case 1:
			    	if(visitanteactivo.pagarConSaldoVirtual(100000)==true){
				    	int num = 000;
				    	for(int i=0; i<parque.getListEntradas(parque.getListPersonas()).size(); i++){
					    	if(parque.getListEntradas(parque.getListPersonas()).get(i).getTipoEntrada() == TipoEntrada.GENERAL){
					    		num++;
					    	}
					    }
				    	nuevaentrada.setId("G"+Integer.toString(num+1));
			    		nuevaentrada.setTipoEntrada(TipoEntrada.GENERAL);
				    	nuevaentrada.setActiva(true);
				    	visitanteactivo.getListEntradas().add(nuevaentrada);
				    	return true;
				    }
			    	nuevaentrada = null;
			    	return false;
			    case 2:
			    	if(visitanteactivo.pagarConSaldoVirtual(200000)==true){
			    		int num = 000;
			    		for(int i=0; i<parque.getListEntradas(parque.getListPersonas()).size(); i++){
					    	if(parque.getListEntradas(parque.getListPersonas()).get(i).getTipoEntrada() == TipoEntrada.FAMILIAR){
			    				num++;
			    			}
			    		}
			    		nuevaentrada.setId("F"+Integer.toString(num+1));
			    		nuevaentrada.setTipoEntrada(TipoEntrada.FAMILIAR);
			    		nuevaentrada.setActiva(true);
			    		visitanteactivo.getListEntradas().add(nuevaentrada);
				    	return true;
				    }
			    	nuevaentrada = null;
			    	return false;
			    case 3:
			    	if(visitanteactivo.pagarConSaldoVirtual(170000)==true){
			    		int num = 000;
			    		for(int i=0; i<parque.getListEntradas(parque.getListPersonas()).size(); i++){
					    	if(parque.getListEntradas(parque.getListPersonas()).get(i).getTipoEntrada() == TipoEntrada.FAST_PASS){
			    				num++;
			    			}
			    		}
			    		nuevaentrada.setId("FP"+Integer.toString(num+1));
			    		nuevaentrada.setTipoEntrada(TipoEntrada.FAST_PASS);
			    		nuevaentrada.setActiva(true);
			    		visitanteactivo.getListEntradas().add(nuevaentrada);
			    		return true;
			    	}
			    	nuevaentrada = null;
			    	return false;
			    default:
			    	nuevaentrada = null;
			    	return false;
		    }
	    }
	    return false;
    }

    //Consultar el mapa del parque

    public void consultarMapa(Parque parque){
	    parque.getMapa();
    }

    // Consultar el tiempo de espera de una atraccion

    public String consultarTiemposEspera(Parque parque, String nombre){
	    int i = parque.buscarZonaByNombreAtraccion(nombre);
	    int j = parque.buscarAtraccionByNombre(nombre);
	    if(i!=-1 && j!=-1){
		    int tiempo = parque.getListZonas().get(i).getListAtracciones().get(j).getTheColaVirtual().getTiempoEspera();
		    return tiempo + " minutos";
	    }
	    return "Atraccion no encontrada";
    }

    // Agregar atraccion favorita 

    public boolean agregarAtraccionFavorita(Parque parque, String nombre){
	    int i = parque.buscarZonaByNombreAtraccion(nombre);
	    int j = parque.buscarAtraccionByNombre(nombre);
	    if(i!=-1 && j!=-1){
	    	visitanteactivo.getAtraccionesFavoritas().add(parque.getListZonas().get(i).getListAtracciones().get(j));
	    	return true;
	    }
	    return false;
    }

    // Mostrar lista de Atracciones favoritas

    public String mostrarAtraccionesFavoritas(Parque parque){
	    String favoritas = "";
	    for(int i=0; i<visitanteactivo.getAtraccionesFavoritas().size(); i++){
	    	favoritas += (visitanteactivo.getAtraccionesFavoritas().get(i).toString() + "\n");
	    }
	    return favoritas;
    }

    // Mostrar notificaciones 

    public String mostrarNotificaciones(Parque parque){
	    String notificaciones = "";
	    for(int i=0; i<visitanteactivo.getListNotificacionesAsignadas().size(); i++){
	    	notificaciones += (visitanteactivo.getListNotificacionesAsignadas().get(i).getTheNotificacion().toString() + "\n");
	    }
	    return notificaciones;
    }

    // Unirse a la cola virtual de una Atraccion

    public boolean unirseAColaVirtualAtraccion(Parque parque, Atraccion atraccion){
		return atraccion.procesarSolicitudCola(visitanteactivo, atraccion);
    }

    // Recargar Saldo Virtual 

    public void RecargarSaldoVirtual(int cantidad){
	    visitanteactivo.setSaldoVirtual(cantidad);
    }

    // Pagar usando Saldo Virtual

    public boolean pagarConSaldoVirtual(int cantidad){
	    if(visitanteactivo.getSaldoVirtual()>cantidad){
		    visitanteactivo.setSaldoVirtual(visitanteactivo.getSaldoVirtual()-cantidad);
		    return true;
	    }
	    return false;
    }


    public double getSaldoVirtual() {
        return saldoVirtual;
    }

    public void setSaldoVirtual(double saldoVirtual) {
        this.saldoVirtual = saldoVirtual;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public boolean isFotografia() {
        return fotografia;
    }

    public void setFotografia(boolean fotografia) {
        this.fotografia = fotografia;
    }

    public List<Atraccion> getAtraccionesFavoritas() {
        return AtraccionesFavoritas;
    }

    public void setAtraccionesFavoritas(List<Atraccion> atraccionesFavoritas) {
        AtraccionesFavoritas = atraccionesFavoritas;
    }

    public List<Entrada> getListEntradas() {
        return listEntradas;
    }

    public void setListEntradas(List<Entrada> listEntrada) {
        this.listEntradas = listEntradas;
    }

    public Parque getTheParque() {
        return theParque;
    }

    public void setTheParque(Parque theParque) {
        this.theParque = theParque;
    }

    public List<NotificacionAsignada> getListNotificacionesAsignadas() {
        return listNotificacionesAsignadas;
    }

    public void setListNotificacionesAsignadas(List<NotificacionAsignada> listNotificacionesAsignadas) {
        this.listNotificacionesAsignadas = listNotificacionesAsignadas;
    }

}
