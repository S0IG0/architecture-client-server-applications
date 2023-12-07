package org.example;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server extends HelloImpl {
    public static void main(String[] args) {
        try {
            HelloImpl hello = new HelloImpl();

            Registry registry = LocateRegistry.createRegistry(1099);
            Hello stub = (Hello) UnicastRemoteObject.exportObject(hello, 0);

            registry.bind("Hello", stub);
            System.out.println("Server ready");
        } catch (Exception e) {
            System.err.println("Server exception: " + e);
            e.printStackTrace();
        }
    }
}
