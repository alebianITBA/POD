package practica5.models;

import java.io.Serializable;

public class Project implements Serializable {
    private final String name;
    private final int amount;

    public Project(String name, int amount) {
        this.name = name;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public int getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Project project = (Project) o;

        return name.equals(project.name);

    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        return "Project{" +
                "name='" + name + '\'' +
                ", amount=" + amount +
                '}';
    }

    public void onCancel() {
    }
}
