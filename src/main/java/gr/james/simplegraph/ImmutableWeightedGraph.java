package gr.james.simplegraph;

import java.util.Set;

/**
 * Represents an immutable, weighted and undirected graph implemented using adjacency lists.
 * <p>
 * The graph can contain self loops but cannot cannot contain parallel edges. More formally, any unordered pair of
 * endpoints may correspond to at most one edge. The edge weights can only be finite {@link Double} values.
 * <p>
 * An unordered pair {@code {a, b}} is a pair of objects with no particular relation between them; the order in which
 * the objects appear in the pair is not significant.
 * <p>
 * Memory Complexity: O(V+E)
 */
public class ImmutableWeightedGraph implements WeightedGraph {
    private static final long serialVersionUID = 1L;

    /**
     * Construct a new empty {@link ImmutableWeightedGraph}.
     * <p>
     * Complexity: O(1)
     */
    ImmutableWeightedGraph() {
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
    public Set<Integer> getEdges(int v) {
        throw new IndexOutOfBoundsException();
    }

    /**
     * {@inheritDoc}
     *
     * @param v {@inheritDoc}
     * @param w {@inheritDoc}
     * @return {@inheritDoc}
     * @throws IndexOutOfBoundsException {@inheritDoc}
     * @throws IllegalArgumentException  {@inheritDoc}
     */
    @Override
    public double getEdgeWeight(int v, int w) {
        throw new IndexOutOfBoundsException();
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public ImmutableWeightedGraph asWeighted() {
        return this;
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public final ImmutableGraph asGraph() {
        return new ImmutableGraph() {
            @Override
            public int size() {
                return ImmutableWeightedGraph.this.size();
            }

            @Override
            public Set<Integer> getEdges(int v) {
                return ImmutableWeightedGraph.this.getEdges(v);
            }
        };
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public final ImmutableDirectedGraph asDirected() {
        return new ImmutableDirectedGraph() {
            @Override
            public int size() {
                return ImmutableWeightedGraph.this.size();
            }

            @Override
            public Set<Integer> getOutEdges(int v) {
                return ImmutableWeightedGraph.this.getEdges(v);
            }

            @Override
            public Set<Integer> getInEdges(int v) {
                return ImmutableWeightedGraph.this.getEdges(v);
            }
        };
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
                return ImmutableWeightedGraph.this.size();
            }

            @Override
            public Set<Integer> getOutEdges(int v) {
                return ImmutableWeightedGraph.this.getEdges(v);
            }

            @Override
            public Set<Integer> getInEdges(int v) {
                return ImmutableWeightedGraph.this.getEdges(v);
            }

            @Override
            public double getEdgeWeight(int source, int target) {
                return ImmutableWeightedGraph.this.getEdgeWeight(source, target);
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
        return GraphsInternal.toString(this);
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
        if (obj == null || !(obj instanceof ImmutableWeightedGraph)) {
            return false;
        }
        final ImmutableWeightedGraph that = (ImmutableWeightedGraph) obj;
        return GraphsInternal.equals(this, that);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public final int hashCode() {
        return GraphsInternal.hashCode(this);
    }
}
