module org.uniquindio.proyectofinalcodigo {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.uniquindio.proyectofinalcodigo to javafx.fxml;
    exports org.uniquindio.proyectofinalcodigo;
}