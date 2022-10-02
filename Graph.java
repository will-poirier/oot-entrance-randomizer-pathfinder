import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class Graph {
    private Map<String, Node> nodes;

    public Graph() {
        nodes = new HashMap<>();
    }

    public void add(String val) {
        nodes.put(val, new Node(val));
    }

    public boolean contains(String val) {
        return nodes.containsKey(val);
    }

    public int size() {
        return nodes.size();
    }

    public void connect(String a, String b) {
        if (nodes.containsKey(a) && nodes.containsKey(b)) {
            nodes.get(a).connect(nodes.get(b));
            nodes.get(b).connect(nodes.get(a));
        }
    }

    public void oneWayConnect(String a, String b) {
        if (nodes.containsKey(a) && nodes.containsKey(b)) {
            nodes.get(a).connect(nodes.get(b));
        }
    }

    @Override
    public String toString() {
        String result = "";
        for (String key : nodes.keySet()) {
            result += key + "\n";
        }
        return result;
    }

    public boolean bfSearch(String start, String end) {
        Node startVertex = nodes.get(start);
        Node endVertex = nodes.get(end);
        Queue<Node> queue = new LinkedList<>();
        Set<Node> set = new HashSet<>();
        queue.add(startVertex);
        set.add(startVertex);

        while (queue.size() > 0) {
            Node Node = queue.poll();
            if (Node.equals(endVertex)) {
                return true;
            }
            for (Node neighbor : Node.getNeighbors()) {
                if (!set.contains(neighbor)) {
                    queue.add(neighbor);
                    set.add(neighbor);
                }
            }
        }

        return false;
    }

    private List<String> makePath(Map<Node, Node> map, Node endVertex) {
        if (!map.containsKey(endVertex)) { return null; }
        List<String> path = new LinkedList<>();
        Node Node = endVertex;
        while (Node != null) { // note to self: .equals(null) is not helpful
            path.add(0, Node.toString());
            Node = map.get(Node);
        }
        return path;
    }
    
    public List<String> bfPath(String start, String end) {
        Node startVertex = nodes.get(start);
        Node endVertex = nodes.get(end);
        Queue<Node> queue = new LinkedList<>();
        Map<Node, Node> map = new HashMap<>();
        queue.add(startVertex);
        map.put(startVertex, null);

        while (queue.size() > 0) {
            Node Node = queue.poll();
            if (Node.equals(endVertex)) {
                break;
            }
            for (Node neighbor : Node.getNeighbors()) {
                if (!map.containsKey(neighbor)) {
                    queue.add(neighbor);
                    map.put(neighbor, Node);
                }
            }
        }

        return makePath(map, endVertex);
    }
}
