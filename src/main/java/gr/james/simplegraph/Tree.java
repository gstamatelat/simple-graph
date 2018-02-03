package gr.james.simplegraph;

import java.util.Set;

/**
 * Represents an unweighted and undirected graph in which any two vertices are connected by exactly one path.
 * <p>
 * This graph must contain at least one vertex. A tree cannot contain self loops or parallel edges. More formally, any
 * unordered pair of endpoints may correspond to at most one edge.
 * <p>
 * An unordered pair {@code {a, b}} is a pair of objects with no particular relation between them; the order in which
 * the objects appear in the pair is not significant.
 * <p>
 * Memory Complexity: O(V+E)
 */
public interface Tree extends Graph {
    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    int size();

    /**
     * Returns the number of undirected edges in this tree, that is {@code size() - 1}.
     * <p>
     * Complexity: O(1)
     *
     * @return the number of undirected edges in this tree
     */
    int edgeCount();

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
     * @see Graphs#equals(Graph, Graph)
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
