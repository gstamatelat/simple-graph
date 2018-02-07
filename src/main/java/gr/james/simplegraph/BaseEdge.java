package gr.james.simplegraph;

/**
 * Base interface for all edge types.
 */
interface BaseEdge {
    /**
     * Indicates whether some other object is equal to this edge.
     * <p>
     * Two edges are equal if they are of the same type, represent the same graph connection and have the same weight
     * (if they are weighted).
     * <p>
     * Complexity: O(1)
     *
     * @param obj the reference object with which to compare
     * @return {@code true} if this edge is equal to the {@code obj} argument, otherwise {@code false}
     */
    @Override
    boolean equals(Object obj);

    /**
     * Returns a hash code value for this edge.
     * <p>
     * Complexity: O(1)
     *
     * @return a hash code value for this edge
     */
    @Override
    int hashCode();

    /**
     * Returns a string representation of this edge.
     * <p>
     * Complexity: O(1)
     *
     * @return a string representation of this edge
     */
    @Override
    String toString();
}
