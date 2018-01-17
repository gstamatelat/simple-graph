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
public class ImmutableWeightedGraph extends AbstractWeightedGraph implements Serializable {
    private static final long serialVersionUID = 1L;

    private final MutableWeightedGraph g;

    /**
     * Construct a new {@link ImmutableWeightedGraph} as a copy of the given graph {@code g}.
     * <p>
     * Complexity: O(V+E)
     *
     * @param g the graph to copy
     * @throws NullPointerException if {code g} is {@code null}
     */
    public ImmutableWeightedGraph(MutableWeightedGraph g) {
        this.g = new MutableWeightedGraph(g);
    }

    /**
     * Get the number of vertices in the graph.
     * <p>
     * Complexity: O(1)
     *
     * @return how many vertices there are in the graph
     */
    @Override
    public int size() {
        return g.size();
    }

    /**
     * Get the edges of a vertex.
     * <p>
     * Complexity: O(1)
     *
     * @param v the vertex index to get the edges of
     * @return an {@link Set} that holds all the adjacent vertices of {@code v}
     * @throws IndexOutOfBoundsException if {@code v} is outside of {@code [O,V)}
     */
    @Override
    public Set<Integer> getEdges(int v) {
        return g.getEdges(v);
    }

    /**
     * Get the weight of the edge connecting {@code v} and {@code w}.
     * <p>
     * Complexity: O(1)
     *
     * @param v one end of the edge
     * @param w the other end of the edge
     * @return the weight of the edge connecting {@code v} and {@code w}
     * @throws IndexOutOfBoundsException if {@code v} or {@code w} are outside of {@code [O,V)}
     * @throws IllegalArgumentException  if there is no edge connecting {@code v} and {@code w}
     */
    @Override
    public double getEdgeWeight(int v, int w) {
        return g.getEdgeWeight(v, w);
    }

    /**
     * Returns a string representation of the graph.
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
        final ImmutableWeightedGraph that = (ImmutableWeightedGraph) obj;
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
