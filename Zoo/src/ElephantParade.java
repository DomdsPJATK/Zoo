import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ElephantParade {

    private String pathIn;
    private String pathOut;
    private Map<Integer, Integer> elephants;
    private List<Integer> currentPositions;
    private List<Integer> predictedPositions;
    private int elephantQuantity;
    private List<List<Integer>> cycles;

    public ElephantParade(String pathIn, String pathOut) {
        this.pathIn = pathIn;
        this.pathOut = pathOut;
        this.elephants = new HashMap<>();
        this.currentPositions = new ArrayList<>();
        this.predictedPositions = new ArrayList<>();
        try {
            readFromFile(pathIn);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.cycles = findCycles();
        System.out.println();
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
        this.elephants = createMapOfElephants(weightsLine, initialPositionsLine, this.elephantQuantity);
        this.currentPositions = createIntegersListFromLine(initialPositionsLine);
        this.predictedPositions = createIntegersListFromLine(predictedPositionsLine);

        System.out.println(elephantQuantity);
        System.out.println(elephants);
        System.out.println(currentPositions);
        System.out.println(predictedPositions);
    }

    private List<List<Integer>> findCycles() {
        List<Boolean> visited = createBooleanListWithDefualtValue(this.elephantQuantity);
        List<List<Integer>> cycles = new ArrayList<>();

        int numberOfvisited = 0;
        for (int i = 0; i < visited.size(); i++) {
            if(!visited.get(i)) {
                visited.set(i, true);
                numberOfvisited++;
                List<Integer> cycle = new ArrayList<>();
                cycle.add(currentPositions.get(i));
                int toFind = predictedPositions.get(i);

                int currentIndex = 0;
                while(cycle.get(0) != toFind){
                    if(!visited.get(currentIndex)){
                        if(currentPositions.get(currentIndex) == toFind){
                            visited.set(currentIndex, true);
                            numberOfvisited++;
                            cycle.add(toFind);
                            toFind = predictedPositions.get(currentIndex);
                        }
                    }
                    if(numberOfvisited == visited.size()) break;
                    currentIndex = (currentIndex < visited.size() - 1) ? currentIndex+1 : 0;
                }
                cycles.add(cycle);
            }
        }
        return cycles;
    }


    public Map<Integer, Integer> createMapOfElephants(String weightsLine, String currentPositionsLine, int quantity) {
        HashMap<Integer, Integer> elephants = new HashMap<>();
        String[] weightsTab = weightsLine.split(" ");;
        String[] positionsTab = currentPositionsLine.split(" ");

        for (int i = 0; i < quantity; i++) {
            elephants.put(Integer.parseInt(positionsTab[i]),Integer.parseInt(weightsTab[i]));
        }

        return elephants;
    }

    public List<Boolean> createBooleanListWithDefualtValue(int quantity){
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
