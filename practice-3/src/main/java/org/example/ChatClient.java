package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ChatClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", ChatServer.port);

        BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader serverInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
        Thread serverListener = new Thread(() -> {
            String serverMessage;
            try {
                while ((serverMessage = serverInput.readLine()) != null) {
                    System.out.println("Полученное сообщение: " + serverMessage);
                }
            } catch (IOException exception) {
                System.out.println(exception.getMessage());
            }
        });
        serverListener.start();

        System.out.println("Вы можете начать отправлять сообщения:");
        String userInputMessage;
        while ((userInputMessage = userInput.readLine()) != null) {
            writer.println(userInputMessage);
        }

        socket.close();
    }
}
