import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class OnionServer {
    public static void main(String[] args) {

        try {
            ServerSocket serverSocket = new ServerSocket(5000); // Opens up port 5000, you may change this to whatever port you want
            System.out.println("Server is waiting for a client to connect...");

            Socket socket = serverSocket.accept();
            System.out.println("Successfully connected to a client");

            InputStream input = socket.getInputStream();
            byte[] buffer = new byte[1024];
            int bytesRead = input.read(buffer);

            String message = new String(buffer, 0, bytesRead, "UTF-8");
            System.out.println("Client says: " + message); // This displays the clients message

            OutputStream output = socket.getOutputStream(); // Sends a reply back to the client

            String reply = "OnionServer: Message was received successfully"; // The reply

            output.write(reply.getBytes("UTF-8"));
            output.flush();

            socket.close();
            serverSocket.close();

        } catch (IOException e) {
            System.out.println("Could not connect to server");
            // Ends gracefully

        }

    }

}
