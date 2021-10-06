module com.example.tiktakmalicek {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.tiktakmalicek to javafx.fxml;
    exports com.example.tiktakmalicek;
}