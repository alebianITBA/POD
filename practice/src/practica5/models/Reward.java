package practica5.models;

public class Reward {
    private final String description;
    private final int amount;

    public Reward(String description, int amount) {
        this.description = description;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Reward{" +
                "description='" + description + '\'' +
                ", amount=" + amount +
                '}';
    }
}
