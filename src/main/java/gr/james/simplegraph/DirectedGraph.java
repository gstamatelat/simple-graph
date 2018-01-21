package gr.james.simplegraph;

import java.io.Serializable;
import java.util.Set;

/**
 * Represents an immutable directed and unweighted graph implemented using adjacency lists.
 * <p>
 * The graph can contain self loops but cannot contain more than one edge from any set of endpoints.
 * <p>
 * Memory Complexity: O(V+E)
 */
public abstract class DirectedGraph implements IDirectedGraph, Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * Package-private constructor.
     */
    DirectedGraph() {
    }

    /**
     * Return a {@link WeightedDirectedGraph} wrapper of this graph.
     * <p>
     * The method {@link WeightedDirectedGraph#getEdgeWeight(int, int)} will always return {@code 1.0} (or throw
     * exception).
     * <p>
     * Complexity: O(1)
     *
     * @return a {@link WeightedDirectedGraph} wrapper of this graph
     */
    public WeightedDirectedGraph toWeightedDirected() {
        return new WeightedDirectedGraph() {
            @Override
            public int size() {
                return DirectedGraph.this.size();
            }

            @Override
            public Set<Integer> getOutEdges(int v) {
                return DirectedGraph.this.getOutEdges(v);
            }

            @Override
            public Set<Integer> getInEdges(int v) {
                return DirectedGraph.this.getInEdges(v);
            }

            @Override
            public double getEdgeWeight(int source, int target) {
                if (target < 0 || target >= size()) {
                    throw new IndexOutOfBoundsException();
                }
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
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(String.format("%s(%d) {%n", "DirectedGraph", size()));
        for (int i = 0; i < size(); i++) {
            for (int adj : getOutEdges(i)) {
                sb.append(String.format("  %d -> %d%n", i, adj));
            }
        }
        sb.append("}");
        return sb.toString();
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
        if (obj == null || !(obj instanceof DirectedGraph)) {
            return false;
        }
        final DirectedGraph that = (DirectedGraph) obj;
        if (size() != that.size()) {
            return false;
        }
        for (int i = 0; i < size(); i++) {
            if (!getOutEdges(i).equals(that.getOutEdges(i))) {
                return false;
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
        int hash = 0;
        for (int i = 0; i < size(); i++) {
            for (int j : getOutEdges(i)) {
                hash += j;
            }
        }
        return hash;
    }
}
