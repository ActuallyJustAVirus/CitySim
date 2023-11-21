module com.sim {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.sim to javafx.fxml;
    exports com.sim;
    opens com.sim.gui to javafx.fxml;
    exports com.sim.gui;
}
