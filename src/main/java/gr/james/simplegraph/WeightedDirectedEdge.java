package gr.james.simplegraph;

/**
 * Represents an edge of a {@link WeightedDirectedGraph}.
 * <p>
 * Memory Complexity: O(1)
 */
public interface WeightedDirectedEdge extends BaseEdge {
    /**
     * Returns the source vertex of this edge.
     * <p>
     * Complexity: O(1)
     *
     * @return the source vertex of this edge
     */
    int source();

    /**
     * Returns the target vertex of this edge.
     * <p>
     * Complexity: O(1)
     *
     * @return the target vertex of this edge
     */
    int target();

    /**
     * Returns the weight of this edge.
     * <p>
     * Complexity: O(1)
     *
     * @return the weight of this edge
     */
    double weight();

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
