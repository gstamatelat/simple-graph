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
public class Graph implements Serializable {
    private static final long serialVersionUID = 1L;

    private final MutableGraph g;

    private Graph(MutableGraph g, boolean copy) {
        if (copy) {
            this.g = new MutableGraph(g);
        } else {
            this.g = g;
        }
    }

    /**
     * Construct a new {@link Graph} from a copy of the given graph {@code g}.
     * <p>
     * Complexity: O(V+E)
     *
     * @param g the mutable graph to copy
     */
    public Graph(MutableGraph g) {
        this(g, true);
    }

    /**
     * Decorate a {@link MutableGraph} as a {@link Graph} and return it.
     * <p>
     * Changes on the input graph will reflect on the instance returned by this method.
     * <p>
     * Complexity: O(1)
     *
     * @param g the underlying mutable graph
     * @return a {@link Graph} that wraps {@code g}
     */
    public static Graph decorate(MutableGraph g) {
        return new Graph(g, false);
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
}
