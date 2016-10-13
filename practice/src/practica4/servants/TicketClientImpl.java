package practica4.servants;

import practica4.interfaces.TicketClient;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class TicketClientImpl implements TicketClient {
    private String name;

    public TicketClientImpl() throws RemoteException {
        UnicastRemoteObject.exportObject(this, 0);
        this.name = name;
    }

    @Override
    public void ticketHold(String concertName) {
        System.out.println("Ticket for " + concertName + " on hold.");
    }

    @Override
    public void vipTicketConfirmed(String concertName, String ticket) {
        System.out.println("VIP Ticket for " + concertName + " confirmed.");
    }

    @Override
    public void ticketConfirmed(String concertName, String ticket) {
        System.out.println("Ticket for " + concertName + " confirmed.");
    }

    @Override
    public void soldOutConcert(String concertName) {
        System.out.println(concertName + " concert is sold out.");
    }

    @Override
    public void canceledConcert(String concertName) {
        System.out.println(concertName + " concert was canceled.");
    }

    @Override
    public String toString() {
        return "Client{" +
                "name='" + name + '\'' +
                '}';
    }
}
