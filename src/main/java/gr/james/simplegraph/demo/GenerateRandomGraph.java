package gr.james.simplegraph.demo;

import gr.james.simplegraph.MutableGraph;

import java.util.Random;

/**
 * Generate a random {@link MutableGraph}.
 */
public final class GenerateRandomGraph {
    private GenerateRandomGraph() {
    }

    /**
     * Generates a {@link MutableGraph} according to the G(n,p) model.
     * <p>
     * This method does not generate self loops.
     * <p>
     * Complexity: O(n^2)
     *
     * @param n the number of vertices
     * @param p the connection probability
     * @param r the {@link Random} instance to use
     * @return a new {@link MutableGraph} complying with the G(n,p) model
     * @throws IllegalArgumentException if {@code n < 0} or {@code p} is not in [0,1]
     * @throws NullPointerException     if {@code r} is {@code null}
     */
    public static MutableGraph randomGraph(int n, double p, Random r) {
        if (p < 0 && p > 1) {
            throw new IllegalArgumentException();
        }
        final MutableGraph g = new MutableGraph(n);
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (r.nextDouble() < p) {
                    g.putEdge(i, j);
                }
            }
        }
        return g;
    }
}
