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
        synchronized (observers) {
            if (!observers.containsKey(genre)) {
                observers.put(genre, new ArrayList<>());
            }
            synchronized (observers.get(genre)) {
                observers.get(genre).add(handle);
            }
        }
    }

    public void start() {
        execute = true;
        while (execute) {
            database.keySet().forEach(genre -> {
                database.get(genre).forEach(movie -> {
                    Optional.ofNullable(observers.get(genre)).ifPresent(clients -> {
                        synchronized (clients) {
                            Iterator it = clients.iterator();
                            while (it.hasNext()) {
                                ClientHandle client = (ClientHandle) it.next();
                                try {
                                    client.notify(movie.getName());
                                    Thread.sleep(1 * 1000);
                                } catch (Exception e) {
                                    it.remove();
                                    System.out.println("Client removed");
                                }
                            }
                        }
                    });
                });
            });
        }
    }

    public void shutdown() {
        this.execute = false;
    }

    public void addMovie(Movie movie) {
        synchronized (database) {
            if (!database.containsKey(movie.getGenre())) {
                database.put(movie.getGenre(), new ArrayList<>());
            }
            database.get(movie.getGenre()).add(movie);
        }
    }
}
