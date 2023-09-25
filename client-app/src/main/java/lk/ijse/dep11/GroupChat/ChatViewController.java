package lk.ijse.dep11.GroupChat;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class ChatViewController {
    public AnchorPane root;
    public TextArea txtChatHistory;
    public ListView lstLoggedUsers;
    public TextField txtMassage;
    public Button btnSend;

    public void btnSendOnAction(ActionEvent actionEvent) {
    }
}
