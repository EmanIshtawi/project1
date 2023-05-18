module com.example.project {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires com.oracle.database.jdbc;
    requires jasperreports;
    requires java.naming;


    opens com.example.project to javafx.fxml;
    exports com.example.project;
}