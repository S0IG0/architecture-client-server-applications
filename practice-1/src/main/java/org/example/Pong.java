package org.example;

public class Pong implements Runnable {
    PingPong pingPong;

    public Pong(PingPong pingPong) {
        this.pingPong = pingPong;
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++){
            pingPong.pong();
        }
    }
}
