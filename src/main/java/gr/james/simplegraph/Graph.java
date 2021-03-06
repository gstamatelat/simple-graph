package gr.james.simplegraph;

import java.util.Iterator;
import java.util.Set;

/**
 * Represents an unweighted and undirected graph.
 * <p>
 * The graph can contain self loops but cannot contain parallel edges. More formally, any unordered pair of endpoints
 * may correspond to at most one edge.
 * <p>
 * An unordered pair {@code {a,b}} is a pair of objects with no particular relation between them; the order in which the
 * objects appear in the pair is not significant.
 * <p>
 * Memory Complexity: O(V+E)
 */
public interface Graph extends BaseGraph {
    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    int size();

    /**
     * Get the adjacent vertices of a vertex.
     * <p>
     * More formally, returns an unmodifiable view of all vertices in this graph adjacent to {@code v}. The vertices
     * returned are in no particular order inside the {@link Set}.
     * <p>
     * You can use the result of this method in a for-each loop like so:
     * <pre><code>
     * for (int v : g.adjacent(X)) {
     *     // Do something with v
     * }
     * </code></pre>
     * <p>
     * You can also use the classic iterator approach:
     * <pre><code>
     * Iterator&lt;Integer&gt; it = g.adjacent(X).iterator();
     * while (it.hasNext()) {
     *     int v = it.next();
     *     // Do something with v
     * }
     * </code></pre>
     * The iterator doesn't support the {@link Iterator#remove()} method, which will always throw
     * {@link UnsupportedOperationException}.
     * <p>
     * You can get the degree of a certain vertex {@code v} by querying the size of the collection returned by this
     * method:
     * <pre><code>
     * int degree = g.adjacent(v).size();
     * </code></pre>
     * The snippet will include the edge to {@code v} itself, if present.
     * <p>
     * You can also use this method to check if two vertices {@code a} and {@code b} are connected via an edge:
     * <pre><code>
     * if (g.adjacent(a).contains(b)) {
     *     System.out.printf("Vertices %d and %d are adjacent%n", a, b);
     * }
     * </code></pre>
     * Be careful when using such construct: {@code g.adjacent(a).contains(b)} will return {@code false} if {@code b}
     * is not an element of this graph. Because this graph is undirected, the condition is equivalent to
     * {@code g.adjacent(b).contains(a)}, but only if both {@code a} and {@code b} are in the graph:
     * <pre><code>
     * boolean connected = g.adjacent(b).contains(a);
     * assert connected == g.adjacent(a).contains(b);
     * </code></pre>
     * <p>
     * Complexity: O(1)
     *
     * @param v the vertex
     * @return a {@link Set} that holds all the adjacent vertices of {@code v}
     * @throws IndexOutOfBoundsException if {@code v} is outside the range {@code [O,V)}
     */
    Set<Integer> adjacent(int v);

    /**
     * Returns a collection of all the edges in this graph.
     * <p>
     * This method is suitable to use in a for-each loop:
     * <pre><code>
     * for (Edge e : g.edges()) {
     *     System.out.println(e);
     * }
     * </code></pre>
     * <p>
     * Complexity: O(1)
     *
     * @return a collection of all the edges in this graph
     */
    Iterable<Edge> edges();

    /**
     * Returns a {@link DirectedGraph} wrapper of this graph.
     * <p>
     * The resulting graph will contain two parallel directed edges for each undirected edge of this graph.
     * <p>
     * Complexity: O(1)
     *
     * @return a {@link DirectedGraph} wrapper of this graph
     */
    DirectedGraph asDirected();

    /**
     * Returns a {@link WeightedGraph} wrapper of this graph.
     * <p>
     * The resulting graph will contain the same edges as this graph with assigned weight {@code 1.0}.
     * <p>
     * Complexity: O(1)
     *
     * @return a {@link WeightedGraph} wrapper of this graph
     */
    WeightedGraph asWeighted();

    /**
     * Returns a {@link WeightedDirectedGraph} wrapper of this graph.
     * <p>
     * The resulting graph will contain two parallel directed edges for each undirected edge of this graph with assigned
     * weight {@code 1.0}.
     * <p>
     * Complexity: O(1)
     *
     * @return a {@link WeightedDirectedGraph} wrapper of this graph
     */
    WeightedDirectedGraph asWeightedDirected();

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    String toString();

    /**
     * {@inheritDoc}
     *
     * @param obj {@inheritDoc}
     * @return {@inheritDoc}
     * @see Graphs#equals(Graph, Graph)
     */
    @Override
    boolean equals(Object obj);

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    int hashCode();
}
