package gr.james.simplegraph;

/**
 * Represents an edge of a {@link Graph}.
 * <p>
 * Memory Complexity: O(1)
 */
public interface Edge extends BaseEdge {
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
