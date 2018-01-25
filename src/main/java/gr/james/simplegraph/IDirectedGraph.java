package gr.james.simplegraph;

import java.util.Iterator;
import java.util.Set;

/**
 * Base interface for directed graphs.
 */
interface IDirectedGraph extends IBaseGraph {
    /**
     * Get the outbound adjacent vertices of a vertex.
     * <p>
     * More formally, returns all vertices in this graph adjacent to {@code v} which can be reached by traversing
     * {@code v}'s outgoing edges in the direction of the edge. The vertices returned are in no particular order inside
     * the {@link Set}.
     * <p>
     * You can use the result of this method in a for-each loop like so:
     * <pre><code>
     * for (int v : g.getOutEdges(X)) {
     *     // Do something with v
     * }
     * </code></pre>
     * <p>
     * You can also use the classic iterator approach:
     * <pre><code>
     * Iterator&lt;Integer&gt; it = g.getOutEdges(X).iterator();
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
     * int outDegree = g.getOutEdges(v).size();
     * </code></pre>
     * The snippet will include the edge to {@code v} itself, if present.
     * <p>
     * You can also use this method to check if a directed edge from {@code a} to {@code b} exists:
     * <pre><code>
     * if (g.getOutEdges(a).contains(b)) {
     *     System.out.printf("There exists an edge from %d to %d%n", a, b);
     * }
     * </code></pre>
     * Be careful when using such construct: {@code g.getOutEdges(a).contains(b)} will return {@code false} if {@code b}
     * is not an element of this graph.
     * <p>
     * Likewise, you can check if the opposite parallel edge exists:
     * <pre><code>
     * if (g.getOutEdges(b).contains(a)) {
     *     System.out.printf("There exists an edge from %d to %d%n", b, a);
     * }
     * </code></pre>
     * <h2>Note for mutable graphs</h2>
     * In the case of mutable graphs the {@link Set} returned is directly backed by the graph and changes will reflect
     * on that {@link Set}. A side effect of this property is that the iterator, like any other, will throw
     * {@link java.util.ConcurrentModificationException} if elements are modified during iteration.
     * <p>
     * Complexity: O(1)
     *
     * @param v the vertex
     * @return a {@link Set} that holds all the outbound adjacent vertices of {@code v}
     * @throws IndexOutOfBoundsException if {@code v} is outside the range {@code [O,V)}
     * @see #getInEdges(int)
     */
    Set<Integer> getOutEdges(int v);

    /**
     * Get the inbound adjacent vertices of a vertex.
     * <p>
     * More formally, returns all vertices in this graph adjacent to {@code v} which can be reached by traversing
     * {@code v}'s incoming edges against the direction of the edge. The vertices returned are in no particular order
     * inside the {@link Set}.
     * <p>
     * You can use this method in the same way you would use {@link #getOutEdges(int)} and you can refer to that for
     * additional documentation.
     * <p>
     * Complexity: O(1)
     *
     * @param v the vertex
     * @return a {@link Set} that holds all the inbound adjacent vertices of {@code v}
     * @throws IndexOutOfBoundsException if {@code v} is outside the range {@code [O,V)}
     * @see #getOutEdges(int)
     */
    Set<Integer> getInEdges(int v);
}
