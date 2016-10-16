package teoria5;

import java.io.*;
import java.rmi.MarshalledObject;
import java.rmi.RemoteException;
import java.rmi.activation.Activatable;
import java.rmi.activation.ActivationID;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StatisticsServant implements Statistics {
    private Map<ArrayContainer, Double> modeCache;
    private File storage;

    public StatisticsServant(ActivationID id, MarshalledObject data) throws IOException, ClassNotFoundException {
        Activatable.exportObject(this, id, 0);
        System.out.println("Starting servant...");
        if (data != null) {
            storage = (File) data.get();
            if (storage.exists()) {
                read();
            } else {
                System.out.println("Generating cache...");
                modeCache = new HashMap<>();
                store();
            }
        }
    }

    private void read() {
        if (storage != null) {
            System.out.println("Reading storage...");
            try (ObjectInputStream ois = new ObjectInputStream((new FileInputStream(storage)))) {
                modeCache = (Map<ArrayContainer, Double>) ois.readObject();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private void store() {
        if (storage != null) {
            System.out.println("Storing data");
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(storage))) {
                oos.writeObject(modeCache);
                oos.flush();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public double getMedian(int[] elements) throws RemoteException {
        check(elements);
        Arrays.sort(elements);
        // Check in the cache if the result was already calculated
        ArrayContainer container = new ArrayContainer(elements);
        if (modeCache.containsKey(container)) {
            return modeCache.get(container);
        }
        int leftIdx = elements.length / 2 - 1;
        int rightIdx = elements.length / 2;

        // Calculate the result
        Double result;
        if (elements.length % 2 == 0) {
            result = (elements[leftIdx] + elements[rightIdx]) / 2.0;
        } else {
            result = Double.valueOf(elements[rightIdx]);
        }
        // Store in the cache
        modeCache.put(container, result);

        return result;
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
