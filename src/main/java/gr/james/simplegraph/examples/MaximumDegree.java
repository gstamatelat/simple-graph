package gr.james.simplegraph.examples;

import gr.james.simplegraph.Graph;

/**
 * Get the maximum degree in a {@link Graph}.
 */
public class MaximumDegree {
    /**
     * Get the maximum degree in a {@link Graph}.
     * <p>
     * Returns {@code -1} if the graph has no vertices. Returns {@code 0} if the graph has at least one vertex but no
     * edges. The degree of a vertex includes a self-loop, if present.
     * <p>
     * Complexity: O(V)
     *
     * @param g the graph
     * @return the maximum degree in {@code g}.
     * @throws NullPointerException if {@code g} is {@code null}
     */
    public static int maximumDegree(Graph g) {
        int max = -1;
        for (int i = 0; i < g.size(); i++) {
            final int degree = g.getEdges(i).size();
            if (degree > max) {
                max = degree;
            }
        }
        return max;
    }
}
