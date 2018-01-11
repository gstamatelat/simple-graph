package gr.james.simplegraph;

import java.util.Set;

/**
 * Represents an immutable undirected and unweighted graph implemented using adjacency lists.
 * <p>
 * The graph can contain self loops but cannot contain more than one edge from any set of endpoints.
 * <p>
 * Memory Complexity: O(V+E)
 */
public class Graph {
    private final MutableGraph g;

    /**
     * Construct a new {@link Graph} from the given graph {@code g}.
     * <p>
     * Complexity: O(V+E)
     *
     * @param g the underlying mutable graph
     */
    public Graph(MutableGraph g) {
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
     *
     * @return a string representation of the graph
     */
    @Override
    public String toString() {
        return g.toString();
    }
}
