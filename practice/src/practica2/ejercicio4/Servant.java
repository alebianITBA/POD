package practica2.ejercicio4;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Servant implements Service {
    private List<String> cookies;

    public Servant() throws RemoteException{
        fillCookies();
        UnicastRemoteObject.exportObject(this, 0);
    }

    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            Service servant = new Servant();
            registry.rebind("Service", servant);
            System.out.println("Service registered");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private void fillCookies() {
        cookies = new ArrayList<>();
        cookies.add("You are very lucky");
    }

    public String ping() throws RemoteException {
        return "pong";
    }

    public String time() throws RemoteException {
        return LocalDateTime.now().toString();
    }

    public String echo(String phrase) throws RemoteException {
        return phrase;
    }

    public String hello(String name) throws RemoteException {
        return "Hello " + name + "!";
    }

    public String fortune() throws RemoteException {
        int randomIndex = new Random().ints(1, 0, cookies.size()).findAny().getAsInt();
        return cookies.get(randomIndex);
    }
}
