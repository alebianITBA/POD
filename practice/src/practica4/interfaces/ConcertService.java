package practica4.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ConcertService extends Remote {
    void create(String name, int confirmAmount, int vipTickets, int maxCapacity) throws RemoteException;
    void cancel(String name) throws RemoteException;
}