package Models;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Permutation {

    private final String pathOut;
    private final String pathIn;
    private int numberOfElephants;
    private List<Cycle> cycles;

    public Permutation(String pathIn, String pathOut) {
        this.pathIn = pathIn;
        this.pathOut = pathOut;

        List<Elephant> elephants = new ArrayList<>();
        elephants.add(new Elephant(1200, 4));
        elephants.add(new Elephant(1200, 1));
        elephants.add(new Elephant(1200, 3));
        elephants.add(new Elephant(1200, 2));
        elephants.add(new Elephant(1200, 5));

        List<Integer> predicted = new ArrayList<>();
        predicted.add(2);
        predicted.add(4);
        predicted.add(5);
        predicted.add(3);
        predicted.add(1);

        cycles = Cycle.findCycles(elephants, predicted);
        for (int i = 0; i < cycles.size(); i++) {
            System.out.println(cycles.get(i).getElephants());
        }
    }

}
