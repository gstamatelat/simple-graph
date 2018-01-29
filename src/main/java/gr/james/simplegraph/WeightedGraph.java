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
public class WeightedGraph implements IWeightedGraph {
    private static final long serialVersionUID = 1L;

    /**
     * Construct a new empty {@link WeightedGraph}.
     * <p>
     * Complexity: O(1)
     */
    WeightedGraph() {
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
    public WeightedGraph asWeighted() {
        return this;
    }

    /**
     * {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public final Graph asGraph() {
        return new Graph() {
            @Override
            public int size() {
                return WeightedGraph.this.size();
            }

            @Override
            public Set<Integer> getEdges(int v) {
                return WeightedGraph.this.getEdges(v);
            }
        };
    }

    /**
     * {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public final DirectedGraph asDirected() {
        return new DirectedGraph() {
            @Override
            public int size() {
                return WeightedGraph.this.size();
            }

            @Override
            public Set<Integer> getOutEdges(int v) {
                return WeightedGraph.this.getEdges(v);
            }

            @Override
            public Set<Integer> getInEdges(int v) {
                return WeightedGraph.this.getEdges(v);
            }
        };
    }

    /**
     * {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public final WeightedDirectedGraph asWeightedDirected() {
        return new WeightedDirectedGraph() {
            @Override
            public int size() {
                return WeightedGraph.this.size();
            }

            @Override
            public Set<Integer> getOutEdges(int v) {
                return WeightedGraph.this.getEdges(v);
            }

            @Override
            public Set<Integer> getInEdges(int v) {
                return WeightedGraph.this.getEdges(v);
            }

            @Override
            public double getEdgeWeight(int source, int target) {
                return WeightedGraph.this.getEdgeWeight(source, target);
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
        if (obj == null || !(obj instanceof WeightedGraph)) {
            return false;
        }
        final WeightedGraph that = (WeightedGraph) obj;
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
