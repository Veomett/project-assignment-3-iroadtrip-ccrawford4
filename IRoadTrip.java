import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.NoSuchFileException;
import java.util.*;
import java.util.stream.Stream;

public class IRoadTrip {
    private final String currentDate = "2020-12-31";
    List<Node> nodes;
    Map<Node, List<Node>> neighbors;
    Graph graph;
    public IRoadTrip (String [] args) {
        if (args.length < 3) {
            System.err.println("ERROR! Not enough command line arguments.");
        }
        File borderFile = new File(args[0]);
        File capDistFile = new File(args[1]);
        File stateNameFile = new File(args[2]);
        try {
            this.nodes = createNodeList(stateNameFile);
            this.neighbors = createNeighborsMap(borderFile);
            this.graph = createGraph(nodes, capDistFile);
        } catch (Exception e) {
            System.err.println(e.toString());
        }
    }

    public Map<Node, List<Node>> createNeighborsMap(File borderFile) {
        Map<Node, List<Node>> result = new HashMap<>();
        try {
            Scanner reader = new Scanner(borderFile);
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                int equalIndex = data.indexOf('=');
                String parentName = data.substring(0, equalIndex - 1);
                Node parentNode = findNodeFromName(parentName);
                if (parentNode != null) {
                    String[] countries = data.substring(equalIndex + 1).split(";");
                    if (countries.length > 1) {
                        List<Node> neighbors = new ArrayList<>();
                        for (String country : countries) {
                            String[] parts = country.split(" ");
                            String neighborName = parts[1];
                            Node neighborNode = findNodeFromName(neighborName);
                            if (neighborNode != null) {
                                neighbors.add(neighborNode);
                            }

                        }
                        result.put(parentNode, neighbors);
                    }
                }
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("ERROR FILE NOT FOUND");
        }
        return result;
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
            return -1;
        }
        if (destination == null) {
            return -1;
        }

        Map<Node, Integer> distances = graph.runDijkstra(source);
        int distance = distances.get(destination);
        if (distance != Integer.MAX_VALUE) {
            return distance;
        }
        return -1;
    }



    boolean validPair(Collection<Node> subList, Node nodeA, Node nodeB) {
        return (nodeA != null && subList.contains(nodeA)) && (nodeB != null && subList.contains(nodeB));
    }
    void addEdges(Graph graph, File capDistFile) {
        try {
            Scanner reader = new Scanner(capDistFile);
            int count = 0;
            while (reader.hasNextLine()) {
                if (count == 0) {
                    reader.nextLine();
                    count++;
                    continue;
                }
                String data = reader.nextLine();
                String[] segment = data.split(",");
                int stateNumber = Integer.parseInt(segment[0]);
                Node source = findNodeFromNumber(stateNumber);
                int stateNumberTwo = Integer.parseInt(segment[2]);
                Node destination = findNodeFromNumber(stateNumberTwo);
                int distance = Integer.parseInt(segment[4]);
                if (neighbors.get(source) != null && neighbors.get(source).contains(destination)) {
                        graph.addEdge(source, destination, distance);
                    }
                }


        } catch (FileNotFoundException e) {
            System.out.println("ERROR! Capital-Distance File Not Found.");
        }
    }
    Graph createGraph(List<Node> subList, File capDistFile) {
        Graph graph = new Graph();
        for (Node n : subList) {
            graph.addNode(n);
        }
        addEdges(graph, capDistFile);
        return graph;
    }

    public List<String> findPath (String country1, String country2) {
        // Replace with your code
        return null;
    }

    public void acceptUserInput() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Enter the name of the first country (type EXIT to quit): ");
            String name = scanner.nextLine();
            Node source = findNodeFromName(name);
            if (name.equals("EXIT")) {
                break;
            }
            if (source == null) {
                System.out.println("Invalid country name. Please enter a valid country name.");
                continue;
            }
            System.out.println("Enter the name of the second country (type EXIT to quit): ");
            String nameTwo = scanner.nextLine();
            Node destination = findNodeFromName(nameTwo);
            if (nameTwo.equals("EXIT")) {
                break;
            }
            if (destination == null) {
                System.out.println("Invalid country name. Please enter a valid country name.");
                continue;
            }
            System.out.println("Route from " + source.getCountryName() + " to " + destination.getCountryName() + ":");
            Map<Node, Integer> search = graph.runDijkstra(source);
            graph.printShortestPath(search, source, destination);
        }
    }




    public static void main(String[] args) {
        IRoadTrip a3 = new IRoadTrip(args);
       // System.out.println(a3.getDistance("Czech Republic", "Russia"));
        a3.acceptUserInput();
    }
}


