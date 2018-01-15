package gr.james.simplegraph.examples;

import gr.james.simplegraph.WeightedGraph;

/**
 * Get the edge with the minimum weight from a {@link WeightedGraph}.
 */
public class MinimumEdgeWeight {
    public static void main(String[] args) {

    }

    /**
     * Returns the minimum edge weight of a {@link WeightedGraph}.
     * <p>
     * Returns {@link Double#NaN} if the graph has no edges.
     * <p>
     * Complexity: O(V+E)
     *
     * @param g the graph
     * @return the minimum edge weight of {@code g}
     * @throws NullPointerException if {@code g} is {@code null}
     */
    public static double minimumEdgeWeight(WeightedGraph g) {
        double min = Double.NaN;
        for (int i = 0; i < g.size(); i++) {
            for (int j : g.getEdges(i)) {
                final double weight = g.getEdgeWeight(i, j);
                if (Double.isNaN(min) || weight < min) {
                    min = weight;
                }
            }
        }
        return min;
    }
}