package teoria4;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientHandle extends Remote {
    void notify(String movieName) throws RemoteException;
}
