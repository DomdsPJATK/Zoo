package Models;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Cycle {

    private LinkedList<Elephant> elephants;

    public Cycle() {
        elephants = new LinkedList<Elephant>();
    }

    public void addElephantToCyckle(Elephant elephant){
        if(elephants == null) new LinkedList<Elephant>();
        elephants.add(elephant);
    }

    public LinkedList<Elephant> getElephants() {
        return elephants;
    }

    public static List<Cycle> findCycles(List<Elephant> elephants, List<Integer> predictedPositions){
        if(elephants.size() != predictedPositions.size()) return new ArrayList<>();
        boolean[] visited = new boolean[elephants.size()];
        int numberOfVisited = 0;
        List<Cycle> cycles = new ArrayList<>();

        for (int i = 0; i < elephants.size(); i++) {
            if(!visited[i]){
                visited[i] = true;
                numberOfVisited++;
                Cycle currentCycle = new Cycle();
                Elephant currentElephant = elephants.get(i);
                currentCycle.addElephantToCyckle(currentElephant);
                int currentPredictedId = predictedPositions.get(i);

                int iter = 0;
                while(true){
                    if(iter == elephants.size()) iter = 0;
                    if(!visited[iter]){
                        if (elephants.get(iter).getId() == currentPredictedId) {
                            visited[iter] = true;
                            currentElephant = elephants.get(iter);
                            currentCycle.addElephantToCyckle(currentElephant);
                            currentPredictedId = predictedPositions.get(iter);
                            numberOfVisited++;
                        }
                    }
                    if(elephants.get(iter).getId() == currentCycle.getElephants().getFirst().getId()) break;
                    iter++;
                }
                cycles.add(currentCycle);
            }
            if(numberOfVisited == visited.length) return cycles;
        }

        return cycles;
    }

}
