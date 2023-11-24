import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.NoSuchFileException;
import java.util.*;
import java.util.stream.Stream;


public class IRoadTrip {
    DisjointSet disjointSet;

    private final String currentDate = "2020-12-31";

    public IRoadTrip (String [] args) {
        if (args.length < 3) {
            System.err.println("ERROR! Not enough command line arguments.");
        }
            File borderFile = new File(args[0]);
            File capDistFile = new File(args[1]);
            File stateNameFile = new File(args[2]);

            int arrSize =  (int) borderFile.length();
            try {
                List<Node> nodes = createNodeList(stateNameFile, arrSize);
                this.disjointSet = createDisjointSet(borderFile, stateNameFile, nodes);
            } catch (Exception e) {
                System.err.println("Error in Main");
            }
    }

    public DisjointSet createDisjointSet(File borderFile, List<Node> nodes) throws Exception{
        DisjointSet result = new DisjointSet(nodes.size());
        try {
            Scanner readBorders = new Scanner(borderFile);
            while (readBorders.hasNextLine()) {
                String borderData = readBorders.nextLine();
                int equalIndex = borderData.indexOf('=');
                String countryName = borderData.substring(0, equalIndex-1);
                String[] segment = borderData.substring(equalIndex + 2).split(";");
                // Finish this
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
            int check = -1;
            Scanner reader = new Scanner(stateNameFile);
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                String[] segment = data.split("\t");
                if (!segment[4].equals(currentDate)) {
                    continue;
                }
                if (check == -1) { // Accounts for first line
                    check++;
                    continue;
                }
                nodes.add(new Node());
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
                nodes.get(nodes.size()-1).setStateNumber(stateNumber);
                nodes.get(nodes.size()-1).setStateId(stateId);
                nodes.get(nodes.size()-1).setCountryName(countryName);
            }
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("Error Parsing File into Node List");
        }
        return nodes;
    }

    public int getDistance (String country1, String country2) {
        // Replace with your code
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



