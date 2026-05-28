package org.uniquindio.proyectofinalcodigo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.uniquindio.proyectofinalcodigo.model.*;
import org.uniquindio.proyectofinalcodigo.viewController.*;

import java.io.IOException;
import java.time.LocalDate;

public class App extends Application {

    private Stage primaryStage;

    // Instancia global del parque
    public static Parque parque;

    // Personas activas en sesion
    public static Visitante visitanteActivo;
    public static Operador operadorActivo;
    public static Administrador adminActivo;

    @Override
    public void start(Stage stage) throws IOException {
        this.primaryStage = stage;
        inicializarDatos();
        abrirLogin();
    }

    // ---- Navegacion ----

    public void abrirLogin() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("inicio.fxml"));
            Pane root = loader.load();
            LoginViewController vc = loader.getController();
            vc.setApp(this);
            primaryStage.setScene(new Scene(root));
            primaryStage.setTitle("Tech-Park UQ");
            primaryStage.show();
        } catch (IOException e) { e.printStackTrace(); }
    }

    public void abrirPanelVisitante() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("visitante.fxml"));
            Pane root = loader.load();
            VisitanteViewController vc = loader.getController();
            vc.setApp(this);
            primaryStage.setScene(new Scene(root));
        } catch (IOException e) { e.printStackTrace(); }
    }

    public void abrirPanelOperador() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("operador.fxml"));
            Pane root = loader.load();
            OperadorViewController vc = loader.getController();
            vc.setApp(this);
            primaryStage.setScene(new Scene(root));
        } catch (IOException e) { e.printStackTrace(); }
    }

    public void abrirPanelAdmin() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("administrador.fxml"));
            Pane root = loader.load();
            AdministradorViewController vc = loader.getController();
            vc.setApp(this);
            primaryStage.setScene(new Scene(root));
        } catch (IOException e) { e.printStackTrace(); }
    }

    // ---- Datos de prueba ----

    private void inicializarDatos() {

        parque = new Parque("Tech-Park UQ", "Calle 12 # 15-23, Armenia", "Soleado",
                "900123456-1", "www.techparkuq.edu.co",
                true, 500, 0, true, LocalDate.now(), null);

        // Zonas
        Zona zonaAventura = new Zona("Z001", "Zona Aventura", parque, "Norte", 150);
        Zona zonaAcuatica = new Zona("Z002", "Zona Acuatica", parque, "Sur", 100);
        Zona zonaFamiliar = new Zona("Z003", "Zona Familiar", parque, "Centro", 200);
        parque.getListZonas().add(zonaAventura);
        parque.getListZonas().add(zonaAcuatica);
        parque.getListZonas().add(zonaFamiliar);

        // Atracciones - Zona Aventura
        ColaVirtual colaRusa = new ColaVirtual(null, 0);
        Atraccion montanaRusa = new Atraccion("A001", "Montana Rusa", 12, 1.40, 12, 0,
                5000, 0, 0, 0, TipoAtraccion.MECANICA_DE_ALTURA, null, EstadoAtraccion.ACTIVA, zonaAventura, colaRusa);
        colaRusa.setAtraccion(montanaRusa);
        zonaAventura.getListAtracciones().add(montanaRusa);
        parque.getListAtracciones().add(montanaRusa);

        ColaVirtual colaGiro = new ColaVirtual(null, 0);
        Atraccion toroMecanico = new Atraccion("A002", "Toro Mecanico", 8, 1.20, 10, 0,
                3000, 0, 0, 0, TipoAtraccion.MECANICA_TERRESTRE, null, EstadoAtraccion.ACTIVA, zonaAventura, colaGiro);
        colaGiro.setAtraccion(toroMecanico);
        zonaAventura.getListAtracciones().add(toroMecanico);
        parque.getListAtracciones().add(toroMecanico);

        // Atracciones - Zona Acuatica
        ColaVirtual colaRapidos = new ColaVirtual(null, 0);
        Atraccion rapidos = new Atraccion("A003", "Rapidos del Rio", 10, 1.10, 8, 0,
                2000, 0, 0, 0, TipoAtraccion.ACUATICA, null, EstadoAtraccion.ACTIVA, zonaAcuatica, colaRapidos);
        colaRapidos.setAtraccion(rapidos);
        zonaAcuatica.getListAtracciones().add(rapidos);
        parque.getListAtracciones().add(rapidos);

        // Atracciones - Zona Familiar
        ColaVirtual colaShow = new ColaVirtual(null, 0);
        Atraccion showMagico = new Atraccion("A004", "Show Magico", 50, 0.0, 0, 0,
                0, 0, 0, 0, TipoAtraccion.SHOW, null, EstadoAtraccion.ACTIVA, zonaFamiliar, colaShow);
        colaShow.setAtraccion(showMagico);
        zonaFamiliar.getListAtracciones().add(showMagico);
        parque.getListAtracciones().add(showMagico);

        // Operadores
        Operador op1 = new Operador(5, parque, zonaAventura, "OP001", "Carlos Mendez",
                LocalDate.of(1990, 3, 10), 1.78, "3201234567", "Colombiana");
        zonaAventura.getListOperadores().add(op1);
        montanaRusa.getListOperadores().add(op1);
        toroMecanico.getListOperadores().add(op1);
        parque.getListPersonas().add(op1);

        Operador op2 = new Operador(3, parque, zonaAcuatica, "OP002", "Laura Torres",
                LocalDate.of(1995, 7, 22), 1.65, "3119876543", "Colombiana");
        zonaAcuatica.getListOperadores().add(op2);
        rapidos.getListOperadores().add(op2);
        parque.getListPersonas().add(op2);

        Operador op3 = new Operador(7, parque, zonaFamiliar, "OP003", "Jorge Rios",
                LocalDate.of(1988, 11, 5), 1.72, "3005551234", "Colombiana");
        zonaFamiliar.getListOperadores().add(op3);
        showMagico.getListOperadores().add(op3);
        parque.getListPersonas().add(op3);

        // Administrador
        Administrador admin1 = new Administrador(parque, "AD001", "Sofia Vargas",
                LocalDate.of(1980, 1, 15), 1.68, "3001112233", "Colombiana");
        parque.getListPersonas().add(admin1);

        // Visitantes
        Visitante v1 = new Visitante(500000, 25, false, parque, "V001", "Andres Garcia",
                LocalDate.of(2000, 4, 12), 1.75, "3101112233", "Colombiana");
        Entrada e1 = new Entrada("G001", LocalDate.now(), LocalDate.now().plusDays(5), true, TipoEntrada.GENERAL);
        v1.getListEntradas().add(e1);
        parque.getListPersonas().add(v1);

        Visitante v2 = new Visitante(300000, 17, false, parque, "V002", "Maria Ruiz",
                LocalDate.of(2007, 9, 30), 1.58, "3204445566", "Colombiana");
        Entrada e2 = new Entrada("FP001", LocalDate.now(), LocalDate.now().plusDays(5), true, TipoEntrada.FAST_PASS);
        v2.getListEntradas().add(e2);
        parque.getListPersonas().add(v2);

        Visitante v3 = new Visitante(200000, 35, false, parque, "V003", "Roberto Castillo",
                LocalDate.of(1989, 6, 20), 1.80, "3007778899", "Colombiana");
        parque.getListPersonas().add(v3);
    }

    public static void main(String[] args) {
        launch();
    }
}
