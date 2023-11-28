import java.util.*;

public class Graph {
    private Map<Node, List<Edge>> adjacencyList;
    Graph() {
        this.adjacencyList = new HashMap<>();
    }
    public void addNode(Node node) {
        adjacencyList.put(node, new ArrayList<>());
    }

    public void addEdge(Node source, Node destination, int weight) {
        Edge edge = new Edge(destination, weight);
        adjacencyList.get(source).add(edge);

        edge = new Edge(source, weight);
        adjacencyList.get(destination).add(edge);
    }

    public Collection<Node> getNodes() {
        return adjacencyList.keySet();
    }

    public List<Edge> getNeighbors(Node node) {
        return adjacencyList.get(node);
    }

}
