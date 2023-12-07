package org.example;

import java.rmi.RemoteException;

public class HelloImpl implements Hello {
    @Override
    public String quadraticEquation(int a, int b, int c) throws RemoteException {
        double x1, x2;
        int D = b * b - 4 * a * c;
        if (D == 0) {
            x1 = -b / (2 * a);
            x2 = x1;
        } else if (D > 0) {
            x1 = (-b + Math.sqrt(D)) / (2 * a);
            x2 = (-b - Math.sqrt(D)) / (2 * a);
        } else {
            return "Нет действительных корней";
        }
        return "x1 = " + x1 + ", x2 = " + x2;
    }
}
