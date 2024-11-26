package ru.kafpin.lr6_bzz;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ru.kafpin.lr6_bzz.utils.DBHelper;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;
public class MainApplication extends Application {
    private static Stage mainStage;
    public static Stage getMainStage() {
        return mainStage;
    }
    public static Connection getConnection(){
        return connection;
    }
    private static Connection connection;
    private static ResourceBundle bundle;
    @Override
    public void start(Stage stage) throws IOException {
        try{
            connection = DBHelper.getConnection();
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("authorization-view.fxml"),bundle);
        Scene scene = new Scene(fxmlLoader.load(), 350 , 180);
        stage.setMinWidth(350);
        stage.setMinHeight(180);
        stage.setTitle(bundle.getString("title"));
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