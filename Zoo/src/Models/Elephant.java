package Models;

public class Elephant {

    private int weight;
    private int number;

    public Elephant(int weight, int number) {
        this.weight = weight;
        this.number = number;
    }

    @Override
    public String toString() {
        return "Elephant number: " + this.number + " weight: " + this.weight;
    }
}
