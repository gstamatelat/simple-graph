package gr.james.simplegraph;

import java.util.Set;

interface IGraph extends IBaseGraph {
    /**
     * Get the edges of a vertex.
     * <p>
     * Complexity: O(1)
     *
     * @param v the vertex index to get the edges of
     * @return a {@link Set} that holds all the adjacent vertices of {@code v}
     * @throws IndexOutOfBoundsException if {@code v} is outside of {@code [O,V)}
     */
    Set<Integer> getEdges(int v);
}
