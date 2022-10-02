package pathfinder;
import java.util.HashMap;
import java.util.Map;

import pathfinder.Edge.EdgeType;

public class Vertex {
    private String name;
    private Map<Vertex, Edge> neighbors;

    public Vertex(String name) {
        this.name = name;
        this.neighbors = new HashMap<Vertex, Edge>();
    }

    public String getName() {
        return name;
    }

    public Map<Vertex, Edge> getNeighbors() {
        return neighbors;
    }

    public void connect(Vertex other, Edge edge) {
        neighbors.put(other, edge);
    }

    public void disconnect(Vertex other) {
        if (neighbors.containsKey(other)) {
            neighbors.remove(other);
        }
    }

    public EdgeType connection(Vertex other, State state) {
        if (neighbors.containsKey(other)) {
            return neighbors.get(other).getType(state);
        } else {
            return null;
        }
    }

    public boolean isConnected(Vertex other, State state) {
        return neighbors.containsKey(other);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Vertex ? ((Vertex)obj).name.equals(this.name) : false;
    }

    @Override
    public String toString() {
        String result = name;
        result += ":{";
        for (Vertex key : neighbors.keySet()) {
            result += "(" + key.name + ", " + neighbors.get(key).toString() + "), ";
        }
        result += "\b\b}";
        return result;
    }
}
