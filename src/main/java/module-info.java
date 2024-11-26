module ru.kafpin.lr6_bzz {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.slf4j;

    opens ru.kafpin.lr6_bzz to javafx.fxml;
    exports ru.kafpin.lr6_bzz;
    exports ru.kafpin.lr6_bzz.controllers;
    opens ru.kafpin.lr6_bzz.controllers to javafx.fxml;
}