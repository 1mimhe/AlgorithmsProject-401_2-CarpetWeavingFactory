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
}
