package gr.james.simplegraph;

import java.util.Set;

/**
 * Base interface for weighted and directed graphs.
 */
public interface IWeightedDirectedGraph extends IDirectedGraph {
    /**
     * Get the weight of the edge from {@code source} to {@code target}.
     * <p>
     * Complexity: O(1)
     *
     * @param source the source of the edge
     * @param target the target of the edge
     * @return the weight of the edge from {@code source} to {@code target}
     * @throws IndexOutOfBoundsException if {@code source} or {@code target} are outside of {@code [O,V)}
     * @throws IllegalArgumentException  if there is no edge from {@code source} to {@code target}
     */
    double getEdgeWeight(int source, int target);

    /**
     * {@inheritDoc}
     *
     * @param v {@inheritDoc}
     * @return {@inheritDoc}
     * @throws IndexOutOfBoundsException {@inheritDoc}
     * @see #getInEdges(int)
     */
    @Override
    Set<Integer> getOutEdges(int v);

    /**
     * {@inheritDoc}
     *
     * @param v {@inheritDoc}
     * @return {@inheritDoc}
     * @throws IndexOutOfBoundsException {@inheritDoc}
     * @see #getOutEdges(int)
     */
    @Override
    Set<Integer> getInEdges(int v);

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    int size();

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    String toString();

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
}
