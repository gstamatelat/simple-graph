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
public class DirectedWeightedGraph implements Serializable {
    private static final long serialVersionUID = 1L;

    private final MutableWeightedDirectedGraph g;

    private DirectedWeightedGraph(MutableWeightedDirectedGraph g, boolean copy) {
        if (copy) {
            this.g = new MutableWeightedDirectedGraph(g);
        } else {
            this.g = g;
        }
    }

    /**
     * Construct a new {@link DirectedWeightedGraph} from a copy of the given graph {@code g}.
     * <p>
     * Complexity: O(V+E)
     *
     * @param g the mutable graph to copy
     */
    public DirectedWeightedGraph(MutableWeightedDirectedGraph g) {
        this(g, true);
    }

    /**
     * Decorate a {@link MutableWeightedDirectedGraph} as a {@link DirectedWeightedGraph} and return it.
     * <p>
     * Changes on the input graph will reflect on the instance returned by this method.
     * <p>
     * Complexity: O(1)
     *
     * @param g the underlying mutable graph
     * @return a {@link DirectedWeightedGraph} that wraps {@code g}
     */
    public static DirectedWeightedGraph decorate(MutableWeightedDirectedGraph g) {
        return new DirectedWeightedGraph(g, false);
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
}
