package main;

import controller.Controller01Main;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        Controller01Main.appMainStage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("../view/view01Main.fxml"));
        primaryStage.setTitle("Hejea MMC Laborator 2 (Metoda Jacobi)");
        primaryStage.setScene(new Scene(root, 900, 700));
        primaryStage.show();
    }
}
