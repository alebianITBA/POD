package practica3;

import rmi.Statistics;
import rmi.StatisticsServant;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Server {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            LibraryService servant = new LibraryServant(startingBooks());
            registry.rebind("Library", servant);
            System.out.println("Service registered");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private static List<Book> startingBooks() {
        List<Book> books = new ArrayList<>();
        Author georgeMartin = new Author("George R. R.", "Martin");
        Author frankHerbert = new Author("Frank", "Herbert");
        Author stephenKing = new Author("Stephen", "King");
        Author isaacAsimov = new Author("Isaac", "Asimov");
        Author brianHerbert = new Author("Brian", "Herbert");
        Author arthurConanDoyle = new Author("Arthur", "Conan Doyle");

        IntStream.rangeClosed(1, 3).forEach(e -> books.add(new Book("978-0345533487", "A Knight of the Seven Kingdoms", "2015-10-06", georgeMartin)));
        IntStream.rangeClosed(1, 4).forEach(e -> books.add(new Book("978-0441294671", "God Emperor of Dune"           , "1987-06-15", frankHerbert)));
        IntStream.rangeClosed(1, 2).forEach(e -> books.add(new Book("978-0451210845", "The Gunslinger"                , "2003-07-01", stephenKing)));
        IntStream.rangeClosed(1, 5).forEach(e -> books.add(new Book("978-0307292063", "The Foundation Trilogy"        , "2011-11-25", isaacAsimov)));
        IntStream.rangeClosed(1, 4).forEach(e -> books.add(new Book("978-0765351494", "Sandworms of Dune"             , "2008-07-01", brianHerbert)));
        IntStream.rangeClosed(1, 1).forEach(e -> books.add(new Book("978-0307743657", "The Shining"                   , "2012-06-26", stephenKing)));
        IntStream.rangeClosed(1, 2).forEach(e -> books.add(new Book("978-0553328257", "The Complete Sherlock Holmes"  , "1986-10-01", arthurConanDoyle)));

        return books;
    }
}
