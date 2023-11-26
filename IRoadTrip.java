import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.NoSuchFileException;
import java.util.*;
import java.util.stream.Stream;

public class IRoadTrip {
    DisjointSet disjointSet;
    private final String currentDate = "2020-12-31";
    Map<String, Integer> nameToNumber;
    Map<String, Integer> vertexToCountry;

    File distanceFile;

    public IRoadTrip (String [] args) {
        if (args.length < 3) {
            System.err.println("ERROR! Not enough command line arguments.");
        }
            File borderFile = new File(args[0]);
            this.distanceFile = new File(args[1]);
            File stateNameFile = new File(args[2]);

            try {
                this.nameToNumber = createMap(stateNameFile);
                this.vertexToCountry = createVertexMap(nameToNumber);
                this.disjointSet = createDisjointSet(borderFile);
            } catch (Exception e) {
                System.err.println(e.toString());
            }
    }

    public Map<String, Integer> createVertexMap(Map<String, Integer> map) {
        Map<String, Integer> result = new HashMap<>();
        int i = 0;
        for (String key : map.keySet()) {
            result.put(key, i);
            i++;
        }
        return result;
    }
    public Map<String, Integer> createMap(File stateNameFile) throws FileNotFoundException {
        Map<String, Integer> stateInfoMap = new HashMap<>();
        try {
            Scanner reader = new Scanner(stateNameFile);
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                String[] segment = data.split("\t");
                if (!segment[segment.length-1].equals(currentDate)) {
                    continue;
                }
                int stateNumber = Integer.parseInt(segment[0]);
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
                stateInfoMap.put(countryName, stateNumber);
            }
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("Error Parsing File into Node List");
        }
        return stateInfoMap;
    }

    public DisjointSet createDisjointSet(File borderFile) throws Exception {
        DisjointSet result = new DisjointSet(vertexToCountry.size() + 1);
        try {
            Scanner readBorders = new Scanner(borderFile);
            while (readBorders.hasNextLine()) {
                String borderData = readBorders.nextLine();
                int equalIndex = borderData.indexOf('=');
                String parentName = borderData.substring(0, equalIndex - 1);
                int parentValue = -1;
                for (String key : vertexToCountry.keySet()) {
                    if (key.contains(parentName)) {
                        parentValue = vertexToCountry.get(key);
                        break;
                    }
                }
                if (parentValue == -1) {
                    continue;
                }
                String[] segment = borderData.substring(equalIndex + 2).split(";");
                for (String child : segment) {
                    int spaceIndex = child.indexOf(" ");
                    if (spaceIndex == -1) {
                        continue;
                    }
                    String childName = child.substring(0, spaceIndex);
                    Integer childValue = -1;
                    for (String key : vertexToCountry.keySet()) {
                        if (key.contains((childName))) {
                            childValue = vertexToCountry.get(key);
                            break;
                        }
                    }
                    if (childValue == -1) {
                        continue;
                    }
                    result.Union(parentValue, childValue);
                }
            }
        }
        catch (FileNotFoundException e) {
            throw new FileNotFoundException("Error Parsing File into Disjoint Set");
        }
        return result;
    }

    public int getDistance (String country1, String country2) {
        if ()
    }

   public DirectedGraph createGraph(List<Integer> vertices) {

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



