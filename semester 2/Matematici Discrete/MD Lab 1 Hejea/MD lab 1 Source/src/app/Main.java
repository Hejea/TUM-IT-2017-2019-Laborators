package app;

import controller.CMain;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        CMain.stage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("/view/MainView.fxml"));
        primaryStage.setTitle("Laboratoare");
        primaryStage.getIcons().add(new Image("file:/../img/icon.png"));

        Scene scene = new Scene(root, 800, 700);
        scene.getStylesheets().add("style/style.css");
        scene.getStylesheets().add("style/combo.css");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
