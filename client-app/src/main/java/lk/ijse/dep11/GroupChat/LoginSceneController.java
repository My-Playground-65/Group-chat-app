package lk.ijse.dep11.GroupChat;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginSceneController {
    public TextField txtName;
    public Button btnLogin;
    public AnchorPane root;

    public void btnLoginOnAction(ActionEvent actionEvent) throws IOException {
        String name=txtName.getText();
        if(true){
//            AnchorPane scene1Root = FXMLLoader.load(getClass().getResource("/view/ChatView.fxml"));
//            Scene scene1 = new Scene(scene1Root);

            FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("/view/ChatView.fxml"));
            AnchorPane chatSceneRoot=fxmlLoader.load();
            Scene scene1 = new Scene(chatSceneRoot);

            Stage primaryStage = (Stage) root.getScene().getWindow();
            primaryStage.setScene(scene1);
            primaryStage.setResizable(true);
            primaryStage.centerOnScreen();

            ChatViewController chatSceneController=fxmlLoader.getController();
            ChatViewController.initData(name);
            return;


        }
    }
}
