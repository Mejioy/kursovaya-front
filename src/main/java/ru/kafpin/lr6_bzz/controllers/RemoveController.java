package ru.kafpin.lr6_bzz.controllers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import lombok.Data;

@Data
public class RemoveController {
    private Stage removeStage;
    private boolean remove=false;
    @FXML
    void onCancel(ActionEvent event) {
        removeStage.close();
    }
    @FXML
    void onOK(ActionEvent event) {
        remove=true;
        removeStage.close();
    }
}