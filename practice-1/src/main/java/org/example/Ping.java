package org.example;


public class Ping implements Runnable{
    PingPong pingPong;

    public Ping(PingPong pingPong) {
        this.pingPong = pingPong;
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++){
            pingPong.ping();
        }
    }
}
