package ru.kafpin.lr6_bzz.controllers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ru.kafpin.lr6_bzz.domains.Client;

import java.util.ResourceBundle;

public class EditClientController {
    private Client client;
    public void setEditStage(Stage editStage) {
        this.editStage = editStage;
    }
    private ResourceBundle bundle;
    public void setBundle(ResourceBundle bundle) {
        this.bundle = bundle;
    }
    public boolean isAction() {
        return action;
    }
    private boolean action=false;
    private Stage editStage;
    public void setClient(Client client) {
        this.client = client;
        tSurName.setText(client.getSurName());
        tName.setText(client.getName());
        tPatronym.setText(client.getPatronym());
        tPhone.setText(client.getPhone());
    }
    @FXML
    private TextField tName;
    @FXML
    private TextField tPatronym;
    @FXML
    private TextField tPhone;
    @FXML
    private TextField tSurName;
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
        if (tSurName.getText()==null||tSurName.getText().trim().isEmpty())
            Error(bundle.getString("surnamefieldempty"));
        else if (tName.getText()==null||tName.getText().trim().isEmpty())
            Error(bundle.getString("firstnamefieldempty"));
        else if (tPatronym.getText()==null||tPatronym.getText().trim().isEmpty())
            Error(bundle.getString("patronymfieldempty"));
        else if (tPhone.getText()==null||tPhone.getText().trim().isEmpty())
            Error(bundle.getString("phonefieldempty"));
        else
        {
            client.setSurName(tSurName.getText().trim());
            client.setName(tName.getText().trim());
            client.setPatronym(tPatronym.getText().trim());
            client.setPhone(tPhone.getText().trim());
            action=true;
            editStage.close();
        }
    }
}