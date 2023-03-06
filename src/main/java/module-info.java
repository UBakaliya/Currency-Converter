module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.example.currency_exchanger to javafx.fxml;
    exports com.example.currency_exchanger;
}