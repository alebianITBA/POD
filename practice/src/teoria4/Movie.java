package teoria4;

import java.io.Serializable;

public class Movie implements Serializable {
    private String name;
    private String genre;

    public Movie(String name, String genre) {
        this.name = name;
        this.genre = genre;
    }

    public String getName() {
        return name;
    }

    public String getGenre() {
        return genre;
    }
}
