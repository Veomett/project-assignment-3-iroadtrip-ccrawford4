import java.util.*;

public class Graph {
    private Map<Node, List<Edge>> adjacencyList;
    private Map<Node, Node> predecessors;
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

    public void printShortestPath(Map<Node, Integer> shortestDistances, Node source, Node destination) {
        List<Node> path = new ArrayList<>();
        Node current = destination;

        while (current != null && current != source) {
            path.add(current);
            current = predecessors.get(current);
        }
        if (current == null) {
            System.out.println("No path from " + source.getCountryName() + " to " + destination.getCountryName());
        }
        else {
            path.add(source);
            Collections.reverse(path);
            System.out.println(path);
        }
    }

    public Map<Node, Integer> runDijkstra(Node source) {
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
        this.predecessors = predecessors;
        return shortestDistances;
    }

    public Collection<Node> getNodes() {
        return adjacencyList.keySet();
    }

    public List<Edge> getNeighbors(Node node) {
        return adjacencyList.get(node);
    }

}
