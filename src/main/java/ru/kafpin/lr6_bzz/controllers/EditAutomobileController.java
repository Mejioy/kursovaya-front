package ru.kafpin.lr6_bzz.controllers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lombok.Data;
import ru.kafpin.lr6_bzz.domains.Automobile;

import java.util.ResourceBundle;

@Data
public class EditAutomobileController {
    private Automobile automobile;
    private Stage editStage;
    private ResourceBundle bundle;
    private boolean action=false;

    public void setAutomobile(Automobile automobile) {
        this.automobile = automobile;
        tMark.setText(automobile.getMark());
        tModel.setText(automobile.getModel());
        tGosnumber.setText(automobile.getGosnumber());
    }
    @FXML
    private TextField tGosnumber;
    @FXML
    private TextField tMark;
    @FXML
    private TextField tModel;
    @FXML
    void onCancel(ActionEvent event) {
        editStage.close();
    }
    private void Error(String text){
        Alert alert;
        alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(bundle.getString("error"));
        alert.setContentText(null);
        alert.setHeaderText(text);
        alert.showAndWait();
    }
    @FXML
    void onOK(ActionEvent event) {
        if (tMark.getText()==null||tMark.getText().trim().isEmpty())
            Error(bundle.getString("markfieldempty"));
        else if (tModel.getText()==null||tModel.getText().trim().isEmpty())
            Error(bundle.getString("modelfieldempty"));
        else if (tGosnumber.getText()==null||tGosnumber.getText().trim().isEmpty())
            Error(bundle.getString("gosnumberfieldempty"));
        else
        {
            if(!tGosnumber.getText().matches("^[ABEKMHOPCTYX]\\d{3}(?<!000)[ABEKMHOPCTYX]{2}\\d{2,3}rus$")){
                Error("Гос.номер должен быть введён в формате: A777AA33rus");
            }
            else{
                automobile.setMark(tMark.getText().trim());
                automobile.setModel(tModel.getText().trim());
                automobile.setGosnumber(tGosnumber.getText().trim());
                action=true;
                editStage.close();
            }
        }
    }
}