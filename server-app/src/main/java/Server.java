import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static ServerSocket serverSocket;

    public Server(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public void startServer(){

        try{
            while(!serverSocket.isClosed()){
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected: " + clientSocket);

                ClentHandller clentHandller = new ClentHandller(clientSocket);
                Thread thread = new Thread(clentHandller);
                thread.start();
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void closeServerSocket(){
        try{

            if(serverSocket != null){
                serverSocket.close();
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket1 = new ServerSocket(5050);
        Server serverApp = new Server(serverSocket);
        serverApp.startServer();

    }
}
