package gr.james.simplegraph;

/**
 * Represents an edge of a {@link WeightedGraph}.
 * <p>
 * Memory Complexity: O(1)
 */
public interface WeightedEdge extends BaseEdge {
    /**
     * Returns the vertex at one end of this edge.
     * <p>
     * Complexity: O(1)
     *
     * @return the vertex at one end of this edge
     */
    int v();

    /**
     * Returns the vertex at the other end of this edge.
     * <p>
     * Complexity: O(1)
     *
     * @return the vertex at the other end of this edge
     */
    int w();

    /**
     * Returns the weight of this edge.
     * <p>
     * Complexity: O(1)
     *
     * @return the weight of this edge
     */
    double weight();

    /**
     * Returns a new {@link WeightedEdge} that has the values of {@link #v()} and {@link #w()} interchanged.
     * <p>
     * Because this is an undirected edge it holds that:
     * <pre><code>
     * assert e.equals(e.swap());
     * </code></pre>
     * <p>
     * Complexity: O(1)
     *
     * @return a new {@link WeightedEdge} that has the values of {@link #v()} and {@link #w()} interchanged
     */
    WeightedEdge swap();

    /**
     * {@inheritDoc}
     *
     * @param obj {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    boolean equals(Object obj);

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    int hashCode();

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    String toString();
}
