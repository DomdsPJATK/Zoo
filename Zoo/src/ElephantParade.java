import Models.Elephant;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ElephantParade {

    private String pathIn;
    private String pathOut;
    private List<Boolean> visited;
//    private List<Integer, Integer> elephants;
    private List<Elephant> elephants;
    private List<Integer> predictedPositions;
    private int elephantQuantity;

    public ElephantParade(String pathIn, String pathOut) {
        this.pathIn = pathIn;
        this.pathOut = pathOut;
        this.visited = new ArrayList<>();
        this.elephants = new ArrayList<>();
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

        this.elephantQuantity = Integer.parseInt(br.readLine());
        this.elephants = createListOfElephants(br.readLine(), br.readLine(), this.elephantQuantity);
        this.predictedPositions = createListOfPredicatedPositions(br.readLine());
        this.visited = createListOfVisited(this.elephantQuantity);
        System.out.println(elephantQuantity);
        System.out.println(elephants);
        System.out.println(predictedPositions);
        System.out.println(visited);
    }

    public List<Boolean> createListOfVisited(int quantity){
        List<Boolean> visited =  new ArrayList<>(Arrays.asList(new Boolean[quantity]));
        Collections.fill(visited, false);
        return visited;
    }

    public List<Integer> createListOfPredicatedPositions(String predicatedLine) {
        int[] predictedTab = Stream.of(predicatedLine.split(" ")).mapToInt(Integer::parseInt).toArray();
        return Arrays.stream(predictedTab).boxed().collect(Collectors.toList());
    }

    public List<Elephant> createListOfElephants(String weightsLine, String currentPositionsLine, int quantity) {
        List<Elephant> elephants = new ArrayList<>();
        String[] weightsTab = weightsLine.split(" ");;
        String[] positionsTab = currentPositionsLine.split(" ");

        for (int i = 0; i < quantity; i++) {
            elephants.add(new Elephant(Integer.parseInt(weightsTab[i]), Integer.parseInt(positionsTab[i])));
        }

        return elephants;
    }

    public static void main(String[] args) {
        ElephantParade elephantParade = new ElephantParade("slo1.in", "result.out");
    }

}
