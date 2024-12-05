module ru.kafpin.lr6_bzz {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;
    requires java.sql;
    requires com.fasterxml.jackson.databind;
    requires org.json;


    opens ru.kafpin.lr6_bzz.domains to com.fasterxml.jackson.databind,org.json;
    opens ru.kafpin.lr6_bzz to javafx.fxml;
    exports ru.kafpin.lr6_bzz;
    exports ru.kafpin.lr6_bzz.controllers;
    exports ru.kafpin.lr6_bzz.domains;
    opens ru.kafpin.lr6_bzz.controllers to javafx.fxml;
}