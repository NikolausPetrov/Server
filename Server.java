import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        int port = 8080;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server started on port " + port);

            try (Socket clientSocket = serverSocket.accept();
                 PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                 BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

                System.out.println("New connection accepted");

                String clientInput = in.readLine();
                System.out.println("Received: " + clientInput + " from port: " + clientSocket.getPort());

                out.println(String.format("Hi %s, your port is %d", clientInput, clientSocket.getPort()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}