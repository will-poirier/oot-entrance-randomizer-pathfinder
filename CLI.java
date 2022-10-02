import java.util.List;
import java.util.Scanner;

public class CLI {
    private static void printPath(List<String> path) {
        try {
            for (String val : path) {
                System.out.println(val + " -> ");
            }
        } catch (NullPointerException npe) {
            System.out.println("No path exists");
        }
    } 

    public static void main(String[] args) {
        Graph graph = new Graph();
        Scanner scanner = new Scanner(System.in);
        String line = "";
        boolean cont = true;
        while (cont) {
            System.out.print(">>>");
            line = scanner.nextLine();
            String[] tokens = line.split(" ");
            switch (tokens[0]) {
                case "add":
                    graph.add(tokens[1]);
                    System.out.println(tokens[1] + " added");
                    break;
                case "contains":
                    if (graph.contains(tokens[1])) {
                        System.out.println(tokens[1] + " is in the graph");
                    } else {
                        System.out.println(tokens[1] + " is not in the graph");
                    }
                    break;
                case "connect":
                    graph.connect(tokens[1], tokens[2]);
                    System.out.println(tokens[1] + " and " + tokens[2] + " connected");
                    break;
                case "oneway":
                    graph.oneWayConnect(tokens[1], tokens[2]);
                    System.out.println(tokens[1] + " and " + tokens[2] + " connected");
                    break;
                case "path":
                    printPath(graph.bfPath(tokens[1], tokens[2]));
                    break;
                case "print":
                    System.out.println(graph.toString());
                    break;
                case "quit":
                    cont = false;
                    System.out.println("Goodbye");
                    break;
                case "help":
                    System.out.println("add <x>: add a new node to the graph");
                    System.out.println("contains <x>: see if the graph contains a certain node");
                    System.out.println("connect <x> <y>: connect two nodes together");
                    System.out.println("oneway <x> <y>: connect two nodes together, but only in one direction (x->y)");
                    System.out.println("path <x> <y>: find and print the path to get from one node to another");
                    System.out.println("print: print the nodes in the graph (but not their connections; that shit complicated)");
                    System.out.println("help: display this message");
                    break;
                default:
                    System.out.println("Invalid command: try 'help' for a list of commands");
                    break;
            }
        }
        scanner.close();
    }
}
