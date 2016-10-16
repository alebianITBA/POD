package practica5.models;

import practica5.interfaces.CrowdFundingBackerResponseHandler;

import java.rmi.RemoteException;

public class Pledge {
    private final int amount;
    private final String name;
    private final CrowdFundingBackerResponseHandler supporter;

    public Pledge(int amount, String name, CrowdFundingBackerResponseHandler supporter) {
        this.amount = amount;
        this.name = name;
        this.supporter = supporter;
    }

    public int getAmount() {
        return amount;
    }

    public CrowdFundingBackerResponseHandler getSupporter() {
        return supporter;
    }

    public String getReward() {
        return "";
    }

    public void onCancel() throws RemoteException {
        supporter.pledgeReturned(name, amount);
    }
}
