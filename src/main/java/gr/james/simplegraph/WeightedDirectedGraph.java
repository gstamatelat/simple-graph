package gr.james.simplegraph;

import java.util.Set;

/**
 * Represents a weighted and directed graph.
 * <p>
 * The graph can contain self loops but cannot contain parallel edges. More formally, any ordered pair of endpoints may
 * correspond to at most one edge. The edge weights can only be finite {@link Double} values.
 * <p>
 * An ordered pair {@code (a, b)} is a pair of objects where the order in which the objects appear in the pair is
 * significant: the ordered pair {@code (a, b)} is different from the ordered pair {@code (b, a)} unless {@code a = b}.
 * <p>
 * Memory Complexity: O(V+E)
 */
public interface WeightedDirectedGraph extends DirectedGraph {
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
     * @return {@inheritDoc}
     * @throws UnsupportedOperationException {@inheritDoc}
     */
    @Override
    DirectedGraph asDirected();

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @throws UnsupportedOperationException {@inheritDoc}
     */
    @Override
    WeightedDirectedGraph asWeightedDirected();

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
