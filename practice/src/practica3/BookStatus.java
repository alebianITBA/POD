package practica3;

public class BookStatus {
    private boolean inLibrary;
    private Book book;

    public BookStatus(Book book) {
        this.book = book;
        this.inLibrary = true;
    }

    public Book getBook() {
        return this.book;
    }

    public synchronized boolean canLend() {
        return inLibrary;
    }

    public synchronized void lend() {
        this.inLibrary = false;
    }

    public synchronized void wasReturned() {
        this.inLibrary = true;
    }
}
