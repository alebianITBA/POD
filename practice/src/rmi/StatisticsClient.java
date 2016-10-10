package rmi;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class StatisticsClient {
    public static void main(String args[]) {
//        if (System.getSecurityManager() == null) {
//            System.setSecurityManager(new SecurityManager());
//        }
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            Statistics stats = (Statistics) registry.lookup("Statistics");

            int[] array = new int[] { 1, 2, 3};
            System.out.println(stats.getAverage(array));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
