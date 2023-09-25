package lk.ijse.dep11.GroupChat;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ChatViewController {
    public AnchorPane root;
    public TextArea txtChatHistory;
    public ListView<String> lstLoggedUsers;
    public TextField txtMassage;
    public Button btnSend;

    public Socket remoteSocket;

    public ArrayList<String> users = new ArrayList<>();

    public void btnSendOnAction(ActionEvent actionEvent) {
        if(btnSend.getText() == null){
            return;
        }else{
            txtChatHistory.setText(txtChatHistory.getText()+"\n"+txtMassage.getText());

            try {
                OutputStream is = remoteSocket.getOutputStream();
                BufferedOutputStream bos = new BufferedOutputStream(is);
                bos.write(txtMassage.getText().getBytes());

            } catch (IOException e) {
                throw new RuntimeException(e);
            }


        }

    }
    public void initialize() throws IOException {
        remoteSocket = new Socket("192.168.136.102", 5500);

        new Thread(() -> {
            InputStream is = null;
            try {
                is = remoteSocket.getInputStream();
                BufferedInputStream bis = new BufferedInputStream(is);
                byte[] buffer = new byte[1024];
                int read =-1;
                String text = txtChatHistory.getText();
                while((read = bis.read(buffer)) != -1){
                    text += new String(buffer,0,read);
                }
                txtChatHistory.setText(text);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

        ObservableList<String> userList = FXCollections.observableList(users);
        lstLoggedUsers.setItems(userList);

        
    }
}
