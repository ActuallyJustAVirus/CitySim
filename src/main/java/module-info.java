module com.sim {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.sim to javafx.fxml;
    exports com.sim;
}
