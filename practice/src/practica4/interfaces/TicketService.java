package practica4.interfaces;

import practica4.interfaces.TicketClient;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface TicketService extends Remote {
    void getTicket(String concertName, TicketClient handler) throws RemoteException;
}