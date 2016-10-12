package teoria4;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface MovieService extends Remote {
    void subscribe(ClientHandle handle, String genre) throws RemoteException;
}
