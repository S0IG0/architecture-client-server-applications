package org.example;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);

            Registry registry = LocateRegistry.getRegistry(null);
            Hello stub = (Hello) registry.lookup("Hello");

            System.out.println("Введите а:");
            int a = scanner.nextInt();
            System.out.println("Введите b:");
            int b = scanner.nextInt();
            System.out.println("Введите c:");
            int c = scanner.nextInt();

            System.out.println(stub.quadraticEquation(a, b, c));

        } catch (Exception e) {
            System.err.println("Client exception: " + e);
            e.printStackTrace();
        }

    }
}
