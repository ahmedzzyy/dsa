package edu.practice.datastructures.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DirectedWeightedGraphList<V> {

    private class Edge {
        V destination;
        double weight;

        Edge(V destination, double weight) {
            this.destination = destination;
            this.weight = weight;
        }
    }

    // NOTE hashmap are used rather than linked list for faster lookups
    private final Map<V, List<Edge>> weightedGraph;
    private int vertexCount;

    DirectedWeightedGraphList(int initialCapacity) {
        weightedGraph = new HashMap<>(initialCapacity);
        vertexCount = 0;
    }

    DirectedWeightedGraphList() {
        this(5);
    }

    public boolean isEmpty() {
        return vertexCount == 0;
    }

    public int getVertexCount() {
        return vertexCount;
    }

    public boolean containsVertex(V v) {
        return weightedGraph.containsKey(v);
    }

    public void addVertex(V vertex) {
        if (weightedGraph.containsKey(vertex)) {
            return;
        }

        weightedGraph.put(vertex, new ArrayList<>());
        vertexCount++;
    }

    public void removeVertex(V vertex) {
        if (!weightedGraph.containsKey(vertex)) {
            return;
        }

        // Remove the graph adjacency list
        weightedGraph.remove(vertex);

        // Remove edges with "vertex" as its destination
        for (List<Edge> edges: weightedGraph.values()) {
            edges.removeIf(edge -> edge.destination.equals(vertex));
        }

        vertexCount--;
    }

    public void addEdge(V u, V v, double weight) {
        if (!weightedGraph.containsKey(u) || !weightedGraph.containsKey(v)) {
            throw new IllegalArgumentException("Both vertices must be in graph.");
        }

        weightedGraph.get(u).add(new Edge(v, weight));
    }

    public void removeEdge(V u, V v) {
        if (!weightedGraph.containsKey(u) || !weightedGraph.containsKey(v)) {
            throw new IllegalArgumentException("Both vertices must be in graph.");
        }

        weightedGraph.get(u).removeIf(edge -> edge.destination.equals(v));
    }

    public double getEdge(V u, V v) {
        if (!weightedGraph.containsKey(u) || !weightedGraph.containsKey(v)) {
            throw new IllegalArgumentException("Both vertices must be in graph.");
        }

        List<Edge> edges = weightedGraph.get(u);
        for (Edge edge : edges) {
            if (edge.destination.equals(v)) {
                return edge.weight;
            }
        }

        return -1;
    }
}
