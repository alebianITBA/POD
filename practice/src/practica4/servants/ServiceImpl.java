package practica4.servants;

import practica4.interfaces.ConcertService;
import practica4.interfaces.TicketClient;
import practica4.interfaces.TicketService;
import practica4.models.Concert;
import practica4.models.Status;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public class ServiceImpl implements ConcertService, TicketService {
    private static Consumer printer = (s) -> System.out.println(s);
    private List<Concert> concerts;

    public ServiceImpl() throws RemoteException {
        UnicastRemoteObject.exportObject(this, 0);
        this.concerts = new LinkedList<>();
    }

    @Override
    public void getTicket(String concertName, TicketClient handler) throws RemoteException {
        Concert concert = findConcert(concertName);
        if (concert == null) {
            return;
        }
        printer.accept("Concert " + concertName + " found");
        synchronized (concert) {
            if (concert.getStatus() == Status.SOLD_OUT) {
                printer.accept("Concert was sold out, telling user.");
                handler.soldOutConcert(concertName);
                return;
            }
            else if (concert.getStatus() == Status.CANCELED) {
                printer.accept("Concert was canceled, telling user.");
                handler.canceledConcert(concertName);
                return;
            }
            boolean booked = concert.book(handler);
        }
    }

    @Override
    public void create(String name, int confirmAmount, int vipTickets, int maxCapacity) throws RemoteException {
        synchronized (concerts) {
            Optional concert = Optional.ofNullable(findConcert(name));
            if (!concert.isPresent()) {
                concerts.add(new Concert(name, vipTickets, confirmAmount, maxCapacity));
                printer.accept("Concert " + name + " registered.");
            }
        }
    }

    @Override
    public void cancel(String name) throws RemoteException {
        Optional.ofNullable(findConcert(name)).ifPresent(concert -> {
            synchronized (concert) {
                concert.cancel();
                printer.accept("Concert " + name + " canceled.");
            }
        });
    }

    private Concert findConcert(String name) {
        Concert concert = null;
        synchronized (concerts) {
            for (Concert c : concerts) {
                if (c.getName().equals(name)) {
                    concert = c;
                }
            }
        }
        return concert;
    }
}
