package org.example;

import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ChatServer {
    final static Integer port = 9090;
    final static Set<PrintWriter> writers = new HashSet<>();
    final static List<String> messages = new ArrayList<>();
    final static ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(9090)) {
            System.out.println("Сервер запущен на " + port + " порту");
            scheduler.scheduleAtFixedRate(ChatServer::broadcastMessages, 0, 5, TimeUnit.SECONDS);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);
                writers.add(writer);
                Thread clientHandler = new Thread(new ClientHandler(clientSocket));
                clientHandler.start();
            }

        } catch (Exception exception) {
            System.out.println("Сервер не запустился");
            System.out.println(exception.getMessage());
        }
    }

    private static class ClientHandler implements Runnable {
        private final Socket clientSocket;
        private BufferedReader reader;

        public ClientHandler(@NotNull Socket socket) {
            this.clientSocket = socket;
            try {
                this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            } catch (IOException exception) {
                System.out.println(exception.getMessage());
            }
        }

        @Override
        public void run() {
            String message;
            try {
                while ((message = reader.readLine()) != null) {
                    System.out.println("Получено сообщение: " + message);
                    synchronized (messages) {
                        messages.add(message);
                    }
                }
            } catch (IOException exception) {
                System.out.println(exception.getMessage());
            } finally {
                synchronized (writers) {
                    try {
                        writers.remove(new PrintWriter(clientSocket.getOutputStream(), true));
                    } catch (IOException exception) {
                        System.out.println(exception.getMessage());
                    }
                }
            }
        }
    }

    private static void broadcastMessages() {
        synchronized (messages) {
            if (!messages.isEmpty()) {
                for (PrintWriter writer : writers) {
                    for (String message : messages) {
                        writer.println(message);
                    }
                }
                messages.clear();
            }
        }
    }
}
