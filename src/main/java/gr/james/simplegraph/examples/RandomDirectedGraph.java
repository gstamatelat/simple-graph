package gr.james.simplegraph.examples;

import gr.james.simplegraph.MutableDirectedGraph;

import java.util.Random;

/**
 * Generate a random {@link MutableDirectedGraph}.
 */
public class RandomDirectedGraph {
    public static void main(String[] args) {

    }

    /**
     * Generates a {@link MutableDirectedGraph} according to the G(n,p) model.
     * <p>
     * This method does not generate self loops.
     * <p>
     * Complexity: O(n^2)
     *
     * @param n the number of vertices
     * @param p the connection probability
     * @param r the {@link Random} instance to use
     * @return a new {@link MutableDirectedGraph} complying with the G(n,p) model
     * @throws IllegalArgumentException if {@code n < 1} or {@code p} is not in [0,1]
     * @throws NullPointerException     if {@code r} is {@code null}
     */
    public static MutableDirectedGraph randomDirectedGraph(int n, double p, Random r) {
        if (p < 0 && p > 1) {
            throw new IllegalArgumentException();
        }
        final MutableDirectedGraph g = new MutableDirectedGraph(n);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j && r.nextDouble() < p) {
                    g.putEdge(i, j);
                }
            }
        }
        return g;
    }
}
