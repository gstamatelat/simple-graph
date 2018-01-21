package gr.james.simplegraph;

import java.io.Serializable;
import java.util.*;

/**
 * Represents an undirected and weighted graph implemented using adjacency lists.
 * <p>
 * The graph allows self loops but does not allow more than one edge from any set of endpoints. The graph only allows
 * finite {@link Double} values as edge weights.
 * <p>
 * Memory Complexity: O(V+E)
 */
public class MutableWeightedGraph implements IWeightedGraph, Serializable {
    private static final long serialVersionUID = 1L;

    private final List<Map<Integer, Double>> edges;

    /**
     * Construct a new empty {@link MutableWeightedGraph} without any vertices.
     * <p>
     * Complexity: O(1)
     */
    public MutableWeightedGraph() {
        this(0);
    }

    /**
     * Construct a new empty {@link MutableWeightedGraph} with {@code n} vertices.
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
     * Construct a new {@link MutableWeightedGraph} as a copy of the given graph {@code g}.
     * <p>
     * Complexity: O(V+E)
     *
     * @param g the graph to copy
     * @throws NullPointerException if {@code g} is {@code null}
     */
    public MutableWeightedGraph(MutableWeightedGraph g) {
        this(g.size());
        for (int v = 0; v < g.size(); v++) {
            for (int w : g.getEdges(v)) {
                putEdge(v, w, g.getEdgeWeight(v, w));
            }
        }
        assert this.equals(g);
    }

    /**
     * Construct a new {@link MutableWeightedGraph} as a copy of the given graph {@code g}.
     * <p>
     * This constructor will set the edge weights to {@code 1.0}.
     * <p>
     * Complexity: O(V+E)
     *
     * @param g the graph to copy
     * @throws NullPointerException if {@code g} is {@code null}
     */
    public MutableWeightedGraph(MutableGraph g) {
        this(g.size());
        for (int v = 0; v < g.size(); v++) {
            for (int w : g.getEdges(v)) {
                putEdge(v, w, 1.0);
            }
        }
    }

    /**
     * Construct a new {@link MutableWeightedGraph} as a copy of the given graph {@code g}.
     * <p>
     * Complexity: O(V+E)
     *
     * @param g the graph to copy
     * @throws NullPointerException if {@code g} is {@code null}
     */
    public MutableWeightedGraph(WeightedGraph g) {
        this(g.size());
        for (int v = 0; v < g.size(); v++) {
            for (int w : g.getEdges(v)) {
                putEdge(v, w, g.getEdgeWeight(v, w));
            }
        }
    }

    /**
     * Construct a new {@link MutableWeightedGraph} as a copy of the given graph {@code g}.
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
        for (int v = 0; v < g.size(); v++) {
            for (int w : g.getEdges(v)) {
                putEdge(v, w, 1.0);
            }
        }
    }

    /**
     * Construct and return a new immutable {@link WeightedGraph} as a copy of this graph.
     * <p>
     * Complexity: O(V+E)
     *
     * @return a copy of this graph as a new {@link WeightedGraph}
     */
    public WeightedGraph toImmutable() {
        final MutableWeightedGraph g = new MutableWeightedGraph(this);
        return new WeightedGraph() {
            @Override
            public int size() {
                return g.size();
            }

            @Override
            public Set<Integer> getEdges(int v) {
                return g.getEdges(v);
            }

            @Override
            public double getEdgeWeight(int v, int w) {
                return g.getEdgeWeight(v, w);
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
        Graphs.checkVertex(this, w);
        final Double weight = edges.get(v).get(w);
        if (weight == null) {
            throw new IllegalArgumentException();
        }
        assert weight.equals(edges.get(w).get(v));
        assert Graphs.isWeightLegal(weight);
        return weight;
    }

    /**
     * Add a vertex to the graph.
     * <p>
     * Complexity: O(1)
     */
    public void addVertex() {
        this.edges.add(new HashMap<Integer, Double>());
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
            Map<Integer, Double> previousOut = edges.get(i);
            Map<Integer, Double> newOut = new HashMap<Integer, Double>();
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
     * Add or update an edge to this graph.
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
        Graphs.checkWeight(weight);
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
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(String.format("%s(%d) {%n", this.getClass().getSimpleName(), size()));
        for (int i = 0; i < size(); i++) {
            for (int adj : getEdges(i)) {
                if (adj >= i) {
                    sb.append(String.format("  %d -- %d [%.2f]%n", i, adj, getEdgeWeight(i, adj)));
                }
            }
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
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final MutableWeightedGraph that = (MutableWeightedGraph) obj;
        return edges.equals(that.edges);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return edges.hashCode();
    }
}
