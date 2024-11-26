package ru.kafpin.lr6_bzz.controllers;
import ru.kafpin.lr6_bzz.MainApplication;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
public class MainController {
    ResourceBundle bundle=ResourceBundle.getBundle("authorization", Locale.getDefault());
    @FXML
    private ComboBox<String> cbAuth;
    @FXML
    void initialize(){
        ObservableList<String> users = FXCollections.observableArrayList();
        users.add(bundle.getString("client"));
        users.add(bundle.getString("employer"));
        users.add(bundle.getString("administrator"));
        cbAuth.setItems(users);
    }
    @FXML
    void onConfirm(ActionEvent event) {
        switch (cbAuth.getSelectionModel().getSelectedIndex()){
            case 0:
                showDialog("service", bundle.getString("clientscene"));
                break;
            case 1:
                showDialog("employer", bundle.getString("employerscene"));
                break;
            case 2:
                showDialog("administrator", bundle.getString("administratorscene"));
                break;
        }
    }
    private void showDialog(String viewName, String titleOfScene) {
        ResourceBundle bundle = ResourceBundle.getBundle(viewName,
                Locale.getDefault());
        FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource(viewName+"-view.fxml"),bundle);
        try {
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage addStage = new Stage();
            addStage.setTitle(titleOfScene);
            addStage.setScene(scene);
            addStage.initModality(Modality.APPLICATION_MODAL);
            addStage.initOwner(MainApplication.getMainStage());
                        switch (viewName){
                case "service":
                    ClientController clientController = loader.getController();
                    clientController.setStage(addStage);
                    break;
                case "employer":
                    EmployerController employerController = loader.getController();
                    employerController.setStage(addStage);
                    break;
                case "administrator":
                    AdministratorController administratorController = loader.getController();
                    administratorController.setStage(addStage);
                    break;
            }
            addStage.showAndWait();
            switch (viewName){
                case "service":
                    break;
                case "employer":
                    break;
                case "administrator":
                    break;
            }

        } catch (IOException e) {
            System.out.println(bundle.getString("loaderror"));
        }
    }
    @FXML
    void onExit(ActionEvent event) {
        Platform.exit();
    }
}