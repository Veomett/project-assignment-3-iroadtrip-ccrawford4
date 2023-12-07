package Source.Classes;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Driver class used to operate/plan the road trip
 */
public class IRoadTrip {
    private Map<String, Node> nameMap;
    private Map<String, Node> caseMap;
    private Map<String, Node> idMap;
    private Map<Integer, Node> numberMap;
    private Graph graph;
    private EdgeCases edgeCases;

    /**
     * IRoadTrip constructor
     * @param args - An array of string arguments representing the fileNames
     */
    public IRoadTrip (String [] args) {
        if (args.length < 3) {
            System.err.println("ERROR! Not enough command line arguments.");
        }
        try {
            String basePath = "./Resources/";
            File borderFile = new File(basePath + args[0]);
            File capDistFile = new File(basePath + args[1]);
            File stateNameFile = new File(basePath + args[2]);

            this.edgeCases = new EdgeCases();
            this.nameMap = new HashMap<>();
            this.numberMap = new HashMap<>();
            this.idMap = new HashMap<>();
            this.caseMap = new HashMap<>();

            createNodeMaps(stateNameFile);
            addNeighbors(borderFile);

            this.graph = createGraph(capDistFile);
        } catch (Exception e) {
            System.err.println(e.toString());
        }
    }

    /**
     * Parses a line of text from the file and returns the node
     * @param line - Line of text from a file
     * @param equalIndex - Index of the equal sign in the line of text
     * @return - Returns the Node based on the country name
     */
    public Node getNodeFromLine(String line, int equalIndex) {
        if (equalIndex == -1) {
            return null;
        }
        String parentName = line.substring(0, equalIndex - 1);
        if (parentName.contains(",")) {
            String[] parts = parentName.split(",");
            parentName = parts[1].trim() + " " + parts[0].trim();
        }
        return findNodeFromName(parentName);
    }

    /**
     * Gets the country name given a string of text
     * @param data - The text being parsed
     * @return - The name of the country after cleaning up the text
     */
    public String getCountryName(String data) {
        int kmIndex = -1;
        for (int i = 0; i < data.length(); i++) {
            if (Character.isDigit(data.charAt(i))) {
                kmIndex = i;
                break;
            }
        }
        if (kmIndex != -1) {
            String countryName = data.substring(0, kmIndex - 1).trim();
            if (countryName.contains("(")) {
                int indexOf = countryName.indexOf('(');
                countryName = countryName.substring(0, indexOf - 1).trim();
            }
            return countryName;
        }
        return data;
    }

    /**
     * Takes in the borders.txt and uses said data to generate neighboring nodes
     * @param borderFile - borders.txt file
     */
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
                    String[] check = data.split("=");
                    if (check.length > 1 && !check[1].equals(" ")) {
                        Node neighbor = findNodeFromName(getCountryName(check[1]).trim());
                        if (neighbor != null && notEdgeCase(n, neighbor)) {
                            n.addNeighbor(n);
                            neighbor.addNeighbor(n);
                        }
                    }
                }

                if (semiColonIndex != -1 && countries.length >= 2 && n != null) {
                    for (String country : countries) {
                        int kmIndex = -1;
                        for (int i = 0; i < country.length(); i++) {
                            if (Character.isDigit(country.charAt(i))) {
                                kmIndex = i;
                                break;
                            }
                        }
                        String countryName = country.substring(0, kmIndex - 1).trim();
                        if (countryName.contains("(")) {
                            int indexOf = countryName.indexOf('(');
                            countryName = countryName.substring(0, indexOf - 1).trim();
                        }
                        Node neighbor = findNodeFromName(countryName);
                        if (neighbor != null && notEdgeCase(n, neighbor)) {
                            n.addNeighbor(neighbor);
                            neighbor.addNeighbor(n);
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("ERROR! FILE NOT FOUND.");
        }

    }

    /**
     * Accounts for the edge case in which the borders.txt file says
     * Canada and Denmark are connected! (They share a maritime border not usable
     * for an international road trip)
     * @param nodeA - Node A
     * @param nodeB - Node B
     * @return - Returns true if one is Canada and the other is Denmark and vise versa
     */
    public boolean notEdgeCase(Node nodeA, Node nodeB) {
        if (nodeA.getStateNumber() == 20 &&
                nodeB.getStateNumber() == 390) {
            return false;
        }
        if (nodeA.getStateNumber() == 390 &&
            nodeB.getStateNumber() == 20) {
            return false;
        }
        return true;
    }

    /**
     * Given a country name it finds and returns said node if it exists
     * @param countryName - Country name as string
     * @return - Returns the node associated with the country name if it exists
     */
    public Node findNodeFromName(String countryName) {
        if (edgeCases.containsKey(countryName.toLowerCase())) {
            countryName = edgeCases.getFormal(countryName.toLowerCase());
        }
        else if (idMap.containsKey(countryName.toUpperCase())) {
            Node n = idMap.get(countryName.toUpperCase());
            countryName = n.getCountryName();
        }
        if (nameMap.containsKey(countryName)) {
            return nameMap.get(countryName);
        }
        if (caseMap.containsKey(countryName.toLowerCase())) {
            return caseMap.get(countryName.toLowerCase());
        }
        return null;

    }

    /**
     * Finds the node given a state number if it exists
     * @param stateNumber - State number being used to search for the node
     * @return - Returns the node associated with the given state number or null if it doesn't exist
     */
    public Node findNodeFromNumber(int stateNumber) {
        if (numberMap.containsKey(stateNumber)) {
            return numberMap.get(stateNumber);
        }
        return null;
    }

    /**
     * Creates all the hashmaps that will be used throughout the class
     * @param stateNameFile - state_name.tsv file
     * @throws Exception - FileNotFoundException()
     */
    void createNodeMaps (File stateNameFile) throws Exception {
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
                nameMap.put(countryName, newNode);
                caseMap.put(countryName.toLowerCase(), newNode);
                numberMap.put(stateNumber, newNode);
                idMap.put(stateId, newNode);
            }
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("Error Parsing File into Node List");
        }
    }

    /**
     * Gets the distance between two countries given their names
     * @param country1 - Name of country one
     * @param country2 - Name of country two
     * @return - Returns the distance between them if they share an edge or -1 if they don't
     */
    public int getDistance (String country1, String country2) {
        Node source = findNodeFromName(country1);
        Node destination = findNodeFromName(country2);
        if (source == null) {
            return -1;
        }
        if (destination == null) {
            return -1;
        }
        if (source == destination) {
            return 0;
        }
        return graph.getDistance(source, destination);
    }
    /**
     * Helper function used to check if one node is neighbors with another
     * @param nodeA - Node one
     * @param nodeB - Node two
     * @return - True if the two are neighbors of each other
     */
    boolean isValidPair(Node nodeA, Node nodeB) {
        if (nodeA == null || nodeB == null) {
            return false;
        }
        return nodeA.getNeighbors().contains(nodeB);
    }

    /**
     * Goes through the capdist.csv file, checks if two countries (nodes)
     * are bordering each other and then adds an edge to the graph with the
     * distance provided in the file into the graph.
     *
     * @param capDistFile - File used to calculate distances between countries
     */
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

    /**
     * Creates the graph and adds all the nodes
     * @param capDistFile - capdist the file containing the capacity and distance information.
     * @return graph
     */
    Graph createGraph(File capDistFile) {
        Graph graph = new Graph();
        for (Node node : nameMap.values()) {
            graph.addNode(node);
        }
        addEdges(graph, capDistFile);
        return graph;
    }

    /**
     * Finds the shortest path between two countries
     * @param country1 the name of the first country
     * @param country2 the name of the second country
     * @return result - a list of country names representing the shortest path or an empty list if no path exists
     */
    public List<String> findPath (String country1, String country2) {
        Node source = findNodeFromName(country1);
        Node destination = findNodeFromName(country2);
        if (source == null || destination == null) {
            return new ArrayList<>();
        }
        if (source == destination) { // Account for if its equal
            return new ArrayList<>(List.of(source.getCountryName()));
        }
        List<Node> nodePath = graph.findPath(source, destination);
        List<String> result = new ArrayList<>();
        for (Node n : nodePath) {
            result.add(n.getCountryName());
        }
        return result;
    }

    /**
     * Gets a node based on user input for a specific country
     * @param countryNumber - a string indicating the position of the country in the input sequence
     * @return node - the selected node, or null if the user chooses to exit
     */
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

    /**
     * Accepts user input for source and destination countries and prints the shortest path
     */
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
            if (graph.findPath(source, destination).size() > 0) {
                System.out.println("Route from " + source.getCountryName() + " to " + destination.getCountryName() + ":");
                graph.printShortestPath(source, destination);
            } else {
                System.out.println("No route exists from " + source.getCountryName() + " to " + destination.getCountryName());
            }
        }
    }

    /**
     * Main method to execute the road trip simulation program
     * @param args - command line arguments
     */
    public static void main(String[] args) {
        IRoadTrip a3 = new IRoadTrip(args);
        List<String> path = a3.findPath("trinidad", "Trinidad and Tobago");
        a3.acceptUserInput();
    }
}


