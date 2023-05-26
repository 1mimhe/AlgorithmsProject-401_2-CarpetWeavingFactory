import java.util.ArrayList;
import java.util.LinkedList;

public class Carpet {
    private LinkedList<Vertice> graph[];    //Graph

    private ArrayList<Vertice> listOfVertices;

    private double price;

    public Carpet(int numOfVertices, double price) {
        graph = new LinkedList[numOfVertices];
        for (int i = 0; i < numOfVertices; ++i)
            graph[i] = new LinkedList<>();
        listOfVertices = new ArrayList<>();
        this.price = price;
    }

    public void addEdge(int value1, int value2) {
        Vertice v1 = returnVertice(value1);
        Vertice v2 = returnVertice(value2);
        graph[v1.value].add(v2);
        graph[v2.value].add(v1);
    }

    private Vertice returnVertice(int value) {
        for (Vertice v : listOfVertices) {
            if (v.value == value) return v;
        }
        Vertice newVertice = new Vertice(value);
        listOfVertices.add(newVertice);
        return newVertice;
    }

    public LinkedList<Vertice>[] getGraph() {
        return graph;
    }

    public ArrayList<Vertice> getListOfVertices() {
        return listOfVertices;
    }

    public double getPrice() {
        return price;
    }
}
