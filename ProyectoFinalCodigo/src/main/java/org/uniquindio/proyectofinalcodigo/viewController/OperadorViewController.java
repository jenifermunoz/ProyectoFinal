package org.uniquindio.proyectofinalcodigo.viewController;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

import org.uniquindio.proyectofinalcodigo.App;
import org.uniquindio.proyectofinalcodigo.controller.OperadorController;
import org.uniquindio.proyectofinalcodigo.model.*;

public class OperadorViewController {

    @FXML private Label lblNombreOperador;
    @FXML private Label lblZonaAsignada;
    @FXML private ListView<String> listAtracciones;
    @FXML private TextField txtCapacidad;
    @FXML private TextField txtDescripcion;
    @FXML private Label lblStatus;
    @FXML private TextArea txtInfoAtraccion;
    @FXML private TextArea txtRevisiones;
    @FXML private ListView<Visitante> listVisitantesAtraccion;
    @FXML private ListView<Visitante> listColaVirtualGF;
    @FXML private ListView<Visitante> listColaVirtualFF;

    private App app;
    private OperadorController oc;

    public void setApp(App app) {
        this.app = app;
        this.oc = new OperadorController(App.parque, App.operadorActivo);
        cargarDatos();
    }

    private void cargarDatos() {
        Operador op = oc.getOperadorActivo();
        if (lblNombreOperador != null) lblNombreOperador.setText("Operador: " + op.getNombre());
        String zona = op.getTheZona() != null ? op.getTheZona().getNombre() : "Sin zona";
        if (lblZonaAsignada != null) lblZonaAsignada.setText("Zona: " + zona);
        cargarListaAtracciones();
    }

    private void cargarListaAtracciones() {
        if (listAtracciones == null) return;
        ObservableList<String> items = FXCollections.observableArrayList();
        for (Atraccion a : oc.getAtraccionesDeZona()) {
            items.add(a.getNombre() + " | " + a.getEstado() + " | Cap: " + a.getCapacidadMaxima());
        }
        listAtracciones.setItems(items);
    }

    private Atraccion getAtraccionSeleccionada() {
        if (listAtracciones == null) return null;
        int idx = listAtracciones.getSelectionModel().getSelectedIndex();
        if (idx < 0) return null;
        return oc.getAtraccionesDeZona().get(idx);
    }

    @FXML
    private void verInfoAtraccion() {
        Atraccion a = getAtraccionSeleccionada();
        if (a == null) { setStatus("Seleccione una atraccion."); return; }
        if (txtInfoAtraccion != null) txtInfoAtraccion.setText(a.mostrarDatos());
    }

    @FXML
    private void activarAtraccion() {
        Atraccion a = getAtraccionSeleccionada();
        if (a == null) { setStatus("Seleccione una atraccion."); return; }
        String desc = txtDescripcion != null ? txtDescripcion.getText() : "";
        boolean ok = oc.registrarEstado(a, 1, desc);
        setStatus(ok ? "Atraccion activada." : "No se pudo activar (debe estar en mantenimiento).");
        cargarListaAtracciones();
    }

    @FXML
    private void ponerEnMantenimiento() {
        Atraccion a = getAtraccionSeleccionada();
        if (a == null) { setStatus("Seleccione una atraccion."); return; }
        String desc = txtDescripcion != null ? txtDescripcion.getText() : "";
        boolean ok = oc.registrarEstado(a, 2, desc);
        setStatus(ok ? "Atraccion en mantenimiento." : "No se pudo cambiar (debe estar activa).");
        cargarListaAtracciones();
    }

    @FXML
    private void iniciarRevision() {
        Atraccion a = getAtraccionSeleccionada();
        if (a == null) { setStatus("Seleccione una atraccion."); return; }
        String desc = txtDescripcion != null ? txtDescripcion.getText() : "Revision de rutina";
        boolean ok = oc.registrarRevision(a, desc);
        setStatus(ok ? "Revision iniciada." : "Ya hay una revision activa.");
        cargarListaAtracciones();
    }

    @FXML
    private void finalizarRevision() {
        Atraccion a = getAtraccionSeleccionada();
        if (a == null) { setStatus("Seleccione una atraccion."); return; }
        String desc = txtDescripcion != null ? txtDescripcion.getText() : "Revision finalizada";
        boolean ok = oc.finalizarRevision(a, desc);
        setStatus(ok ? "Revision finalizada." : "No hay revision activa para finalizar.");
        cargarListaAtracciones();
    }

    @FXML
    private void mostrarRevisionesTecnicas(){
        if (txtRevisiones != null) txtRevisiones.setText(oc.mostrarRevisionesTecnicas());
    }

    @FXML
    private void cambiarCapacidad() {
        Atraccion a = getAtraccionSeleccionada();
        if (a == null) { setStatus("Seleccione una atraccion."); return; }
        try {
            int cap = Integer.parseInt(txtCapacidad != null ? txtCapacidad.getText() : "0");
            oc.cambiarCapacidad(cap, a);
            setStatus("Capacidad actualizada a " + cap + ".");
            cargarListaAtracciones();
        } catch (NumberFormatException e) {
            setStatus("Ingrese un numero valido.");
        }
    }

    @FXML
    private void mostrarColaVirtualGF() {
        Atraccion a = getAtraccionSeleccionada();
        ArrayList<Visitante> datos = oc.mostrarColaVirtualGF(a);

        System.out.println("Visitantes encontrados: " + datos.size());

        ObservableList<Visitante> listaObservable = FXCollections.observableArrayList(datos);
        listColaVirtualGF.setItems(listaObservable);
    }

    @FXML
    private void mostrarColaVirtualFF() {
        Atraccion a = getAtraccionSeleccionada();
        ArrayList<Visitante> datos = oc.mostrarColaVirtualFF(a);
        ObservableList<Visitante> listaObservable = FXCollections.observableArrayList(datos);
        listColaVirtualFF.setItems(listaObservable);
    }

    @FXML
    private void mostrarVisitantesAtraccion() {
        Atraccion a = getAtraccionSeleccionada();
        ArrayList<Visitante> datos = oc.mostrarVisitantesAtraccion(a);
        ObservableList<Visitante> listaObservable = FXCollections.observableArrayList(datos);
        listVisitantesAtraccion.setItems(listaObservable);
    }

    @FXML 
    private void actualizarColasVirtuales(){
        Atraccion a = getAtraccionSeleccionada();
        if (a == null) { setStatus("Seleccione una atraccion."); return; }
        mostrarColaVirtualFF();
        mostrarColaVirtualGF();
        setStatus("Colas Virtuales actualizadas.");
    }

    @FXML
    private void permitirAccesoAtraccion(){
        Atraccion a = getAtraccionSeleccionada();
        if (a == null) { setStatus("Seleccione una atraccion."); return; }
        boolean ok = oc.permitirAcceso(a);
        setStatus(ok ? "Visitantes ingresados a la atraccion." : "Retire los visitantes actuales primero.");
        mostrarColaVirtualFF();
        mostrarColaVirtualGF();
        mostrarVisitantesAtraccion();
        cargarListaAtracciones();
    }

    @FXML
    private void retirarVisitantes() {
        Atraccion a = getAtraccionSeleccionada();
        if (a == null) { setStatus("Seleccione una atraccion."); return; }
        boolean ok = oc.retirarVisitantes(a);
        setStatus(ok ? "Visitantes retirados." : "No habia visitantes.");
        mostrarVisitantesAtraccion();
        cargarListaAtracciones();
    }

    @FXML
    private void cerrarSesion() {
        App.operadorActivo = null;
        app.abrirLogin();
    }

    private void setStatus(String msg) {
        if (lblStatus != null) lblStatus.setText(msg);
    }
}
