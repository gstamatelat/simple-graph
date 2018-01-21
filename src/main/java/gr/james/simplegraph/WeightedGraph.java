package gr.james.simplegraph;

import java.io.Serializable;
import java.util.Set;

/**
 * Represents an immutable undirected and weighted graph implemented using adjacency lists.
 * <p>
 * The graph can contain self loops but cannot contain more than one edge from any set of endpoints. The edge weights
 * can only be finite {@link Double} values.
 * <p>
 * Memory Complexity: O(V+E)
 */
public abstract class WeightedGraph implements IWeightedGraph, Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * Package-private constructor.
     */
    WeightedGraph() {
    }

    /**
     * Return a {@link Graph} wrapper of this graph.
     * <p>
     * Complexity: O(1)
     *
     * @return a {@link Graph} wrapper of this graph
     */
    public Graph toGraph() {
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
     * Return a {@link WeightedDirectedGraph} wrapper of this graph.
     * <p>
     * Complexity: O(1)
     *
     * @return a {@link WeightedDirectedGraph} wrapper of this graph
     */
    public WeightedDirectedGraph toWeightedDirected() {
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
        if (obj == null || !(obj instanceof WeightedGraph)) {
            return false;
        }
        final WeightedGraph that = (WeightedGraph) obj;
        if (size() != that.size()) {
            return false;
        }
        for (int i = 0; i < size(); i++) {
            if (!getEdges(i).equals(that.getEdges(i))) {
                return false;
            }
            for (int j : getEdges(i)) {
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
