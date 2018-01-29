package gr.james.simplegraph;

import java.util.Set;

/**
 * Base interface for weighted and undirected graphs.
 */
public interface WeightedGraph extends Graph {
    /**
     * Get the weight of the edge connecting {@code v} and {@code w}.
     * <p>
     * Complexity: O(1)
     *
     * @param v one end of the edge
     * @param w the other end of the edge
     * @return the weight of the edge connecting {@code v} and {@code w}
     * @throws IndexOutOfBoundsException if {@code v} or {@code w} are outside of {@code [O,V)}
     * @throws IllegalArgumentException  if there is no edge connecting {@code v} and {@code w}
     */
    double getEdgeWeight(int v, int w);

    /**
     * {@inheritDoc}
     *
     * @param v {@inheritDoc}
     * @return {@inheritDoc}
     * @throws IndexOutOfBoundsException if {@code v} is outside the range {@code [O,V)}
     */
    @Override
    Set<Integer> getEdges(int v);

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
     * @throws UnsupportedOperationException {@inheritDoc}
     */
    @Override
    Graph asGraph();

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
    WeightedGraph asWeighted();

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
