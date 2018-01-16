package gr.james.simplegraph;

import java.io.Serializable;
import java.util.Set;

/**
 * Represents an undirected and unweighted graph implemented using adjacency lists.
 * <p>
 * The graph allows self loops but does not allow more than one edge from any set of endpoints.
 * <p>
 * Memory Complexity: O(V+E)
 */
public class MutableGraph implements Serializable {
    private static final long serialVersionUID = 1L;

    private final MutableWeightedDirectedGraph g;

    /**
     * Construct a new empty {@link MutableGraph} without any vertices.
     * <p>
     * Complexity: O(1)
     */
    public MutableGraph() {
        this.g = new MutableWeightedDirectedGraph();
    }

    /**
     * Construct a new empty {@link MutableGraph} with {@code n} vertices.
     * <p>
     * Complexity: O(n)
     *
     * @param n the amount of vertices to put in the graph
     * @throws IllegalArgumentException if {@code n < 0}
     */
    public MutableGraph(int n) {
        this.g = new MutableWeightedDirectedGraph(n);
    }

    /**
     * Construct a new {@link MutableGraph} as a copy of the given graph {@code g}.
     * <p>
     * Complexity: O(V+E)
     *
     * @param g the graph to copy
     */
    public MutableGraph(MutableGraph g) {
        this(g.size());
        for (int v = 0; v < g.size(); v++) {
            for (int w : g.getEdges(v)) {
                putEdge(v, w);
            }
        }
        assert this.equals(g);
    }

    /**
     * Get the number of vertices in the graph.
     * <p>
     * Complexity: O(1)
     *
     * @return how many vertices there are in the graph
     */
    public int size() {
        return this.g.size();
    }

    /**
     * Add a vertex to the graph.
     * <p>
     * Complexity: O(1)
     */
    public void addVertex() {
        this.g.addVertex();
    }

    /**
     * Add many vertices to the graph.
     * <p>
     * Complexity: O(n)
     *
     * @param n how many vertices to add
     * @throws IllegalArgumentException if {@code n < 0}
     */
    public void addVertices(int n) {
        this.g.addVertices(n);
    }

    /**
     * Remove a vertex along with all of its edges from the graph.
     * <p>
     * Complexity: O(V+E)
     *
     * @param v the vertex to remove from the graph
     * @throws IndexOutOfBoundsException if {@code v} is outside of {@code [O,V)}
     */
    public void removeVertex(int v) {
        this.g.removeVertex(v);
    }

    /**
     * Add an edge to this graph.
     * <p>
     * Complexity: O(1)
     *
     * @param v one end of the edge
     * @param w the other end of the edge
     * @return {@code true} if there was previously no edge connecting {@code v} with {@code w}, otherwise {@code false}
     * @throws IndexOutOfBoundsException if {@code v} or {@code w} are outside of {@code [O,V)}
     */
    public boolean putEdge(int v, int w) {
        if (this.g.putEdge(v, w, 1.0) == null) {
            this.g.putEdge(w, v, 1.0);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Remove an edge from this graph.
     * <p>
     * Complexity: O(1)
     *
     * @param v one end of the edge
     * @param w the other end of the edge
     * @return {@code true} if there was previously an edge connecting {@code v} with {@code w}, otherwise {@code false}
     * @throws IndexOutOfBoundsException if {@code v} or {@code w} are outside of {@code [O,V)}
     */
    public boolean removeEdge(int v, int w) {
        if (this.g.removeEdge(v, w) != null) {
            this.g.removeEdge(w, v);
            return true;
        } else {
            return false;
        }
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
        return this.g.getOutEdges(v);
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
        sb.append(String.format("{%n"));
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
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final MutableGraph that = (MutableGraph) obj;
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
