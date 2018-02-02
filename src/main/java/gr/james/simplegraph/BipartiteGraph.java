package gr.james.simplegraph;

import java.util.Set;

/**
 * Represents an unweighted and undirected bipartite graph.
 * <p>
 * A bipartite graph comprises of two independent sets of vertices, namely set {@code A} and set {@code B}. An edge can
 * only connect one vertex in set {@code A} to one vertex in set {@code B}. Therefore, the graph cannot contain self
 * loops.
 * <p>
 * Furthermore, the graph cannot contain parallel edges. More formally, any unordered pair of endpoints may correspond
 * to at most one edge.
 * <p>
 * An unordered pair {@code {a, b}} is a pair of objects with no particular relation between them; the order in which
 * the objects appear in the pair is not significant.
 * <p>
 * Memory Complexity: O(V+E)
 */
public interface BipartiteGraph extends Graph {
    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    int size();

    /**
     * Returns a {@link Set} of all vertices in set {@code A}.
     * <p>
     * Complexity: O(1)
     *
     * @return a {@link Set} of all vertices in set {@code A}
     * @see #setB()
     */
    Set<Integer> setA();

    /**
     * Returns a {@link Set} of all vertices in set {@code B}.
     * <p>
     * Complexity: O(1)
     *
     * @return a {@link Set} of all vertices in set {@code B}
     * @see #setA()
     */
    Set<Integer> setB();

    /**
     * {@inheritDoc}
     *
     * @param v {@inheritDoc}
     * @return {@inheritDoc}
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    @Override
    Set<Integer> getEdges(int v);

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    Iterable<Edge> edges();

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    DirectedGraph asDirected();

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    WeightedGraph asWeighted();

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
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
