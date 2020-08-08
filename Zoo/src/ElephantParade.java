import Models.Elephant;

import java.io.*;
import java.util.*;

public class ElephantParade {

    private String pathIn;
    private String pathOut;
    private Map<Integer, Elephant> elephants;
    private int elephantQuantity;
    private List<List<Elephant>> cycles;
    private int result = 0;

    public ElephantParade(String pathIn, String pathOut) {
        this.pathIn = pathIn;
        this.pathOut = pathOut;
        this.elephants = new HashMap<>();
        try {
            readFromFile(pathIn);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.cycles = findCycles();
        System.out.println(cycles);
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


    private List<List<Elephant>> findCycles() {
        List<List<Elephant>> cycles = new ArrayList<>();
        int singleCycleQuantity = 0;

        for (Map.Entry<Integer, Elephant> entry : elephants.entrySet()) {
            Elephant currentElephant = entry.getValue();
            if(!currentElephant.isVisited()) {
                currentElephant.setVisited(true);
                List<Elephant> cycle = new ArrayList<>();
                cycle.add(currentElephant);

                while(cycle.get(0).getId() != currentElephant.getPairId()){
                    Elephant nextElephant = elephants.get(currentElephant.getPairId());
                    nextElephant.setVisited(true);
                    cycle.add(nextElephant);
                    currentElephant = nextElephant;
                }

                if(cycle.size() == 1) singleCycleQuantity++;
                cycles.add(cycle);
            }
        }
        System.out.println(singleCycleQuantity);
        return cycles;
    }


    public Map<Integer, Elephant> createMapOfElephants(String weightsLine, String currentPositionsLine, String predictedPositionsLine, int quantity) {
        HashMap<Integer, Elephant> elephants = new HashMap<>();
        String[] weightsTab = weightsLine.split(" ");;
        String[] positionsTab = currentPositionsLine.split(" ");
        String[] predictedPositionsTab = predictedPositionsLine.split(" ");

        for (int i = 0; i < quantity; i++) {
            elephants.put(Integer.parseInt(positionsTab[i]),new Elephant(Short.parseShort(weightsTab[i]),Integer.parseInt(positionsTab[i]),Integer.parseInt(predictedPositionsTab[i])));
        }

        return elephants;
    }

    //-------------------------------------------------------------------

    public static void main(String[] args) {
        ElephantParade elephantParade = new ElephantParade("slo1.in", "result.out");
    }

}
