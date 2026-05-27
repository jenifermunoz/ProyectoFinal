package org.uniquindio.proyectofinalcodigo.viewController;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.uniquindio.proyectofinalcodigo.App;
import org.uniquindio.proyectofinalcodigo.controller.AdministradorController;
import org.uniquindio.proyectofinalcodigo.model.*;

import java.time.LocalDate;

public class AdministradorViewController {

    @FXML private Label lblNombreAdmin;
    @FXML private Label lblStatus;

    // Tabs de operadores
    @FXML private ListView<String> listOperadores;
    @FXML private TextField txtOpCedula, txtOpNombre, txtOpEstatura, txtOpTelefono;

    // Tabs de zonas
    @FXML private ListView<String> listZonas;
    @FXML private TextField txtZonaNombre, txtZonaUbicacion, txtZonaMax;

    // Tabs de atracciones
    @FXML private ListView<String> listAtraccionesAdmin;
    @FXML private TextField txtAtrNombre, txtAtrCap, txtAtrAltMin, txtAtrEdadMin, txtAtrCosto;
    @FXML private ChoiceBox<String> cbTipoAtraccion;
    @FXML private ChoiceBox<String> cbZonaAtraccion;

    // Asignaciones
    @FXML private ChoiceBox<String> cbOperadorAsignar;
    @FXML private ChoiceBox<String> cbZonaAsignar;
    @FXML private ChoiceBox<String> cbAtraccionAsignar;

    // Reporte y jornada
    @FXML private TextArea txtReporte;

    private App app;
    private AdministradorController ac;

    public void setApp(App app) {
        this.app = app;
        this.ac = new AdministradorController(App.parque, App.adminActivo);
        cargarDatos();
    }

    private void cargarDatos() {
        if (lblNombreAdmin != null) lblNombreAdmin.setText("Admin: " + App.adminActivo.getNombre());
        cargarListaOperadores();
        cargarListaZonas();
        cargarListaAtracciones();
        cargarChoiceBoxes();
    }

    private void cargarListaOperadores() {
        if (listOperadores == null) return;
        ObservableList<String> items = FXCollections.observableArrayList();
        for (Operador op : ac.getOperadores())
            items.add(op.getCedula() + " - " + op.getNombre());
        listOperadores.setItems(items);
    }

    private void cargarListaZonas() {
        if (listZonas == null) return;
        ObservableList<String> items = FXCollections.observableArrayList();
        for (Zona z : ac.getZonas())
            items.add(z.getNombre() + " | " + z.getUbicacion() + " | Max:" + z.getVisitantesMax());
        listZonas.setItems(items);
    }

    private void cargarListaAtracciones() {
        if (listAtraccionesAdmin == null) return;
        ObservableList<String> items = FXCollections.observableArrayList();
        for (Atraccion a : ac.getAtracciones())
            items.add(a.getNombre() + " | " + a.getEstado() + " | " + a.getTipo());
        listAtraccionesAdmin.setItems(items);
    }

    private void cargarChoiceBoxes() {
        if (cbTipoAtraccion != null) {
            cbTipoAtraccion.setItems(FXCollections.observableArrayList(
                "MECANICA_TERRESTRE", "MECANICA_DE_ALTURA", "ACUATICA", "SHOW", "OTRO"));
        }
        ObservableList<String> nombresZonas = FXCollections.observableArrayList();
        for (Zona z : ac.getZonas()) nombresZonas.add(z.getNombre());
        if (cbZonaAtraccion != null) cbZonaAtraccion.setItems(nombresZonas);
        if (cbZonaAsignar != null) cbZonaAsignar.setItems(FXCollections.observableList(nombresZonas));

        ObservableList<String> nombresOps = FXCollections.observableArrayList();
        for (Operador op : ac.getOperadores()) nombresOps.add(op.getCedula() + " - " + op.getNombre());
        if (cbOperadorAsignar != null) cbOperadorAsignar.setItems(nombresOps);

        ObservableList<String> nombresAtrs = FXCollections.observableArrayList();
        for (Atraccion a : ac.getAtracciones()) nombresAtrs.add(a.getNombre());
        if (cbAtraccionAsignar != null) cbAtraccionAsignar.setItems(nombresAtrs);
    }

    // ---- Operadores ----

    @FXML
    private void registrarOperador() {
        try {
            boolean ok = ac.registrarOperador(
                txtOpCedula.getText(), txtOpNombre.getText(),
                LocalDate.now().minusYears(30),
                Double.parseDouble(txtOpEstatura.getText()),
                txtOpTelefono.getText(), "Colombiana");
            setStatus(ok ? "Operador registrado." : "Ya existe ese operador.");
            cargarListaOperadores(); cargarChoiceBoxes();
        } catch (Exception e) { setStatus("Error: verifique los datos."); }
    }

    @FXML
    private void modificarOperador() {
        try {
            boolean ok = ac.modificarOperador(
                txtOpCedula.getText(), txtOpNombre.getText(),
                Double.parseDouble(txtOpEstatura.getText()),
                txtOpTelefono.getText(), "Colombiana");
            setStatus(ok ? "Operador actualizado." : "Operador no encontrado.");
            cargarListaOperadores();
        } catch (Exception e) { setStatus("Error: verifique los datos."); }
    }

    @FXML
    private void despedirOperador() {
        boolean ok = ac.despedirOperador(txtOpCedula.getText());
        setStatus(ok ? "Operador despedido." : "Operador no encontrado.");
        cargarListaOperadores(); cargarChoiceBoxes();
    }

    @FXML
    private void verDatosOperador() {
        String resultado = ac.consultarDatos(1, txtOpCedula.getText());
        if (txtReporte != null) txtReporte.setText(resultado);
    }

    // ---- Zonas ----

    @FXML
    private void registrarZona() {
        try {
            int max = Integer.parseInt(txtZonaMax.getText());
            boolean ok = ac.registrarZona(txtZonaNombre.getText(), txtZonaUbicacion.getText(), max);
            setStatus(ok ? "Zona creada." : "Ya existe esa zona.");
            cargarListaZonas(); cargarChoiceBoxes();
        } catch (Exception e) { setStatus("Error: verifique los datos."); }
    }

    @FXML
    private void modificarZona() {
        try {
            int max = Integer.parseInt(txtZonaMax.getText());
            boolean ok = ac.modificarZona(txtZonaNombre.getText(), max);
            setStatus(ok ? "Zona actualizada." : "Zona no encontrada.");
            cargarListaZonas();
        } catch (Exception e) { setStatus("Error: verifique los datos."); }
    }

    @FXML
    private void eliminarZona() {
        boolean ok = ac.eliminarZona(txtZonaNombre.getText());
        setStatus(ok ? "Zona eliminada." : "Zona no encontrada.");
        cargarListaZonas(); cargarChoiceBoxes();
    }

    @FXML
    private void verDatosZona() {
        String resultado = ac.consultarDatos(2, txtZonaNombre.getText());
        if (txtReporte != null) txtReporte.setText(resultado);
    }

    // ---- Atracciones ----

    @FXML
    private void registrarAtraccion() {
        try {
            String nombreZona = cbZonaAtraccion != null ? cbZonaAtraccion.getValue() : "";
            int idxZona = App.parque.buscarZonaByNombre(nombreZona);
            if (idxZona == -1) { setStatus("Seleccione una zona valida."); return; }
            Zona zona = App.parque.getListZonas().get(idxZona);

            String tipoStr = cbTipoAtraccion != null ? cbTipoAtraccion.getValue() : "OTRO";
            TipoAtraccion tipo = TipoAtraccion.valueOf(tipoStr != null ? tipoStr : "OTRO");

            boolean ok = ac.registrarAtraccion(zona, txtAtrNombre.getText(),
                Integer.parseInt(txtAtrCap.getText()),
                Double.parseDouble(txtAtrAltMin.getText()),
                Integer.parseInt(txtAtrEdadMin.getText()),
                Double.parseDouble(txtAtrCosto.getText()), tipo);
            setStatus(ok ? "Atraccion creada." : "Ya existe o no hay operadores suficientes.");
            cargarListaAtracciones(); cargarChoiceBoxes();
        } catch (Exception e) { setStatus("Error: verifique los datos."); }
    }

    @FXML
    private void eliminarAtraccion() {
        boolean ok = ac.eliminarAtraccion(txtAtrNombre.getText());
        setStatus(ok ? "Atraccion eliminada." : "Atraccion no encontrada.");
        cargarListaAtracciones(); cargarChoiceBoxes();
    }

    @FXML
    private void verDatosAtraccion() {
        String resultado = ac.consultarDatos(3, txtAtrNombre.getText());
        if (txtReporte != null) txtReporte.setText(resultado);
    }

    // ---- Asignaciones ----

    @FXML
    private void asignarOpAZona() {
        if (cbOperadorAsignar == null || cbZonaAsignar == null) return;
        int idxOp = cbOperadorAsignar.getSelectionModel().getSelectedIndex();
        int idxZona = cbZonaAsignar.getSelectionModel().getSelectedIndex();
        if (idxOp < 0 || idxZona < 0) { setStatus("Seleccione operador y zona."); return; }
        Operador op = ac.getOperadores().get(idxOp);
        Zona zona = ac.getZonas().get(idxZona);
        boolean ok = ac.asignarOperadorAZona(op, zona);
        op.setTheZona(zona);
        setStatus(ok ? "Operador asignado a zona." : "El operador ya tiene zona.");
    }

    @FXML
    private void asignarOpAAtraccion() {
        if (cbOperadorAsignar == null || cbAtraccionAsignar == null) return;
        int idxOp = cbOperadorAsignar.getSelectionModel().getSelectedIndex();
        int idxAtr = cbAtraccionAsignar.getSelectionModel().getSelectedIndex();
        if (idxOp < 0 || idxAtr < 0) { setStatus("Seleccione operador y atraccion."); return; }
        Operador op = ac.getOperadores().get(idxOp);
        Atraccion atr = ac.getAtracciones().get(idxAtr);
        boolean ok = ac.asignarOperadorAAtraccion(op, atr);
        setStatus(ok ? "Operador asignado a atraccion." : "No pertenece a la misma zona.");
    }

    // ---- Alertas ----

    @FXML
    private void activarLluvia() {
        ac.activarAlerta(1);
        setStatus("Alerta de lluvia fuerte activada. Atracciones acuaticas y de altura cerradas.");
        cargarListaAtracciones();
    }

    @FXML
    private void activarTormenta() {
        ac.activarAlerta(2);
        setStatus("Alerta de tormenta activada. Atracciones acuaticas y de altura cerradas.");
        cargarListaAtracciones();
    }

    @FXML
    private void desactivarAlerta() {
        ac.desactivarAlerta();
        setStatus("Alerta climatica desactivada.");
        cargarListaAtracciones();
    }

    // ---- Jornada ----

    @FXML
    private void terminarJornada() {
        ac.terminarJornada();
        setStatus("Jornada terminada. Puede consultar el reporte.");
    }

    @FXML
    private void consultarReporte() {
        String reporte = ac.consultarReporte();
        if (txtReporte != null) txtReporte.setText(reporte);
    }

    @FXML
    private void cerrarSesion() {
        App.adminActivo = null;
        app.abrirLogin();
    }

    private void setStatus(String msg) {
        if (lblStatus != null) lblStatus.setText(msg);
    }
}
