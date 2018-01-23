package gr.james.simplegraph;

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
     * Complexity: O(1)
     *
     * @param v the vertex
     * @return a {@link Set} that holds all the outbound adjacent vertices of {@code v}
     * @throws IndexOutOfBoundsException if {@code v} is outside the range {@code [O,V)}
     */
    Set<Integer> getOutEdges(int v);

    /**
     * Get the inbound adjacent vertices of a vertex.
     * <p>
     * More formally, returns all vertices in this graph adjacent to {@code v} which can be reached by traversing
     * {@code v}'s incoming edges against the direction of the edge. The vertices returned are in no particular order
     * inside the {@link Set}.
     * <p>
     * Complexity: O(1)
     *
     * @param v the vertex
     * @return a {@link Set} that holds all the inbound adjacent vertices of {@code v}
     * @throws IndexOutOfBoundsException if {@code v} is outside the range {@code [O,V)}
     */
    Set<Integer> getInEdges(int v);
}
