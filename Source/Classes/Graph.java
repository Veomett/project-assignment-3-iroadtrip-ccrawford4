package Source.Classes;
import java.util.*;
/**
 * @class Graph - custom Graph undirected weighted graph
 */
public class Graph {
    private final Map<Node, List<Edge>> adjacencyList;

    /**
     * Graph constructor
     */
    Graph() {
        this.adjacencyList = new HashMap<>();
    }

    /**
     * Adds a node and an empty list to the adjacency list hashmap
     * @param node - custom Node object used as vertexes in the graph
     */
    public void addNode(Node node) {
        adjacencyList.put(node, new LinkedList<>());
    }

    /**
     * Adds a new edge to the graph
     * @param source - Source Node
     * @param destination - Destination node
     * @param weight - Weight (distance between the two nodes)
     */
    public void addEdge(Node source, Node destination, int weight) {
        Edge edge = new Edge(destination, weight);
        adjacencyList.get(source).add(edge);

        edge = new Edge(source, weight);
        adjacencyList.get(destination).add(edge);
    }

    /**
     * Method used to determine if the node is an island or has no neighboring nodes in the graph
     * @param node - The node being checked
     * @return - True if the node is an island and false if not
     */
    public boolean isIsland(Node node) {
        return node.getNeighbors().size() == 0;
    }

    /**
     * Returns a list of nodes representing the path from a source node to a destination node
     * @param source - Source Node
     * @param destination - Destination Node
     * @return - Returns an ArrayList of nodes representing the path
     */
    List<Node> findPath(Node source, Node destination) {
        Map<Node, Node> predecessors = runDijkstra(source);
        List<Node> path = new ArrayList<>();
        if (isIsland(source) || isIsland(destination)) {
            return path;
        }
        Node current = destination;
        while (current != null && current != source) {
            path.add(current);
            current = predecessors.get(current);
        }
        if (current != source) {
            return new ArrayList<>();
        }
        path.add(source);
        Collections.reverse(path);
        return path;
    }

    /**
     * Gets the distance between two nodes if there is an edge and returns -1 if no edge exists
     * @param source - The source node
     * @param destination - The destination node
     * @return - Reruns the edge weight between two nodes if there is one
     */
    public int getDistance(Node source, Node destination) {
        List<Edge> edges = adjacencyList.get(source);
        for (Edge edge : edges) {
            if (edge.destination == destination) {
                return edge.weight;
            }
        }
        return -1;
    }

    /**
     * Prints the shortest path using the country names between two nodes
     * @param source - Source node
     * @param destination - Destination node
     */
    public void printShortestPath(Node source, Node destination) {
        List<Node> path = findPath(source, destination);
        if (path.size() <= 1) {
            System.out.println("* " + source.getCountryName() + " --> " + source.getCountryName() + " (0 km.)");
        }
        for (int i = 0; i < path.size() - 1; i++) {
            String countryOne = path.get(i).getCountryName();
            String countryTwo = path.get(i + 1).getCountryName();
            int weight = findEdgeWeight(path.get(i), path.get(i+1));
            System.out.println("* " + countryOne + " --> " + countryTwo + " (" + weight + " km.)");
        }
    }

    /**
     * Finds the edge weight between a source and destination node if one exists
     * @param source - Source node
     * @param destination - Destination node
     * @return - Returns the edge weight between the two nodes
     */
    int findEdgeWeight(Node source, Node destination) {
        for (Edge edge : adjacencyList.get(source)) {
            if (edge.destination == destination) {
                return edge.weight;
            }
        }
        return -1;
    }

    /**
     * Runs Dijkstra's Algorithm and returns a hashmap with the predecessors of the path traversed
     * @param source - Source Node
     * @return - Predecessors map used for findPath() and printPath() methods
     */
    public Map<Node, Node> runDijkstra(Node source) {
        Map<Node, Integer> shortestDistances = new HashMap<>();
        Map<Node, Node> predecessors = new HashMap<>();
        PriorityQueue<Edge> minHeap = new PriorityQueue<>(Comparator.comparingInt(edge -> edge.weight));
        for (Node node : this.getNodes()) {
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

            for (Edge edge : this.getNeighbors(currentNode)) {
                int newDistance = currentDistance + edge.weight;
                if (newDistance < shortestDistances.get(edge.destination)) {
                    minHeap.removeIf(e -> e.destination.equals(edge.destination));
                    minHeap.offer(new Edge(edge.destination, newDistance));
                    shortestDistances.put(edge.destination, newDistance);
                    predecessors.put(edge.destination, currentNode);
                }
            }
        }
        return predecessors;
    }

    /**
     * Gets all the nodes from the graph
     * @return - The key set from the adjacency list
     */
    public Collection<Node> getNodes() {
        return adjacencyList.keySet();
    }

    /**
     * Gets the neighbor nodes (ie all the ones connected to this node) given an input node
     * @param node - Node being evaluated
     * @return - The neighboring nodes
     */
    public List<Edge> getNeighbors(Node node) {
        return adjacencyList.get(node);
    }

}
