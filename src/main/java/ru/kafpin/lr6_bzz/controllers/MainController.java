package ru.kafpin.lr6_bzz.controllers;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Locale;
import java.util.ResourceBundle;
public class MainController {
    ResourceBundle bundle=ResourceBundle.getBundle("authorization", Locale.getDefault());

    private HttpURLConnection conn;
    private URL url;
    @FXML
    private Button bExit;

    @FXML
    private Button bLogin;

    @FXML
    private Button bRegistry;

    @FXML
    private TextField tLogin;

    @FXML
    private TextField tPassword;


    @FXML
    void onRegistry(ActionEvent event) {
        showDialog("registry", bundle.getString("registryscene"),"");
    }

    @FXML
    void initialize(){
    }
    @FXML
    void onConfirm(ActionEvent event) {
        boolean loginSuccess = true;
        String encodedAuth = "";
        try {
            url = new URL("http://127.0.0.1:8080/api/login");
            conn = (HttpURLConnection) url.openConnection();
            String auth = tLogin.getText()+':'+(tPassword.getText().trim());
            encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes());
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Authorization", "Basic " + encodedAuth);
            if(200 != conn.getResponseCode()){
                loginSuccess = false;
                System.out.printf("Response code = "+conn.getResponseCode());
            }

        } catch (IOException e) {
            System.out.println("URL/Connection error");
        }

        StringBuilder response = new StringBuilder();
        try(BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8))){
            String responseLine;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine);
                response.append("\n");
            }
        } catch (IOException e) {
            System.out.println("bufferReader error");
        }

        System.out.println(response);
        if(loginSuccess){
            switch (response.toString().trim()){
                case "ROLE_EMPLOYER":
                    showDialog("employer", bundle.getString("employerscene"),encodedAuth);
                    break;
                case "ROLE_ADMIN":
                    showDialog("administrator", bundle.getString("administratorscene"),encodedAuth);
                    break;
                case "ROLE_USER":
                    System.out.println(encodedAuth);
                    showDialog("client", bundle.getString("clientscene"),encodedAuth);
                    break;
            }
        }
        else
            Error("Неверный логин/пароль");

    }
    private void Error(String text){
        Alert alert;
        alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(bundle.getString("error"));
        alert.setContentText(null);
        alert.setHeaderText(text);
        alert.showAndWait();
    }
    private void showDialog(String viewName, String titleOfScene,String encodedAuth) {
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
                case "employer":
                    EmployerController employerController = loader.getController();
                    employerController.setEmployerStage(addStage);
                    employerController.setEncodedAuth(encodedAuth);
                    break;
                case "administrator":
                    AdministratorController administratorController = loader.getController();
                    administratorController.setAdministratorStage(addStage);
                    administratorController.setEncodedAuth(encodedAuth);
                    break;
                case "registry":
                    RegistryController registryController = loader.getController();
                    registryController.setRegistryStage(addStage);
                    break;
                default:
                    ClientController clientController = loader.getController();
                    clientController.setClientStage(addStage);
                    clientController.setEncodedAuth(encodedAuth);
                    break;
            }
            addStage.showAndWait();
        } catch (IOException e) {
            System.out.println(bundle.getString("loaderror"));
        }
    }
    @FXML
    void onExit(ActionEvent event) {
        Platform.exit();
    }
}