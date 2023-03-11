module com.client {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires okhttp3;
    requires kotlin.stdlib;
    requires org.json;
    requires org.controlsfx.controls;
    requires com.sanctionco.jmail;

    opens com.client to javafx.fxml;
    exports com.client;
    exports com.client.controllers;
    exports com.client.model;
    opens com.client.controllers to javafx.fxml;
    exports com.client.managers;
    opens com.client.managers to javafx.fxml;
}