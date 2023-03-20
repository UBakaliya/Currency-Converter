module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.controlsfx.controls;
    requires com.fasterxml.jackson.core;

    opens com.example.currency_converter to javafx.fxml;
    exports com.example.currency_converter;
}