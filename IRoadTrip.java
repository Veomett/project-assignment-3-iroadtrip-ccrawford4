import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.NoSuchFileException;
import java.util.*;
import java.util.stream.Stream;

public class IRoadTrip {
    DisjointSet disjointSet;
    private final String currentDate = "2020-12-31";
    List<Node> nodes;
    File capDistFile;
    public IRoadTrip (String [] args) {
        if (args.length < 3) {
            System.err.println("ERROR! Not enough command line arguments.");
        }
        File borderFile = new File(args[0]);
        this.capDistFile = new File(args[1]);
        File stateNameFile = new File(args[2]);

        try {
            this.nodes = createNodeList(stateNameFile);
            this.disjointSet = createDisjointSet(borderFile, nodes);
        } catch (Exception e) {
            System.err.println(e.toString());
        }
    }

    public Node findNodeFromName(String countryName) {
        for (Node node : nodes) {
            if (node.getCountryName().contains(countryName)) {
                return node;
            }
        }
        return null;
    }

    public Node findNodeFromNumber(int stateNumber) {
        for (Node node : nodes) {
            if (node.getStateNumber() == stateNumber) {
                return node;
            }
        }
        return null;
    }

    public int getBiggestStateNumber(List<Node> nodes) {
        int maxStateNumber = 0;
        for (Node node : nodes) {
            maxStateNumber = Math.max(maxStateNumber, node.getStateNumber());
        }
        return maxStateNumber;
    }

    public DisjointSet createDisjointSet(File borderFile, List<Node> nodes) throws Exception {
        DisjointSet result = new DisjointSet(getBiggestStateNumber(nodes) + 1);
        try {
            Scanner readBorders = new Scanner(borderFile);
            while (readBorders.hasNextLine()) {
                String borderData = readBorders.nextLine();
                int equalIndex = borderData.indexOf('=');
                String countryName = borderData.substring(0, equalIndex - 1).trim();
                Node parent = findNodeFromName(countryName);
                if (parent == null) { // Account for if the parent is not found inside the state_name file
                    continue;
                }
                String[] segment = borderData.substring(equalIndex + 2).split(";");
                for (String country : segment) {
                    country = country.trim();
                    String[] names = country.split(" ");
                    String name = names[0];
                    Node child = findNodeFromName(name);
                    if (child == null) {
                        continue;
                    }
                    result.Union(parent.getStateNumber(), child.getStateNumber());

                }
            }
        }
        catch (FileNotFoundException e) {
            throw new FileNotFoundException("Error Parsing File into Disjoint Set");
        }
        return result;
    }

    List<Node> createNodeList (File stateNameFile) throws Exception {
        List<Node> nodes = new ArrayList<>();
        try {
            Scanner reader = new Scanner(stateNameFile);
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                String[] segment = data.split("\t");
                if (!segment[segment.length-1].equals(currentDate)) {
                    continue;
                }
                int stateNumber = Integer.parseInt(segment[0]);
                String stateId = segment[1];
                String countryName = "";
                int index = 2;
                while (true) {
                    char test = segment[index].charAt(0);
                    if (Character.isDigit(test)) {
                        break;
                    }
                    else {
                        countryName += segment[index];
                        countryName += " ";
                    }
                    index++;
                }
                Node newNode = new Node(stateNumber, stateId, countryName);

                nodes.add(newNode);
            }
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("Error Parsing File into Node List");
        }
        return nodes;
    }

    public int getDistance (String country1, String country2) {
        Node source = findNodeFromName(country1);
        Node destination = findNodeFromName(country2);
        if (source == null) {
            System.out.println("ERROR! " + country1 + " is not found.");
            return -1;
        }
        if (destination == null) {
            System.out.println("ERROR! " + country2 + " is not found.");
            return -1;
        }
        int parentOne = disjointSet.Find(source.getStateNumber());
        int parentTwo = disjointSet.Find(destination.getStateNumber());
        if (parentOne != parentTwo) {
            return -1; // The countries are not connected
        }
        List<Node> subList = findLocalNodes(parentOne);
        Graph graph = createGraph(subList);
        Map<Node, Integer> shortestDistances = runDijkstra(graph, source);
        int shortestDistance = shortestDistances.get(destination);

        if (shortestDistance == Integer.MAX_VALUE) {
            return -1;
        }
        return shortestDistance;
    }


    public Map<Node, Integer> runDijkstra(Graph graph, Node source) {
        Map<Node, Integer> shortestDistances = new HashMap<>();
        PriorityQueue<Edge> minHeap = new PriorityQueue<>(Comparator.comparingInt(edge -> edge.weight));

        for (Node node : graph.getNodes()) {
            shortestDistances.put(node, Integer.MAX_VALUE);
        }
        shortestDistances.put(source, 0);

        minHeap.offer(new Edge(source, 0));

        while (!minHeap.isEmpty()) {
            Edge currentEdge = minHeap.poll();
            Node currentNode = currentEdge.destination;
            int currentDistance = currentEdge.weight;

            if (currentDistance > shortestDistances.get(currentNode)) {
                continue;
            }

            for (Edge edge : graph.getNeighbors(currentNode)) {
                int newDistance = currentDistance + edge.weight;
                if (newDistance < shortestDistances.get(edge.destination)) {
                    minHeap.offer(new Edge(edge.destination, newDistance));
                }
            }
        }
        return shortestDistances;
    }


    List<Node> findLocalNodes(int parent) {
        List<Node> result = new ArrayList<>();
        for (Node n : nodes) {
            if (disjointSet.Find(n.getStateNumber()) == parent) {
                result.add(n);
            }
        }
        return result;
    }

    boolean validPair(Collection<Node> subList, Node nodeA, Node nodeB) {
        return (nodeA != null && subList.contains(nodeA)) && (nodeB != null && subList.contains(nodeB));
    }
    void addEdges(Graph graph) {
        try {
            Scanner reader = new Scanner(capDistFile);
            int count = 0;
            Collection<Node> subList = graph.getNodes();
            while (reader.hasNextLine()) {
                if (count == 0) {
                    reader.nextLine();
                    count++;
                    continue;
                }
                String data = reader.nextLine();
                String[] segment = data.split(",");
                int stateNumber = Integer.parseInt(segment[0]);
                Node node = findNodeFromNumber(stateNumber);
                int stateNumberTwo = Integer.parseInt(segment[2]);
                Node childNode = findNodeFromNumber(stateNumberTwo);
                int distance = Integer.parseInt(segment[4]);
                if (validPair(subList, node, childNode)) {
                    graph.addEdge(node, childNode, distance);
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("ERROR! Capital-Distance File Not Found.");
        }
    }
    Graph createGraph(List<Node> subList) {
        Graph graph = new Graph();
        for (Node n : subList) {
            graph.addNode(n);
        }
        addEdges(graph);
        return graph;
    }

    public List<String> findPath (String country1, String country2) {
        // Replace with your code
        return null;
    }

    public void acceptUserInput() {
        // Replace with your code
        System.out.println("IRoadTrip - skeleton");
    }



    public static void main(String[] args) {
        IRoadTrip a3 = new IRoadTrip(args);
        System.out.println(a3.getDistance("America", "Canada"));
        a3.acceptUserInput();
    }
}


