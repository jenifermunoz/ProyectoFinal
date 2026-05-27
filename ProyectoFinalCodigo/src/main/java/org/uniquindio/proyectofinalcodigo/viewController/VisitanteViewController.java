package org.uniquindio.proyectofinalcodigo.viewController;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.uniquindio.proyectofinalcodigo.App;
import org.uniquindio.proyectofinalcodigo.controller.VisitanteController;
import org.uniquindio.proyectofinalcodigo.model.*;

public class VisitanteViewController {

    // Perfil
    @FXML private Label lblNombreUsuario;
    @FXML private Label lblSaldo;
    @FXML private Label lblEntradaActiva;
    @FXML private TextField txtNombre;
    @FXML private TextField txtEstatura;
    @FXML private TextField txtTelefono;
    @FXML private TextField txtEdad;

    // Tickets
    @FXML private Label lblMsgTicket;

    // Atracciones
    @FXML private ListView<String> listAtracciones;
    @FXML private Label lblInfoAtraccion;
    @FXML private Label lblMsgAtraccion;

    // Favoritas
    @FXML private TextArea txtFavoritas;

    // Notificaciones
    @FXML private TextArea txtNotificaciones;

    // Saldo
    @FXML private TextField txtRecarga;
    @FXML private Label lblMsgSaldo;

    // General
    @FXML private Label lblStatus;

    private App app;
    private VisitanteController vc;

    public void setApp(App app) {
        this.app = app;
        this.vc = new VisitanteController(App.parque, App.visitanteActivo);
        cargarDatos();
    }

    private void cargarDatos() {
        Visitante v = vc.getVisitanteActivo();
        lblNombreUsuario.setText("Bienvenido, " + v.getNombre());
        actualizarSaldo();
        actualizarEntrada();

        // Llenar lista de atracciones
        ObservableList<String> nombres = FXCollections.observableArrayList();
        for (Atraccion a : vc.getAtracciones()) {
            nombres.add(a.getNombre() + " [" + a.getEstado() + "]");
        }
        if (listAtracciones != null) listAtracciones.setItems(nombres);
    }

    private void actualizarSaldo() {
        if (lblSaldo != null)
            lblSaldo.setText("Saldo: $" + (int) vc.getVisitanteActivo().getSaldoVirtual());
    }

    private void actualizarEntrada() {
        if (lblEntradaActiva == null) return;
        var entradas = vc.getVisitanteActivo().getListEntradas();
        if (entradas.isEmpty()) {
            lblEntradaActiva.setText("Sin entrada activa");
        } else {
            Entrada ultima = entradas.get(entradas.size() - 1);
            lblEntradaActiva.setText(ultima.isActiva() ? "Entrada: " + ultima.getTipoEntrada() : "Sin entrada activa");
        }
    }

    @FXML
    private void comprarGeneral() {
        boolean ok = vc.comprarTicket(1);
        if (lblMsgTicket != null) lblMsgTicket.setText(ok ? "Ticket GENERAL comprado." : "No se pudo comprar (saldo insuficiente o ya tiene entrada activa).");
        actualizarSaldo(); actualizarEntrada();
    }

    @FXML
    private void comprarFamiliar() {
        boolean ok = vc.comprarTicket(2);
        if (lblMsgTicket != null) lblMsgTicket.setText(ok ? "Ticket FAMILIAR comprado." : "No se pudo comprar.");
        actualizarSaldo(); actualizarEntrada();
    }

    @FXML
    private void comprarFastPass() {
        boolean ok = vc.comprarTicket(3);
        if (lblMsgTicket != null) lblMsgTicket.setText(ok ? "Ticket FAST-PASS comprado." : "No se pudo comprar.");
        actualizarSaldo(); actualizarEntrada();
    }

    @FXML
    private void consultarTiempoEspera() {
        if (listAtracciones == null) return;
        int idx = listAtracciones.getSelectionModel().getSelectedIndex();
        if (idx < 0) { if (lblInfoAtraccion != null) lblInfoAtraccion.setText("Seleccione una atraccion."); return; }
        String nombre = vc.getAtracciones().get(idx).getNombre();
        String tiempo = vc.consultarTiempoEspera(nombre);
        if (lblInfoAtraccion != null) lblInfoAtraccion.setText("Espera: " + tiempo);
    }

    @FXML
    private void agregarFavorita() {
        if (listAtracciones == null) return;
        int idx = listAtracciones.getSelectionModel().getSelectedIndex();
        if (idx < 0) { if (lblMsgAtraccion != null) lblMsgAtraccion.setText("Seleccione una atraccion."); return; }
        String nombre = vc.getAtracciones().get(idx).getNombre();
        boolean ok = vc.agregarFavorita(nombre);
        if (lblMsgAtraccion != null) lblMsgAtraccion.setText(ok ? "Agregada a favoritas." : "No encontrada.");
    }

    @FXML
    private void unirseACola() {
        if (listAtracciones == null) return;
        int idx = listAtracciones.getSelectionModel().getSelectedIndex();
        if (idx < 0) { if (lblMsgAtraccion != null) lblMsgAtraccion.setText("Seleccione una atraccion."); return; }
        Atraccion a = vc.getAtracciones().get(idx);
        boolean ok = vc.unirseACola(a);
        if (lblMsgAtraccion != null) lblMsgAtraccion.setText(ok ? "Unido a la cola de: " + a.getNombre() : "No se pudo unir (requisitos no cumplidos o atraccion no activa).");
    }

    @FXML
    private void verFavoritas() {
        if (txtFavoritas != null) txtFavoritas.setText(vc.verFavoritas());
    }

    @FXML
    private void verNotificaciones() {
        if (txtNotificaciones != null) txtNotificaciones.setText(vc.verNotificaciones());
    }

    @FXML
    private void actualizarPerfil() {
        try {
            String nombre = txtNombre != null ? txtNombre.getText() : vc.getVisitanteActivo().getNombre();
            double estatura = txtEstatura != null ? Double.parseDouble(txtEstatura.getText()) : vc.getVisitanteActivo().getEstatura();
            String tel = txtTelefono != null ? txtTelefono.getText() : vc.getVisitanteActivo().getNumeroTelefono();
            int edad = txtEdad != null ? Integer.parseInt(txtEdad.getText()) : vc.getVisitanteActivo().getEdad();
            vc.actualizarPerfil(nombre, estatura, tel, vc.getVisitanteActivo().getNacionalidad(), edad);
            if (lblStatus != null) lblStatus.setText("Perfil actualizado.");
            cargarDatos();
        } catch (Exception e) {
            if (lblStatus != null) lblStatus.setText("Error: verifique los datos.");
        }
    }

    @FXML
    private void recargarSaldo() {
        try {
            int cantidad = Integer.parseInt(txtRecarga != null ? txtRecarga.getText() : "0");
            vc.recargarSaldo(cantidad);
            actualizarSaldo();
            if (lblMsgSaldo != null) lblMsgSaldo.setText("Saldo recargado.");
        } catch (NumberFormatException e) {
            if (lblMsgSaldo != null) lblMsgSaldo.setText("Ingrese un numero valido.");
        }
    }

    @FXML
    private void cerrarSesion() {
        App.visitanteActivo = null;
        app.abrirLogin();
    }
}
