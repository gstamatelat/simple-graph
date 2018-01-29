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
public class Graph implements IGraph {
    private static final long serialVersionUID = 1L;

    /**
     * Construct a new empty {@link Graph}.
     * <p>
     * Complexity: O(1)
     */
    Graph() {
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
    public Graph asGraph() {
        return this;
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public final WeightedGraph asWeighted() {
        return new WeightedGraph() {
            @Override
            public int size() {
                return Graph.this.size();
            }

            @Override
            public Set<Integer> getEdges(int v) {
                return Graph.this.getEdges(v);
            }

            @Override
            public double getEdgeWeight(int v, int w) {
                GraphsInternal.checkVertex(this, w);
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
    public final DirectedGraph asDirected() {
        return new DirectedGraph() {
            @Override
            public int size() {
                return Graph.this.size();
            }

            @Override
            public Set<Integer> getOutEdges(int v) {
                return Graph.this.getEdges(v);
            }

            @Override
            public Set<Integer> getInEdges(int v) {
                return Graph.this.getEdges(v);
            }
        };
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public final WeightedDirectedGraph asWeightedDirected() {
        return new WeightedDirectedGraph() {
            @Override
            public int size() {
                return Graph.this.size();
            }

            @Override
            public Set<Integer> getOutEdges(int v) {
                return Graph.this.getEdges(v);
            }

            @Override
            public Set<Integer> getInEdges(int v) {
                return Graph.this.getEdges(v);
            }

            @Override
            public double getEdgeWeight(int source, int target) {
                GraphsInternal.checkVertex(this, target);
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
        if (obj == null || !(obj instanceof Graph)) {
            return false;
        }
        final Graph that = (Graph) obj;
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
