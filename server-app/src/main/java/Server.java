import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {

    private static List<Socket> cllentList = new ArrayList<>();
    private static String message = "";
    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(5050);
        while(true){
            System.out.println("Waiting for incoming connection");
            Socket localSocket = serverSocket.accept();
            new Thread(()->{
                cllentList.add(localSocket);
                try {
                    InputStream is = localSocket.getInputStream();
                    BufferedInputStream bis = new BufferedInputStream(is);

                    while (true) {
                        byte[] buffer = new byte[1024];
                        int read = bis.read(buffer);
                        if (read == -1) throw new EOFException();
                        message += new String(buffer, 0, read);
                        System.out.println(message);
                        notifyClients();
                    }
                }catch (EOFException e) {
                    cllentList.remove(localSocket);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }).start();
        }
    }
    public static void notifyClients(){
        new Thread(() ->{
            for (Socket client : cllentList) {
                try {
                    OutputStream os = client.getOutputStream();
                    BufferedOutputStream bos = new BufferedOutputStream(os);
                    bos.write(message.getBytes());
                    bos.flush();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
        }).start();

    }

}
