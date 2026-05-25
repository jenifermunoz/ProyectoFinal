package org.uniquindio.proyectofinalcodigo.model;

import java.time.LocalDate;

public class Administrador extends Persona {

    public Administrador(String cedula, String nombre, LocalDate fechaNacimiento, double estatura, String numeroTelefono, String nacionalidad) {
        super(cedula, nombre, fechaNacimiento, estatura, numeroTelefono, nacionalidad);
    }

}

