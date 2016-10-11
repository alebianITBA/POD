package practica3;

import java.io.Serializable;

public class Author implements Serializable {
    private static final long serialVersionUID = 20001L;

    private String firstName;
    private String lastName;

    public Author(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
