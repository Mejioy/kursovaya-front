package ru.kafpin.lr6_bzz.controllers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;
public class RemoveController {
    private Stage removeStage;
    public void setRemoveStage(Stage removeStage) {
        this.removeStage = removeStage;
    }
    private boolean remove=false;
    public boolean isRemove() {
        return remove;
    }
    public void setRemove(boolean remove) {
        this.remove = remove;
    }
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