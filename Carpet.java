import java.util.LinkedList;

public class Carpet {
    private int n;
    private LinkedList<Integer> g[];

    public Carpet(int n) {
        this.n = n;
        g = new LinkedList[n];
        for (int i = 0; i < n; ++i)
            g[i] = new LinkedList();
    }

    void addEdge(int v1, int v2) {
        g[v1].add(v2);
        g[v2].add(v1);
    }
}
