package org.example;

public class PingPong {
    boolean flag = false;

    public synchronized void ping() {
        while (flag) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.print("Ping ");
        flag = true;
        notify();
    }

    public synchronized void pong() {
        while (!flag) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.print("Pong ");
        flag = false;
        notify();
    }
}
