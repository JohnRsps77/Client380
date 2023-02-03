module com.csun.client {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.csun.client to javafx.fxml;
    exports com.csun.client;
}