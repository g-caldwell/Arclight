module com.caldwell.arclight {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.caldwell.arclight.bodies to javafx.base;
    opens com.caldwell.arclight to javafx.fxml;
    opens com.caldwell.arclight.journal to javafx.fxml;
    opens com.caldwell.arclight.manager to javafx.fxml;

    exports com.caldwell.arclight;
}