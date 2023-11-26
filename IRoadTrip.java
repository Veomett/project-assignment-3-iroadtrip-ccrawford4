import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.NoSuchFileException;
import java.util.*;
import java.util.stream.Stream;

public class IRoadTrip {
    DisjointSet disjointSet;
    private final String currentDate = "2020-12-31";
    List<Node> nodes;
    public IRoadTrip (String [] args) {
        if (args.length < 3) {
            System.err.println("ERROR! Not enough command line arguments.");
        }
            File borderFile = new File(args[0]);
            File capDistFile = new File(args[1]);
            File stateNameFile = new File(args[2]);

            int arrSize =  (int) borderFile.length();
            try {
                this.nodes = createNodeList(stateNameFile, arrSize);
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
                    int spaceIndex = country.indexOf(" ");
                    if (spaceIndex == -1) {
                        continue;
                    }
                    String name = country.substring(0, spaceIndex);
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

    List<Node> createNodeList (File stateNameFile, int size) throws Exception {
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
                Node newNode = new Node();
                newNode.setStateNumber(stateNumber);
                newNode.setStateId(stateId);
                newNode.setCountryName(countryName);

                nodes.add(newNode);
            }
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("Error Parsing File into Node List");
        }
        return nodes;
    }

    public int getDistance (String country1, String country2) {
        Node nodeOne = findNodeFromName(country1);
        Node nodeTwo = findNodeFromName(country2);
        if (nodeOne == null) {
            System.out.println("ERROR! " + country1 + " is not found.");
            return -1;
        }
        if (nodeTwo == null) {
            System.out.println("ERROR! " + country2 + " is not found.");
            return -1;
        }
        int parentOne = disjointSet.Find(nodeOne.getStateNumber());
        int parentTwo = disjointSet.Find(nodeTwo.getStateNumber());
        if (parentOne != parentTwo) {
            return -1; // The countries are not connected
        }
        List<Integer> vertices = disjointSet.findAll(parentOne);
        DirectedGraph graph = new DirectedGraph(vertices.size());
        Map<Integer, Node> map = new HashMap<>();

        for (int i = 0; i < vertices.size(); i++) {
            map.put(i, findNodeFromNumber(vertices.get(i)));
            // Stuck here
        }
        return -1;
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

        a3.acceptUserInput();
    }
}



