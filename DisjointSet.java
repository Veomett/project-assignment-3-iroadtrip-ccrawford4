import java.util.Arrays;
public class DisjointSet {
    public int n;
    private int[] setArr;

    public DisjointSet (int size) {
        n = size;
        int[] tempArr = new int[n];
        for (int i = 0; i < n; i++) {
            tempArr[i] = -1;
        }

        setArr = tempArr;
    }

    public int Find (int k) {
        if (setArr[k] >= 0) {
            setArr[k] = Find(setArr[k]);
            return setArr[k];
        }
        else {
            return k;
        }
    }

    private int getRank (int k) {
        return setArr[Find(k)];
    }

    public void Union (int a, int b) {
        int x = Find(a);
        int y = Find(b);

        if (x == y) {
            return;
        }

        if (getRank(x) > getRank(y)) {
            setArr[x] = y;
        }
        else if (getRank(x) < getRank(y)) {
            setArr[y] = x;
        }
        else {
            setArr[x] = y;
            setArr[Find(y)] = setArr[Find(y)] - 1;
        }
    }

    public void printSets() {
        System.out.println(Arrays.toString(setArr));
    }
}


