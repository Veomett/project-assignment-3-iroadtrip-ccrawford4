package Source.Classes;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
public class IRoadTrip {
    List<Node> nodes;
    Map<Node, List<Node>> neighbors;
    Graph graph;
    public IRoadTrip (String [] args) {
        if (args.length < 3) {
            System.err.println("ERROR! Not enough command line arguments.");
        }
        String basePath = "./Resources/";
        File borderFile = new File(basePath + args[0]);
        File capDistFile = new File(basePath + args[1]);
        File stateNameFile = new File(basePath + args[2]);
        try {
            this.nodes = createNodeList(stateNameFile);
            addNeighbors(borderFile);
           // this.neighbors = createNeighborsMap(borderFile);
            this.graph = createGraph(nodes, capDistFile);
        } catch (Exception e) {
            System.err.println(e.toString());
        }
    }

    public Node getNodeFromLine(String line, int equalIndex) {
        if (equalIndex == -1) {
            return null;
        }
        String parentName = line.substring(0, equalIndex - 1);
        return findNodeFromName(parentName);
    }
    public void addNeighbors(File borderFile) {
        try {
            Scanner stream = new Scanner(borderFile);
            while (stream.hasNextLine()) {
                String data = stream.nextLine();
                int equalIndex = data.indexOf('=');
                Node n = getNodeFromLine(data, equalIndex);
                if (!stream.hasNextLine()) {
                    return;
                }
                String[] countries = data.substring(equalIndex + 1).split(";");
                int semiColonIndex = data.indexOf(';');
                if (semiColonIndex == -1 && n != null) {
                    Node neighbor = findNodeFromName(data.substring(equalIndex + 1));
                    if (neighbor != null) {
                        n.addNeighbor(n);
                    }
                }

                if (countries.length > 2 && n != null) {
                    for (String country : countries) {
                        int kmIndex = -1;
                        for (int i = 0; i < country.length(); i++) {
                            if (Character.isDigit(country.charAt(i))) {
                                kmIndex = i;
                                break;
                            }
                        }
                        String countryName = country.substring(0, kmIndex - 1).trim();
                        Node neighbor = findNodeFromName(countryName);
                        if (neighbor != null) {
                            n.addNeighbor(neighbor);
                        }
                    }
                }
            }


        } catch (FileNotFoundException e) {
            System.err.println("ERROR! FILE NOT FOUND.");
        }

    }

    /*public Map<Node, List<Node>> createNeighborsMap(File borderFile) {
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
    }*/

    public Node findNodeFromName(String countryName) {
        for (Node node : nodes) {
            if (node.getCountryName().toLowerCase().contains(countryName.toLowerCase())) {
                return node;
            }
            if (node.getStateId().toLowerCase().contains(countryName.toLowerCase())) {
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
                String currentDate = "2020-12-31";
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
                    } else {
                        countryName += segment[index];
                        countryName += " ";
                    }
                    index++;
                }
                countryName = countryName.trim();
                if (countryName.contains(",")) {
                    String[] updatedSegment = countryName.split(",");
                    countryName = updatedSegment[1].trim() + " " + updatedSegment[0].trim();
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
            System.out.println("source is null");
            return -1;
        }
        if (destination == null) {
            System.out.println("destination is null");
            return -1;
        }
        return graph.getDistance(source, destination);
    }


    boolean isValidPair(Node nodeA, Node nodeB) {
        return (nodeA != null) && (nodeB != null) && nodeA.getNeighbors().contains(nodeB);
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

                if (isValidPair(source, destination)) {
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
        Node source = findNodeFromName(country1);
        Node destination = findNodeFromName(country2);
        if (source == null) {
            return new ArrayList<>();
        }
        if (destination == null) {
            return new ArrayList<>();
        }
        return graph.findPath(source, destination);
    }

    public Node getNodeFromInput(String countryNumber) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Enter the name of the " + countryNumber + " country (type EXIT to quit): ");
            String countryName = scanner.nextLine();
            Node node = findNodeFromName(countryName);
            if (countryName.equals("EXIT")) {
                return null;
            }
            if (node == null) {
                System.out.println("Invalid country name. Please enter a valid country name.");
                continue;
            }
            return node;
        }
    }
    public void acceptUserInput() {
        while (true) {
            Node source = getNodeFromInput("first");
            if (source == null) {
                return;
            }
            Node destination = getNodeFromInput("second");
            if (destination == null) {
                return;
            }
            System.out.println("Route from " + source.getCountryName() + " to " + destination.getCountryName() + ":");
            graph.printShortestPath(source, destination);
        }

    }

    public static void main(String[] args) {
        IRoadTrip a3 = new IRoadTrip(args);
       // System.out.println(a3.getDistance("Czech Republic", "Russia"));
        a3.acceptUserInput();
    }
}


