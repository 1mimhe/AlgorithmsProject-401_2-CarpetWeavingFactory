import java.util.ArrayList;
import java.util.Set;

public class Panel {
    public static void designPanel() {
        System.out.println("\n*Design Panel*");

        System.out.print("Enter the Price of Your Carpet=> ");
        double price = Main.input.nextDouble();

        System.out.print("Number of Carpet's Patterns=> ");
        int numOfPatterns = Main.input.nextInt();
        Main.input.nextLine();

        System.out.println("Enter Relations Between Patterns.");
        System.out.println("[Patterns=>0...n-1] [Relations=> Ex: 0 5, And 0 For Exit]");

        Carpet newCarpet = new Carpet(numOfPatterns, price);

        int i = 1;
        while (true){
            System.out.print("Relation " + i++ + "=> ");
            String[] k = Main.input.nextLine().split(" ");
            if (k.length == 1) break;
            newCarpet.addEdge(Integer.parseInt(k[0]), Integer.parseInt(k[1]));
        }

        System.out.println("\nDesigning...");
        Controller.designNewCarpet(newCarpet, numOfPatterns);
        System.out.print("\nEnter 0 for Exit=> ");
        Main.input.nextLine();
    }

    public static void listOfCarpets() {
        System.out.println("\n*List of Carpets*");
        int i = 1;
        for (Carpet carpet : Main.carpets) {
            System.out.println("Carpet #" + i++);
            System.out.println("Price=> " + carpet.getPrice() + "$");

            int j = 1;
            for (Vertice pattern : carpet.getListOfVertices()) {
                    System.out.println("Pattern " + j++ + " --->  Color "
                            + pattern.color);
            }
        }

        System.out.print("\nEnter 0 for Exit=> ");
        Main.input.nextLine();
    }

    public static void buyCarpet() {
        System.out.println("\n*Buy Carpet*");
        System.out.print("Enter Your Budget=> ");
        double budget = Main.input.nextInt();
        Main.input.nextLine();

        System.out.println("\nProcessing...");
        Set<Carpet> result = Controller.buyCarpet(budget);
        printBuyResult(result, budget);

        System.out.print("\nEnter 0 for Exit=> ");
        Main.input.nextLine();
    }

    private static void printBuyResult(Set<Carpet> result, double budget) {
        System.out.println("*Receipt*\n");
        double totalPrice = 0;
        int i = 1;
        for (Carpet carpet : result) {
            System.out.println("#Carpet" + i++ + ":");
            printCarpet(carpet.getListOfVertices(), carpet.getPrice());
            totalPrice += carpet.getPrice();
        }

        System.out.println("\nNumber of Bought Carpets=> " + result.size());
        System.out.println("\nTotal Price=> " + totalPrice + "$");
        System.out.println("Remaining Budget=> " + (budget - totalPrice) + "$");
    }

    public static void shortestPath() {
        System.out.println("\n*Nearest Store*");
        System.out.print("Enter Number of Stores=> ");
        int numOfStores = Main.input.nextInt();
        Main.input.nextLine();

        System.out.println("Enter Adjacency Matrix(Graph) of Stores:");
        int[][] graph = new int[numOfStores][numOfStores];
        for (int i = 0; i < numOfStores; i++) {
            for (int j = 0; j < numOfStores; j++) {
                graph[i][j] = Main.input.nextInt();
            }
        }

        System.out.print("Enter Source=> ");
        int source = Main.input.nextInt();
        Main.input.nextLine();

        System.out.println("\nProcessing...");
        Controller.shortestPath(graph, source);
        System.out.print("\nEnter 0 for Exit=> ");
        Main.input.nextLine();
    }

    public static void printShortestPath(int nearestIndex, int minDistance, int[] parents) {
        System.out.println("The Nearest Store=> " + nearestIndex
                + " [With " + minDistance + " Distance]");
        System.out.print("Path=> ");
        printPath(nearestIndex, parents);
        System.out.println("Done");
    }

    private static void printPath(int currentVertex, int[] parents) {
        if (currentVertex == -1) return;
        printPath(parents[currentVertex], parents);
        System.out.print(currentVertex + "->");
    }

    public static void printCarpet(ArrayList<Vertice> result, double price) {
        System.out.println("Price=> " + price + "$");
        for (int i = 0; i < result.size(); i++)
            System.out.println("Pattern " + i + " --->  Color "
                    + result.get(i).color);
    }
}
