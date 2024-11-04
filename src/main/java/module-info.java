module com.example.lab3f {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    requires org.mariadb.jdbc;
    requires org.slf4j;


    requires  waffle.jna;
    opens com.example.lab3f to javafx.fxml;
    exports com.example.lab3f;
}