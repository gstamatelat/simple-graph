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
     * @return a string representation of a graph
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
     * @return a string representation of a graph
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
     * @return a string representation of a graph
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
     * @return a string representation of a graph
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
}
