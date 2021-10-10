package il.ac.tau.cs.sw1.ex7;

import java.util.*;


public class Graph implements Greedy<Graph.Edge> {

    final Edge errorEdge = new Edge(-1, -1, -1.0);
    List<Edge> lst; //Graph is represented in Edge-List. It is undirected. Assumed to be connected.
    int n; //nodes are in [0,...,n-1]

    Graph(int n1, List<Edge> lst1) {
        lst = lst1;
        n = n1 + 1;
        checkEdges();
    }


    private void checkEdges() {
        cleanEdges();
        if (this.lst.size() < this.n - 1) {
            LinkedList<Edge> errorEdgesList = new LinkedList<Edge>();
            errorEdgesList.add(errorEdge);
            this.lst = errorEdgesList;
        }
    }

    private void cleanEdges() {
        this.lst.removeIf(edge -> edge.node1 == edge.node2);
    }

    private LinkedList<HashSet<Integer>> getNeighborsList(List<Edge> lst) {
        LinkedList<HashSet<Integer>> neighbors = new LinkedList<HashSet<Integer>>();
        for (int i = 0; i < this.n; i++) {
            neighbors.add(i, new HashSet<Integer>());
        }
        for (Edge edge : lst) {
            neighbors.get(edge.node1).add(edge.node2);
            neighbors.get(edge.node2).add(edge.node1);
        }
        return neighbors;
    }

    private boolean hasPath(LinkedList<HashSet<Integer>> neighbors, int startNode, int endNode) {
        if (startNode < 0) {
            return false;
        }
        boolean[] visited = new boolean[this.n];

        visited[startNode] = true;
        LinkedList<Integer> queue = new LinkedList<Integer>(neighbors.get(startNode));

        while (queue.size() != 0) {
            int node = queue.poll();
            for (int i : neighbors.get(node)) {
                if (i == endNode) {
                    return true;
                }
                if (!visited[i]) {
                    visited[i] = true;
                    queue.add(i);
                }
            }
        }
        return false;
    }

    private boolean BFS(LinkedList<HashSet<Integer>> neighbors, int node) {

        boolean[] visited = new boolean[this.n];

        visited[node] = true;
        LinkedList<Integer> queue = new LinkedList<Integer>(neighbors.get(node));

        while (queue.size() != 0) {
            int firstNode = queue.poll();

            for (int i : neighbors.get(firstNode)) {
                if (!visited[i]) {
                    visited[i] = true;
                    queue.add(i);
                }
            }
        }

        for (boolean H : visited) {
            if (!H) {
                return false;
            }
        }
        return true;
    }


    public static class Edge implements Comparable<Edge> {
        int node1, node2;
        double weight;

        Edge(int n1, int n2, double w) {
            node1 = n1;
            node2 = n2;
            weight = w;
        }

        @Override
        public String toString() {
            return "{" + "(" + node1 + "," + node2 + "), weight=" + weight + '}';
        }

        @Override
        public int compareTo(Edge o) {
            int comparison = Double.compare(this.weight, o.weight);
            switch (comparison) {
                case -1:
                    return -1;
                case 0:
                    int node1Comparison = Integer.compare(this.node1, o.node1);
                    if (node1Comparison == 0) {
                        return Integer.compare(this.node2, o.node2);
                    }
                    return node1Comparison;
                case 1:
                    return 1;
            }
            return 0;
        }

    }

    @Override
    public Iterator<Edge> selection() {
        List<Edge> lst = this.lst;
        Collections.sort(lst);
        return lst.iterator();
    }

    @Override
    public boolean feasibility(List<Edge> candidates_lst, Edge element) {
        if (element.node1 == -1) {
            return false;
        }
        LinkedList<HashSet<Integer>> neighbors = getNeighborsList(candidates_lst);
        return !(hasPath(neighbors, element.node1, element.node2));
    }

    @Override
    public void assign(List<Edge> candidates_lst, Edge element) {
        candidates_lst.add(element);
    }

    @Override
    public boolean solution(List<Edge> candidates_lst) {
        LinkedList<HashSet<Integer>> neighbors = getNeighborsList(candidates_lst);

        for (int i = 0; i < this.n; i++) {
            if (neighbors.get(i).size() != 0) {
                if (this.lst.contains(errorEdge)) {
                    this.lst = null;
                    return false;
                }
                return BFS(neighbors, neighbors.get(i).iterator().next());
            }
        }
        return true;
    }
}
