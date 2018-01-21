package gr.james.simplegraph;

import java.util.*;

/**
 * Represents a weighted and directed graph implemented using adjacency lists.
 * <p>
 * The graph allows self loops but does not allow more than one edge from any set of endpoints. The graph only allows
 * finite {@link Double} values as edge weights.
 * <p>
 * Memory Complexity: O(V+E)
 */
public class MutableWeightedDirectedGraph implements IWeightedDirectedGraph {
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
    public MutableWeightedDirectedGraph(MutableWeightedDirectedGraph g) {
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
    public MutableWeightedDirectedGraph(MutableDirectedGraph g) {
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
    public MutableWeightedDirectedGraph(MutableWeightedGraph g) {
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
    public MutableWeightedDirectedGraph(MutableGraph g) {
        this(g.size());
        for (int v = 0; v < g.size(); v++) {
            for (int w : g.getEdges(v)) {
                putEdge(v, w, 1.0);
            }
        }
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
     * Construct and return a new immutable {@link WeightedDirectedGraph} as a copy of this graph.
     * <p>
     * Complexity: O(V+E)
     *
     * @return a copy of this graph as a new {@link WeightedDirectedGraph}
     */
    public WeightedDirectedGraph toImmutable() {
        final MutableWeightedDirectedGraph g = new MutableWeightedDirectedGraph(this);
        return new WeightedDirectedGraph() {
            @Override
            public int size() {
                return g.size();
            }

            @Override
            public Set<Integer> getOutEdges(int v) {
                return g.getOutEdges(v);
            }

            @Override
            public Set<Integer> getInEdges(int v) {
                return g.getInEdges(v);
            }

            @Override
            public double getEdgeWeight(int source, int target) {
                return g.getEdgeWeight(source, target);
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
     * Add a vertex to the graph.
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
     * Remove a vertex along with all of its edges from the graph.
     * <p>
     * Complexity: O(V+E)
     *
     * @param v the vertex to remove from the graph
     * @throws IndexOutOfBoundsException if {@code v} is outside of {@code [O,V)}
     */
    public void removeVertex(int v) {
        Graphs.checkVertex(this, v);
        for (int i = 0; i < size(); i++) {
            Map<Integer, Double> previousOut = outEdges.get(i);
            Map<Integer, Double> newOut = new HashMap<Integer, Double>();
            for (Map.Entry<Integer, Double> e : previousOut.entrySet()) {
                if (e.getKey() > v) {
                    newOut.put(e.getKey() - 1, e.getValue());
                } else if (e.getKey() < v) {
                    newOut.put(e.getKey(), e.getValue());
                }
            }
            outEdges.set(i, newOut);

            Map<Integer, Double> previousIn = inEdges.get(i);
            Map<Integer, Double> newIn = new HashMap<Integer, Double>();
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
     * Add or update an edge to this graph.
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
    public String toString() {
        return Graphs.toString(this);
    }

    /**
     * {@inheritDoc}
     *
     * @param obj {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof MutableWeightedDirectedGraph)) {
            return false;
        }
        final MutableWeightedDirectedGraph that = (MutableWeightedDirectedGraph) obj;
        return Graphs.equals(this, that);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Graphs.hashCode(this);
    }
}
