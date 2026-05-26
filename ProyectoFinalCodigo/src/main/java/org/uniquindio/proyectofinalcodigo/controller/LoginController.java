package org.uniquindio.proyectofinalcodigo.controller;

import org.uniquindio.proyectofinalcodigo.model.Administrador;
import org.uniquindio.proyectofinalcodigo.model.Operador;
import org.uniquindio.proyectofinalcodigo.model.Parque;
import org.uniquindio.proyectofinalcodigo.model.Visitante;

public class LoginController {

    private Parque theParque;

    private Visitante visitanteActivo;
    private Operador operadorActivo;
    private Administrador adminActivo;

    public LoginController(Parque theParque){
        this.theParque = theParque;
    }

    public Visitante loginVisitante(String cedula){
        if(theParque.buscarVisitantebyCedula(cedula)!=-1){
            Visitante visitante = theParque.getListVisitantes(theParque.getListPersonas()).get(theParque.buscarVisitantebyCedula(cedula));
            return visitante;
        }
        return null;
    }

    

    public Operador loginOperador(String cedula){
        if(theParque.buscarOperadorByCedula(cedula)!=-1){
        Operador operador = theParque.getListOperadores(theParque.getListPersonas()).get(theParque.buscarOperadorByCedula(cedula));
        return operador;
        }
        return null;
    }

    public Administrador loginAdmin(String cedula){
        if(theParque.buscarAdministradorByCedula(cedula)!=-1){
        Administrador admin = theParque.getListAdministradores(theParque.getListPersonas()).get(theParque.buscarAdministradorByCedula(cedula));
        return admin;
        }
        return null;
    }

}
