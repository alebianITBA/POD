package practica4;

import practica4.interfaces.TicketClient;
import practica4.interfaces.TicketService;
import practica4.servants.TicketClientImpl;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            TicketService tickets = (TicketService) registry.lookup("Tickets");

            TicketClient client = new TicketClientImpl();

            tickets.getTicket("Metallica", client);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
