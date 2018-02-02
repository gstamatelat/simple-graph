package gr.james.simplegraph;

import java.util.*;

/**
 * The {@link WeightedGraph} implementation using adjacency lists.
 */
public class MutableWeightedGraph implements WeightedGraph {
    private static final long serialVersionUID = 1L;

    private final List<Map<Integer, Double>> edges;

    /**
     * Constructs a new empty {@link MutableWeightedGraph}.
     * <p>
     * Complexity: O(1)
     */
    public MutableWeightedGraph() {
        this.edges = new ArrayList<Map<Integer, Double>>();
    }

    /**
     * Constructs a new empty {@link MutableWeightedGraph} with {@code n} unconnected vertices.
     * <p>
     * Complexity: O(n)
     *
     * @param n the amount of vertices to put in the graph
     * @throws IllegalArgumentException if {@code n < 0}
     */
    public MutableWeightedGraph(int n) {
        this.edges = new ArrayList<Map<Integer, Double>>(n);
        addVertices(n);
        assert size() == n;
    }

    /**
     * Constructs a new {@link MutableWeightedGraph} as a copy of the given graph {@code g}.
     * <p>
     * Complexity: O(V+E)
     *
     * @param g the graph to copy
     * @throws NullPointerException if {@code g} is {@code null}
     */
    public MutableWeightedGraph(WeightedGraph g) {
        this(g.size());
        for (WeightedEdge e : g.edges()) {
            putEdge(e.v(), e.w(), e.weight());
        }
        assert g.equals(this);
    }

    /**
     * Constructs a new {@link MutableWeightedGraph} as a copy of the given graph {@code g}.
     * <p>
     * This constructor will set the edge weights to {@code 1.0}.
     * <p>
     * Complexity: O(V+E)
     *
     * @param g the graph to copy
     * @throws NullPointerException if {@code g} is {@code null}
     */
    public MutableWeightedGraph(Graph g) {
        this(g.size());
        for (Edge e : g.edges()) {
            putEdge(e.v(), e.w(), 1.0);
        }
        assert g.asWeighted().equals(this);
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
    public Set<Integer> getEdges(int v) {
        final Map<Integer, Double> edges = this.edges.get(v);
        return Collections.unmodifiableSet(edges.keySet());
    }

    /**
     * {@inheritDoc}
     *
     * @param v {@inheritDoc}
     * @param w {@inheritDoc}
     * @return {@inheritDoc}
     * @throws IndexOutOfBoundsException {@inheritDoc}
     * @throws IllegalArgumentException  {@inheritDoc}
     */
    @Override
    public double getEdgeWeight(int v, int w) {
        Graphs.requireVertexInGraph(this, w);
        final Double weight = edges.get(v).get(w);
        if (weight == null) {
            throw new IllegalArgumentException();
        }
        assert weight.equals(edges.get(w).get(v));
        assert Graphs.isWeightLegal(weight);
        return weight;
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
    public final Iterable<WeightedEdge> edges() {
        return new Iterable<WeightedEdge>() {
            @Override
            public Iterator<WeightedEdge> iterator() {
                return new AbstractIterator<WeightedEdge>() {
                    private int currentVertex;
                    private Iterator<Integer> edges;

                    @Override
                    void init() {
                        currentVertex = -1;
                        edges = Graphs.emptyIterator();
                    }

                    @Override
                    WeightedEdge computeNext() {
                        while (true) {
                            if (currentVertex >= MutableWeightedGraph.this.size()) {
                                return null;
                            }
                            if (currentVertex == MutableWeightedGraph.this.size() - 1 && !edges.hasNext()) {
                                return null;
                            }
                            if (!edges.hasNext()) {
                                edges = MutableWeightedGraph.this.getEdges(++currentVertex).iterator();
                                continue;
                            }
                            final int e = edges.next();
                            if (e < currentVertex) {
                                continue;
                            }
                            return new WeightedEdgeImpl(currentVertex, e,
                                    MutableWeightedGraph.this.getEdgeWeight(currentVertex, e));
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
     * assert g.getEdges(g.size() - 1).isEmpty();
     * </code></pre>
     * <p>
     * Complexity: O(1)
     */
    public void addVertex() {
        this.edges.add(new HashMap<Integer, Double>());
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
            final Map<Integer, Double> previousOut = edges.get(i);
            final Map<Integer, Double> newOut = new HashMap<Integer, Double>();
            for (Map.Entry<Integer, Double> e : previousOut.entrySet()) {
                if (e.getKey() > v) {
                    newOut.put(e.getKey() - 1, e.getValue());
                } else if (e.getKey() < v) {
                    newOut.put(e.getKey(), e.getValue());
                }
            }
            edges.set(i, newOut);
        }
        edges.remove(v);
    }

    /**
     * Adds an edge on this graph or updates the weight of an existing one.
     * <p>
     * Complexity: O(1)
     *
     * @param v      one end of the edge
     * @param w      the other end of the edge
     * @param weight the weight of the edge
     * @return the previous weight of the edge connecting {@code v} and {@code w} or {@code null} if there was
     * previously no edge
     * @throws IllegalArgumentException  if {@code weight} is not finite
     * @throws IndexOutOfBoundsException if {@code v} or {@code w} are outside of {@code [O,V)}
     */
    public Double putEdge(int v, int w, double weight) {
        Graphs.requireWeightLegal(weight);
        final Double a = edges.get(v).put(w, weight);
        final Double b = edges.get(w).put(v, weight);
        assert a == null ? b == null : a.equals(b);
        return a;
    }

    /**
     * Remove an edge from this graph.
     * <p>
     * Complexity: O(1)
     *
     * @param v one end of the edge
     * @param w the other end of the edge
     * @return the previous weight of the edge connecting {@code v} and {@code w} or {@code null} if there was
     * previously no edge
     * @throws IndexOutOfBoundsException if {@code v} or {@code w} are outside of {@code [O,V)}
     */
    public Double removeEdge(int v, int w) {
        final Double a = edges.get(v).remove(w);
        final Double b = edges.get(w).remove(v);
        assert a == null ? b == null : a.equals(b);
        return a;
    }

    /**
     * Construct and return a new unmodifiable {@link WeightedGraph} as a copy of this graph.
     * <p>
     * The object produced by this method is completely independent of this graph.
     * <p>
     * This method is equivalent to
     * <pre><code>
     * return new MutableWeightedGraph(this).asUnmodifiable();
     * </code></pre>
     * <p>
     * Complexity: O(V+E)
     *
     * @return a copy of this graph as a new unmodifiable {@link WeightedGraph}
     */
    public final WeightedGraph toImmutable() {
        return new MutableWeightedGraph(this).asUnmodifiable();
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
    public final WeightedGraph asUnmodifiable() {
        return new MutableWeightedGraph() {
            @Override
            public int size() {
                return MutableWeightedGraph.this.size();
            }

            @Override
            public Set<Integer> getEdges(int v) {
                return MutableWeightedGraph.this.getEdges(v);
            }

            @Override
            public double getEdgeWeight(int v, int w) {
                return MutableWeightedGraph.this.getEdgeWeight(v, w);
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
            public Double putEdge(int v, int w, double weight) {
                throw new UnsupportedOperationException();
            }

            @Override
            public Double removeEdge(int v, int w) {
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
    public final MutableGraph asGraph() {
        return new MutableGraph() {
            @Override
            public int size() {
                return MutableWeightedGraph.this.size();
            }

            @Override
            public Set<Integer> getEdges(int v) {
                return MutableWeightedGraph.this.getEdges(v);
            }

            @Override
            public void addVertex() {
                MutableWeightedGraph.this.addVertex();
            }

            @Override
            public void removeVertex(int v) {
                MutableWeightedGraph.this.removeVertex(v);
            }

            @Override
            public boolean putEdge(int v, int w) {
                return MutableWeightedGraph.this.putEdge(v, w, 1.0) == null;
            }

            @Override
            public boolean removeEdge(int v, int w) {
                return MutableWeightedGraph.this.removeEdge(v, w) != null;
            }
        };
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public final MutableDirectedGraph asDirected() {
        return new MutableDirectedGraph() {
            @Override
            public int size() {
                return MutableWeightedGraph.this.size();
            }

            @Override
            public Set<Integer> getOutEdges(int v) {
                return MutableWeightedGraph.this.getEdges(v);
            }

            @Override
            public Set<Integer> getInEdges(int v) {
                return MutableWeightedGraph.this.getEdges(v);
            }

            @Override
            public void addVertex() {
                MutableWeightedGraph.this.addVertex();
            }

            @Override
            public void removeVertex(int v) {
                MutableWeightedGraph.this.removeVertex(v);
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
    public final MutableWeightedDirectedGraph asWeightedDirected() {
        return new MutableWeightedDirectedGraph() {
            @Override
            public int size() {
                return MutableWeightedGraph.this.size();
            }

            @Override
            public Set<Integer> getOutEdges(int v) {
                return MutableWeightedGraph.this.getEdges(v);
            }

            @Override
            public Set<Integer> getInEdges(int v) {
                return MutableWeightedGraph.this.getEdges(v);
            }

            @Override
            public double getEdgeWeight(int source, int target) {
                return MutableWeightedGraph.this.getEdgeWeight(source, target);
            }

            @Override
            public void addVertex() {
                MutableWeightedGraph.this.addVertex();
            }

            @Override
            public void removeVertex(int v) {
                MutableWeightedGraph.this.removeVertex(v);
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
        sb.append(String.format("%s(%d) {%n", "WeightedGraph", this.size()));
        for (WeightedEdge e : edges()) {
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
        if (obj == null || !(obj instanceof WeightedGraph)) {
            return false;
        }
        final WeightedGraph that = (WeightedGraph) obj;
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
            for (int j : this.getEdges(i)) {
                hash += (j ^ new Double(this.getEdgeWeight(i, j)).hashCode());
            }
        }
        return hash;
    }
}
