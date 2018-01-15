package gr.james.simplegraph.examples;

import gr.james.simplegraph.Graph;

/**
 * Get the vertex with the maximum degree from a {@link Graph}.
 */
public class MaximumDegree {
    /**
     * Get the maximum degree in a {@link Graph}.
     * <p>
     * Returns {@code 0} if the graph has no edges.
     * <p>
     * Complexity: O(V)
     *
     * @param g the graph
     * @return the maximum degree in {@code g}.
     * @throws NullPointerException if {@code g} is {@code null}
     */
    public static int maximumDegree(Graph g) {
        int max = 0;
        for (int i = 0; i < g.size(); i++) {
            final int degree = g.getEdges(i).size();
            if (degree > max) {
                max = degree;
            }
        }
        return max;
    }
}
