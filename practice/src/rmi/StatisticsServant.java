package rmi;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StatisticsServant implements Statistics {

    public StatisticsServant() throws RemoteException {
        super();
        UnicastRemoteObject.exportObject(this, 0);
    }

    public static void main(String[] args) {
//        if (System.getSecurityManager() == null) {
//            System.setSecurityManager(new SecurityManager());
//        }
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            Statistics servant = new StatisticsServant();
            registry.rebind("Statistics", servant);
            System.out.println("Service registered");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public double getMedian(int[] elements) throws RemoteException {
        check(elements);
        Arrays.sort(elements);

        int leftIdx = elements.length / 2 - 1;
        int rightIdx = elements.length / 2;

        if (elements.length % 2 == 0) {
            return (elements[leftIdx] + elements[rightIdx]) / 2.0;
        }
        return elements[rightIdx];
    }

    @Override
    public List<Integer> getMode(int[] elements) throws RemoteException {
        check(elements);
        final Map<Integer, Long> map = Arrays.stream(elements)
                                             .boxed()
                                             .collect(Collectors.groupingBy(Function.identity(),
                                                                            Collectors.counting()));
        final long maxFrequency = map.values().stream().max(Comparator.naturalOrder()).get();

        return map.entrySet()
                  .stream()
                  .filter(tuple -> tuple.getValue() == maxFrequency)
                  .map(Map.Entry::getKey)
                  .collect(Collectors.toList());
    }

    @Override
    public double getAverage(int[] elements) throws RemoteException {
        check(elements);
        return Arrays.stream(elements).average().getAsDouble();
    }

    private void check(int[] elements) throws RemoteException {
        if (elements == null || elements.length == 0) {
            throw new RemoteException("Invalid argument");
        }
    }
}
