package gr.james.simplegraph.examples;

import gr.james.simplegraph.DirectedGraph;

import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * Demonstration of depth first search (DFS) using a stack.
 */
public class DepthFirstSearch {
    /**
     * Performs DFS on a directed graph.
     * <p>
     * Complexity: O(V+E)
     *
     * @param g      the graph
     * @param source the starting vertex
     * @return the number of vertices that are reachable from {@code source} (self included)
     * @throws NullPointerException      if {@code g} is {@code null}
     * @throws IndexOutOfBoundsException if {@code source} is not in the graph
     */
    public static int depthFirstSearch(DirectedGraph g, int source) {
        final Deque<Integer> stack = new LinkedList<Integer>();
        final Set<Integer> visited = new HashSet<Integer>();
        stack.push(source);
        while (!stack.isEmpty()) {
            final int next = stack.pop();
            if (visited.add(next)) {
                for (int adj : g.getOutEdges(next)) {
                    stack.push(adj);
                }
            }
        }
        return visited.size();
    }
}
