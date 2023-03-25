module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.xml;
    requires jakarta.json;
    requires jakarta.json.bind;

    opens com.example.demo to javafx.fxml;
    exports com.example.demo;
}