package practica4.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface TicketClient extends Remote {
    void ticketHold(String concertName) throws RemoteException;
    void vipTicketConfirmed(String concertName, String ticket) throws RemoteException;
    void ticketConfirmed(String concertName, String ticket) throws RemoteException;
    void soldOutConcert(String concertName) throws RemoteException;
    void canceledConcert(String concertName) throws RemoteException;
}