package gr.james.simplegraph;

import java.util.Iterator;
import java.util.Set;

/**
 * Base interface for undirected graphs.
 */
interface IGraph extends IBaseGraph {
    /**
     * Get the adjacent vertices of a vertex.
     * <p>
     * More formally, returns all vertices in this graph adjacent to {@code v}. The vertices returned are in no
     * particular order inside the {@link Set}.
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
     * You can use this method to check if two vertices {@code a} and {@code b} are connected via an edge:
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
     * <h2>Note for mutable graphs</h2>
     * In the case of mutable graphs the {@link Set} returned is directly backed by the graph and changes will reflect
     * on that {@link Set}. A side effect of this property is that the iterator, like any other, will throw
     * {@link java.util.ConcurrentModificationException} if elements are modified during iteration.
     * <p>
     * Complexity: O(1)
     *
     * @param v the vertex
     * @return a {@link Set} that holds all the adjacent vertices of {@code v}
     * @throws IndexOutOfBoundsException if {@code v} is outside the range {@code [O,V)}
     */
    Set<Integer> getEdges(int v);
}
