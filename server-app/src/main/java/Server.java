import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Server {
    private ServerSocket serverSocket;
    private List<ClientController> clients = new ArrayList<>();

    public Server(int port) {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server is up and running at " + port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startServer() {
        try {
            while (!serverSocket.isClosed()) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected: " + clientSocket);

                // Create a new ClientController instance for the connected client
                ClientController clientController = new ClientController(clientSocket);
                clients.add(clientController);

                // Start a separate thread to handle client communication
                Thread clientThread = new Thread(clientController);
                clientThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeServerSocket() {
        try {
            if (serverSocket != null && !serverSocket.isClosed()) {
                serverSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        int port = 5050; // Change this to the desired port number
        Server serverApp = new Server(port);
        serverApp.startServer();
    }

    // Inner class for handling client communication
    private class ClientController implements Runnable {
        private Socket clientSocket;
        private PrintWriter out;
        private BufferedReader in;

        public ClientController(Socket socket) {
            this.clientSocket = socket;
            try {
                out = new PrintWriter(socket.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            try {
                String message;
                while ((message = in.readLine()) != null) {
                    System.out.println("Received from client: " + message);

                    // Broadcast the received message to all clients
                    broadcast(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                // Client disconnected, cleanup
                try {
                    clientSocket.close();
                    clients.remove(this);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        public void sendMessage(String message) {
            out.println(message);
        }

        private void broadcast(String message) {
            for (ClientController client : clients) {
                // Send the message to all clients except the sender
                if (client != this) {
                    client.sendMessage(message);
                }
            }
        }
    }
}
