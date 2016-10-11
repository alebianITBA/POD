package practica3;

import java.io.Serializable;
import java.time.LocalDate;

public class Book implements Serializable {
    private static final long serialVersionUID = 10001L;

    private String isbn;
    private String title;
    private LocalDate publicationDate;
    private Author author;

    public Book(String isbn, String title, String date, Author author) {
        this.isbn = isbn;
        this.title = title;
        this.publicationDate = LocalDate.parse(date);
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public Author getAuthor() {
        return author;
    }
}
