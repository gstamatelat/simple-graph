package gr.james.simplegraph;

/**
 * Helper graph utilities.
 */
final class GraphsInternal {
    /**
     * Check if a vertex is inside the bounds of a graph.
     * <p>
     * Complexity: O(1)
     *
     * @param g the graph
     * @param v the vertex
     * @return {@code v}
     * @throws NullPointerException      if {@code g} is {@code null}
     * @throws IndexOutOfBoundsException if {@code v} is not in {@code g}
     */
    static int checkVertex(BaseGraph g, int v) {
        if (v < 0 || v >= g.size()) {
            throw new IndexOutOfBoundsException();
        }
        return v;
    }

    /**
     * Returns a string representation of a graph.
     * <p>
     * Complexity: O(V+E)
     *
     * @param g the graph
     * @return a string representation of {@code g}
     * @throws NullPointerException if {@code g} is {@code null}
     */
    static String toString(Graph g) {
        final StringBuilder sb = new StringBuilder();
        sb.append(String.format("%s(%d) {%n", "ImmutableGraph", g.size()));
        for (int i = 0; i < g.size(); i++) {
            for (int adj : g.getEdges(i)) {
                if (adj >= i) {
                    sb.append(String.format("  %d -- %d%n", i, adj));
                }
            }
        }
        sb.append("}");
        return sb.toString();
    }

    /**
     * Returns a string representation of a graph.
     * <p>
     * Complexity: O(V+E)
     *
     * @param g the graph
     * @return a string representation of {@code g}
     * @throws NullPointerException if {@code g} is {@code null}
     */
    static String toString(DirectedGraph g) {
        final StringBuilder sb = new StringBuilder();
        sb.append(String.format("%s(%d) {%n", "ImmutableDirectedGraph", g.size()));
        for (int i = 0; i < g.size(); i++) {
            for (int adj : g.getOutEdges(i)) {
                sb.append(String.format("  %d -> %d%n", i, adj));
            }
        }
        sb.append("}");
        return sb.toString();
    }

    /**
     * Returns a string representation of a graph.
     * <p>
     * Complexity: O(V+E)
     *
     * @param g the graph
     * @return a string representation of {@code g}
     * @throws NullPointerException if {@code g} is {@code null}
     */
    static String toString(WeightedGraph g) {
        final StringBuilder sb = new StringBuilder();
        sb.append(String.format("%s(%d) {%n", "ImmutableWeightedGraph", g.size()));
        for (int i = 0; i < g.size(); i++) {
            for (int adj : g.getEdges(i)) {
                if (adj >= i) {
                    sb.append(String.format("  %d -- %d [%.2f]%n", i, adj, g.getEdgeWeight(i, adj)));
                }
            }
        }
        sb.append("}");
        return sb.toString();
    }

    /**
     * Returns a string representation of a graph.
     * <p>
     * Complexity: O(V+E)
     *
     * @param g the graph
     * @return a string representation of {@code g}
     * @throws NullPointerException if {@code g} is {@code null}
     */
    static String toString(WeightedDirectedGraph g) {
        final StringBuilder sb = new StringBuilder();
        sb.append(String.format("%s(%d) {%n", "ImmutableWeightedDirectedGraph", g.size()));
        for (int i = 0; i < g.size(); i++) {
            for (int adj : g.getOutEdges(i)) {
                sb.append(String.format("  %d -> %d [%.2f]%n", i, adj, g.getEdgeWeight(i, adj)));
            }
        }
        sb.append("}");
        return sb.toString();
    }

    /**
     * Checks if two graphs are equal.
     * <p>
     * Two graphs are equal if they are of the same class type, have the same number of vertices and their edges
     * represent the same mapping.
     * <p>
     * Complexity: O(V+E)
     *
     * @param a one graph
     * @param b the other graph
     * @return {@code true} if {@code a} is equal to {@code b}, otherwise {@code false}
     * @throws NullPointerException if {@code a} or {@code b} is {@code null}
     */
    static boolean equals(Graph a, Graph b) {
        if (a.size() != b.size()) {
            return false;
        }
        for (int i = 0; i < a.size(); i++) {
            if (!a.getEdges(i).equals(b.getEdges(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if two graphs are equal.
     * <p>
     * Two graphs are equal if they are of the same class type, have the same number of vertices and their edges
     * represent the same mapping.
     * <p>
     * Complexity: O(V+E)
     *
     * @param a one graph
     * @param b the other graph
     * @return {@code true} if {@code a} is equal to {@code b}, otherwise {@code false}
     * @throws NullPointerException if {@code a} or {@code b} is {@code null}
     */
    static boolean equals(DirectedGraph a, DirectedGraph b) {
        if (a.size() != b.size()) {
            return false;
        }
        for (int i = 0; i < a.size(); i++) {
            if (!a.getOutEdges(i).equals(b.getOutEdges(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if two graphs are equal.
     * <p>
     * Two graphs are equal if they are of the same class type, have the same number of vertices and their edges
     * represent the same mapping.
     * <p>
     * Complexity: O(V+E)
     *
     * @param a one graph
     * @param b the other graph
     * @return {@code true} if {@code a} is equal to {@code b}, otherwise {@code false}
     * @throws NullPointerException if {@code a} or {@code b} is {@code null}
     */
    static boolean equals(WeightedGraph a, WeightedGraph b) {
        if (a.size() != b.size()) {
            return false;
        }
        for (int i = 0; i < a.size(); i++) {
            if (!a.getEdges(i).equals(b.getEdges(i))) {
                return false;
            }
            for (int j : a.getEdges(i)) {
                if (a.getEdgeWeight(i, j) != b.getEdgeWeight(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Checks if two graphs are equal.
     * <p>
     * Two graphs are equal if they are of the same class type, have the same number of vertices and their edges
     * represent the same mapping.
     * <p>
     * Complexity: O(V+E)
     *
     * @param a one graph
     * @param b the other graph
     * @return {@code true} if {@code a} is equal to {@code b}, otherwise {@code false}
     * @throws NullPointerException if {@code a} or {@code b} is {@code null}
     */
    static boolean equals(WeightedDirectedGraph a, WeightedDirectedGraph b) {
        if (a.size() != b.size()) {
            return false;
        }
        for (int i = 0; i < a.size(); i++) {
            if (!a.getOutEdges(i).equals(b.getOutEdges(i))) {
                return false;
            }
            for (int j : a.getOutEdges(i)) {
                if (a.getEdgeWeight(i, j) != b.getEdgeWeight(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Returns a hash code value for a graph.
     * <p>
     * Complexity: O(V+E)
     *
     * @param g the graph
     * @return a hash code value for {@code g}
     * @throws NullPointerException if {@code g} is {@code null}
     */
    static int hashCode(Graph g) {
        int hash = 0;
        for (int i = 0; i < g.size(); i++) {
            for (int j : g.getEdges(i)) {
                hash += j;
            }
        }
        return hash;
    }

    /**
     * Returns a hash code value for a graph.
     * <p>
     * Complexity: O(V+E)
     *
     * @param g the graph
     * @return a hash code value for {@code g}
     * @throws NullPointerException if {@code g} is {@code null}
     */
    static int hashCode(DirectedGraph g) {
        int hash = 0;
        for (int i = 0; i < g.size(); i++) {
            for (int j : g.getOutEdges(i)) {
                hash += j;
            }
        }
        return hash;
    }

    /**
     * Returns a hash code value for a graph.
     * <p>
     * Complexity: O(V+E)
     *
     * @param g the graph
     * @return a hash code value for {@code g}
     * @throws NullPointerException if {@code g} is {@code null}
     */
    static int hashCode(WeightedGraph g) {
        int hash = 0;
        for (int i = 0; i < g.size(); i++) {
            for (int j : g.getEdges(i)) {
                hash += (j ^ new Double(g.getEdgeWeight(i, j)).hashCode());
            }
        }
        return hash;
    }

    /**
     * Returns a hash code value for a graph.
     * <p>
     * Complexity: O(V+E)
     *
     * @param g the graph
     * @return a hash code value for {@code g}
     * @throws NullPointerException if {@code g} is {@code null}
     */
    static int hashCode(WeightedDirectedGraph g) {
        int hash = 0;
        for (int i = 0; i < g.size(); i++) {
            for (int j : g.getOutEdges(i)) {
                hash += (j ^ new Double(g.getEdgeWeight(i, j)).hashCode());
            }
        }
        return hash;
    }
}