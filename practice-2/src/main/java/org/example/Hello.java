package org.example;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Hello extends Remote {
    String quadraticEquation(int a, int b, int c) throws RemoteException;
}
