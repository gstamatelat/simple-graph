package gr.james.simplegraph;

import java.util.Set;

/**
 * Represents an immutable, unweighted and undirected graph implemented using adjacency lists.
 * <p>
 * The graph can contain self loops but cannot cannot contain parallel edges. More formally, any unordered pair of
 * endpoints may correspond to at most one edge.
 * <p>
 * An unordered pair {@code {a, b}} is a pair of objects with no particular relation between them; the order in which
 * the objects appear in the pair is not significant.
 * <p>
 * Memory Complexity: O(V+E)
 */
public class ImmutableGraph implements Graph {
    private static final long serialVersionUID = 1L;

    /**
     * Construct a new empty {@link ImmutableGraph}.
     * <p>
     * Complexity: O(1)
     */
    ImmutableGraph() {
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
     * @return {@inheritDoc}
     */
    @Override
    public ImmutableGraph asGraph() {
        return this;
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public final ImmutableWeightedGraph asWeighted() {
        return new ImmutableWeightedGraph() {
            @Override
            public int size() {
                return ImmutableGraph.this.size();
            }

            @Override
            public Set<Integer> getEdges(int v) {
                return ImmutableGraph.this.getEdges(v);
            }

            @Override
            public double getEdgeWeight(int v, int w) {
                Graphs.checkVertex(this, w);
                if (!getEdges(v).contains(w)) {
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
    public final ImmutableDirectedGraph asDirected() {
        return new ImmutableDirectedGraph() {
            @Override
            public int size() {
                return ImmutableGraph.this.size();
            }

            @Override
            public Set<Integer> getOutEdges(int v) {
                return ImmutableGraph.this.getEdges(v);
            }

            @Override
            public Set<Integer> getInEdges(int v) {
                return ImmutableGraph.this.getEdges(v);
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
                return ImmutableGraph.this.size();
            }

            @Override
            public Set<Integer> getOutEdges(int v) {
                return ImmutableGraph.this.getEdges(v);
            }

            @Override
            public Set<Integer> getInEdges(int v) {
                return ImmutableGraph.this.getEdges(v);
            }

            @Override
            public double getEdgeWeight(int source, int target) {
                Graphs.checkVertex(this, target);
                if (!getEdges(source).contains(target)) {
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
        if (obj == null || !(obj instanceof ImmutableGraph)) {
            return false;
        }
        final ImmutableGraph that = (ImmutableGraph) obj;
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
