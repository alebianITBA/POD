package practica5.interfaces;

import practica5.models.Project;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/** Service to support projects through RMI */
public interface CrowdFundingBackerService extends Remote {
    /** @return list of projects available */
    public List<Project> listProjects() throws RemoteException;
    /**
     * Async method, responses are sent through:
     * {@link CrowdFundingBackerResponseHandler}
     */
    public void pledge(Project project, int amount, CrowdFundingBackerResponseHandler supporter) throws RemoteException;
}
