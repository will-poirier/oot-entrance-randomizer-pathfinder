import java.util.HashSet;
import java.util.Set;

public class Node {
    private String name;
    private Set<Node> neighbors;

    public Node(String name) {
        this.name = name;
        this.neighbors = new HashSet<Node>();
    }

    public Set<Node> getNeighbors() {
        return neighbors;
    }

    public void connect(Node neighbor) {
        neighbors.add(neighbor);
    }

    public boolean isConnected(Node neighbor) {
        return neighbors.contains(neighbor);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Node ? ((Node)obj).name.equals(this.name) : false;
    }

    @Override
    public String toString() {
        return name;
    }
}