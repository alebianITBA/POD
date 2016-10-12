package teoria4;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

public class MovieServiceImpl implements MovieService {
    private Map<String, List<Movie>> database;
    private Map<String, List<ClientHandle>> observers;
    private boolean execute;

    public MovieServiceImpl() throws RemoteException {
        UnicastRemoteObject.exportObject(this, 0);
        this.database = new HashMap<>();
        this.observers = new HashMap<>();
    }

    @Override
    public void subscribe(ClientHandle handle, String genre) throws RemoteException {
        if (!observers.containsKey(genre)) {
            observers.put(genre, new ArrayList<>());
        }
        observers.get(genre).add(handle);
    }

    public void start() {
        execute = true;
        while (execute) {
            database.keySet().forEach(genre -> {
                database.get(genre).forEach(movie -> {
                    Optional.ofNullable(observers.get(genre)).ifPresent(clients -> {
                        clients.forEach(client -> {
                            try {
                                client.notify(movie.getName());
                                Thread.sleep(1 * 1000);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        });
                    });
                });
            });
        }
    }

    public void shutdown() {
        this.execute = false;
    }

    public void addMovie(Movie movie) {
        if (!database.containsKey(movie.getGenre())) {
            database.put(movie.getGenre(), new ArrayList<>());
        }
        database.get(movie.getGenre()).add(movie);
    }
}
