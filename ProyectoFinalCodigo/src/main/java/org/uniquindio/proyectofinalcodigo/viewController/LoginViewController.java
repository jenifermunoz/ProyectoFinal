package org.uniquindio.proyectofinalcodigo.viewController;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.uniquindio.proyectofinalcodigo.App;
import org.uniquindio.proyectofinalcodigo.controller.LoginController;
import org.uniquindio.proyectofinalcodigo.model.*;

public class LoginViewController {

    @FXML private TextField txtCedula;
    @FXML private Label lblError;

    private App app;
    private LoginController loginController;

    public void setApp(App app) {
        this.app = app;
        this.loginController = new LoginController(App.parque);
    }

    @FXML
    private void LoginVisitante() {
        String cedula = txtCedula.getText().trim();
        Visitante v = loginController.loginVisitante(cedula);
        if (v != null) {
            App.visitanteActivo = v;
            app.abrirPanelVisitante();
        } else {
            
            // Si no existe, ofrecer registro
            lblError.setText("Cedula no encontrada. Regístrese primero.");
        }
    }

    @FXML
    private void LoginOperador() {
        String cedula = txtCedula.getText().trim();
        Operador op = loginController.loginOperador(cedula);
        if (op != null) {
            App.operadorActivo = op;
            app.abrirPanelOperador();
        } else {
            lblError.setText("Operador no encontrado.");
        }
    }

    @FXML
    private void LoginAdmin() {
        String cedula = txtCedula.getText().trim();
        Administrador admin = loginController.loginAdmin(cedula);
        if (admin != null) {
            App.adminActivo = admin;
            app.abrirPanelAdmin();
        } else {
            lblError.setText("Administrador no encontrado.");
        }
    }

    @FXML
    private void RegistrarVisitante() {
        // Abre dialogo de registro
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Registro de Visitante");
        dialog.setHeaderText("Complete sus datos:");

        TextField fCedula = new TextField(); fCedula.setPromptText("Cedula");
        TextField fNombre = new TextField(); fNombre.setPromptText("Nombre");
        TextField fEdad = new TextField(); fEdad.setPromptText("Edad");
        TextField fEstatura = new TextField(); fEstatura.setPromptText("Estatura (ej: 1.70)");
        TextField fTelefono = new TextField(); fTelefono.setPromptText("Telefono");
        TextField fSaldo = new TextField(); fSaldo.setPromptText("Saldo inicial");

        javafx.scene.layout.VBox box = new javafx.scene.layout.VBox(8,
                new Label("Cedula:"), fCedula,
                new Label("Nombre:"), fNombre,
                new Label("Edad:"), fEdad,
                new Label("Estatura:"), fEstatura,
                new Label("Telefono:"), fTelefono,
                new Label("Saldo:"), fSaldo);
        dialog.getDialogPane().setContent(box);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        dialog.showAndWait().ifPresent(bt -> {
            if (bt == ButtonType.OK) {
                try {
                    String cedula2 = fCedula.getText().trim();
                    String nombre = fNombre.getText().trim();
                    int edad = Integer.parseInt(fEdad.getText().trim());
                    double estatura = Double.parseDouble(fEstatura.getText().trim());
                    String tel = fTelefono.getText().trim();
                    double saldo = Double.parseDouble(fSaldo.getText().trim());

                    if (App.parque.buscarVisitantebyCedula(cedula2) == -1) {
                        Visitante nuevo = new Visitante(saldo, edad, false, App.parque, cedula2, nombre,
                                java.time.LocalDate.now().minusYears(edad), estatura, tel, "Colombiana");
                        App.parque.getListPersonas().add(nuevo);
                        App.visitanteActivo = nuevo;
                        app.abrirPanelVisitante();
                    } else {
                        lblError.setText("Ya existe un visitante con esa cedula.");
                    }
                } catch (Exception ex) {
                    lblError.setText("Datos invalidos, verifique los campos.");
                }
            }
        });
    }
}
