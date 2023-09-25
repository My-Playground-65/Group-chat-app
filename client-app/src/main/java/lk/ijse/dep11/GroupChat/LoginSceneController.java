package lk.ijse.dep11.GroupChat;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class LoginSceneController {
    public TextField txtName;
    public Button btnLogin;
    public AnchorPane root;

//    public ArrayList<User> users = new ArrayList<User>();

    public void btnLoginOnAction(ActionEvent actionEvent) throws IOException {
        String name = txtName.getText();
//        if(isExist(name)){
//            txtName.selectAll();
//            txtName.requestFocus();
//            return;
//        }else{
            //users.add(new User(name));
            FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("/view/ChatView.fxml"));
            AnchorPane chatSceneRoot=fxmlLoader.load();
            Scene scene1 = new Scene(chatSceneRoot);

            Stage primaryStage = (Stage) root.getScene().getWindow();
            primaryStage.setScene(scene1);
            primaryStage.setResizable(true);
            primaryStage.centerOnScreen();

            ChatViewController chatSceneController = fxmlLoader.getController();
            chatSceneController.initData(name);
//        }
    }
//    public boolean isExist(String name){
//        for (User user: users) {
//            if(user.name.equals(name)) return true;
//        }
//        return false;
//    }
}
