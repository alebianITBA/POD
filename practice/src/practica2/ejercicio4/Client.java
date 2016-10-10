package practica2.ejercicio4;

import rmi.Statistics;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {
    public static void main(String args[]) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            Service service = (Service) registry.lookup("Service");

            System.out.println(service.ping());
            System.out.println(service.time());
            System.out.println(service.echo("hola"));
            System.out.println(service.hello("Pepe"));
            System.out.println(service.fortune());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
