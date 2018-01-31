package gr.james.simplegraph;

import java.util.Iterator;
import java.util.Set;

/**
 * Represents a weighted and undirected graph.
 * <p>
 * The graph can contain self loops but cannot contain parallel edges. More formally, any unordered pair of endpoints
 * may correspond to at most one edge. The edge weights can only be finite {@link Double} values.
 * <p>
 * An unordered pair {@code {a, b}} is a pair of objects with no particular relation between them; the order in which
 * the objects appear in the pair is not significant.
 * <p>
 * Memory Complexity: O(V+E)
 */
public interface WeightedGraph extends BaseGraph {
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
     * If the graph is modifiable, the {@link Set} returned is directly backed by the graph and changes will reflect on
     * that {@link Set}. A side effect of this property is that the iterator, like any other, will throw
     * {@link java.util.ConcurrentModificationException} if elements are modified during iteration.
     * <p>
     * You can use the result of this method in a for-each loop like so:
     * <pre><code>
     * for (int v : g.getEdges(X)) {
     *     // Do something with v
     * }
     * </code></pre>
     * <p>
     * You can also use the classic iterator approach:
     * <pre><code>
     * Iterator&lt;Integer&gt; it = g.getEdges(X).iterator();
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
     * int degree = g.getEdges(v).size();
     * </code></pre>
     * The snippet will include the edge to {@code v} itself, if present.
     * <p>
     * You can also use this method to check if two vertices {@code a} and {@code b} are connected via an edge:
     * <pre><code>
     * if (g.getEdges(a).contains(b)) {
     *     System.out.printf("Vertices %d and %d are adjacent%n", a, b);
     * }
     * </code></pre>
     * Be careful when using such construct: {@code g.getEdges(a).contains(b)} will return {@code false} if {@code b}
     * is not an element of this graph. Because this graph is undirected, the condition is equivalent to
     * {@code g.getEdges(b).contains(a)}, but only if both {@code a} and {@code b} are in the graph:
     * <pre><code>
     * boolean connected = g.getEdges(b).contains(a);
     * assert connected == g.getEdges(a).contains(b);
     * </code></pre>
     * <p>
     * Complexity: O(1)
     *
     * @param v the vertex
     * @return a {@link Set} that holds all the adjacent vertices of {@code v}
     * @throws IndexOutOfBoundsException if {@code v} is outside the range {@code [O,V)}
     */
    Set<Integer> getEdges(int v);

    /**
     * Get the weight of the edge connecting {@code v} and {@code w}.
     * <p>
     * The weight of an edge can be any {@link Double} value but {@link Double#NaN}, {@link Double#POSITIVE_INFINITY}
     * and {@link Double#NEGATIVE_INFINITY}.
     * <p>
     * Complexity: O(1)
     *
     * @param v one end of the edge
     * @param w the other end of the edge
     * @return the weight of the edge connecting {@code v} and {@code w}
     * @throws IndexOutOfBoundsException if {@code v} or {@code w} are outside of {@code [O,V)}
     * @throws IllegalArgumentException  if there is no edge connecting {@code v} and {@code w}
     */
    double getEdgeWeight(int v, int w);

    /**
     * Returns a {@link Graph} wrapper of this graph.
     * <p>
     * Complexity: O(1)
     *
     * @return a {@link Graph} wrapper of this graph
     */
    Graph asGraph();

    /**
     * Returns a {@link DirectedGraph} wrapper of this graph.
     * <p>
     * Complexity: O(1)
     *
     * @return a {@link DirectedGraph} wrapper of this graph
     */
    DirectedGraph asDirected();

    /**
     * Returns a {@link WeightedDirectedGraph} wrapper of this graph.
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
