package ru.kafpin.lr6_bzz.controllers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lombok.Data;
import ru.kafpin.lr6_bzz.domains.Employer;

import java.util.ResourceBundle;

@Data
public class EditEmployerController {
    private Employer employer;
    private ResourceBundle bundle;
    private boolean action=false;
    private Stage editStage;

    public void setEmployer(Employer employer) {
        this.employer = employer;
        tSurName.setText(employer.getSurName());
        tName.setText(employer.getName());
        tPatronym.setText(employer.getPatronym());
        tPhone.setText(employer.getPhone());
        tCity.setText(employer.getCity());
        tStreet.setText(employer.getStreet());
        tHouse.setText(String.valueOf(employer.getHouse()));
        if(employer.getAppartment()!=null)
            tAppartment.setText(String.valueOf(employer.getAppartment()));
    }

    @FXML
    private TextField tAppartment;
    @FXML
    private TextField tCity;
    @FXML
    private TextField tHouse;
    @FXML
    private TextField tName;
    @FXML
    private TextField tPatronym;
    @FXML
    private TextField tPhone;
    @FXML
    private TextField tStreet;
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
        if (tHouse.getText()==null||tHouse.getText().trim().isEmpty() ||  Integer.parseInt(tHouse.getText()) <= 0)
            Error(bundle.getString("houseincorrect"));
        else if (tAppartment.getText()!=null&&!tAppartment.getText().trim().isEmpty()&&Integer.parseInt(tAppartment.getText()) <= 0)
            Error(bundle.getString("appartmentincorrect"));
        else if (tSurName.getText()==null||tSurName.getText().trim().isEmpty())
            Error(bundle.getString("surnamefieldempty"));
        else if (tName.getText()==null||tName.getText().trim().isEmpty())
            Error(bundle.getString("firstnamefieldempty"));
        else if (tPatronym.getText()==null||tPatronym.getText().trim().isEmpty())
            Error(bundle.getString("patronymfieldempty"));
        else if (tPhone.getText()==null||tPhone.getText().trim().isEmpty())
            Error(bundle.getString("phonefieldempty"));
        else if (tCity.getText()==null||tCity.getText().trim().isEmpty())
            Error(bundle.getString("cityfieldempty"));
        else if (tStreet.getText()==null||tStreet.getText().trim().isEmpty())
            Error(bundle.getString("streetfieldempty"));
        else
        {
            employer.setSurName(tSurName.getText().trim());
            employer.setName(tName.getText().trim());
            employer.setPatronym(tPatronym.getText().trim());
            employer.setPhone(tPhone.getText().trim());
            employer.setCity(tCity.getText().trim());
            employer.setStreet(tStreet.getText().trim());
            employer.setHouse(Integer.parseInt(tHouse.getText().trim()));
            if (!tAppartment.getText().trim().isEmpty())
                employer.setAppartment(Integer.parseInt(tAppartment.getText().trim()));
            else
                employer.setNullAppartment();
            action=true;
            editStage.close();
        }
    }
}