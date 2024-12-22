package ru.kafpin.lr6_bzz.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lombok.Data;
import lombok.Setter;
import ru.kafpin.lr6_bzz.domains.RegisterClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;

@Data
public class RegistryController {

    @Setter
    private Stage registryStage;

    private RegisterClient client;
    private final ResourceBundle bundle = ResourceBundle.getBundle("registry", Locale.getDefault());
    private boolean action=false;

    private HttpURLConnection conn;
    private final ObjectMapper mapper = new ObjectMapper();
    private URL url;

    @FXML
    private TextField tName;
    @FXML
    private TextField tPatronym;
    @FXML
    private TextField tPhone;
    @FXML
    private TextField tSurName;
    @FXML
    private TextField tPass1;
    @FXML
    private TextField tPass2;
    @FXML
    void onCancel(ActionEvent event) {
        registryStage.close();
    }
    private void Error(String text){
        Alert alert;
        alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(bundle.getString("error"));
        alert.setContentText(null);
        alert.setHeaderText(text);
        alert.showAndWait();
    }
    private void Success(String text){
        Alert alert;
        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(bundle.getString("error"));
        alert.setContentText(null);
        alert.setHeaderText(text);
        alert.showAndWait();
    }
    @FXML
    void onOK(ActionEvent event) {

        if(!Objects.equals(tPass1.getText().trim(), tPass2.getText().trim()))
            Error("Пароли не совпадают");
        else {
            if (tSurName.getText()==null||tSurName.getText().trim().isEmpty())
                Error(bundle.getString("surnamefieldempty"));
            else if (tName.getText()==null||tName.getText().trim().isEmpty())
                Error(bundle.getString("firstnamefieldempty"));
            else if (tPatronym.getText()==null||tPatronym.getText().trim().isEmpty())
                Error(bundle.getString("patronymfieldempty"));
            else if (tPhone.getText()==null||tPhone.getText().trim().isEmpty())
                Error(bundle.getString("phonefieldempty"));
            else if (tPass1.getText()==null||tPass1.getText().trim().isEmpty())
                Error(bundle.getString("passwordfieldempty"));
            else
            {
                if(tPass1.getText().matches("[^a-zA-Z\\d]")) {
                    Error(bundle.getString("passwordcheck"));
                }
                client = new RegisterClient();
                client.setSurName(tSurName.getText().trim());
                client.setName(tName.getText().trim());
                client.setPatronym(tPatronym.getText().trim());
                client.setPhone(tPhone.getText().trim());
                client.setPassword(new BCryptPasswordEncoder().encode(tPass1.getText().trim()));
                if(tryCreateUser()){
                    Success("Пользователь успешно зарегистрирован");
                    action=true;
                    registryStage.close();
                }
                else
                    Error("Пользователь с таким номером телефона уже существует");
            }
        }
    }
    private boolean tryCreateUser(){
        String json = parseSingleUserToJson(client);
        System.out.println(json);

        try {
            url = new URL("http://127.0.0.1:8080/api/registry");
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);
            try(OutputStream os = conn.getOutputStream()) {
                byte[] input = json.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }
            if(200 != conn.getResponseCode()){
                System.out.printf("Response code = "+conn.getResponseCode());
            }
        }
        catch (IOException e) {
            System.out.println("error of write outputstream");
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

        return response.toString().trim().equals("success registry");
    }
    private String parseSingleUserToJson(RegisterClient client){
        String json = null;
        try {
            json = mapper.writeValueAsString(client);
        } catch (JsonProcessingException e) {
            System.out.println(e);
            System.out.println("Error of write in json");
        }
        return json;
    }
}