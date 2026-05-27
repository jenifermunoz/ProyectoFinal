module org.uniquindio.proyectofinalcodigo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.logging;

    opens org.uniquindio.proyectofinalcodigo to javafx.fxml;
    exports org.uniquindio.proyectofinalcodigo;

    opens org.uniquindio.proyectofinalcodigo.viewController to javafx.fxml;
    exports org.uniquindio.proyectofinalcodigo.viewController;

    opens org.uniquindio.proyectofinalcodigo.controller to javafx.fxml;
    exports org.uniquindio.proyectofinalcodigo.controller;

    exports org.uniquindio.proyectofinalcodigo.model;
}
