package test;

import java.rmi.RemoteException;
import java.util.Arrays;

import rmi.StatisticsServant;
import rmi.Statistics;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class StatisticsServantTest {
    private static Statistics service;

    @Before
    public final void before() throws RemoteException {
        service = new StatisticsServant();
    }

    @Test
    public final void getMeanTest() throws RemoteException {
        assertEquals(9.0, service.getMedian(new int[] { 10, 20, 5, 6, 9 }), 0.1);
        assertEquals(35.5, service.getMedian(new int[] { 80, 10, 40, 31 }), 0.1);
    }

    @Test
    public final void getAverageTest() throws RemoteException {
        assertEquals(4.4, service.getAverage(new int[] { 3, 5, 5, 8, 1 }), 0.1);
    }

    @Test
    public final void getMode() throws RemoteException {
        assertEquals(Arrays.asList(8), service.getMode(new int[] { 7, 2, 8, 2, 8, 8 }));
        assertEquals(Arrays.asList(2, 8), service.getMode(new int[] { 8, 7, 2, 8, 8, 2, 8, 2, 2 }));
        assertEquals(Arrays.asList(2, 3, 4), service.getMode(new int[] { 2, 2, 4, 3, 4, 3 }));
    }
}
