module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.example.Currency_Exchanger to javafx.fxml;
    exports com.example.Currency_Exchanger;
}