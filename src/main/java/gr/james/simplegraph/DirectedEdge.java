package gr.james.simplegraph;

/**
 * Represents an edge of a {@link DirectedGraph}.
 * <p>
 * Normally, this interface is only used when calling the method {@link DirectedGraph#edges()}.
 * <p>
 * Memory Complexity: O(1)
 */
public interface DirectedEdge extends BaseEdge {
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
