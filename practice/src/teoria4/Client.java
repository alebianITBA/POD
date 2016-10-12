package teoria4;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Client {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            MovieService movies = (MovieService) registry.lookup("Movies");

            ClientHandle client1 = new ClientHandleImpl();
            ClientHandle client2 = new ClientHandleImpl();

            movies.subscribe(client1, "Comedia");
            movies.subscribe(client2, "Drama");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
