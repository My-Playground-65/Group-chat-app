package lk.ijse.dep11.GroupChat;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AppInitializer extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage)throws IOException {
        primaryStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/LoginScene.fxml"))));
        primaryStage.setResizable(false);
        primaryStage.setTitle("File IO Example App 1");
        primaryStage.show();
        primaryStage.centerOnScreen();

    }
}
