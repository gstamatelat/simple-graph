package gr.james.simplegraph;

import java.io.Serializable;
import java.util.Set;

/**
 * Represents an immutable unweighted and undirected graph implemented using adjacency lists.
 * <p>
 * The graph can contain self loops but cannot contain more than one edge from any set of endpoints.
 * <p>
 * Memory Complexity: O(V+E)
 */
public class Graph implements IGraph, Serializable {
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
     * Returns a {@link WeightedGraph} wrapper of this graph.
     * <p>
     * The method {@link WeightedGraph#getEdgeWeight(int, int)} will always return {@code 1.0} (or throw exception).
     * <p>
     * Complexity: O(1)
     *
     * @return a {@link WeightedGraph} wrapper of this graph
     */
    public final WeightedGraph toWeighted() {
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
                Graphs.checkVertex(this, w);
                if (!getEdges(v).contains(w)) {
                    throw new IllegalArgumentException();
                }
                return 1;
            }
        };
    }

    /**
     * Returns a {@link DirectedGraph} wrapper of this graph.
     * <p>
     * Complexity: O(1)
     *
     * @return a {@link DirectedGraph} wrapper of this graph
     */
    public final DirectedGraph toDirected() {
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
     * Returns a {@link WeightedDirectedGraph} wrapper of this graph.
     * <p>
     * The method {@link WeightedDirectedGraph#getEdgeWeight(int, int)} will always return {@code 1.0} (or throw
     * exception).
     * <p>
     * Complexity: O(1)
     *
     * @return a {@link WeightedDirectedGraph} wrapper of this graph
     */
    public final WeightedDirectedGraph toWeightedDirected() {
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
        if (obj == null || !(obj instanceof Graph)) {
            return false;
        }
        final Graph that = (Graph) obj;
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
