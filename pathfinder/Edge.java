package pathfinder;
import java.util.Map;

public class Edge {
    private String name;
    private Map<State, Vertex> to;
    private Map<State, Vertex> from;
    private Map<State, EdgeType> typeMap;

    public enum EdgeType {
        TWO_WAY,
        ONE_WAY,
        NO_WAY
    }

    public Edge(String name, Map<State, Vertex> to, Map<State, Vertex> from, Map<State, EdgeType> map) {
        this.name = name;
        this.to = to;
        this.from = from;
        this.typeMap = map;
        updateVertices();
    }

    private void updateVertices() {
        for (State state : typeMap.keySet()) {
            Vertex toVert = to.get(state);
            Vertex fromVert = from.get(state);
            switch (typeMap.get(state)) {
                case TWO_WAY:
                    toVert.connect(fromVert, this);
                    fromVert.connect(toVert, this);
                    break;
                case ONE_WAY:
                    fromVert.connect(toVert, this);
                    toVert.disconnect(fromVert);
                    break;
                case NO_WAY:
                    fromVert.disconnect(toVert);
                    toVert.disconnect(fromVert);
            }
        }
    }

    private void clearVertexConnections(State state) {
        to.get(state).disconnect(from.get(state));
        from.get(state).disconnect(to.get(state));
    }

    public Vertex getTo(State state) {
        return to.get(state);
    }

    public Vertex getFrom(State state) {
        return from.get(state);
    }

    public EdgeType getType(State state) {
        return typeMap.get(state);
    }

    public void setTo(State state, Vertex toVert) {
        clearVertexConnections(state);
        to.put(state, toVert);
        updateVertices();
    }

    public void setFrom(State state, Vertex fromVert) {
        clearVertexConnections(state);
        from.put(state, fromVert);
        updateVertices();
    }

    public void setEdgeType(State state, EdgeType edgeType) {
        typeMap.put(state, edgeType);
        updateVertices();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Edge) {
            Edge o = (Edge)obj;
            return o.to.equals(to) && o.from.equals(from) && o.typeMap.equals(typeMap);
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        String result = name + "{(";
        for (State key : to.keySet()) {
            result += from.get(key).getName() + " ";
            switch (typeMap.get(key)) {
                case ONE_WAY:
                    result = "-->";
                    break;
                case TWO_WAY:
                    result = "<->";
                    break;
                case NO_WAY:
                    result = "-/>";
                    break;
            }
            result += " " + to.get(key).getName() + "), ";
        }
        result += "\b\b}";
        return result;
    }
}
