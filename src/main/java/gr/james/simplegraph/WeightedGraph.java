package gr.james.simplegraph;

import java.util.Set;

/**
 * Represents a weighted and undirected graph.
 * <p>
 * The graph can contain self loops but cannot contain parallel edges. More formally, any unordered pair of endpoints
 * may correspond to at most one edge. The edge weights can only be finite {@link Double} values.
 * <p>
 * An unordered pair {@code {a, b}} is a pair of objects with no particular relation between them; the order in which
 * the objects appear in the pair is not significant.
 * <p>
 * Memory Complexity: O(V+E)
 */
public interface WeightedGraph extends Graph {
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
     * @throws IndexOutOfBoundsException if {@code v} is outside the range {@code [O,V)}
     */
    @Override
    Set<Integer> getEdges(int v);

    /**
     * Get the weight of the edge connecting {@code v} and {@code w}.
     * <p>
     * The weight of an edge can be any {@link Double} value but {@link Double#NaN}, {@link Double#POSITIVE_INFINITY}
     * and {@link Double#NEGATIVE_INFINITY}.
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
