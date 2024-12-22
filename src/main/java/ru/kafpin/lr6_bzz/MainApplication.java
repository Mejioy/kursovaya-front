package ru.kafpin.lr6_bzz;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.Getter;


import java.io.IOException;
import java.sql.Connection;
import java.util.Locale;
import java.util.ResourceBundle;
public class MainApplication extends Application {
    @Getter
    private static Stage mainStage;
    private static Connection connection;
    private static ResourceBundle bundle;
    public static String encodedAuth;
    public static String login;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("authorization-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 350 , 180);
        stage.setResizable(false);
        stage.setMinWidth(350);
        stage.setMinHeight(180);
        stage.setTitle("Авторизация");
        stage.setScene(scene);
        stage.show();
    }
    @Override
    public void stop() throws Exception {
        if(connection != null){
            connection.close();
        }
        super.stop();
    }
    public static void main(String[] args) {
        Locale.setDefault(Locale.getDefault());
        bundle = ResourceBundle.getBundle("authorization",
                Locale.getDefault());
        launch();
    }
}