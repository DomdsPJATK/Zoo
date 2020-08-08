package Models;

public class Elephant {

    private short weight;
    private int id;
    private int pairId;
    private boolean isVisited;

    public Elephant(int id, int pairId) {
        this.weight = weight;
        this.id = id;
        this.pairId = pairId;
        this.isVisited = false;
    }

    public void setPairId(short newPairId) {
        this.pairId = newPairId;
    }

    public short getWeight() {
        return weight;
    }

    public int getId() {
        return id;
    }

    public int getPairId() {
        return pairId;
    }

    public void setWeight(short weight) {
        this.weight = weight;
    }

    public boolean isVisited() {
        return isVisited;
    }

    public void setVisited(boolean visited) {
        isVisited = visited;
    }

    public String print() {
        return "Elephant id: " + this.id + " -> pairId: " + this.pairId + ", weight: " + this.weight;
    }

    @Override
    public String toString() {
        return String.valueOf(this.id);
    }

}
