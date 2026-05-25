package org.uniquindio.proyectofinalcodigo.model;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Visitante extends Persona {
    private double saldoVirtual;
    private int edad;
    private boolean fotografia;
    private List<Atraccion> AtraccionesFavoritas;
    private List<Entrada> listEntrada;

    public Visitante(double saldoVirtual, int edad, boolean fotografia, String cedula, String nombre, LocalDate fechaNacimiento, double estatura, String numeroTelefono, String nacionalidad){

        super(cedula,nombre,fechaNacimiento,estatura,numeroTelefono,nacionalidad);
        this.saldoVirtual=saldoVirtual;
        this.edad=edad;
        this.fotografia=fotografia;
        this.AtraccionesFavoritas = new ArrayList<>();
        this.listEntrada = new ArrayList<>();

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

    public List<Entrada> getListEntrada() {
        return listEntrada;
    }

    public void setListEntrada(List<Entrada> listEntrada) {
        this.listEntrada = listEntrada;
    }

}
