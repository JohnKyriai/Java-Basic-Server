package onionet.network.test;

import java.net.Socket;
import java.io.IOException;
import java.util.Scanner;
import java.io.OutputStream;
import java.io.InputStream;

public class OnionClientTest {
    public static void main(String[] args) {

        Scanner testScanner = new Scanner(System.in);

        try {
            System.out.println("Please enter an IPV4 Address: ");
            String ipChoice = testScanner.nextLine(); // Saves the address in memory as a string

            System.out.println("Please enter a port: ");
            int portChoice = testScanner.nextInt(); // Saves the port in memory as an integer

            Socket socket = new Socket(ipChoice, portChoice);

            System.out.println("Successfully connected to " + ipChoice + ":" + portChoice);
            System.out.println("==============================================");

            testScanner.nextLine();

            System.out.println("Do you want to send a message to the server? (y/n)");
            String messageChoice = testScanner.nextLine();

            if (messageChoice.equalsIgnoreCase("y")) {
                System.out.println("Enter your message for the admin");
                String actualMessage = testScanner.nextLine();

                OutputStream output = socket.getOutputStream();

                String message = actualMessage;

                output.write(message.getBytes("UTF-8"));
                output.flush();

                InputStream response = socket.getInputStream();

                byte[] buffer = new byte[1024];
                int bytesRead = response.read(buffer);

                String reply = new String(buffer, 0, bytesRead, "UTF-8");
                System.out.println("Server says: " + reply);

            } else if (messageChoice.equalsIgnoreCase("n")) {
                System.out.println("No message will be sent to the server");

            } else {
                System.out.println("Illegal choice");
            }

            socket.close();

        } catch (IOException e) {
            System.out.println("There was an error while connecting to the server");
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
            // Program ends gracefully if an error occurs

        }

        testScanner.close();

    }

}
