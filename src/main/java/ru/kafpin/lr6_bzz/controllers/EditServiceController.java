package ru.kafpin.lr6_bzz.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lombok.Data;
import ru.kafpin.lr6_bzz.domains.Service;
import java.util.ResourceBundle;

@Data
public class EditServiceController {
    private Service service;
    private ResourceBundle bundle;
    private boolean action=false;
    private Stage editStage;

    public void setService(Service service) {
        this.service = service;
        tName.setText(service.getName());
        tPrice.setText(String.valueOf(service.getPrice()));
        if(service.getDescription()!=null)
            tDescription.setText(service.getDescription());
    }

    @FXML
    private TextArea tDescription;
    @FXML
    private TextField tName;
    @FXML
    private TextField tPrice;
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
        if(tName.getText()==null || tName.getText().trim().isEmpty())
            Error(bundle.getString("servicenamefieldempty"));
        else if (tPrice.getText()==null ||tPrice.getText().trim().isEmpty() || Integer.parseInt(tPrice.getText()) < 0)
            Error(bundle.getString("priceincorrect"));
        else
        {
            service.setName(tName.getText().trim());
            service.setPrice(Integer.parseInt(tPrice.getText().trim()));
            if(!tDescription.getText().trim().isEmpty())
                service.setDescription(tDescription.getText());
            else
                service.setNullDescription();
            action=true;
            editStage.close();
        }
    }
}