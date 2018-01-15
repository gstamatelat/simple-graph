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
public class DirectedGraph implements Serializable {
    private static final long serialVersionUID = 1L;

    private final MutableDirectedGraph g;

    private DirectedGraph(MutableDirectedGraph g, boolean copy) {
        if (copy) {
            this.g = new MutableDirectedGraph(g);
        } else {
            this.g = g;
        }
        assert this.g.equals(g);
    }

    /**
     * Construct a new {@link DirectedGraph} from a copy of the given graph {@code g}.
     * <p>
     * Complexity: O(V+E)
     *
     * @param g the mutable graph to copy
     */
    public DirectedGraph(MutableDirectedGraph g) {
        this(g, true);
    }

    /**
     * Decorate a {@link MutableDirectedGraph} as a {@link DirectedGraph} and return it.
     * <p>
     * Changes on the input graph will reflect on the instance returned by this method.
     * <p>
     * Complexity: O(1)
     *
     * @param g the underlying mutable graph
     * @return a {@link DirectedGraph} that wraps {@code g}
     */
    public static DirectedGraph decorate(MutableDirectedGraph g) {
        return new DirectedGraph(g, false);
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
}
