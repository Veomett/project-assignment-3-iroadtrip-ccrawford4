package Source.Classes;

import java.util.HashSet;
import java.util.Set;

public class Node {
    private final int stateNumber;
    private final String stateId;
    private final String countryName;

    private Set<Node> neighbors;
    Node(int stateNumber, String stateId, String countryName) {
        this.stateNumber = stateNumber;
        this.stateId = stateId;
        this.countryName = countryName;
        this.neighbors = new HashSet<>();
    }
    public int getStateNumber() {
        return stateNumber;
    }

    public String getStateId() {
        return stateId;
    }

    public void addNeighbor(Node node) {
        neighbors.add(node);
    }
    public Set<Node> getNeighbors() {
        return neighbors;
    }
    public String getCountryName() {
        return countryName;
    }
}