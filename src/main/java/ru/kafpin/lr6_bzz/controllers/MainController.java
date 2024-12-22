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
import java.io.InputStream;
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
        showDialog("registry", "Форма регистрации клиента");
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
            System.out.println(encodedAuth);
            MainApplication.encodedAuth = encodedAuth;
            MainApplication.login = tLogin.getText();
            switch (response.toString().trim()){
                case "ROLE_EMPLOYER":
                    showDialog("employer", "Форма для работы сотрудника");
                    break;
                case "ROLE_ADMIN":
                    showDialog("administrator", "Форма для работы администратора");
                    break;
                case "ROLE_USER":
                    showDialog("client", "Форма для работы клиента");
                    break;
            }
        }
        else
            Error("Неверный логин/пароль");

    }
    private void Error(String text){
        Alert alert;
        alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Ошибка");
        alert.setContentText(null);
        alert.setHeaderText(text);
        alert.showAndWait();
    }
    private void showDialog(String viewName, String titleOfScene) {
        ResourceBundle bundle = ResourceBundle.getBundle(viewName,
                Locale.getDefault());
        FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource(viewName+"-view.fxml"),bundle);
        System.out.println(viewName);
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

                    break;
                case "administrator":
                    AdministratorController administratorController = loader.getController();
                    administratorController.setAdministratorStage(addStage);

                    break;
                case "registry":
                    RegistryController registryController = loader.getController();
                    registryController.setRegistryStage(addStage);
                    break;
                case "client":
                    ClientController clientController = loader.getController();
                    clientController.setClientStage(addStage);
//                    clientController.setEncodedAuth(encodedAuth);
                    break;
            }
            addStage.showAndWait();
        } catch (IOException e) {
            System.out.println(e);
            System.out.println("Ошибка при загрузке формы");
        }
    }
    @FXML
    void onExit(ActionEvent event) {
        Platform.exit();
    }
}