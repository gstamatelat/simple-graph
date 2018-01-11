package gr.james.simplegraph;

import java.util.Set;

/**
 * Represents an immutable undirected and weighted graph implemented using adjacency lists.
 * <p>
 * The graph can contain self loops but cannot contain more than one edge from any set of endpoints. The edge weights
 * can only be finite {@link Double} values.
 * <p>
 * Memory Complexity: O(V+E)
 */
public class WeightedGraph {
    private final MutableWeightedGraph g;

    /**
     * Construct a new {@link WeightedGraph} from the given graph {@code g}.
     * <p>
     * Complexity: O(V+E)
     *
     * @param g the underlying mutable graph
     */
    public WeightedGraph(MutableWeightedGraph g) {
        this.g = new MutableWeightedGraph(g);
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
}
