package gr.james.simplegraph;

import java.util.Set;

/**
 * Represents an immutable weighted and directed graph implemented using adjacency lists.
 * <p>
 * The graph can contain self loops but cannot contain more than one edge from any set of endpoints. The edge weights
 * can only be finite {@link Double} values.
 * <p>
 * Memory Complexity: O(V+E)
 */
public class WeightedDirectedGraph implements IWeightedDirectedGraph {
    private static final long serialVersionUID = 1L;

    /**
     * Construct a new empty {@link WeightedDirectedGraph}.
     * <p>
     * Complexity: O(1)
     */
    WeightedDirectedGraph() {
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
     * @param source {@inheritDoc}
     * @param target {@inheritDoc}
     * @return {@inheritDoc}
     * @throws IndexOutOfBoundsException {@inheritDoc}
     * @throws IllegalArgumentException  {@inheritDoc}
     */
    @Override
    public double getEdgeWeight(int source, int target) {
        throw new IndexOutOfBoundsException();
    }

    /**
     * Returns a {@link DirectedGraph} wrapper of this graph.
     * <p>
     * Complexity: O(1)
     *
     * @return a {@link DirectedGraph} wrapper of this graph
     */
    public final DirectedGraph asDirected() {
        return new DirectedGraph() {
            @Override
            public int size() {
                return WeightedDirectedGraph.this.size();
            }

            @Override
            public Set<Integer> getOutEdges(int v) {
                return WeightedDirectedGraph.this.getOutEdges(v);
            }

            @Override
            public Set<Integer> getInEdges(int v) {
                return WeightedDirectedGraph.this.getInEdges(v);
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
        if (obj == null || !(obj instanceof WeightedDirectedGraph)) {
            return false;
        }
        final WeightedDirectedGraph that = (WeightedDirectedGraph) obj;
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
