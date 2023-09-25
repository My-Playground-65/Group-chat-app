package lk.ijse.dep11.GroupChat;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
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


    public static String name = "";
    public Label lblName;


    public void initData(String data) {
        lblName.setText(data);
    }

    public Socket remoteSocket;

    public ArrayList<String> users = new ArrayList<>();

    public void btnSendOnAction(ActionEvent actionEvent) {
        if(btnSend.getText() == null){
            return;
        }else{
                try {
                    OutputStream os = remoteSocket.getOutputStream();
                    BufferedOutputStream bos = new BufferedOutputStream(os);
                    bos.write((lblName.getText()+" :"+txtMassage.getText()+"\n").getBytes());
                    bos.flush();
                    txtMassage.clear();

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
        }

    }
    public void initialize() throws IOException {

        new Thread(() -> {

            InputStream is = null;
            try {

                remoteSocket = new Socket("192.168.8.190", 5050);
                is = remoteSocket.getInputStream();
                BufferedInputStream bis = new BufferedInputStream(is);
                byte[] buffer = new byte[1024];
                int read =-1;
                while((read = bis.read(buffer)) != -1){
                    int finalRead = read;
                    Platform.runLater(()->{
                        txtChatHistory.setText(lblName.getText()+" :"+ new String(buffer,0, finalRead)+"\n");
                    });

                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

        ObservableList<String> userList = FXCollections.observableList(users);
        lstLoggedUsers.setItems(userList);

        Platform.runLater(()->{
            root.getScene().getWindow().setOnCloseRequest(e ->{
                try {
                    OutputStream is = remoteSocket.getOutputStream();
                    is.write((lblName.getText()+ " left\n").getBytes());
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

            });
        });
    }

}
