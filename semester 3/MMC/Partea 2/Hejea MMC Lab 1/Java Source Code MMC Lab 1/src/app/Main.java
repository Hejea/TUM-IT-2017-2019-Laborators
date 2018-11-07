package app;

import controllers.ControllerMain;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        ControllerMain.appMainStage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("/views/view0Main.fxml"));
        primaryStage.setTitle("Hejea MMC Laborator 1 : ");
        primaryStage.setScene(new Scene(root, 500, 435));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
