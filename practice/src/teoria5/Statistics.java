package teoria5;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface Statistics extends Remote {
    public double getMedian(int[] elements) throws RemoteException;
    public List<Integer> getMode(int[] elements) throws RemoteException;
    public double getAverage(int[] elements) throws RemoteException;
}
