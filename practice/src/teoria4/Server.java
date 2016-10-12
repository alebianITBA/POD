package teoria4;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            MovieServiceImpl servant = new MovieServiceImpl();
            registry.rebind("Movies", servant);

            servant.addMovie(new Movie("Mr Holmes"              , "Drama"));
            servant.addMovie(new Movie("Las sillas musicales"   , "Comedia"));
            servant.addMovie(new Movie("Un dia perfecto"        , "Drama"));
            servant.addMovie(new Movie("Aloha"                  , "Comedia"));
            servant.addMovie(new Movie("Anacleto"               , "Aventura"));
            servant.addMovie(new Movie("Atico sin ascenso"      , "Drama"));
            servant.addMovie(new Movie("Everest"                , "Aventura"));
            servant.addMovie(new Movie("Capitan diente de sable", "Aventura"));
            servant.addMovie(new Movie("Una semana en corcega"  , "Comedia"));
            servant.addMovie(new Movie("Eden"                   , "Drama"));

            System.out.println("Service registered");

            servant.start();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
