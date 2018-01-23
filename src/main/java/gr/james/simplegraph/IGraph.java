package gr.james.simplegraph;

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
     * Complexity: O(1)
     *
     * @param v the vertex
     * @return a {@link Set} that holds all the adjacent vertices of {@code v}
     * @throws IndexOutOfBoundsException if {@code v} is outside the range {@code [O,V)}
     */
    Set<Integer> getEdges(int v);
}
