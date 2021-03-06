package gr.james.simplegraph;

import java.util.Iterator;
import java.util.Set;

/**
 * Represents a weighted and directed graph.
 * <p>
 * The graph can contain self loops but cannot contain parallel edges. More formally, any ordered pair of endpoints may
 * correspond to at most one edge. The edge weights can only be finite {@link Double} values.
 * <p>
 * An ordered pair {@code (a,b)} is a pair of objects where the order in which the objects appear in the pair is
 * significant: the ordered pair {@code (a,b)} is different from the ordered pair {@code (b,a)} unless {@code a = b}.
 * <p>
 * Memory Complexity: O(V+E)
 */
public interface WeightedDirectedGraph extends BaseGraph {
    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    int size();

    /**
     * Get the outbound adjacent vertices of a vertex.
     * <p>
     * More formally, returns an unmodifiable view of all vertices in this graph adjacent to {@code v} which can be
     * reached by traversing {@code v}'s outgoing edges in the direction of the edge. The vertices returned are in no
     * particular order inside the {@link Set}.
     * <p>
     * You can use the result of this method in a for-each loop like so:
     * <pre><code>
     * for (int v : g.adjacentOut(X)) {
     *     // Do something with v
     * }
     * </code></pre>
     * <p>
     * You can also use the classic iterator approach:
     * <pre><code>
     * Iterator&lt;Integer&gt; it = g.adjacentOut(X).iterator();
     * while (it.hasNext()) {
     *     int v = it.next();
     *     // Do something with v
     * }
     * </code></pre>
     * The iterator doesn't support the {@link Iterator#remove()} method, which will always throw
     * {@link UnsupportedOperationException}.
     * <p>
     * You can get the out degree of a certain vertex {@code v} by querying the size of the collection returned by this
     * method:
     * <pre><code>
     * int outDegree = g.adjacentOut(v).size();
     * </code></pre>
     * The snippet will include the edge to {@code v} itself, if present.
     * <p>
     * You can also use this method to check if a directed edge from {@code a} to {@code b} exists:
     * <pre><code>
     * if (g.adjacentOut(a).contains(b)) {
     *     System.out.printf("There exists an edge from %d to %d%n", a, b);
     * }
     * </code></pre>
     * Be careful when using such construct: {@code g.adjacentOut(a).contains(b)} will return {@code false} if {@code b}
     * is not an element of this graph.
     * <p>
     * Likewise, you can check if the opposite parallel edge exists:
     * <pre><code>
     * if (g.adjacentOut(b).contains(a)) {
     *     System.out.printf("There exists an edge from %d to %d%n", b, a);
     * }
     * </code></pre>
     * <p>
     * Complexity: O(1)
     *
     * @param v the vertex
     * @return a {@link Set} that holds all the outbound adjacent vertices of {@code v}
     * @throws IndexOutOfBoundsException if {@code v} is outside the range {@code [O,V)}
     * @see #adjacentIn(int)
     */
    Set<Integer> adjacentOut(int v);

    /**
     * Get the inbound adjacent vertices of a vertex.
     * <p>
     * More formally, returns an unmodifiable view of all vertices in this graph adjacent to {@code v} which can be
     * reached by traversing {@code v}'s incoming edges against the direction of the edge. The vertices returned are in
     * no particular order inside the {@link Set}.
     * <p>
     * You can use this method in the same way you would use {@code adjacentOut(int)} and you can refer to that for
     * additional information.
     * <p>
     * Complexity: O(1)
     *
     * @param v the vertex
     * @return a {@link Set} that holds all the inbound adjacent vertices of {@code v}
     * @throws IndexOutOfBoundsException if {@code v} is outside the range {@code [O,V)}
     * @see #adjacentOut(int)
     */
    Set<Integer> adjacentIn(int v);

    /**
     * Get the weight of the edge from {@code source} to {@code target}.
     * <p>
     * The weight of an edge can be any {@link Double} value except {@link Double#NaN}, {@link Double#POSITIVE_INFINITY}
     * and {@link Double#NEGATIVE_INFINITY}. In other words, it can be any finite {@link Double} value.
     * <p>
     * Complexity: O(1)
     *
     * @param source the source of the edge
     * @param target the target of the edge
     * @return the weight of the edge from {@code source} to {@code target}
     * @throws IndexOutOfBoundsException if {@code source} or {@code target} are outside of {@code [O,V)}
     * @throws IllegalArgumentException  if there is no edge from {@code source} to {@code target}
     */
    double getEdgeWeight(int source, int target);

    /**
     * Returns a collection of all the edges in this graph.
     * <p>
     * This method is suitable to use in a for-each loop:
     * <pre><code>
     * for (WeightedDirectedEdge e : g.edges()) {
     *     System.out.println(e);
     * }
     * </code></pre>
     * <p>
     * Complexity: O(1)
     *
     * @return a collection of all the edges in this graph
     */
    Iterable<WeightedDirectedEdge> edges();

    /**
     * Returns a {@link DirectedGraph} wrapper of this graph.
     * <p>
     * The resulting graph is treated like unweighted and will, thus, lose the ability to get the weights assigned to
     * the edges of this graph.
     * <p>
     * Complexity: O(1)
     *
     * @return a {@link DirectedGraph} wrapper of this graph
     */
    DirectedGraph asDirected();

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
     * @see Graphs#equals(WeightedDirectedGraph, WeightedDirectedGraph)
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
