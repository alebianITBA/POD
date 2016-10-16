package practica5.servants;

import practica5.interfaces.CrowdFundingBackerResponseHandler;

import java.rmi.RemoteException;

public class ClientServant implements CrowdFundingBackerResponseHandler {
    @Override
    public void errorOnPledge(String name, int amount, String errorMsg) throws RemoteException {
        System.out.println("There was an error placing $" + amount + " on project " + name + " error: " + errorMsg);
    }

    @Override
    public void pledgeReceived(String name, int amount) throws RemoteException {
        System.out.println("Pledge of $" + amount + " received on project " + name);

    }

    @Override
    public void prizeGranted(String name, int amount, String reward) throws RemoteException {
        System.out.println("Price granted " + " for project " + name + " reward: " + reward);

    }

    @Override
    public void pledgeReturned(String name, int amount) throws RemoteException {
        System.out.println("Pledge of $" + amount + " of project " + name + " returned because it was cancelled");

    }
}
