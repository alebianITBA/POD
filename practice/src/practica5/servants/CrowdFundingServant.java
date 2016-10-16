package practica5.servants;

import practica5.interfaces.CrowdFundingBackerResponseHandler;
import practica5.interfaces.CrowdFundingBackerService;
import practica5.interfaces.CrowdFundingInitiatorService;
import practica5.models.Pledge;
import practica5.models.Project;
import practica5.models.Reward;

import java.io.IOException;
import java.rmi.MarshalledObject;
import java.rmi.RemoteException;
import java.rmi.activation.Activatable;
import java.rmi.activation.ActivationID;
import java.util.*;

public class CrowdFundingServant implements CrowdFundingInitiatorService, CrowdFundingBackerService {
    private List<Project> activeProjects;
    private List<Project> cancelledProjects;
    private Map<Project, List<Reward>> rewards;
    private Map<Project, List<Pledge>> pledges;

    public CrowdFundingServant(ActivationID id, MarshalledObject data) throws IOException, ClassNotFoundException {
        Activatable.exportObject(this, id, 0);
        System.out.println("Starting servant...");
        activeProjects = new ArrayList<>();
        cancelledProjects = new ArrayList<>();
        rewards = new HashMap<>();
        pledges = new HashMap<>();
        if (data != null) {
//            storage = (File) data.get();
//            if (storage.exists()) {
//                read();
//            } else {
//                System.out.println("Generating cache...");
//                modeCache = new HashMap<>();
//                store();
//            }
        }
    }

    @Override
    public boolean createProject(String name, int goal, List<Reward> prizes) throws RemoteException {
        synchronized (activeProjects) {
            if (findProject(name) != null) {
                return false;
            }
            Project project = new Project(name, goal);
            activeProjects.add(project);
            rewards.put(project, Optional.ofNullable(prizes).orElse(new LinkedList<>()));
            pledges.put(project, new ArrayList<>());
        }
        return true;
    }

    @Override
    public boolean cancelProject(String name) throws RemoteException {
        Project project = null;
        synchronized (activeProjects) {
            project = findProject(name);
            if (project == null) {
                return false;
            } else {
                synchronized (cancelledProjects) {
                    activeProjects.remove(project);
                    cancelledProjects.add(project);
                }
            }
        }
        Optional.ofNullable(pledges.get(project)).ifPresent(p -> {
            p.stream().parallel().forEach(pledge -> {
                try {
                    pledge.onCancel();
                } catch (RemoteException e) {
                    System.out.println("Error cancelling");
                }
            });
        });
        return true;
    }

    @Override
    public List<Project> listProjects() throws RemoteException {
        return activeProjects;
    }

    @Override
    public void pledge(Project project, int amount, CrowdFundingBackerResponseHandler supporter) throws RemoteException {
        synchronized (activeProjects) {
            Project storedProject = findProject(project.getName());
            if (storedProject == null) {
                supporter.errorOnPledge(project.getName(), amount, "Doesn't exist");
                return;
            }
            pledges.get(storedProject).add(new Pledge(amount, project.getName(), supporter));
            supporter.pledgeReceived(project.getName(), amount);
            checkRewards(project);
        }
    }

    private void checkRewards(Project project) {
        int collected = 0;
        for (Pledge p: pledges.get(project)) {
            collected += p.getAmount();
        }
        if (collected > project.getAmount()) {
            pledges.get(project).stream().parallel().forEach(pledge -> {
                try {
                    pledge.getSupporter().prizeGranted(project.getName(), pledge.getAmount(), pledge.getReward());
                } catch (RemoteException e) {
                    System.out.println("Error granting prize");
                }
            });
        }
    }

    private Project findProject(String name) {
        for (Project p: activeProjects) {
            if (p.getName().equals(name)) {
                return  p;
            }
        }
        return null;
    }
}
