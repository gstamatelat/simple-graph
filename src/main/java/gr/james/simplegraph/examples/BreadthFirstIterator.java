package gr.james.simplegraph.examples;

import gr.james.simplegraph.DirectedGraph;
import gr.james.simplegraph.Graph;
import gr.james.simplegraph.WeightedDirectedGraph;
import gr.james.simplegraph.WeightedGraph;

import java.util.*;

/**
 * Implementation of breadth first search (BFS) as {@link Iterator}.
 * <p>
 * Memory Complexity: O(V)
 */
public class BreadthFirstIterator implements Iterator<Integer> {
    private final DirectedGraph g;

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
    public BreadthFirstIterator(DirectedGraph g, int source) {
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
    public BreadthFirstIterator(Graph g, int source) {
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
    public BreadthFirstIterator(WeightedGraph g, int source) {
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
    public BreadthFirstIterator(WeightedDirectedGraph g, int source) {
        this(g.asDirected(), source);
    }

    /**
     * Returns {@code true} if BFS has more vertices to discover, otherwise {@code false}.
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
     * Returns the next vertex imposed by the BFS order.
     * <p>
     * Complexity: O(d), where d is the out degree of the next vertex
     *
     * @return the next vertex
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
