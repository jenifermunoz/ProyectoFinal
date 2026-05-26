module org.uniquindio.proyectofinalcodigo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.logging;


    opens org.uniquindio.proyectofinalcodigo to javafx.fxml;
    exports org.uniquindio.proyectofinalcodigo;
}