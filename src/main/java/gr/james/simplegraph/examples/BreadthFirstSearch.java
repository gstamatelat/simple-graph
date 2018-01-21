package gr.james.simplegraph.examples;

import gr.james.simplegraph.DirectedGraph;

import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * Demonstration of breadth first search (BFS) using a queue.
 */
public class BreadthFirstSearch {
    /**
     * Performs BFS on a directed graph.
     * <p>
     * Complexity: O(V+E)
     *
     * @param g      the graph
     * @param source the source vertex
     * @return the number of vertices that are reachable from {@code source} (self included)
     * @throws NullPointerException      if {@code g} is {@code null}
     * @throws IndexOutOfBoundsException if {@code source} is not in the graph
     */
    public static int breadthFirstSearch(DirectedGraph g, int source) {
        final Deque<Integer> queue = new LinkedList<Integer>();
        final Set<Integer> visited = new HashSet<Integer>();
        queue.offer(source);
        visited.add(source);
        while (!queue.isEmpty()) {
            final int next = queue.poll();
            for (int adj : g.getOutEdges(next)) {
                if (visited.add(adj)) {
                    queue.offer(adj);
                }
            }
        }
        return visited.size();
    }
}
