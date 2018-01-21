package gr.james.simplegraph;

/**
 * Helper graph utilities.
 */
final class Graphs {
    /**
     * Check is a weight value is legal.
     * <p>
     * A weight value is legal if it is not any of the following values:
     * <ul>
     * <li>{@link Double#NaN}</li>
     * <li>{@link Double#POSITIVE_INFINITY}</li>
     * <li>{@link Double#NEGATIVE_INFINITY}</li>
     * </ul>
     * <p>
     * Complexity: O(1)
     *
     * @param weight the weight value to check
     * @return {@code weight}
     * @throws IllegalArgumentException if {@code weight} is not legal
     */
    static double checkWeight(double weight) {
        if (!isWeightLegal(weight)) {
            throw new IllegalArgumentException();
        }
        return weight;
    }

    /**
     * Check is a weight value is legal.
     * <p>
     * A weight value is legal if it is not any of the following values:
     * <ul>
     * <li>{@link Double#NaN}</li>
     * <li>{@link Double#POSITIVE_INFINITY}</li>
     * <li>{@link Double#NEGATIVE_INFINITY}</li>
     * </ul>
     * <p>
     * Complexity: O(1)
     *
     * @param weight the weight value to check
     * @return {@code true} if {@code weight} is legal, otherwise {@code false}
     */
    static boolean isWeightLegal(double weight) {
        return !Double.isNaN(weight) && !Double.isInfinite(weight);
    }

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
    static int checkVertex(IBaseGraph g, int v) {
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
     */
    static String toString(IGraph g) {
        final StringBuilder sb = new StringBuilder();
        sb.append(String.format("%s(%d) {%n", "Graph", g.size()));
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
     */
    static String toString(IDirectedGraph g) {
        final StringBuilder sb = new StringBuilder();
        sb.append(String.format("%s(%d) {%n", "DirectedGraph", g.size()));
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
     */
    static String toString(IWeightedGraph g) {
        final StringBuilder sb = new StringBuilder();
        sb.append(String.format("%s(%d) {%n", "WeightedGraph", g.size()));
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
     */
    static String toString(IWeightedDirectedGraph g) {
        final StringBuilder sb = new StringBuilder();
        sb.append(String.format("%s(%d) {%n", "WeightedDirectedGraph", g.size()));
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
     * Two graphs are equal if they are of same type, have the same number of vertices and their edges represent the
     * same mapping.
     * <p>
     * Complexity: O(V+E)
     *
     * @param a one graph
     * @param b the other graph
     * @return {@code true} if {@code a} is equal to {@code b}, otherwise {@code false}
     */
    static boolean equals(IGraph a, IGraph b) {
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
     * Two graphs are equal if they are of same type, have the same number of vertices and their edges represent the
     * same mapping.
     * <p>
     * Complexity: O(V+E)
     *
     * @param a one graph
     * @param b the other graph
     * @return {@code true} if {@code a} is equal to {@code b}, otherwise {@code false}
     */
    static boolean equals(IDirectedGraph a, IDirectedGraph b) {
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
     * Two graphs are equal if they are of same type, have the same number of vertices and their edges represent the
     * same mapping.
     * <p>
     * Complexity: O(V+E)
     *
     * @param a one graph
     * @param b the other graph
     * @return {@code true} if {@code a} is equal to {@code b}, otherwise {@code false}
     */
    static boolean equals(IWeightedGraph a, IWeightedGraph b) {
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
     * Two graphs are equal if they are of same type, have the same number of vertices and their edges represent the
     * same mapping.
     * <p>
     * Complexity: O(V+E)
     *
     * @param a one graph
     * @param b the other graph
     * @return {@code true} if {@code a} is equal to {@code b}, otherwise {@code false}
     */
    static boolean equals(IWeightedDirectedGraph a, IWeightedDirectedGraph b) {
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
     */
    static int hashCode(IGraph g) {
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
     */
    static int hashCode(IDirectedGraph g) {
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
     */
    static int hashCode(IWeightedGraph g) {
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
     */
    static int hashCode(IWeightedDirectedGraph g) {
        int hash = 0;
        for (int i = 0; i < g.size(); i++) {
            for (int j : g.getOutEdges(i)) {
                hash += (j ^ new Double(g.getEdgeWeight(i, j)).hashCode());
            }
        }
        return hash;
    }
}
