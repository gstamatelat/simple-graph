package gr.james.simplegraph;

import java.util.*;

/**
 * The {@link WeightedDirectedGraph} implementation using adjacency lists.
 */
public class MutableWeightedDirectedGraph implements WeightedDirectedGraph {
    private static final long serialVersionUID = 1L;

    private final List<Map<Integer, Double>> outEdges;
    private final List<Map<Integer, Double>> inEdges;

    /**
     * Constructs a new empty {@link MutableWeightedDirectedGraph}.
     * <p>
     * Complexity: O(1)
     */
    public MutableWeightedDirectedGraph() {
        this.inEdges = new ArrayList<Map<Integer, Double>>();
        this.outEdges = new ArrayList<Map<Integer, Double>>();
    }

    /**
     * Constructs a new empty {@link MutableWeightedDirectedGraph} with {@code n} unconnected vertices.
     * <p>
     * Complexity: O(n)
     *
     * @param n the amount of vertices to put in the graph
     * @throws IllegalArgumentException if {@code n < 0}
     */
    public MutableWeightedDirectedGraph(int n) {
        this.inEdges = new ArrayList<Map<Integer, Double>>(n);
        this.outEdges = new ArrayList<Map<Integer, Double>>(n);
        addVertices(n);
        assert size() == n;
    }

    /**
     * Constructs a new {@link MutableWeightedDirectedGraph} as a copy of the given graph {@code g}.
     * <p>
     * Complexity: O(V+E)
     *
     * @param g the graph to copy
     * @throws NullPointerException if {@code g} is {@code null}
     */
    public MutableWeightedDirectedGraph(WeightedDirectedGraph g) {
        this(g.size());
        for (WeightedDirectedEdge e : g.edges()) {
            putEdge(e.source(), e.target(), e.weight());
        }
        assert g.equals(this);
    }

    /**
     * Constructs a new {@link MutableWeightedDirectedGraph} as a copy of the given graph {@code g}.
     * <p>
     * This constructor will set the edge weights to {@code 1.0}.
     * <p>
     * Complexity: O(V+E)
     *
     * @param g the graph to copy
     * @throws NullPointerException if {@code g} is {@code null}
     */
    public MutableWeightedDirectedGraph(DirectedGraph g) {
        this(g.asWeightedDirected());
    }

    /**
     * Constructs a new {@link MutableWeightedDirectedGraph} as a copy of the given graph {@code g}.
     * <p>
     * This constructor will create two parallel directed edges for every edge in {@code g}.
     * <p>
     * Complexity: O(V+E)
     *
     * @param g the graph to copy
     * @throws NullPointerException if {@code g} is {@code null}
     */
    public MutableWeightedDirectedGraph(WeightedGraph g) {
        this(g.asWeightedDirected());
    }

    /**
     * Constructs a new {@link MutableWeightedDirectedGraph} as a copy of the given graph {@code g}.
     * <p>
     * This constructor will set the edge weights to {@code 1.0} and create two parallel directed edges for every edge
     * in {@code g}.
     * <p>
     * Complexity: O(V+E)
     *
     * @param g the graph to copy
     * @throws NullPointerException if {@code g} is {@code null}
     */
    public MutableWeightedDirectedGraph(Graph g) {
        this(g.asWeightedDirected());
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int size() {
        assert this.inEdges.size() == this.outEdges.size();
        return this.inEdges.size();
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
     * @see #adjacentIn(int)
     */
    @Override
    public Set<Integer> adjacentOut(int v) {
        final Map<Integer, Double> edges = this.outEdges.get(v);
        return Collections.unmodifiableSet(edges.keySet());
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
     * @see #adjacentOut(int)
     */
    @Override
    public Set<Integer> adjacentIn(int v) {
        final Map<Integer, Double> edges = this.inEdges.get(v);
        return Collections.unmodifiableSet(edges.keySet());
    }

    /**
     * {@inheritDoc}
     *
     * @param source {@inheritDoc}
     * @param target {@inheritDoc}
     * @return {@inheritDoc}
     * @throws IndexOutOfBoundsException {@inheritDoc}
     * @throws IllegalArgumentException  {@inheritDoc}
     */
    @Override
    public double getEdgeWeight(int source, int target) {
        Graphs.requireVertexInGraph(this, target);
        final Double weight = outEdges.get(source).get(target);
        if (weight == null) {
            throw new IllegalArgumentException();
        }
        assert weight.equals(inEdges.get(target).get(source));
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
    public final Iterable<WeightedDirectedEdge> edges() {
        return new Iterable<WeightedDirectedEdge>() {
            @Override
            public Iterator<WeightedDirectedEdge> iterator() {
                return new AbstractIterator<WeightedDirectedEdge>() {
                    private int currentVertex;
                    private Iterator<Integer> edges;

                    @Override
                    void init() {
                        currentVertex = -1;
                        edges = Graphs.emptyIterator();
                    }

                    @Override
                    WeightedDirectedEdge computeNext() {
                        while (true) {
                            if (currentVertex >= MutableWeightedDirectedGraph.this.size()) {
                                return null;
                            }
                            if (currentVertex == MutableWeightedDirectedGraph.this.size() - 1 && !edges.hasNext()) {
                                return null;
                            }
                            if (!edges.hasNext()) {
                                edges = MutableWeightedDirectedGraph.this.adjacentOut(++currentVertex).iterator();
                                continue;
                            }
                            final int e = edges.next();
                            return new WeightedDirectedEdgeImpl(currentVertex, e,
                                    MutableWeightedDirectedGraph.this.getEdgeWeight(currentVertex, e));
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
     * assert g.adjacentOut(g.size() - 1).isEmpty();
     * assert g.adjacentIn(g.size() - 1).isEmpty();
     * </code></pre>
     * <p>
     * Complexity: O(1)
     */
    public void addVertex() {
        this.inEdges.add(new HashMap<Integer, Double>());
        this.outEdges.add(new HashMap<Integer, Double>());
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
            final Map<Integer, Double> previousOut = outEdges.get(i);
            final Map<Integer, Double> newOut = new HashMap<Integer, Double>();
            for (Map.Entry<Integer, Double> e : previousOut.entrySet()) {
                if (e.getKey() > v) {
                    newOut.put(e.getKey() - 1, e.getValue());
                } else if (e.getKey() < v) {
                    newOut.put(e.getKey(), e.getValue());
                }
            }
            outEdges.set(i, newOut);

            final Map<Integer, Double> previousIn = inEdges.get(i);
            final Map<Integer, Double> newIn = new HashMap<Integer, Double>();
            for (Map.Entry<Integer, Double> e : previousIn.entrySet()) {
                if (e.getKey() > v) {
                    newIn.put(e.getKey() - 1, e.getValue());
                } else if (e.getKey() < v) {
                    newIn.put(e.getKey(), e.getValue());
                }
            }
            inEdges.set(i, newIn);
        }
        outEdges.remove(v);
        inEdges.remove(v);
    }

    /**
     * Adds an edge on this graph or updates the weight of an existing one.
     * <p>
     * Complexity: O(1)
     *
     * @param source the source of the edge
     * @param target the target of the edge
     * @param weight the weight of the edge
     * @return the previous weight of the edge from {@code source} to {@code target} or {@code null} if there was
     * previously no edge
     * @throws IllegalArgumentException  if {@code weight} is not finite
     * @throws IndexOutOfBoundsException if {@code source} or {@code target} are outside of {@code [O,V)}
     */
    public Double putEdge(int source, int target, double weight) {
        Graphs.requireWeightLegal(weight);
        final Double a = outEdges.get(source).put(target, weight);
        final Double b = inEdges.get(target).put(source, weight);
        assert a == null ? b == null : a.equals(b);
        return a;
    }

    /**
     * Remove an edge from this graph.
     * <p>
     * Complexity: O(1)
     *
     * @param source the source of the edge
     * @param target the target of the edge
     * @return the previous weight of the edge from {@code source} to {@code target} or {@code null} if there was
     * previously no edge
     * @throws IndexOutOfBoundsException if {@code source} or {@code target} are outside of {@code [O,V)}
     */
    public Double removeEdge(int source, int target) {
        final Double a = outEdges.get(source).remove(target);
        final Double b = inEdges.get(target).remove(source);
        assert a == null ? b == null : a.equals(b);
        return a;
    }

    /**
     * Construct and return a new unmodifiable {@link WeightedDirectedGraph} as a copy of this graph.
     * <p>
     * The object produced by this method is completely independent of this graph.
     * <p>
     * This method is equivalent to
     * <pre><code>
     * return new MutableWeightedDirectedGraph(this).asUnmodifiable();
     * </code></pre>
     * <p>
     * Complexity: O(V+E)
     *
     * @return a copy of this graph as a new unmodifiable {@link WeightedDirectedGraph}
     */
    public final WeightedDirectedGraph toImmutable() {
        return new MutableWeightedDirectedGraph(this).asUnmodifiable();
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
    public final WeightedDirectedGraph asUnmodifiable() {
        return new MutableWeightedDirectedGraph() {
            @Override
            public int size() {
                return MutableWeightedDirectedGraph.this.size();
            }

            @Override
            public Set<Integer> adjacentOut(int v) {
                return MutableWeightedDirectedGraph.this.adjacentOut(v);
            }

            @Override
            public Set<Integer> adjacentIn(int v) {
                return MutableWeightedDirectedGraph.this.adjacentIn(v);
            }

            @Override
            public double getEdgeWeight(int source, int target) {
                return MutableWeightedDirectedGraph.this.getEdgeWeight(source, target);
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
                return MutableWeightedDirectedGraph.this.size();
            }

            @Override
            public Set<Integer> adjacentOut(int v) {
                return MutableWeightedDirectedGraph.this.adjacentOut(v);
            }

            @Override
            public Set<Integer> adjacentIn(int v) {
                return MutableWeightedDirectedGraph.this.adjacentIn(v);
            }

            @Override
            public void addVertex() {
                MutableWeightedDirectedGraph.this.addVertex();
            }

            @Override
            public void removeVertex(int v) {
                MutableWeightedDirectedGraph.this.removeVertex(v);
            }

            @Override
            public boolean putEdge(int source, int target) {
                return MutableWeightedDirectedGraph.this.putEdge(source, target, 1.0) == null;
            }

            @Override
            public boolean removeEdge(int source, int target) {
                return MutableWeightedDirectedGraph.this.removeEdge(source, target) != null;
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
        sb.append(String.format("%s(%d) {%n", "WeightedDirectedGraph", this.size()));
        for (WeightedDirectedEdge e : edges()) {
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
        if (obj == null || !(obj instanceof WeightedDirectedGraph)) {
            return false;
        }
        final WeightedDirectedGraph that = (WeightedDirectedGraph) obj;
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
            for (int j : this.adjacentOut(i)) {
                hash += (j ^ new Double(this.getEdgeWeight(i, j)).hashCode());
            }
        }
        return hash;
    }
}
