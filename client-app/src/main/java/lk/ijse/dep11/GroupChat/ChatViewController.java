package lk.ijse.dep11.GroupChat;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.text.BreakIterator;

public class ChatViewController {
    public AnchorPane root;
    public TextArea txtChatHistory;
    public ListView lstLoggedUsers;
    public TextField txtMassage;
    public Button btnSend;
    public Label lblName;

    public static void initData(String name) {
        

    }

    public void btnSendOnAction(ActionEvent actionEvent) {
    }
}
