module com.example.projetfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.xml;


    opens com.example.projetfx to javafx.fxml;
    exports com.example.projetfx;
}