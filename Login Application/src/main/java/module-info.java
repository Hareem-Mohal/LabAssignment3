module com.example.labassign28 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.labassign28 to javafx.fxml;
    exports com.example.labassign28;
}