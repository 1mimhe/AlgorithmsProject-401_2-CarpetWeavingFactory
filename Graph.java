import java.util.Arrays;

public class Graph {
    public static void designNewCarpet(Carpet newCarpet, int n) {
        int[] result = new int[n];

        result[0]  = 0;

        boolean[] availableForPaint = new boolean[n];
        Arrays.fill(availableForPaint, true);

        // Assign colors to remaining n-1 vertices
        for (int i = 1; i < n; i++)
        {
            for (var list : newCarpet.getGraph()[i]) {
                if (result[list.value] != -1)
                    availableForPaint[result[list.value]] = false;
            }

            // Find the first availableForPaint color
            int cr;
            for (cr = 0; cr < n; cr++){
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

    public static void shortestPath(int[][] graph, int source) {
        int[] distance = new int[graph.length];

        Boolean[] sptSet = new Boolean[graph.length];

        for (int i = 0; i < graph.length; i++) {
            distance[i] = Integer.MAX_VALUE; // Infinite
            sptSet[i] = false;
        }

        // Distance of source vertex from itself is always 0
        distance[source] = 0;

        // Find the shortest path for all vertices
        for (int count = 0; count < graph.length - 1; count++) {
            int min = minDistance(distance, sptSet);

            sptSet[min] = true;

            for (int i = 0; i < graph.length; i++) {
                if (!sptSet[i] && graph[min][i] != 0
                        && distance[min] != Integer.MAX_VALUE
                        && distance[min] + graph[min][i] < distance[i])
                    distance[i] = distance[min] + graph[min][i];
            }
        }

//        printDistance(distance);
    }
    private static int minDistance(int[] distance, Boolean[] sptSet)
    {
        int min = Integer.MAX_VALUE, min_index = -1;

        for (int i = 0; i < distance.length; i++) {
            if (!sptSet[i] && distance[i] <= min) {
                min = distance[i];
                min_index = i;
            }
        }

        return min_index;
    }

//    private static void printDistance(int[] distance)
//    {
//        System.out.println(
//                "Vertex \t\t Distance from Source");
//        for (int i = 0; i < V; i++)
//            System.out.println(i + " \t\t " + distance[i]);
//    }
}
