package practica4;

import practica4.servants.ServiceImpl;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            ServiceImpl servant = new ServiceImpl();
            registry.rebind("Tickets", servant);
            registry.rebind("Tickets 2", servant);

            servant.create("Metallica", 2, 2, 5);
            servant.create("The Killers", 2, 1, 10);
            servant.create("Pink Floyd", 5, 2, 10);

            System.out.println("Service registered");

            Thread.sleep(20 * 1000);
            servant.cancel("Metallica");
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
