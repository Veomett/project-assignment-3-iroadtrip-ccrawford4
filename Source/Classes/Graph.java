package Source.Classes;
import java.util.*;

public class Graph {
    private final Map<Node, List<Edge>> adjacencyList;
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

    public boolean isIsland(Node node) {
        return node.getNeighbors().size() == 0;
    }
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
        path.add(source);
        Collections.reverse(path);
        return path;
    }

    public int getDistance(Node source, Node destination) {
        List<Edge> edges = adjacencyList.get(source);
        for (Edge edge : edges) {
            if (edge.destination == destination) {
                return edge.weight;
            }
        }
        return -1;
    }

    public void printShortestPath(Node source, Node destination) {
        List<Node> path = findPath(source, destination);
        for (int i = 0; i < path.size() - 1; i++) {
            String countryOne = path.get(i).getCountryName();
            String countryTwo = path.get(i + 1).getCountryName();
            int weight = findEdgeWeight(path.get(i), path.get(i+1));
            System.out.println("* " + countryOne + " --> " + countryTwo + " (" + weight + " km.)");
        }
    }

    int findEdgeWeight(Node source, Node destination) {
        for (Edge edge : adjacencyList.get(source)) {
            if (edge.destination == destination) {
                return edge.weight;
            }
        }
        return -1;
    }

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

    public Collection<Node> getNodes() {
        return adjacencyList.keySet();
    }

    public List<Edge> getNeighbors(Node node) {
        return adjacencyList.get(node);
    }

}
