package practica3;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

public class LibraryServant implements LibraryService {
    private List<Book> books;
    private Map<String, List<BookStatus>> lendDatabase;

    public LibraryServant(List<Book> books) throws RemoteException {
        UnicastRemoteObject.exportObject(this, 0);
        this.books = books;
        this.lendDatabase = new HashMap<>();
        books.forEach(book -> addBookToLibrary(book));
    }

    @Override
    public List<String> listBooks() throws RemoteException {
        List<String> result = new LinkedList<>();
        books.forEach(book -> result.add(book.getIsbn() + "-" + book.getTitle()));
        return result;
    }

    @Override
    public Book lendBook(String isbn) throws RemoteException {
        Optional<List<BookStatus>> database = Optional.ofNullable(lendDatabase.get(isbn));
        Book toLend = null;
        if (database.isPresent()) {
            synchronized (database.get()) {
                Optional<BookStatus> bs = database.get().stream().filter(status -> status.canLend()).findAny();
                if (bs.isPresent()) {
                    bs.get().lend();
                    toLend = bs.get().getBook();
                }
            }
        }
        return toLend;
    }

    @Override
    public void returnBook(Book bookToReturn) throws RemoteException {
        Optional<List<BookStatus>> database = Optional.ofNullable(lendDatabase.get(bookToReturn.getIsbn()));
        if (database.isPresent()) {
            synchronized (database.get()) {
                Optional<BookStatus> bs = database.get().stream().filter(status -> !status.canLend()).findAny();
                if (bs.isPresent()) {
                    bs.get().wasReturned();
                }
            }
        }
    }

    private void addBookToLibrary(Book book) {
        String isbn = book.getIsbn();
        Optional<List<BookStatus>> bookList = Optional.ofNullable(lendDatabase.get(isbn));
        if (!bookList.isPresent()) {
            lendDatabase.put(isbn, new ArrayList<>());
        }
        lendDatabase.get(isbn).add(new BookStatus(book));
    }
}
