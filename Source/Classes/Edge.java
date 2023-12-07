package Source.Classes;
/**
 * @class Edge - used in the graph
 */
public class Edge {
        Node destination;
        int weight;
        Edge(Node destination, int weight) {
            this.destination = destination;
            this.weight = weight;
        }
}
