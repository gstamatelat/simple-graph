package gr.james.simplegraph;

import java.util.*;

/**
 * Represents an unweighted and undirected graph implemented using adjacency lists.
 * <p>
 * The graph can contain self loops but cannot cannot contain parallel edges. More formally, any unordered pair of
 * endpoints may correspond to at most one edge.
 * <p>
 * An unordered pair {@code {a, b}} is a pair of objects with no particular relation between them; the order in which
 * the objects appear in the pair is not significant.
 * <p>
 * Memory Complexity: O(V+E)
 */
public class MutableGraph implements Graph {
    private static final long serialVersionUID = 1L;

    private final List<Set<Integer>> edges;

    /**
     * Construct a new empty {@link MutableGraph}.
     * <p>
     * Complexity: O(1)
     */
    public MutableGraph() {
        this(0);
    }

    /**
     * Construct a new empty {@link MutableGraph} with {@code n} vertices.
     * <p>
     * Complexity: O(n)
     *
     * @param n the amount of vertices to put in the graph
     * @throws IllegalArgumentException if {@code n < 0}
     */
    public MutableGraph(int n) {
        this.edges = new ArrayList<Set<Integer>>(n);
        addVertices(n);
        assert size() == n;
    }

    /**
     * Construct a new {@link MutableGraph} as a copy of the given graph {@code g}.
     * <p>
     * Complexity: O(V+E)
     *
     * @param g the graph to copy
     * @throws NullPointerException if {@code g} is {@code null}
     */
    public MutableGraph(Graph g) {
        this(g.size());
        for (int v = 0; v < g.size(); v++) {
            for (int w : g.getEdges(v)) {
                putEdge(v, w);
            }
        }
        assert this.equals(g);
    }

    /**
     * Construct a new {@link MutableGraph} as a copy of the given graph {@code g}.
     * <p>
     * This constructor will not copy the edge weights.
     * <p>
     * Complexity: O(V+E)
     *
     * @param g the graph to copy
     * @throws NullPointerException if {@code g} is {@code null}
     */
    public MutableGraph(WeightedGraph g) {
        this(g.size());
        for (int v = 0; v < g.size(); v++) {
            for (int w : g.getEdges(v)) {
                putEdge(v, w);
            }
        }
    }

    /**
     * Construct and return a new {@link ImmutableGraph} as a copy of this graph.
     * <p>
     * Complexity: O(V+E)
     *
     * @return a copy of this graph as a new {@link ImmutableGraph}
     */
    public final ImmutableGraph toImmutable() {
        return new ImmutableGraph() {
            private final MutableGraph g =
                    new MutableGraph(MutableGraph.this);

            @Override
            public int size() {
                return g.size();
            }

            @Override
            public Set<Integer> getEdges(int v) {
                return g.getEdges(v);
            }
        };
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public MutableGraph asGraph() {
        return this;
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public MutableDirectedGraph asDirected() {
        return new MutableDirectedGraph() {
            @Override
            public int size() {
                return MutableGraph.this.size();
            }

            @Override
            public Set<Integer> getOutEdges(int v) {
                return MutableGraph.this.getEdges(v);
            }

            @Override
            public Set<Integer> getInEdges(int v) {
                return MutableGraph.this.getEdges(v);
            }

            @Override
            public void addVertex() {
                MutableGraph.this.addVertex();
            }

            @Override
            public void addVertices(int n) {
                MutableGraph.this.addVertices(n);
            }

            @Override
            public void removeVertex(int v) {
                MutableGraph.this.removeVertex(v);
            }

            @Override
            public boolean putEdge(int source, int target) {
                throw new UnsupportedOperationException();
            }

            @Override
            public boolean removeEdge(int source, int target) {
                throw new UnsupportedOperationException();
            }
        };
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public MutableWeightedGraph asWeighted() {
        return new MutableWeightedGraph() {
            @Override
            public int size() {
                return MutableGraph.this.size();
            }

            @Override
            public Set<Integer> getEdges(int v) {
                return MutableGraph.this.getEdges(v);
            }

            @Override
            public double getEdgeWeight(int v, int w) {
                Graphs.checkEdgeExists(MutableGraph.this, v, w);
                return 1.0;
            }

            @Override
            public void addVertex() {
                MutableGraph.this.addVertex();
            }

            @Override
            public void addVertices(int n) {
                MutableGraph.this.addVertices(n);
            }

            @Override
            public void removeVertex(int v) {
                MutableGraph.this.removeVertex(v);
            }

            @Override
            public Double putEdge(int v, int w, double weight) {
                throw new UnsupportedOperationException();
            }

            @Override
            public Double removeEdge(int v, int w) {
                return MutableGraph.this.removeEdge(v, w) ? 1.0 : null;
            }
        };
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public MutableWeightedDirectedGraph asWeightedDirected() {
        return new MutableWeightedDirectedGraph() {
            @Override
            public int size() {
                return MutableGraph.this.size();
            }

            @Override
            public Set<Integer> getOutEdges(int v) {
                return MutableGraph.this.getEdges(v);
            }

            @Override
            public Set<Integer> getInEdges(int v) {
                return MutableGraph.this.getEdges(v);
            }

            @Override
            public double getEdgeWeight(int source, int target) {
                Graphs.checkEdgeExists(MutableGraph.this, source, target);
                return 1.0;
            }

            @Override
            public void addVertex() {
                MutableGraph.this.addVertex();
            }

            @Override
            public void addVertices(int n) {
                MutableGraph.this.addVertices(n);
            }

            @Override
            public void removeVertex(int v) {
                MutableGraph.this.removeVertex(v);
            }

            @Override
            public Double putEdge(int source, int target, double weight) {
                throw new UnsupportedOperationException();
            }

            @Override
            public Double removeEdge(int source, int target) {
                throw new UnsupportedOperationException();
            }
        };
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int size() {
        return this.edges.size();
    }

    /**
     * {@inheritDoc}
     *
     * @param v {@inheritDoc}
     * @return {@inheritDoc}
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    @Override
    public Set<Integer> getEdges(int v) {
        final Set<Integer> edges = this.edges.get(v);
        return Collections.unmodifiableSet(edges);
    }

    /**
     * Adds a vertex to the graph.
     * <p>
     * This method adds a new unconnected vertex in the graph with ID equal to {@code size} and then increases the value
     * of {@code size} by one.
     * <pre><code>
     * int previousSize = g.size();
     * g.addVertex();
     * assert g.size() == previousSize + 1;
     * System.out.printf("The new vertex ID is %d%n", g.size() - 1);
     * assert g.getEdges(g.size() - 1).isEmpty();
     * </code></pre>
     * <p>
     * Complexity: O(1)
     */
    public void addVertex() {
        this.edges.add(new HashSet<Integer>());
    }

    /**
     * Add many vertices to the graph.
     * <p>
     * This method is equivalent to
     * <pre><code>
     * if (n &lt; 0) {
     *     throw new IllegalArgumentException();
     * }
     * for (int i = 0; i &lt; n; i++) {
     *     addVertex();
     * }
     * </code></pre>
     * <p>
     * Complexity: O(n)
     *
     * @param n how many vertices to add
     * @throws IllegalArgumentException if {@code n < 0}
     */
    public void addVertices(int n) {
        if (n < 0) {
            throw new IllegalArgumentException();
        }
        for (int i = 0; i < n; i++) {
            addVertex();
        }
    }

    /**
     * Removes a vertex along with all of its edges from the graph.
     * <p>
     * This method works in a way that preserves the insertion order of vertices. More specifically, initially all edges
     * referring to {@code v} are removed, resulting in {@code v} being unconnected. Afterwards, while {@code v} is
     * removed, all vertices with ID {@code > v} slide one position to the left to occupy the empty slot. Finally, the
     * {@code size} of the graph is reduced by one. <b>A side effect of this process is that the vertices with ID higher
     * than {@code v} are mutated to an ID that is lower by one unit.</b>
     * <p>
     * Complexity: O(V+E)
     *
     * @param v the vertex to remove from the graph
     * @throws IndexOutOfBoundsException if {@code v} is outside of {@code [O,V)}
     */
    public void removeVertex(int v) {
        Graphs.checkVertex(this, v);
        for (int i = 0; i < size(); i++) {
            final Set<Integer> previousOut = edges.get(i);
            final Set<Integer> newOut = new HashSet<Integer>();
            for (Integer e : previousOut) {
                if (e > v) {
                    newOut.add(e - 1);
                } else if (e < v) {
                    newOut.add(e);
                }
            }
            edges.set(i, newOut);
        }
        edges.remove(v);
    }

    /**
     * Adds an edge on this graph.
     * <p>
     * Complexity: O(1)
     *
     * @param v one end of the edge
     * @param w the other end of the edge
     * @return {@code true} if there was previously no edge connecting {@code v} with {@code w}, otherwise {@code false}
     * @throws IndexOutOfBoundsException if {@code v} or {@code w} are outside of {@code [O,V)}
     */
    public boolean putEdge(int v, int w) {
        final boolean a = edges.get(v).add(w);
        final boolean b = edges.get(w).add(v);
        assert a == b;
        return a;
    }

    /**
     * Remove an edge from this graph.
     * <p>
     * Complexity: O(1)
     *
     * @param v one end of the edge
     * @param w the other end of the edge
     * @return {@code true} if there was previously an edge connecting {@code v} with {@code w}, otherwise {@code false}
     * @throws IndexOutOfBoundsException if {@code v} or {@code w} are outside of {@code [O,V)}
     */
    public boolean removeEdge(int v, int w) {
        final boolean a = edges.get(v).remove(w);
        final boolean b = edges.get(w).remove(v);
        assert a == b;
        return a;
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public final String toString() {
        return Graphs.toString(this);
    }

    /**
     * {@inheritDoc}
     *
     * @param obj {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof Graph)) {
            return false;
        }
        final Graph that = (Graph) obj;
        return Graphs.equals(this, that);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public final int hashCode() {
        return Graphs.hashCode(this);
    }
}
