package gr.james.simplegraph;

import java.util.Set;

/**
 * Represents an immutable, unweighted and directed graph implemented using adjacency lists.
 * <p>
 * The graph can contain self loops but cannot cannot contain parallel edges. More formally, any ordered pair of
 * endpoints may correspond to at most one edge.
 * <p>
 * An ordered pair {@code (a, b)} is a pair of objects where the order in which the objects appear in the pair is
 * significant: the ordered pair {@code (a, b)} is different from the ordered pair {@code (b, a)} unless {@code a = b}.
 * <p>
 * Memory Complexity: O(V+E)
 */
public class ImmutableDirectedGraph implements DirectedGraph {
    private static final long serialVersionUID = 1L;

    /**
     * Construct a new empty {@link ImmutableDirectedGraph}.
     * <p>
     * Complexity: O(1)
     */
    ImmutableDirectedGraph() {
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int size() {
        return 0;
    }

    /**
     * {@inheritDoc}
     *
     * @param v {@inheritDoc}
     * @return {@inheritDoc}
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    @Override
    public Set<Integer> getOutEdges(int v) {
        throw new IndexOutOfBoundsException();
    }

    /**
     * {@inheritDoc}
     *
     * @param v {@inheritDoc}
     * @return {@inheritDoc}
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    @Override
    public Set<Integer> getInEdges(int v) {
        throw new IndexOutOfBoundsException();
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public ImmutableDirectedGraph asDirected() {
        return this;
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public final ImmutableWeightedDirectedGraph asWeightedDirected() {
        return new ImmutableWeightedDirectedGraph() {
            @Override
            public int size() {
                return ImmutableDirectedGraph.this.size();
            }

            @Override
            public Set<Integer> getOutEdges(int v) {
                return ImmutableDirectedGraph.this.getOutEdges(v);
            }

            @Override
            public Set<Integer> getInEdges(int v) {
                return ImmutableDirectedGraph.this.getInEdges(v);
            }

            @Override
            public double getEdgeWeight(int source, int target) {
                Graphs.checkVertex(this, target);
                if (!getOutEdges(source).contains(target)) {
                    throw new IllegalArgumentException();
                }
                return 1;
            }
        };
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public final String toString() {
        return Graphs.toString(this);
    }

    /**
     * {@inheritDoc}
     *
     * @param obj {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ImmutableDirectedGraph)) {
            return false;
        }
        final ImmutableDirectedGraph that = (ImmutableDirectedGraph) obj;
        return Graphs.equals(this, that);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public final int hashCode() {
        return Graphs.hashCode(this);
    }
}
