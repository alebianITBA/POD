package teoria5;

import utils.Service;
import utils.ServiceImpl;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Client {
    public static void main(String[] args) {

    }

    private static void executeRemote() throws RemoteException, NotBoundException, MalformedURLException {
        Service handle = (Service) Naming.lookup("//127.0.0.1:8000/service");
        System.out.println(handle.getDate());
        System.out.println(handle.farenheitToCelsious(120.0));
    }

    private static void executeLocal() {
        ServiceImpl service = new ServiceImpl();
        System.out.println(service.getDate());
        System.out.println(service.farenheitToCelsious(138.0));
    }
}
