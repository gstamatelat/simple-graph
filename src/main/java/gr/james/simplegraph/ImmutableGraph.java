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
public class ImmutableGraph implements Graph, Serializable {
    private static final long serialVersionUID = 1L;

    private final MutableGraph g;

    /**
     * Construct a new {@link ImmutableGraph} as a copy of the given graph {@code g}.
     * <p>
     * Complexity: O(V+E)
     *
     * @param g the graph to copy
     * @throws NullPointerException if {code g} is {@code null}
     */
    public ImmutableGraph(MutableGraph g) {
        this.g = new MutableGraph(g);
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
     * Get the edges of a vertex.
     * <p>
     * Complexity: O(1)
     *
     * @param v the vertex index to get the edges of
     * @return an {@link Set} that holds all the adjacent vertices of {@code v}
     * @throws IndexOutOfBoundsException if {@code v} is outside of {@code [O,V)}
     */
    public Set<Integer> getEdges(int v) {
        return g.getEdges(v);
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
        final ImmutableGraph that = (ImmutableGraph) obj;
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
