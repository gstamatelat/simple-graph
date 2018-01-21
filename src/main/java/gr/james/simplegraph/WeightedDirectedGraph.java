package gr.james.simplegraph;

import java.io.Serializable;
import java.util.Set;

/**
 * Represents an immutable directed and weighted graph implemented using adjacency lists.
 * <p>
 * The graph can contain self loops but cannot contain more than one edge from any set of endpoints. The edge weights
 * can only be finite {@link Double} values.
 * <p>
 * Memory Complexity: O(V+E)
 */
public abstract class WeightedDirectedGraph implements IWeightedDirectedGraph, Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * Package-private constructor.
     */
    WeightedDirectedGraph() {
    }

    /**
     * Return a {@link DirectedGraph} wrapper of this graph.
     * <p>
     * Complexity: O(1)
     *
     * @return a {@link DirectedGraph} wrapper of this graph
     */
    public DirectedGraph toDirected() {
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
    public String toString() {
        return Graphs.toString(this);
    }

    /**
     * {@inheritDoc}
     *
     * @param obj {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof WeightedDirectedGraph)) {
            return false;
        }
        final WeightedDirectedGraph that = (WeightedDirectedGraph) obj;
        if (size() != that.size()) {
            return false;
        }
        for (int i = 0; i < size(); i++) {
            if (!getOutEdges(i).equals(that.getOutEdges(i))) {
                return false;
            }
            for (int j : getOutEdges(i)) {
                if (getEdgeWeight(i, j) != that.getEdgeWeight(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Graphs.hashCode(this);
    }
}
