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
     * Construct a new empty {@link MutableWeightedDirectedGraph}.
     * <p>
     * Complexity: O(1)
     */
    public MutableWeightedDirectedGraph() {
        this(0);
    }

    /**
     * Construct a new empty {@link MutableWeightedDirectedGraph} with {@code n} vertices.
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
     * Construct a new {@link MutableWeightedDirectedGraph} as a copy of the given graph {@code g}.
     * <p>
     * Complexity: O(V+E)
     *
     * @param g the graph to copy
     * @throws NullPointerException if {@code g} is {@code null}
     */
    public MutableWeightedDirectedGraph(WeightedDirectedGraph g) {
        this(g.size());
        for (int v = 0; v < g.size(); v++) {
            for (int w : g.getOutEdges(v)) {
                putEdge(v, w, g.getEdgeWeight(v, w));
            }
        }
        assert this.equals(g);
    }

    /**
     * Construct a new {@link MutableWeightedDirectedGraph} as a copy of the given graph {@code g}.
     * <p>
     * This constructor will set the edge weights to {@code 1.0}.
     * <p>
     * Complexity: O(V+E)
     *
     * @param g the graph to copy
     * @throws NullPointerException if {@code g} is {@code null}
     */
    public MutableWeightedDirectedGraph(DirectedGraph g) {
        this(g.size());
        for (int v = 0; v < g.size(); v++) {
            for (int w : g.getOutEdges(v)) {
                putEdge(v, w, 1.0);
            }
        }
    }

    /**
     * Construct a new {@link MutableWeightedDirectedGraph} as a copy of the given graph {@code g}.
     * <p>
     * This constructor will create two parallel directed edges for every edge in {@code g}.
     * <p>
     * Complexity: O(V+E)
     *
     * @param g the graph to copy
     * @throws NullPointerException if {@code g} is {@code null}
     */
    public MutableWeightedDirectedGraph(WeightedGraph g) {
        this(g.size());
        for (int v = 0; v < g.size(); v++) {
            for (int w : g.getEdges(v)) {
                putEdge(v, w, g.getEdgeWeight(v, w));
            }
        }
    }

    /**
     * Construct a new {@link MutableWeightedDirectedGraph} as a copy of the given graph {@code g}.
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
        this(g.size());
        for (int v = 0; v < g.size(); v++) {
            for (int w : g.getEdges(v)) {
                putEdge(v, w, 1.0);
            }
        }
    }

    /**
     * Construct and return a new unmodifiable {@link WeightedDirectedGraph} as a copy of this graph.
     * <p>
     * The object produced by this method is completely independent of this graph.
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
            public Set<Integer> getOutEdges(int v) {
                return MutableWeightedDirectedGraph.this.getOutEdges(v);
            }

            @Override
            public Set<Integer> getInEdges(int v) {
                return MutableWeightedDirectedGraph.this.getInEdges(v);
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
            public void addVertices(int n) {
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
            public Set<Integer> getOutEdges(int v) {
                return MutableWeightedDirectedGraph.this.getOutEdges(v);
            }

            @Override
            public Set<Integer> getInEdges(int v) {
                return MutableWeightedDirectedGraph.this.getInEdges(v);
            }

            @Override
            public void addVertex() {
                MutableWeightedDirectedGraph.this.addVertex();
            }

            @Override
            public void addVertices(int n) {
                MutableWeightedDirectedGraph.this.addVertices(n);
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
    public final MutableWeightedDirectedGraph asWeightedDirected() {
        return this;
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
     *
     * @param v {@inheritDoc}
     * @return {@inheritDoc}
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    @Override
    public Set<Integer> getOutEdges(int v) {
        final Map<Integer, Double> edges = this.outEdges.get(v);
        return Collections.unmodifiableSet(edges.keySet());
    }

    /**
     * {@inheritDoc}
     *
     * @param v {@inheritDoc}
     * @return {@inheritDoc}
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    @Override
    public Set<Integer> getInEdges(int v) {
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
        Graphs.checkVertex(this, target);
        final Double weight = outEdges.get(source).get(target);
        if (weight == null) {
            throw new IllegalArgumentException();
        }
        assert weight.equals(inEdges.get(target).get(source));
        assert Graphs.isWeightLegal(weight);
        return weight;
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
     * assert g.getOutEdges(g.size() - 1).isEmpty();
     * assert g.getInEdges(g.size() - 1).isEmpty();
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
        Graphs.checkWeight(weight);
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
        return Graphs.hashCode(this);
    }
}
