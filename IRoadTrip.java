import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.NoSuchFileException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Stream;

public class IRoadTrip {
    DisjointSet disjointSet;
    public IRoadTrip (String [] args) {
        if (args.length < 3) {
            System.err.println("ERROR! Not enough command line arguments.");
        }
            File borderFile = new File(args[0]);
            File capDistFile = new File(args[1]);
            File stateNameFile = new File(args[2]);

            disjointSet =
    }

    public DisjointSet createDisjointSet(File borderFile, File stateNameFile) {
        long numCountries = borderFile.length();
        DisjointSet disjointSet = new DisjointSet((int) numCountries);

        try {
            int i = -1;
            Node[] nodeArr = new Node[(int) numCountries];
            Scanner reader = new Scanner(stateNameFile);
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                if (i == -1) { // Accounts for first line
                    i++;
                    continue;
                }
                String[] segment = data.split("\n");
                nodeArr[i] = new Node();
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
                nodeArr[i].setStateNumber(stateNumber);
                nodeArr[i].setStateId(stateId);
                nodeArr[i].setCountryName(countryName);
                i++;
            }

            

        } catch (FileNotFoundException e) {
            System.out.println("ERROR! File Not Found");
        }
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



