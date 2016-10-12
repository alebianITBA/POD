package teoria4;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ClientHandleImpl implements ClientHandle {

    public ClientHandleImpl() throws RemoteException {
        UnicastRemoteObject.exportObject(this, 0);
    }

    @Override
    public void notify(String movieName) throws RemoteException {
        System.out.println("Nueva pelicula: " + movieName);
    }
}
