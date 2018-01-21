package gr.james.simplegraph;

import java.io.Serializable;
import java.util.Set;

/**
 * Represents an immutable undirected and unweighted graph implemented using adjacency lists.
 * <p>
 * The graph can contain self loops but cannot contain more than one edge from any set of endpoints.
 * <p>
 * Memory Complexity: O(V+E)
 */
public abstract class Graph implements Serializable, IGraph {
    private static final long serialVersionUID = 1L;

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public abstract int size();

    /**
     * {@inheritDoc}
     *
     * @param v {@inheritDoc}
     * @return {@inheritDoc}
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    @Override
    public abstract Set<Integer> getEdges(int v);

    /**
     * Return a {@link WeightedGraph} wrapper of this graph.
     * <p>
     * The method {@link WeightedGraph#getEdgeWeight(int, int)} will always return {@code 1.0} (or throw exception).
     * <p>
     * Complexity: O(1)
     *
     * @return a {@link WeightedGraph} wrapper of this graph
     */
    public WeightedGraph toWeighted() {
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
                if (w < 0 || w >= size()) {
                    throw new IndexOutOfBoundsException();
                }
                if (!getEdges(v).contains(w)) {
                    throw new IllegalArgumentException();
                }
                return 1;
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
                if (target < 0 || target >= size()) {
                    throw new IndexOutOfBoundsException();
                }
                if (!getEdges(source).contains(target)) {
                    throw new IllegalArgumentException();
                }
                return 1;
            }
        };
    }

    /**
     * Returns a string representation of the graph.
     * <p>
     * Complexity: O(V+E)
     *
     * @return a string representation of the graph
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(String.format("%s(%d) {%n", "Graph", size()));
        for (int i = 0; i < size(); i++) {
            for (int adj : getEdges(i)) {
                if (adj >= i) {
                    sb.append(String.format("  %d -- %d%n", i, adj));
                }
            }
        }
        sb.append("}");
        return sb.toString();
    }

    /**
     * Indicates whether some other object is equal to this graph.
     * <p>
     * Two graphs are equal if they are of same type, have the same number of vertices and their edges represent the
     * same mapping.
     * <p>
     * Complexity: O(V+E)
     *
     * @param obj the reference object with which to compare
     * @return {@code true} if this graph is equal to the {@code obj} argument, otherwise {@code false}
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof Graph)) {
            return false;
        }
        final Graph that = (Graph) obj;
        if (size() != that.size()) {
            return false;
        }
        for (int i = 0; i < size(); i++) {
            if (!getEdges(i).equals(that.getEdges(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns a hash code value for this graph.
     * <p>
     * Complexity: O(V+E)
     *
     * @return a hash code value for this graph
     */
    @Override
    public int hashCode() {
        int hash = 0;
        for (int i = 0; i < size(); i++) {
            for (int j : getEdges(i)) {
                hash += j;
            }
        }
        return hash;
    }
}
