import java.util.Arrays;
public class DisjointSet {
    public int n;
    private Node[] setArr;
    public DisjointSet(int numItems) {
       n = numItems;
       setArr = new Node[n];
       for (int i = 0; i < n; i++) {
           setArr[i] = new Node();
       }
    }

    public Node Find(Node n) {
        if (setArr[n.getStateNumber()].getStateNumber() >= 0) {
            setArr[n.getStateNumber()] = Find(setArr[n.getStateNumber()]);
            return setArr[n.getStateNumber()];
        }
        else {
            return n;
        }
    }

    private int getRank(Node n) {
        return setArr[n.getStateNumber()].getStateNumber();
    }

    public void Union(Node a, Node b) {
        Node x = Find(a);
        Node y = Find(b);

        if (x.getStateNumber() == y.getStateNumber()) {
            return;
        }

        if (getRank(x) > getRank(y)) {
            setArr[x.getStateNumber()] = y;
        }
        else if (getRank(x) < getRank(y)) {
            setArr[y.getStateNumber()] = x;
        }
        else {
            setArr[x.getStateNumber()] = y;
            setArr[y.getStateNumber()].setStateNumber(Find(y).getStateNumber() - 1);
        }
    }

    public void printSets() {
        System.out.println(Arrays.toString(setArr));
    }

}

