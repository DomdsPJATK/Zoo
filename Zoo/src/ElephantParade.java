import Models.Elephant;

import java.io.*;
import java.util.*;

public class ElephantParade {

    private String pathIn;
    private String pathOut;
    private Map<Integer, Elephant> elephants;
    private int elephantQuantity;
    private long result;
    private short globalMin;

    public ElephantParade(String pathIn, String pathOut) {
        this.pathIn = pathIn;
        this.pathOut = pathOut;
        this.elephants = new LinkedHashMap<>();
        try {
            readFromFile(pathIn);
        } catch (IOException e) {
            e.printStackTrace();
        }
        result = countResultCostOfReorder();
        System.out.println(result);
    }


    private void readFromFile(String path) throws IOException{
        File file = new File(path);
        FileInputStream fileInputStream = new FileInputStream(file);
        BufferedReader br = new BufferedReader(new InputStreamReader(fileInputStream));

        String elephantQuantityLine = br.readLine();
        String weightsLine = br.readLine();
        String initialPositionsLine = br.readLine();
        String predictedPositionsLine = br.readLine();

        this.elephantQuantity = Integer.parseInt(elephantQuantityLine);
        this.elephants = createMapOfElephants(weightsLine, initialPositionsLine, predictedPositionsLine, this.elephantQuantity);

    }


    private long countResultCostOfReorder() {
        long result = 0;

        for (Map.Entry<Integer, Elephant> entry : elephants.entrySet()) {
            Elephant currentElephant = entry.getValue();
            if(!currentElephant.isVisited()) {
                currentElephant.setVisited(true);
                int firstVertexId = currentElephant.getId();
                short cycleMin = currentElephant.getWeight();
                long cycleSum = currentElephant.getWeight();
                int cycleQuantity = 1;

                while(firstVertexId != currentElephant.getPairId()){
                    Elephant nextElephant = elephants.get(currentElephant.getPairId());
                    nextElephant.setVisited(true);
                    cycleMin = (cycleMin > nextElephant.getWeight()) ? nextElephant.getWeight() : cycleMin;
                    cycleSum += nextElephant.getWeight();
                    cycleQuantity++;
                    currentElephant = nextElephant;
                }

                System.out.println(cycleSum + " " + cycleMin + " " + cycleQuantity + " " + globalMin);
                long firstMethod = cycleSum + (cycleQuantity - 2) * cycleMin;
                long secondMethod = cycleSum + cycleMin + (cycleQuantity + 1) * globalMin;
                result += (firstMethod > secondMethod) ? secondMethod : firstMethod;
            }
        }

        return result;
    }


    public Map<Integer, Elephant> createMapOfElephants(String weightsLine, String currentPositionsLine, String predictedPositionsLine, int quantity) {
        LinkedHashMap<Integer, Elephant> elephants = new LinkedHashMap<>();
        String[] weightsTab = weightsLine.split(" ");;
        String[] positionsTab = currentPositionsLine.split(" ");
        String[] predictedPositionsTab = predictedPositionsLine.split(" ");

        globalMin = Short.parseShort(weightsTab[0]);

        for (int i = 0; i < quantity; i++) {
            short currentWeight = Short.parseShort(weightsTab[i]);
            globalMin = (globalMin > currentWeight) ? currentWeight : globalMin;
            elephants.put(Integer.parseInt(positionsTab[i]),new Elephant(currentWeight,Integer.parseInt(positionsTab[i]),Integer.parseInt(predictedPositionsTab[i])));
        }

        System.out.println(globalMin);

        return elephants;
    }

    //-------------------------------------------------------------------

    public static void main(String[] args) {
        ElephantParade elephantParade = new ElephantParade("Zoo/slo1.in", "result.out");
    }

}
