public class Panel {
    public static void designPanel() {
        System.out.println("*Design Panel*");

        System.out.print("Enter the Price of Your Carpet=> ");
        double price = Main.input.nextDouble();

        System.out.print("Number of Carpet's Patterns=>");
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

        Controller.designNewCarpet(newCarpet, numOfPatterns);
    }

    public static void listOfCarpets() {
        System.out.println("*List of Carpets*");
        int i = 1;
        for (Carpet carpet : Main.carpets) {
            System.out.println("Carpet #" + i++);
            System.out.println("Price=> " + carpet.getPrice() + "$");

            int j = 1;
            for (Vertice pattern : carpet.getListOfVertices()) {
                    System.out.println("Pattern " + j++ + " --->  Color "
                            + pattern);
            }
        }
        System.out.print("Enter 0 for Exit=> ");
        Main.input.nextLine();
    }
}
