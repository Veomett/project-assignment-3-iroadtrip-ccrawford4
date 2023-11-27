import java.util.*;

public class DirectedGraph {
    private class Edge {
        int source;
        int destination;
        int weight;

        protected Edge(int source, int destination, int weight) {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }
    }

    int vertices;
    LinkedList<Edge>[] adjacencyList;

    DirectedGraph(int vertices) {
        this.vertices = vertices;
        adjacencyList = new LinkedList[vertices];

        for (int i = 0; i < vertices; i++) {
            adjacencyList[i] = new LinkedList<>();
        }
    }

    public void addEdge(int source, int destination, int weight) {
        Edge edge = new Edge(source, destination, weight);
        adjacencyList[source].addFirst(edge);
    }

    public int findShortestPath(int vertexOne, int vertexTwo) {
        // work on this
        return -1;
    }

    public void printGraph() {
        for (int i = 0; i < vertices; i++) {
            LinkedList<Edge> list = adjacencyList[i];
            for (int j = 0; j < list.size(); j++) {
                System.out.println("Vertex " + i + " is connected to " +
                        list.get(j).destination + " with weight " + list.get(j).weight);
            }
        }
    }
}
