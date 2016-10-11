package practica3;

import practica2.ejercicio4.Service;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.ExportException;

public class Client {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            LibraryService library = (LibraryService) registry.lookup("Library");

            System.out.println(library.listBooks());
            String gunslingerIsbn = "978-0451210845";
            Book gunslinger1 = library.lendBook(gunslingerIsbn);
            if (gunslinger1 != null) {
                System.out.println("Got " + gunslinger1.getTitle());
            }
            Book gunslinger2 = library.lendBook(gunslingerIsbn);
            if (gunslinger1 != null) {
                System.out.println("Got " + gunslinger2.getTitle());
            }
            Book gunslinger3 = library.lendBook(gunslingerIsbn);
            if (gunslinger1 == null) {
                System.out.println("Library was out of " + gunslinger2.getTitle());
            }
            library.returnBook(gunslinger2);
            System.out.println("Returned one " + gunslinger2.getTitle());
            gunslinger3 = library.lendBook(gunslingerIsbn);
            if (gunslinger3 != null) {
                System.out.println("Got " + gunslinger2.getTitle());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
