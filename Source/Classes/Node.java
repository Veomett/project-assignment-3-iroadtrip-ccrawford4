package Source.Classes;

import java.util.HashSet;
import java.util.Set;

/**
 * Represents a node in a graph, corresponding to a state in a country
 */
public class Node {
    private final int stateNumber;
    private final String stateId;
    private final String countryName;

    private Set<Node> neighbors;

    /**
     * Constructs a new Node with the specified state information
     *
     * @param stateNumber - the numerical identifier of the state
     * @param stateId - the unique identifier of the state
     * @param countryName - the name of the country to which the state belongs
     */
    Node(int stateNumber, String stateId, String countryName) {
        this.stateNumber = stateNumber;
        this.stateId = stateId;
        this.countryName = countryName;
        this.neighbors = new HashSet<>();
    }

    /**
     * Get the unique identifier of the state
     * @return the state ID
     */
    public int getStateNumber() {
        return stateNumber;
    }

    /**
     * Gets the unique identifier of the state
     * @return the state ID
     */
    public String getStateId() {
        return stateId;
    }

    /**
     * Add a neighboring node to the current node
     * @param node - the node to be added as a neighbor
     */
    public void addNeighbor(Node node) {
        neighbors.add(node);
    }

    /**
     * Gets the set of the neighboring nodes
     * @return the set of the neighboring nodes
     */
    public Set<Node> getNeighbors() {
        return neighbors;
    }

    /**
     * Gets the name of the country to which the state belongs
     * @return the country name
     */
    public String getCountryName() {
        return countryName;
    }
}