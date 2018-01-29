package gr.james.simplegraph.examples;

import gr.james.simplegraph.ImmutableDirectedGraph;

/**
 * Count all the directed edges of a {@link ImmutableDirectedGraph}.
 */
public final class CountEdges {
    private CountEdges() {
    }

    /**
     * Get the number of directed edges in a {@link ImmutableDirectedGraph}.
     * <p>
     * Complexity: O(V)
     *
     * @param g the graph
     * @return the number of directed edges in {@code g}
     * @throws NullPointerException if {@code g} is {@code null}
     */
    public static int countEdges(ImmutableDirectedGraph g) {
        int edgesCount = 0;
        for (int i = 0; i < g.size(); i++) {
            edgesCount += g.getOutEdges(i).size();
        }
        return edgesCount;
    }
}
