package practica5.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

/** Backer response service */
public interface CrowdFundingBackerResponseHandler extends Remote {
    /**
     * Sends any error occurred calling:
     * {@link CrowdFundingBackerService#pledge}
     */
    public void errorOnPledge(String name, int amount, String errorMsg) throws RemoteException;
    /**
     * Called after {@link CrowdFundingBackerService#pledge} if the amount was accepted
     * but the prize is not given because the project is not confirmed yet.
     */
    public void pledgeReceived(String name, int amount) throws RemoteException;
    /**
     * Called when a prize is given to a confirmed project.
     */
    public void prizeGranted(String name, int amount, String reward) throws RemoteException;
    /**
     * Called when returning the amount after a project was cancelled.
     */
    public void pledgeReturned(String name, int amount) throws RemoteException;
}
