import java.util.*;

public class DirectedGraph {
    private Map<Node, List<Edge>> adjacencyList;

    public DirectedGraph() {
        this.adjacencyList = new HashMap<>();
    }

    public void addNode(Node node) {
        adjacencyList.putIfAbsent(node, new ArrayList<>());
    }

    public void addEdge(Node source, Node destination, int weight) {
        adjacencyList.get(source).add(new Edge(destination, weight));
    }

    public Map<Node, Integer> dijkstra(Node startNode) {
        Map<Node, Integer> distances = new HashMap<>();
        PriorityQueue<NodeDistancePair> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(NodeDistancePair::getDistance));

        for (Node node : adjacencyList.keySet()) {
            distances.put(node, Integer.MAX_VALUE);
        }

        distances.put(startNode, 0);
        priorityQueue.add(new NodeDistancePair(startNode, 0));

        while (!priorityQueue.isEmpty()) {
            Node currentNode = priorityQueue.poll().getNode();

            for (Edge neighbor : adjacencyList.get(currentNode)) {
                int newDistance = distances.get(currentNode) + neighbor.getWeight();

                if (newDistance < distances.get(neighbor.getDestination())) {
                    distances.put(neighbor.getDestination(), newDistance);
                    priorityQueue.add(new NodeDistancePair(neighbor.getDestination(), newDistance));
                }
            }
        }

        return distances;
    }

    private static class Edge {
        private final Node destination;
        private final int weight;

        public Edge(Node destination, int weight) {
            this.destination = destination;
            this.weight = weight;
        }

        public Node getDestination() {
            return destination;
        }

        public int getWeight() {
            return weight;
        }
    }

    private static class NodeDistancePair {
        private final Node node;
        private final int distance;

        public NodeDistancePair(Node node, int distance) {
            this.node = node;
            this.distance = distance;
        }

        public Node getNode() {
            return node;
        }

        public int getDistance() {
            return distance;
        }
    }

    public static void main(String[] args) {
        DirectedGraph graph = new DirectedGraph();

        Node nodeA = new Node(1, "A", "Country1");
        Node nodeB = new Node(2, "B", "Country2");
        Node nodeC = new Node(3, "C", "Country3");

        graph.addNode(nodeA);
        graph.addNode(nodeB);
        graph.addNode(nodeC);

        graph.addEdge(nodeA, nodeB, 1);
        graph.addEdge(nodeB, nodeC, 2);
        graph.addEdge(nodeA, nodeC, 5);

        Map<Node, Integer> distances = graph.dijkstra(nodeA);

        for (Node node : distances.keySet()) {
            System.out.println("Distance from " + node.getStateId() + " to " + nodeA.getStateId() + ": " + distances.get(node));
        }
    }
}
