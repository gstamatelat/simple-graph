package gr.james.simplegraph;

import java.io.Serializable;
import java.util.Set;

/**
 * Represents an undirected and weighted graph implemented using adjacency lists.
 * <p>
 * The graph allows self loops but does not allow more than one edge from any set of endpoints. The graph only allows
 * finite {@link Double} values as edge weights.
 * <p>
 * Memory Complexity: O(V+E)
 */
public class MutableWeightedGraph implements Serializable {
    private static final long serialVersionUID = 1L;

    private final MutableWeightedDirectedGraph g;

    /**
     * Construct a new empty {@link MutableWeightedGraph} without any vertices.
     * <p>
     * Complexity: O(1)
     */
    public MutableWeightedGraph() {
        this.g = new MutableWeightedDirectedGraph();
    }

    /**
     * Construct a new empty {@link MutableWeightedGraph} with {@code n} vertices.
     * <p>
     * Complexity: O(n)
     *
     * @param n the amount of vertices to put in the graph
     * @throws IllegalArgumentException if {@code n < 0}
     */
    public MutableWeightedGraph(int n) {
        this.g = new MutableWeightedDirectedGraph(n);
    }

    /**
     * Construct a new {@link MutableWeightedGraph} as a copy of the given graph {@code g}.
     * <p>
     * Complexity: O(V+E)
     *
     * @param g the graph to copy
     */
    public MutableWeightedGraph(MutableWeightedGraph g) {
        this(g.size());
        for (int v = 0; v < g.size(); v++) {
            for (int w : g.getEdges(v)) {
                putEdge(v, w, g.getEdgeWeight(v, w));
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
     * Add or update an edge to this graph.
     * <p>
     * Complexity: O(1)
     *
     * @param v      one end of the edge
     * @param w      the other end of the edge
     * @param weight the weight of the edge
     * @return the previous weight of the edge connecting {@code v} and {@code w} or {@code null} if there was
     * previously no edge
     * @throws IllegalArgumentException  if {@code weight} is not finite
     * @throws IndexOutOfBoundsException if {@code v} or {@code w} are outside of {@code [O,V)}
     */
    public Double putEdge(int v, int w, double weight) {
        final Double previousWeight = this.g.putEdge(v, w, weight);
        this.g.putEdge(w, v, weight);
        return previousWeight;
    }

    /**
     * Remove an edge from this graph.
     * <p>
     * Complexity: O(1)
     *
     * @param v one end of the edge
     * @param w the other end of the edge
     * @return the previous weight of the edge connecting {@code v} and {@code w} or {@code null} if there was
     * previously no edge
     * @throws IndexOutOfBoundsException if {@code v} or {@code w} are outside of {@code [O,V)}
     */
    public Double removeEdge(int v, int w) {
        this.g.removeEdge(v, w);
        return this.g.removeEdge(w, v);
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
        return this.g.getEdgeWeight(v, w);
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
                    sb.append(String.format("  %d -- %d [%.2f]%n", i, adj, getEdgeWeight(i, adj)));
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
        final MutableWeightedGraph that = (MutableWeightedGraph) obj;
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
