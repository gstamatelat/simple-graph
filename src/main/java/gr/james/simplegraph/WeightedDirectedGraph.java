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
public class WeightedDirectedGraph implements Serializable {
    private static final long serialVersionUID = 1L;

    private final MutableWeightedDirectedGraph g;

    private WeightedDirectedGraph(MutableWeightedDirectedGraph g, boolean copy) {
        if (copy) {
            this.g = new MutableWeightedDirectedGraph(g);
        } else {
            this.g = g;
        }
        assert this.g.equals(g);
    }

    /**
     * Construct a new {@link WeightedDirectedGraph} as a copy of the given graph {@code g}.
     * <p>
     * Complexity: O(V+E)
     *
     * @param g the mutable graph to copy
     */
    public WeightedDirectedGraph(MutableWeightedDirectedGraph g) {
        this(g, true);
    }

    /**
     * Decorate a {@link MutableWeightedDirectedGraph} as a {@link WeightedDirectedGraph} and return it.
     * <p>
     * Changes on the input graph will reflect on the instance returned by this method.
     * <p>
     * Complexity: O(1)
     *
     * @param g the underlying mutable graph
     * @return a {@link WeightedDirectedGraph} that wraps {@code g}
     */
    public static WeightedDirectedGraph decorate(MutableWeightedDirectedGraph g) {
        return new WeightedDirectedGraph(g, false);
    }

    /**
     * Get the number of vertices in the graph.
     * <p>
     * Complexity: O(1)
     *
     * @return how many vertices there are in the graph
     */
    public int size() {
        return g.size();
    }

    /**
     * Get the outbound edges of a vertex.
     * <p>
     * Complexity: O(1)
     *
     * @param v the vertex index to get the outbound edges of
     * @return an {@link Set} that holds all the outbound adjacent vertices of {@code v}
     * @throws IndexOutOfBoundsException if {@code v} is outside of {@code [O,V)}
     */
    public Set<Integer> getOutEdges(int v) {
        return g.getOutEdges(v);
    }

    /**
     * Get the inbound edges of a vertex.
     * <p>
     * Complexity: O(1)
     *
     * @param v the vertex index to get the inbound edges of
     * @return an {@link Set} that holds all the inbound adjacent vertices of {@code v}
     * @throws IndexOutOfBoundsException if {@code v} is outside of {@code [O,V)}
     */
    public Set<Integer> getInEdges(int v) {
        return g.getInEdges(v);
    }

    /**
     * Get the weight of the edge from {@code source} to {@code target}.
     * <p>
     * Complexity: O(1)
     *
     * @param source the source of the edge
     * @param target the target of the edge
     * @return the weight of the edge from {@code source} to {@code target}
     * @throws IndexOutOfBoundsException if {@code source} or {@code target} are outside of {@code [O,V)}
     * @throws IllegalArgumentException  if there is no edge from {@code source} to {@code target}
     */
    public double getEdgeWeight(int source, int target) {
        return g.getEdgeWeight(source, target);
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
        return g.toString();
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
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final WeightedDirectedGraph that = (WeightedDirectedGraph) obj;
        return g.equals(that.g);
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
        return g.hashCode();
    }
}
