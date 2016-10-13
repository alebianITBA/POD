package practica4.models;

import practica4.interfaces.TicketClient;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class Concert {
    private final String name;
    private int currentTickets = 0;
    private final int vipTickets;
    private final int minimumTickets;
    private final int maxCapacity;
    private Status status = Status.LESS_THAN_MINIMUM;

    List<TicketClient> vipClients;
    List<TicketClient> regularClients;


    public Concert(String name, int vipTickets, int minimumTickets, int maxCapacity) {
        this.name = name;
        this.vipTickets = vipTickets;
        this.minimumTickets = minimumTickets;
        this.maxCapacity = maxCapacity;
        this.vipClients = new ArrayList<>(vipTickets);
        this.regularClients = new ArrayList<>(maxCapacity - vipTickets);
    }

    public Status getStatus() {
        return status;
    }

    public boolean book(TicketClient client) {
        synchronized (status) {
            if (status == Status.SOLD_OUT || status == Status.CANCELED) {
                return false;
            }
            List<TicketClient> list = null;
            if (currentTickets < vipTickets) {
                list = vipClients;
            } else {
                list = regularClients;
            }
            synchronized (list) {
                list.add(client);
            }
            currentTickets++;
            updateStatus();
            if (status == Status.LESS_THAN_MINIMUM) {
                try {
                    client.ticketHold(name);
                } catch (RemoteException e) {
                    System.out.println("There was an error holding ticket for " + client.toString());
                }
            }
            return true;
        }
    }

    private void updateStatus() {
        if (currentTickets == maxCapacity) {
            status = Status.SOLD_OUT;
        } else if (status != Status.MINIMUM_PASSED && currentTickets > minimumTickets) {
            status = Status.MINIMUM_PASSED;
            vipClients.stream().parallel().forEach(client -> {
                try {
                    client.vipTicketConfirmed(name, "");
                } catch (RemoteException e) {
                    System.out.println("There was an error communicating " + client.toString() + " about VIP ticket confirmation for " + name);
                }
            });
            regularClients.stream().parallel().forEach(client -> {
                try {
                    client.ticketConfirmed(name, "");
                } catch (RemoteException e) {
                    System.out.println("There was an error communicating " + client.toString() + " about ticket confirmation for " + name);
                }
            });
        }
    }

    public String getName() {
        return name;
    }

    public void cancel() {
        synchronized (status) {
            status = Status.CANCELED;
        }
        vipClients.stream().parallel().forEach(client -> {
            try {
                client.canceledConcert(name);
            } catch (RemoteException e) {
                System.out.println("There was an error communicating " + client.toString() + " about VIP ticket cancellation for " + name);
            }
        });
        regularClients.stream().parallel().forEach(client -> {
            try {
                client.canceledConcert(name);
            } catch (RemoteException e) {
                System.out.println("There was an error communicating " + client.toString() + " about ticket cancellation for " + name);
            }
        });
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Concert concert = (Concert) o;

        return name.equals(concert.name);
    }

    @Override
    public String toString() {
        return "Concert{" +
                "name='" + name + '\'' +
                '}';
    }
}
