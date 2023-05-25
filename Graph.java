import java.util.Arrays;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class Graph {
    public static void designNewCarpet(Carpet newCarpet, int n) {
        int[] result = new int[n];

        result[0] = 0;

        boolean[] availableForPaint = new boolean[n];
        Arrays.fill(availableForPaint, true);

        // Assign colors to remaining n-1 vertices
        for (int i = 1; i < n; i++) {
            for (var list : newCarpet.getGraph()[i]) {
                if (result[list.value] != -1)
                    availableForPaint[result[list.value]] = false;
            }

            // Find the first availableForPaint color
            int cr;
            for (cr = 0; cr < n; cr++) {
                if (availableForPaint[cr])
                    break;
            }

            result[i] = cr; // Assign the found color

            // Reset the values
            Arrays.fill(availableForPaint, true);
        }

        for (int i = 0; i < result.length; i++) {
            newCarpet.getListOfVertices().get(i).color = result[i];
        }

        System.out.println("Your New Designed Carpet:");
        for (int i = 0; i < n; i++)
            System.out.println("Pattern " + i + " --->  Color "
                    + result[i]);
        Main.carpets.add(newCarpet);
    }

    public static int buyCarpet(int W, int[] weight, int[] value, int n) {


        if (n == 0 || W == 0)
            return 0;


        if (weight[n - 1] > W)
            return buyCarpet(W, weight, value, n - 1);


        else return max(value[n - 1] + buyCarpet(W - weight[n - 1], weight, value, n - 1),
                buyCarpet(W, weight, value, n - 1));
    }

    public static void shortestPath(int[][] graph, int source) {
        int[] minDistances = new int[graph.length];

        boolean[] sptSet = new boolean[graph.length];

        for (int i = 0; i < graph.length; i++) {
            minDistances[i] = Integer.MAX_VALUE; //Infinite
            sptSet[i] = false;
        }

        // Distance of source vertex from itself is always 0
        minDistances[source] = 0;

        int[] parents = new int[graph.length];

        // The starting vertex does not have a parent
        parents[source] = -1;

        for (int i = 1; i < graph.length; i++) {
            int nearestVertex = -1;
            int shortestDistance = Integer.MAX_VALUE;

            for (int j = 0; j < graph.length; j++) {
                if (!sptSet[j] &&
                        (minDistances[j] < shortestDistance)) {
                    nearestVertex = j;
                    shortestDistance = minDistances[j];
                }
            }

            sptSet[nearestVertex] = true;

            for (int j = 0; j < graph.length; j++) {
                int edgeDistance = graph[nearestVertex][j];

                if (edgeDistance > 0 &&
                        ((shortestDistance + edgeDistance) < minDistances[j])) {
                    parents[j] = nearestVertex;
                    minDistances[j] = shortestDistance + edgeDistance;
                }
            }
        }

        // Find minDistance with index
        int minDistance = -1, minDistance_index = -1;
        for (int i = 1; i < minDistances.length; i++) {
            if (minDistances[i] < minDistance) {
                minDistance_index = i;
                minDistance = minDistances[i];
            }
        }

        printResult(minDistance_index, minDistance, parents);
    }

    private static void printResult(int nearestIndex, int minDistance, int[] parents) {
        System.out.println("The Nearest Store=> " + nearestIndex
                + "* With " + minDistance + " Distance");
        System.out.print("Path=> ");
        printPath(nearestIndex, parents);
        System.out.println();
    }

    private static void printPath(int currentVertex, int[] parents) {
        if (currentVertex == -1) return;
        printPath(parents[currentVertex], parents);
        System.out.print(currentVertex + "->");
    }
}
