import java.util.ArrayList;
import java.util.Arrays;
import static java.lang.Math.max;

public class Controller {
    public static void designNewCarpet(Carpet newCarpet, int numOfPatterns) {
        int[] result = new int[numOfPatterns];

        result[0] = 0;

        boolean[] availableForPaint = new boolean[numOfPatterns];
        Arrays.fill(availableForPaint, true);

        // Assign colors to remaining numOfPatterns-1 vertices
        for (int i = 1; i < numOfPatterns; i++) {
            for (var v : newCarpet.getGraph()[i]) {
                if (result[v.value] != -1)
                    availableForPaint[result[v.value]] = false;
            }

            // Find the first availableForPaint color
            int color;
            for (color = 0; color < numOfPatterns; color++) {
                if (availableForPaint[color])
                    break;
            }

            result[i] = color; // Assign the found color

            // Reset the values
            Arrays.fill(availableForPaint, true);
        }

        for (int i = 0; i < result.length; i++) {
            newCarpet.getListOfVertices().get(i).color = result[i];
        }

        printNewDesignedCarpet(result, newCarpet.getPrice());
        Main.carpets.add(newCarpet);
    }

    private static void printNewDesignedCarpet(int[] result, double price) {
        System.out.println("Your New Designed Carpet:");
        System.out.println("Price=> " + price + "$");
        for (int i = 0; i < result.length; i++)
            System.out.println("Pattern " + i + " --->  Color "
                    + result[i]);
    }

    public static ArrayList<Carpet> buyCarpet(double budget) {
        double[] prices = new double[Main.carpets.size()];
        for (int i = 0; i < prices.length; i++) {
            prices[i] =
                    Main.carpets.get(i).getPrice();
        }
        ArrayList<Carpet> resultOfCarpets = new ArrayList<>();
        maxNumOfCarpet(budget, prices, Main.carpets.size(), resultOfCarpets);
        return resultOfCarpets;
    }

    private static int maxNumOfCarpet(double budget, double[] prices, int numOfCarpets, ArrayList<Carpet> result) {
        if (numOfCarpets == 0 || budget == 0)
            return 0;

        if (prices[numOfCarpets - 1] > budget)
            return maxNumOfCarpet(budget, prices, numOfCarpets - 1, result);

        int includeItem = maxNumOfCarpet(budget - prices[numOfCarpets - 1], prices, numOfCarpets - 1, result) + 1;
        int excludeItem = maxNumOfCarpet(budget, prices, numOfCarpets - 1, result);

        if (includeItem > excludeItem) {
            result.add(Main.carpets.get(numOfCarpets - 1));
            return includeItem;
        } else {
            return excludeItem;
        }
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

        printShortestPath(minDistance_index, minDistance, parents);
    }

    private static void printShortestPath(int nearestIndex, int minDistance, int[] parents) {
        System.out.println("The Nearest Store=> " + nearestIndex
                + "* With " + minDistance + " Distance");
        System.out.print("Path=> ");
        printPath(nearestIndex, parents);
    }

    private static void printPath(int currentVertex, int[] parents) {
        if (currentVertex == -1) return;
        printPath(parents[currentVertex], parents);
        System.out.print(currentVertex + "->");
    }
}
