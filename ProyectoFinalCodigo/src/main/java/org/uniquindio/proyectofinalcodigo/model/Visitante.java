package org.uniquindio.proyectofinalcodigo.model;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Visitante extends Persona {
    private double saldoVirtual;
    private int edad;
    private boolean fotografia;
    private Parque theParque;
    private ArrayList<Atraccion> AtraccionesFavoritas;
    private ArrayList<Entrada> listEntradas;
    private ArrayList<NotificacionAsignada> listNotificacionesAsignadas;

    public Visitante(double saldoVirtual, int edad, boolean fotografia, Parque theParque, String cedula, String nombre, LocalDate fechaNacimiento, double estatura, String numeroTelefono, String nacionalidad){

        super(cedula,nombre,fechaNacimiento,estatura,numeroTelefono,nacionalidad);
        this.saldoVirtual=saldoVirtual;
        this.edad=edad;
        this.fotografia=fotografia;
        this.theParque = theParque;
        this.AtraccionesFavoritas = new ArrayList<>();
        this.listEntradas = new ArrayList<>();
        this.listNotificacionesAsignadas = new ArrayList<>();

    }

    @Override
    public String toString() {
        return super.toString() + "Visitante [saldoVirtual=" + saldoVirtual + ", edad=" + edad + ", fotografia=" + fotografia
                + ", theParque=" + theParque + "]";
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
		this.setNombre(nombre);
		this.setEstatura(estatura);
		this.setNumeroTelefono(numeroTelefono);
		this.setNacionalidad(nacionalidad);
		this.setEdad(edad);
    }

    // Comprar tiquetes

    public boolean comprarTickets(Parque parque, String cedula, int tipo){
        int index = this.getListEntradas().size()-1;
        if(this.getListEntradas().isEmpty()){
            index = 0;
        }
	    boolean sinEntradaActiva = this.getListEntradas().isEmpty() || !this.getListEntradas().get(this.getListEntradas().size()-1).isActiva();
        if(sinEntradaActiva && parque.getCapacidadMax() > parque.getListVisitantes(parque.getListPersonas()).size()){
		    Entrada nuevaentrada = new Entrada(null, LocalDate.now(), LocalDate.now().plusDays(5), false, null);
		    switch(tipo){
		    	case 1:
			    	if(this.pagarConSaldoVirtual(100000)==true){
				    	int num = 000;
				    	for(int i=0; i<parque.getListEntradas(parque.getListPersonas()).size(); i++){
					    	if(parque.getListEntradas(parque.getListPersonas()).get(i).getTipoEntrada() == TipoEntrada.GENERAL){
					    		num++;
					    	}
					    }
				    	nuevaentrada.setId("G"+String.format("%03d", num+1));
			    		nuevaentrada.setTipoEntrada(TipoEntrada.GENERAL);
				    	nuevaentrada.setActiva(true);
				    	this.getListEntradas().add(nuevaentrada);
				    	return true;
				    }
			    	nuevaentrada = null;
			    	return false;
			    case 2:
			    	if(this.pagarConSaldoVirtual(200000)==true){
			    		int num = 000;
			    		for(int i=0; i<parque.getListEntradas(parque.getListPersonas()).size(); i++){
					    	if(parque.getListEntradas(parque.getListPersonas()).get(i).getTipoEntrada() == TipoEntrada.FAMILIAR){
			    				num++;
			    			}
			    		}
			    		nuevaentrada.setId("F"+String.format("%03d", num+1));
			    		nuevaentrada.setTipoEntrada(TipoEntrada.FAMILIAR);
			    		nuevaentrada.setActiva(true);
			    		this.getListEntradas().add(nuevaentrada);
				    	return true;
				    }
			    	nuevaentrada = null;
			    	return false;
			    case 3:
			    	if(this.pagarConSaldoVirtual(170000)==true){
			    		int num = 000;
			    		for(int i=0; i<parque.getListEntradas(parque.getListPersonas()).size(); i++){
					    	if(parque.getListEntradas(parque.getListPersonas()).get(i).getTipoEntrada() == TipoEntrada.FAST_PASS){
			    				num++;
			    			}
			    		}
			    		nuevaentrada.setId("FP"+String.format("%03d", num+1));
			    		nuevaentrada.setTipoEntrada(TipoEntrada.FAST_PASS);
			    		nuevaentrada.setActiva(true);
			    		this.getListEntradas().add(nuevaentrada);
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

    // Ver lista tiquetes

    public String mostrarListEntradas(){
        String lista = "";
        if(this.getListEntradas().isEmpty()==false){
            for(int i=0; i<this.getListEntradas().size(); i++){
                lista += this.getListEntradas().get(i).toString() + "\n";
            }
        }else{
        lista = "No posees ningun ticket. Compra uno!";
        }
        return lista;
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
            boolean repetido = false;
            for(int k = 0; k<this.getAtraccionesFavoritas().size(); k++){
                if(this.getAtraccionesFavoritas().get(k) == parque.getListZonas().get(i).getListAtracciones().get(j)){
                    repetido = true;
                }
            }
            if(repetido == false){
                this.getAtraccionesFavoritas().add(parque.getListZonas().get(i).getListAtracciones().get(j));
	    	    return true;
            }
	    }
	    return false;
    }

    // Mostrar lista de Atracciones favoritas

    public String mostrarAtraccionesFavoritas(Parque parque){
	    String favoritas = "";
        if(this.getAtraccionesFavoritas().isEmpty()==false){
	        for(int i=0; i<this.getAtraccionesFavoritas().size(); i++){
	    	    favoritas += (this.getAtraccionesFavoritas().get(i).mostrarDatos() + "\n");
	        }
        }else{
        favoritas = "No tienes ninguna atraccion marcada como favorita. Marca una!";
        }
	    return favoritas;
    }

    // Mostrar notificaciones 

    public String mostrarNotificaciones(Parque parque){
	    String notificaciones = "";
	    for(int i=0; i<this.getListNotificacionesAsignadas().size(); i++){
	    	notificaciones += (this.getListNotificacionesAsignadas().get(i).getTheNotificacion().toString() + "\n");
	    }
	    return notificaciones;
    }

    // Unirse a la cola virtual de una Atraccion

    public boolean unirseAColaVirtualAtraccion(Parque parque, Atraccion atraccion){
        if(this.getListEntradas().isEmpty() != true){
            if(this.getListEntradas().get(this.getListEntradas().size()-1).isActiva()==true){
                if(this.pagarConSaldoVirtual(atraccion.getCostoAdicional())){
                    return atraccion.procesarSolicitudCola(this, atraccion);
                }
                return false;
            }
            return false;
        }
        return false;
    }

    // Recargar Saldo Virtual 

    public void RecargarSaldoVirtual(int cantidad){
	    this.setSaldoVirtual(cantidad);
    }

    // Pagar usando Saldo Virtual

    public boolean pagarConSaldoVirtual(double cantidad){
        System.out.println(this.getSaldoVirtual()>=cantidad);
	    if(this.getSaldoVirtual()>=cantidad){
		    this.setSaldoVirtual(this.getSaldoVirtual()-cantidad);
            System.out.println(this.getSaldoVirtual());
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

    public void setAtraccionesFavoritas(ArrayList<Atraccion> atraccionesFavoritas) {
        AtraccionesFavoritas = atraccionesFavoritas;
    }

    public List<Entrada> getListEntradas() {
        return listEntradas;
    }

    public void setListEntradas(ArrayList<Entrada> listEntradas) {
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

    public void setListNotificacionesAsignadas(ArrayList<NotificacionAsignada> listNotificacionesAsignadas) {
        this.listNotificacionesAsignadas = listNotificacionesAsignadas;
    }

}
