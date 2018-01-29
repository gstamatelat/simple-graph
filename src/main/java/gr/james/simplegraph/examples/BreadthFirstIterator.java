package gr.james.simplegraph.examples;

import gr.james.simplegraph.ImmutableDirectedGraph;
import gr.james.simplegraph.ImmutableGraph;
import gr.james.simplegraph.ImmutableWeightedDirectedGraph;
import gr.james.simplegraph.ImmutableWeightedGraph;

import java.util.*;

/**
 * Implementation of breadth-first search (BFS) as {@link Iterator Iterator&lt;Integer&gt;}.
 * <p>
 * The iterator iterates over the vertices of the graph at the order at which they are discovered. Only the vertices
 * that are discoverable from the source will be returned. Therefore, if the graph is not strongly connected, only a
 * subset of the vertices may be discovered. The source vertex itself is included in the iteration and is guaranteed to
 * be returned first.
 * <p>
 * A typical usage of the {@code BreadthFirstIterator} is illustrated below.
 * <pre><code>
 * BreadthFirstIterator bfs = new BreadthFirstIterator(g, source);
 * while (bfs.hasNext()) {
 *     final int v = bfs.next();
 *     // Do something with v
 * }
 * </code></pre>
 * <p>
 * {@code BreadthFirstIterator} does not support the {@link Iterator#remove()} method.
 * <p>
 * Memory Complexity: O(V)
 */
public class BreadthFirstIterator implements Iterator<Integer> {
    private final ImmutableDirectedGraph g;

    private final Deque<Integer> queue;
    private final Set<Integer> visited;

    /**
     * Construct a new {@link BreadthFirstIterator} from a given graph and a source vertex.
     * <p>
     * Complexity: O(1)
     *
     * @param g      the graph to perform BFS in
     * @param source the source vertex
     * @throws NullPointerException      if {@code g} is {@code null}
     * @throws IndexOutOfBoundsException if {@code source} is not in {@code g}
     */
    public BreadthFirstIterator(ImmutableDirectedGraph g, int source) {
        if (source < 0 || source >= g.size()) {
            throw new IndexOutOfBoundsException();
        }

        this.g = g;
        this.queue = new LinkedList<Integer>();
        this.visited = new HashSet<Integer>();

        this.queue.offer(source);
        this.visited.add(source);
    }

    /**
     * Construct a new {@link BreadthFirstIterator} from a given graph and a source vertex.
     * <p>
     * Complexity: O(1)
     *
     * @param g      the graph to perform BFS in
     * @param source the source vertex
     * @throws NullPointerException      if {@code g} is {@code null}
     * @throws IndexOutOfBoundsException if {@code source} is not in {@code g}
     */
    public BreadthFirstIterator(ImmutableGraph g, int source) {
        this(g.asDirected(), source);
    }

    /**
     * Construct a new {@link BreadthFirstIterator} from a given graph and a source vertex.
     * <p>
     * Complexity: O(1)
     *
     * @param g      the graph to perform BFS in
     * @param source the source vertex
     * @throws NullPointerException      if {@code g} is {@code null}
     * @throws IndexOutOfBoundsException if {@code source} is not in {@code g}
     */
    public BreadthFirstIterator(ImmutableWeightedGraph g, int source) {
        this(g.asDirected(), source);
    }

    /**
     * Construct a new {@link BreadthFirstIterator} from a given graph and a source vertex.
     * <p>
     * Complexity: O(1)
     *
     * @param g      the graph to perform BFS in
     * @param source the source vertex
     * @throws NullPointerException      if {@code g} is {@code null}
     * @throws IndexOutOfBoundsException if {@code source} is not in {@code g}
     */
    public BreadthFirstIterator(ImmutableWeightedDirectedGraph g, int source) {
        this(g.asDirected(), source);
    }

    /**
     * Returns {@code true} if BFS has more vertices to discover, otherwise {@code false}.
     * <p>
     * The first invocation to this method will always return {@code true} because the source vertex is guaranteed to be
     * returned first.
     * <p>
     * Complexity: O(1)
     *
     * @return {@code true} if BFS has more vertices to discover, otherwise {@code false}
     */
    @Override
    public boolean hasNext() {
        return !queue.isEmpty();
    }

    /**
     * Returns the next vertex that is discovered by BFS.
     * <p>
     * The first invocation to this method will always return the source vertex.
     * <p>
     * Complexity: O(d), where d is the out degree of the next vertex
     *
     * @return the next vertex that is discovered by BFS
     * @throws NoSuchElementException if there are no more vertices to be discovered
     */
    @Override
    public Integer next() {
        final Integer next = queue.poll();

        if (next == null) {
            throw new NoSuchElementException();
        }

        for (int v : g.getOutEdges(next)) {
            if (visited.add(v)) {
                queue.offer(v);
            }
        }

        return next;
    }

    /**
     * This method is unsupported and always throws {@link UnsupportedOperationException}.
     *
     * @throws UnsupportedOperationException always
     */
    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
}
