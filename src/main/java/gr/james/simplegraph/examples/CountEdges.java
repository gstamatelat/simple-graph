package gr.james.simplegraph.examples;

import gr.james.simplegraph.DirectedGraph;

/**
 * Count all the directed edges of a {@link DirectedGraph}.
 */
public class CountEdges {
    /**
     * Get the number of directed edges of a {@link DirectedGraph}.
     * <p>
     * Complexity: O(V)
     *
     * @param g the graph
     * @return the number of directed edges of {@code g}
     * @throws NullPointerException if {@code g} is {@code null}
     */
    public static int countEdges(DirectedGraph g) {
        int edgesCount = 0;
        for (int i = 0; i < g.size(); i++) {
            edgesCount += g.getOutEdges(i).size();
        }
        return edgesCount;
    }
}
