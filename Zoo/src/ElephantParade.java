import Models.Elephant;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ElephantParade {

    private String pathIn;
    private String pathOut;
    private List<Boolean> visited;
    private Map<Integer, Integer> elephants;
    private List<Integer> currentPositions;
    private List<Integer> predictedPositions;
    private int elephantQuantity;

    public ElephantParade(String pathIn, String pathOut) {
        this.pathIn = pathIn;
        this.pathOut = pathOut;
        this.visited = new ArrayList<>();
        this.elephants = new HashMap<>();
        this.currentPositions = new ArrayList<>();
        this.predictedPositions = new ArrayList<>();
        try {
            readFromFile(pathIn);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readFromFile(String path) throws IOException{
        File file = new File(path);
        FileInputStream fileInputStream = new FileInputStream(file);
        BufferedReader br = new BufferedReader(new InputStreamReader(fileInputStream));

        String elephantQuantityLine = br.readLine();
        String weightsLine = br.readLine();
        String initialPositionsLine = br.readLine();
        String predictedPositionsLine = br.readLine();

        this.elephantQuantity = Integer.parseInt(elephantQuantityLine);
        this.elephants = createMapOfElephants(weightsLine, initialPositionsLine, this.elephantQuantity);
        this.currentPositions = createIntegersListFromLine(initialPositionsLine);
        this.predictedPositions = createIntegersListFromLine(predictedPositionsLine);
        this.visited = createListOfVisited(this.elephantQuantity);

        System.out.println(elephantQuantity);
        System.out.println(elephants);
        System.out.println(currentPositions);
        System.out.println(predictedPositions);
        System.out.println(visited);
    }

    private Map<Integer, Integer> createMapOfElephants(String weightsLine, String currentPositionsLine, int quantity) {
        HashMap<Integer, Integer> elephants = new HashMap<>();
        String[] weightsTab = weightsLine.split(" ");;
        String[] positionsTab = currentPositionsLine.split(" ");

        for (int i = 0; i < quantity; i++) {
            elephants.put(Integer.parseInt(positionsTab[i]),Integer.parseInt(weightsTab[i]));
        }

        return elephants;
    }

    public List<Boolean> createListOfVisited(int quantity){
        List<Boolean> visited =  new ArrayList<>(Arrays.asList(new Boolean[quantity]));
        Collections.fill(visited, false);
        return visited;
    }

    public List<Integer> createIntegersListFromLine(String line) {
        int[] tab = Stream.of(line.split(" ")).mapToInt(Integer::parseInt).toArray();
        return Arrays.stream(tab).boxed().collect(Collectors.toList());
    }


    public static void main(String[] args) {
        ElephantParade elephantParade = new ElephantParade("slo1.in", "result.out");
    }

}
