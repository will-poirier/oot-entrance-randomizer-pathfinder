package pathfinder;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import pathfinder.Edge.EdgeType;
import pathfinder.view.controller.GraphObserver;

public class Graph {
    private Map<String, Edge> edges;
    private Map<State, Map<String, Vertex>> vertices;
    private Set<GraphObserver> observers;

    public Graph() {
        edges = new HashMap<>();
        vertices = new HashMap<>();
        observers = new HashSet<>();
    }

    public void addObserver(GraphObserver obs) {
        observers.add(obs);
    }

    private void notifyObservers(String effect, Object[] information) {
        for (GraphObserver obs : observers) {
            obs.inform(effect, information);
        }
    }

    public void addVertex(State state, Vertex a) {
        vertices.get(state).put(a.getName(), a);
        Object[] info = {a};
        notifyObservers("Vertex Added Directly", info);
    }

    public void addEdge(Edge a) {
        edges.put(a.getName(), a);
        Object[] info = {a};
        notifyObservers("Edge Added Directly", info);
    }

    public Edge getEdge(String name) {
        return edges.get(name);
    }

    public Vertex getVertex(State state, String name) {
        return vertices.get(state).get(name);
    }

    public void connect(String name, String toVert, String fromVert) {
        Map<State, Edge.EdgeType> typeMap = new HashMap<>();
        for (State state : vertices.keySet()) {
            typeMap.put(state, EdgeType.TWO_WAY);
        }
        connect(name, toVert, fromVert, typeMap);
    }

    public void connect(String name, String toVert, String fromVert, Map<State, Edge.EdgeType> typeMap) {
        Map<State, Vertex> toVerts = new HashMap<>();
        Map<State, Vertex> fromVerts = new HashMap<>();
        for (State state : typeMap.keySet()) {
            toVerts.put(state, new Vertex(toVert + " (" + state + ")"));
            fromVerts.put(state, new Vertex(fromVert + " (" + state + ")"));
        }
        connect(name, toVerts, fromVerts, typeMap);
    }

    public void connect (String name, Map<State, Vertex> toVerts, Map<State, Vertex> fromVerts, Map<State, Edge.EdgeType> typeMap) {
        edges.put(name, new Edge(name, toVerts, fromVerts, typeMap));
        Object[] info = {name, toVerts, fromVerts, typeMap};
        notifyObservers("Connection Made", info);
    }

    // Pathfinding woohoo
    public boolean bfSearch(State state, String start, String end) {
        Vertex startVertex = vertices.get(state).get(start);
        Vertex endVertex = vertices.get(state).get(end);
        Queue<Vertex> queue = new LinkedList<>();
        Set<Vertex> set = new HashSet<>();
        queue.add(startVertex);
        set.add(startVertex);

        while (queue.size() > 0) {
            Vertex Vertex = queue.poll();
            if (Vertex.equals(endVertex)) {
                return true;
            }
            for (Vertex neighbor : Vertex.getNeighbors().keySet()) {
                if (!set.contains(neighbor)) {
                    queue.add(neighbor);
                    set.add(neighbor);
                }
            }
        }

        return false;
    }

    private List<String> makePath(Map<Vertex, Vertex> map, Vertex endVertex) {
        if (!map.containsKey(endVertex)) { return null; }
        List<String> path = new LinkedList<>();
        Vertex Vertex = endVertex;
        while (Vertex != null) {
            path.add(0, Vertex.toString());
            Vertex = map.get(Vertex);
        }
        return path;
    }
    
    public List<String> bfPath(State state, String start, String end) {
        Vertex startVertex = vertices.get(state).get(start);
        Vertex endVertex = vertices.get(state).get(end);
        Queue<Vertex> queue = new LinkedList<>();
        Map<Vertex, Vertex> map = new HashMap<>();
        queue.add(startVertex);
        map.put(startVertex, null);

        while (queue.size() > 0) {
            Vertex Vertex = queue.poll();
            if (Vertex.equals(endVertex)) {
                break;
            }
            for (Vertex neighbor : Vertex.getNeighbors().keySet()) {
                if (!map.containsKey(neighbor)) {
                    queue.add(neighbor);
                    map.put(neighbor, Vertex);
                }
            }
        }

        return makePath(map, endVertex);
    }
}
