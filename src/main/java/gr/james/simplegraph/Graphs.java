package gr.james.simplegraph;

/**
 * Helper graph utilities.
 */
public final class Graphs {
    private Graphs() {
    }

    /**
     * Check if a weight value is legal.
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
    public static double checkWeight(double weight) {
        if (!isWeightLegal(weight)) {
            throw new IllegalArgumentException();
        }
        return weight;
    }

    /**
     * Check if a weight value is legal.
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
    public static boolean isWeightLegal(double weight) {
        return !Double.isNaN(weight) && !Double.isInfinite(weight);
    }

    /**
     * Check if an edge exists in a graph.
     *
     * @param g the graph
     * @param v one end of the edge
     * @param w the other end of the edge
     * @throws NullPointerException      if {@code g} is {@code null}
     * @throws IndexOutOfBoundsException if {@code v} or {@code w} are not elements of {@code g}
     * @throws IllegalArgumentException  if no edge connecting {@code v} with {@code w} exists
     */
    public static void checkEdgeExists(Graph g, int v, int w) {
        checkVertex(g, w);
        if (!g.getEdges(v).contains(w)) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Check if an edge exists in a graph.
     *
     * @param g      the graph
     * @param source the source of the edge
     * @param target the target of the edge
     * @throws NullPointerException      if {@code g} is {@code null}
     * @throws IndexOutOfBoundsException if {@code source} or {@code target} are not elements of {@code g}
     * @throws IllegalArgumentException  if no edge from {@code source} to {@code target} exists
     */
    public static void checkEdgeExists(DirectedGraph g, int source, int target) {
        checkVertex(g, target);
        if (!g.getOutEdges(source).contains(target)) {
            throw new IllegalArgumentException();
        }
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
    public static int checkVertex(BaseGraph g, int v) {
        if (v < 0 || v >= g.size()) {
            throw new IndexOutOfBoundsException();
        }
        return v;
    }

    /**
     * Checks if two graphs are equal.
     * <p>
     * Two graphs are equal if they are of the same type, have the same number of vertices and their edges represent the
     * same mapping.
     * <p>
     * Complexity: O(V+E)
     *
     * @param a one graph
     * @param b the other graph
     * @return {@code true} if {@code a} is equal to {@code b}, otherwise {@code false}
     * @throws NullPointerException if {@code a} or {@code b} is {@code null}
     */
    public static boolean equals(Graph a, Graph b) {
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
     * Two graphs are equal if they are of the same type, have the same number of vertices and their edges represent the
     * same mapping.
     * <p>
     * Complexity: O(V+E)
     *
     * @param a one graph
     * @param b the other graph
     * @return {@code true} if {@code a} is equal to {@code b}, otherwise {@code false}
     * @throws NullPointerException if {@code a} or {@code b} is {@code null}
     */
    public static boolean equals(DirectedGraph a, DirectedGraph b) {
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
     * Two graphs are equal if they are of the same type, have the same number of vertices and their edges represent the
     * same mapping.
     * <p>
     * Complexity: O(V+E)
     *
     * @param a one graph
     * @param b the other graph
     * @return {@code true} if {@code a} is equal to {@code b}, otherwise {@code false}
     * @throws NullPointerException if {@code a} or {@code b} is {@code null}
     */
    public static boolean equals(WeightedGraph a, WeightedGraph b) {
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
     * Two graphs are equal if they are of the same type, have the same number of vertices and their edges represent the
     * same mapping.
     * <p>
     * Complexity: O(V+E)
     *
     * @param a one graph
     * @param b the other graph
     * @return {@code true} if {@code a} is equal to {@code b}, otherwise {@code false}
     * @throws NullPointerException if {@code a} or {@code b} is {@code null}
     */
    public static boolean equals(WeightedDirectedGraph a, WeightedDirectedGraph b) {
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
    public static int hashCode(Graph g) {
        int hash = 1;
        for (int i = 0; i < g.size(); i++) {
            hash = 31 * hash;
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
    public static int hashCode(DirectedGraph g) {
        int hash = 1;
        for (int i = 0; i < g.size(); i++) {
            hash = 31 * hash;
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
    public static int hashCode(WeightedGraph g) {
        int hash = 1;
        for (int i = 0; i < g.size(); i++) {
            hash = 31 * hash;
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
    public static int hashCode(WeightedDirectedGraph g) {
        int hash = 1;
        for (int i = 0; i < g.size(); i++) {
            hash = 31 * hash;
            for (int j : g.getOutEdges(i)) {
                hash += (j ^ new Double(g.getEdgeWeight(i, j)).hashCode());
            }
        }
        return hash;
    }
}
