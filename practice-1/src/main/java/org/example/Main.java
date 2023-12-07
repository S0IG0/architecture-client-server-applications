package org.example;


public class Main {
    public static void main(String[] args) {
        PingPong pingPong = new PingPong();

        Ping ping = new Ping(pingPong);
        Pong pong = new Pong(pingPong);


        new Thread(ping).start();
        new Thread(pong).start();
    }
}