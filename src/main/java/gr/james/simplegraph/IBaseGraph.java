package gr.james.simplegraph;

import java.io.Serializable;

/**
 * Base interface for all graph types.
 */
public interface IBaseGraph extends Serializable {
    /**
     * Get the number of vertices in the graph.
     * <p>
     * Because vertices are numbered in the range {@code [0, size)}, you can use this method in a typical for-loop to
     * iterate over the vertices in the graph:
     * <pre><code>
     * for (int v = 0; v &lt; g.size(); v++) {
     *     // Do something with v
     * }
     * </code></pre>
     * <p>
     * Furthermore, the vertex IDs reflect the actual order at which they were inserted in the graph. So you can get the
     * oldest and newest vertices like so:
     * <pre><code>
     * int oldestVertex = 0;
     * int newestVertex = g.size() - 1;
     * </code></pre>
     * <p>
     * Complexity: O(1)
     *
     * @return how many vertices there are in the graph
     */
    int size();

    /**
     * Returns a string representation of the graph.
     * <p>
     * Complexity: O(V+E)
     *
     * @return a string representation of the graph
     */
    @Override
    String toString();

    /**
     * Indicates whether some other object is equal to this graph.
     * <p>
     * Two graphs are equal if they are of the same class type, have the same number of vertices and their edges
     * represent the same mapping.
     * <p>
     * Complexity: O(V+E)
     *
     * @param obj the reference object with which to compare
     * @return {@code true} if this graph is equal to the {@code obj} argument, otherwise {@code false}
     */
    @Override
    boolean equals(Object obj);

    /**
     * Returns a hash code value for this graph.
     * <p>
     * Complexity: O(V+E)
     *
     * @return a hash code value for this graph
     */
    @Override
    int hashCode();
}
