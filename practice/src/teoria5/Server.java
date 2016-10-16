package teoria5;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.activation.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Properties;

public class Server {
    private static final String SERVANT_CLASS = "teoria5.StatisticsServant";
    private static final String CODEBASE_PATH = "file://../teoria5/server/target/classes";
    private static final String POLICY_PATH = "file:///java.policy";

    public static void main(String[] args) {
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        try {
            Properties pList = new Properties();
            pList.put("java.security.policy", POLICY_PATH);
            ActivationGroupDesc.CommandEnvironment configInfo = null;
            ActivationGroupDesc groupDesc = new ActivationGroupDesc(pList, configInfo);

            ActivationGroupID anAGroupID = ActivationGroup.getSystem().registerGroup(groupDesc);
            ActivationDesc aServantDescription = new ActivationDesc(anAGroupID, SERVANT_CLASS, CODEBASE_PATH, null);
            Remote stub = (Statistics) Activatable.register(aServantDescription);

            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            registry.rebind("Statistics", stub);
            System.out.println("Service registered");
            System.exit(0);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (ActivationException e) {
            e.printStackTrace();
        }
    }
}
