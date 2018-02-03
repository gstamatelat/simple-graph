package gr.james.simplegraph;

import java.util.*;

/**
 * The {@link Graph} implementation using adjacency lists.
 */
public class MutableGraph implements Graph {
    private static final long serialVersionUID = 1L;

    private final List<Set<Integer>> edges;

    /**
     * Constructs a new empty {@link MutableGraph}.
     * <p>
     * Complexity: O(1)
     */
    public MutableGraph() {
        this.edges = new ArrayList<Set<Integer>>();
    }

    /**
     * Constructs a new empty {@link MutableGraph} with {@code n} unconnected vertices.
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
     * Constructs a new {@link MutableGraph} as a copy of the given graph {@code g}.
     * <p>
     * Complexity: O(V+E)
     *
     * @param g the graph to copy
     * @throws NullPointerException if {@code g} is {@code null}
     */
    public MutableGraph(Graph g) {
        this(g.size());
        for (Edge e : g.edges()) {
            putEdge(e.v(), e.w());
        }
        assert g.equals(this);
    }

    /**
     * Constructs a new {@link MutableGraph} as a copy of the given graph {@code g}.
     * <p>
     * This constructor will not copy the edge weights.
     * <p>
     * Complexity: O(V+E)
     *
     * @param g the graph to copy
     * @throws NullPointerException if {@code g} is {@code null}
     */
    public MutableGraph(WeightedGraph g) {
        this(g.asGraph());
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
     * <h2>Implementation note</h2>
     * The {@link Set} returned is directly backed by the graph and changes will reflect on that {@link Set}. A side
     * effect of this property is that the iterator, like any other, will throw
     * {@link java.util.ConcurrentModificationException} if elements are modified during iteration.
     *
     * @param v {@inheritDoc}
     * @return {@inheritDoc}
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    @Override
    public Set<Integer> adjacent(int v) {
        final Set<Integer> edges = this.edges.get(v);
        return Collections.unmodifiableSet(edges);
    }

    /**
     * {@inheritDoc}
     * <h2>Implementation note</h2>
     * The {@link Iterable} returned is directly backed by the graph and changes will reflect on that {@link Iterable}.
     * A side effect of this property is that the iterator, like any other, will throw
     * {@link java.util.ConcurrentModificationException} if elements are modified during iteration.
     *
     * @return {@inheritDoc}
     */
    @Override
    public final Iterable<Edge> edges() {
        return new Iterable<Edge>() {
            @Override
            public Iterator<Edge> iterator() {
                return new AbstractIterator<Edge>() {
                    private int currentVertex;
                    private Iterator<Integer> edges;

                    @Override
                    void init() {
                        currentVertex = -1;
                        edges = Graphs.emptyIterator();
                    }

                    @Override
                    Edge computeNext() {
                        while (true) {
                            if (currentVertex >= MutableGraph.this.size()) {
                                return null;
                            }
                            if (currentVertex == MutableGraph.this.size() - 1 && !edges.hasNext()) {
                                return null;
                            }
                            if (!edges.hasNext()) {
                                edges = MutableGraph.this.adjacent(++currentVertex).iterator();
                                continue;
                            }
                            final int e = edges.next();
                            if (e < currentVertex) {
                                continue;
                            }
                            return new EdgeImpl(currentVertex, e);
                        }
                    }
                };
            }
        };
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
     * assert g.adjacent(g.size() - 1).isEmpty();
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
    public final void addVertices(int n) {
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
        Graphs.requireVertexInGraph(this, v);
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
     * Construct and return a new unmodifiable {@link Graph} as a copy of this graph.
     * <p>
     * The object produced by this method is completely independent of this graph.
     * <p>
     * This method is equivalent to
     * <pre><code>
     * return new MutableGraph(this).asUnmodifiable();
     * </code></pre>
     * <p>
     * Complexity: O(V+E)
     *
     * @return a copy of this graph as a new unmodifiable {@link Graph}
     */
    public final Graph toImmutable() {
        return new MutableGraph(this).asUnmodifiable();
    }

    /**
     * Returns an unmodifiable decorator around this graph.
     * <p>
     * Invoking any mutation methods on the resulting graph will result in {@link UnsupportedOperationException}.
     * <p>
     * Complexity: O(1)
     *
     * @return an unmodifiable decorator around this graph
     */
    public final Graph asUnmodifiable() {
        return new MutableGraph() {
            @Override
            public int size() {
                return MutableGraph.this.size();
            }

            @Override
            public Set<Integer> adjacent(int v) {
                return MutableGraph.this.adjacent(v);
            }

            @Override
            public void addVertex() {
                throw new UnsupportedOperationException();
            }

            @Override
            public void removeVertex(int v) {
                throw new UnsupportedOperationException();
            }

            @Override
            public boolean putEdge(int v, int w) {
                throw new UnsupportedOperationException();
            }

            @Override
            public boolean removeEdge(int v, int w) {
                throw new UnsupportedOperationException();
            }
        };
    }

    /**
     * {@inheritDoc}
     * <h2>Implementation note</h2>
     * Because there are semantic differences among different types of graphs, some mutation methods in the resulting
     * graph may be ambiguous when forwarded to this graph. Instead of trying to circumvent these types of restrictions
     * with unusual or opinionated ways, these methods will immediately throw {@link UnsupportedOperationException}.
     *
     * @return {@inheritDoc}
     */
    @Override
    public final MutableDirectedGraph asDirected() {
        return new MutableDirectedGraph() {
            @Override
            public int size() {
                return MutableGraph.this.size();
            }

            @Override
            public Set<Integer> adjacentOut(int v) {
                return MutableGraph.this.adjacent(v);
            }

            @Override
            public Set<Integer> adjacentIn(int v) {
                return MutableGraph.this.adjacent(v);
            }

            @Override
            public void addVertex() {
                MutableGraph.this.addVertex();
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
     * <h2>Implementation note</h2>
     * Because there are semantic differences among different types of graphs, some mutation methods in the resulting
     * graph may be ambiguous when forwarded to this graph. Instead of trying to circumvent these types of restrictions
     * with unusual or opinionated ways, these methods will immediately throw {@link UnsupportedOperationException}.
     *
     * @return {@inheritDoc}
     */
    @Override
    public final MutableWeightedGraph asWeighted() {
        return new MutableWeightedGraph() {
            @Override
            public int size() {
                return MutableGraph.this.size();
            }

            @Override
            public Set<Integer> getEdges(int v) {
                return MutableGraph.this.adjacent(v);
            }

            @Override
            public double getEdgeWeight(int v, int w) {
                Graphs.requireEdgeExists(MutableGraph.this, v, w);
                return 1.0;
            }

            @Override
            public void addVertex() {
                MutableGraph.this.addVertex();
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
     * <h2>Implementation note</h2>
     * Because there are semantic differences among different types of graphs, some mutation methods in the resulting
     * graph may be ambiguous when forwarded to this graph. Instead of trying to circumvent these types of restrictions
     * with unusual or opinionated ways, these methods will immediately throw {@link UnsupportedOperationException}.
     *
     * @return {@inheritDoc}
     */
    @Override
    public final MutableWeightedDirectedGraph asWeightedDirected() {
        return new MutableWeightedDirectedGraph() {
            @Override
            public int size() {
                return MutableGraph.this.size();
            }

            @Override
            public Set<Integer> getOutEdges(int v) {
                return MutableGraph.this.adjacent(v);
            }

            @Override
            public Set<Integer> getInEdges(int v) {
                return MutableGraph.this.adjacent(v);
            }

            @Override
            public double getEdgeWeight(int source, int target) {
                Graphs.requireEdgeExists(MutableGraph.this, source, target);
                return 1.0;
            }

            @Override
            public void addVertex() {
                MutableGraph.this.addVertex();
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
    public final String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(String.format("%s(%d) {%n", "Graph", this.size()));
        for (Edge e : edges()) {
            sb.append(String.format("  %s%n", e));
        }
        sb.append("}");
        return sb.toString();
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
        int hash = 1;
        for (int i = 0; i < this.size(); i++) {
            hash = 31 * hash;
            for (int j : this.adjacent(i)) {
                hash += j;
            }
        }
        return hash;
    }
}
