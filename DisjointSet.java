import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DisjointSet {
    public int n;
    private int[] setArr;
    public DisjointSet(int numItems) {
       n = numItems;
       setArr = new int[n];
       for (int i = 0; i < n; i++) {
           setArr[i] = -1;
       }
    }

    public int Find(int k) {
        if (setArr[k] >= 0) {
            setArr[k] = Find(setArr[k]);
            return setArr[k];
        }
        else {
            return k;
        }
    }

    private int getRank(int k) {
        return setArr[Find(k)];
    }

    public List<Integer> findAll(int k) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            if (setArr[i] == k) {
                result.add(i);
            }
        }
        return result;
    }

    public void Union(int a, int b) {
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

    public void printSetsVertically() {
        for (int i = 0; i < setArr.length; i++) {
            System.out.println("i: " + i + " arr[i]: " + setArr[i]);
        }
    }

}

