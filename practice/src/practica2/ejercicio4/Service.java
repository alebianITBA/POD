package practica2.ejercicio4;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Service extends Remote {
    public String ping() throws RemoteException;
    public String time() throws RemoteException;
    public String echo(String phrase) throws RemoteException;
    public String hello(String name) throws RemoteException;
    public String fortune() throws RemoteException;
}
