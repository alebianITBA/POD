package teoria4;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            MovieService movies = (MovieService) registry.lookup("Movies");

            ClientHandle client1 = new ClientHandleImpl();

            movies.subscribe(client1, "Drama");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
