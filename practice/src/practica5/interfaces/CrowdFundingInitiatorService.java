package practica5.interfaces;

import practica5.models.Reward;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/** Project management service through RMI */
public interface CrowdFundingInitiatorService extends Remote {
    /** @return true if the project was created */
    boolean createProject(String name, int goal, List<Reward> prizes) throws RemoteException;
    /** @return true if the project was canceled */
    boolean cancelProject(String name) throws RemoteException;
}
