package gr.james.simplegraph;

import java.util.*;

/**
 * Represents an unweighted and directed graph implemented using adjacency lists.
 * <p>
 * The graph can contain self loops but cannot cannot contain parallel edges. More formally, any ordered pair of
 * endpoints may correspond to at most one edge.
 * <p>
 * An ordered pair {@code (a, b)} is a pair of objects where the order in which the objects appear in the pair is
 * significant: the ordered pair {@code (a, b)} is different from the ordered pair {@code (b, a)} unless {@code a = b}.
 * <p>
 * Memory Complexity: O(V+E)
 */
public class MutableDirectedGraph implements IDirectedGraph {
    private static final long serialVersionUID = 1L;

    private final List<Set<Integer>> outEdges;
    private final List<Set<Integer>> inEdges;

    /**
     * Construct a new empty {@link MutableDirectedGraph}.
     * <p>
     * Complexity: O(1)
     */
    public MutableDirectedGraph() {
        this(0);
    }

    /**
     * Construct a new empty {@link MutableDirectedGraph} with {@code n} vertices.
     * <p>
     * Complexity: O(n)
     *
     * @param n the amount of vertices to put in the graph
     * @throws IllegalArgumentException if {@code n < 0}
     */
    public MutableDirectedGraph(int n) {
        this.inEdges = new ArrayList<Set<Integer>>(n);
        this.outEdges = new ArrayList<Set<Integer>>(n);
        addVertices(n);
        assert size() == n;
    }

    /**
     * Construct a new {@link MutableDirectedGraph} as a copy of the given graph {@code g}.
     * <p>
     * This constructor will not copy the edge weights.
     * <p>
     * Complexity: O(V+E)
     *
     * @param g the graph to copy
     * @throws NullPointerException if {@code g} is {@code null}
     */
    public MutableDirectedGraph(MutableWeightedDirectedGraph g) {
        this(g.size());
        for (int v = 0; v < g.size(); v++) {
            for (int w : g.getOutEdges(v)) {
                putEdge(v, w);
            }
        }
    }

    /**
     * Construct a new {@link MutableDirectedGraph} as a copy of the given graph {@code g}.
     * <p>
     * This constructor will not copy the edge weights and will create two parallel directed edges for every edge in
     * {@code g}.
     * <p>
     * Complexity: O(V+E)
     *
     * @param g the graph to copy
     * @throws NullPointerException if {@code g} is {@code null}
     */
    public MutableDirectedGraph(MutableWeightedGraph g) {
        this(g.size());
        for (int v = 0; v < g.size(); v++) {
            for (int w : g.getEdges(v)) {
                putEdge(v, w);
            }
        }
    }

    /**
     * Construct a new {@link MutableDirectedGraph} as a copy of the given graph {@code g}.
     * <p>
     * Complexity: O(V+E)
     *
     * @param g the graph to copy
     * @throws NullPointerException if {@code g} is {@code null}
     */
    public MutableDirectedGraph(MutableDirectedGraph g) {
        this(g.size());
        for (int v = 0; v < g.size(); v++) {
            for (int w : g.getOutEdges(v)) {
                putEdge(v, w);
            }
        }
        assert this.equals(g);
    }

    /**
     * Construct a new {@link MutableDirectedGraph} as a copy of the given graph {@code g}.
     * <p>
     * This constructor will create two parallel directed edges for every edge in {@code g}.
     * <p>
     * Complexity: O(V+E)
     *
     * @param g the graph to copy
     * @throws NullPointerException if {@code g} is {@code null}
     */
    public MutableDirectedGraph(MutableGraph g) {
        this(g.size());
        for (int v = 0; v < g.size(); v++) {
            for (int w : g.getEdges(v)) {
                putEdge(v, w);
            }
        }
    }

    /**
     * Construct a new {@link MutableDirectedGraph} as a copy of the given graph {@code g}.
     * <p>
     * This constructor will not copy the edge weights.
     * <p>
     * Complexity: O(V+E)
     *
     * @param g the graph to copy
     * @throws NullPointerException if {@code g} is {@code null}
     */
    public MutableDirectedGraph(WeightedDirectedGraph g) {
        this(g.size());
        for (int v = 0; v < g.size(); v++) {
            for (int w : g.getOutEdges(v)) {
                putEdge(v, w);
            }
        }
    }

    /**
     * Construct a new {@link MutableDirectedGraph} as a copy of the given graph {@code g}.
     * <p>
     * This constructor will not copy the edge weights and will create two parallel directed edges for every edge in
     * {@code g}.
     * <p>
     * Complexity: O(V+E)
     *
     * @param g the graph to copy
     * @throws NullPointerException if {@code g} is {@code null}
     */
    public MutableDirectedGraph(WeightedGraph g) {
        this(g.size());
        for (int v = 0; v < g.size(); v++) {
            for (int w : g.getEdges(v)) {
                putEdge(v, w);
            }
        }
    }

    /**
     * Construct a new {@link MutableDirectedGraph} as a copy of the given graph {@code g}.
     * <p>
     * Complexity: O(V+E)
     *
     * @param g the graph to copy
     * @throws NullPointerException if {@code g} is {@code null}
     */
    public MutableDirectedGraph(DirectedGraph g) {
        this(g.size());
        for (int v = 0; v < g.size(); v++) {
            for (int w : g.getOutEdges(v)) {
                putEdge(v, w);
            }
        }
    }

    /**
     * Construct a new {@link MutableDirectedGraph} as a copy of the given graph {@code g}.
     * <p>
     * This constructor will create two parallel directed edges for every edge in {@code g}.
     * <p>
     * Complexity: O(V+E)
     *
     * @param g the graph to copy
     * @throws NullPointerException if {@code g} is {@code null}
     */
    public MutableDirectedGraph(Graph g) {
        this(g.size());
        for (int v = 0; v < g.size(); v++) {
            for (int w : g.getEdges(v)) {
                putEdge(v, w);
            }
        }
    }

    /**
     * Construct and return a new immutable {@link DirectedGraph} as a copy of this graph.
     * <p>
     * Complexity: O(V+E)
     *
     * @return a copy of this graph as a new {@link DirectedGraph}
     */
    public DirectedGraph toImmutable() {
        final MutableDirectedGraph g = new MutableDirectedGraph(this);
        return new DirectedGraph() {
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
        final Set<Integer> edges = this.outEdges.get(v);
        return Collections.unmodifiableSet(edges);
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
        final Set<Integer> edges = this.inEdges.get(v);
        return Collections.unmodifiableSet(edges);
    }

    /**
     * Add a vertex to the graph.
     * <p>
     * Complexity: O(1)
     */
    public void addVertex() {
        this.inEdges.add(new HashSet<Integer>());
        this.outEdges.add(new HashSet<Integer>());
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
            final Set<Integer> previousOut = outEdges.get(i);
            final Set<Integer> newOut = new HashSet<Integer>();
            for (Integer e : previousOut) {
                if (e > v) {
                    newOut.add(e - 1);
                } else if (e < v) {
                    newOut.add(e);
                }
            }
            outEdges.set(i, newOut);

            final Set<Integer> previousIn = inEdges.get(i);
            final Set<Integer> newIn = new HashSet<Integer>();
            for (Integer e : previousIn) {
                if (e > v) {
                    newIn.add(e - 1);
                } else if (e < v) {
                    newIn.add(e);
                }
            }
            inEdges.set(i, newIn);
        }
        outEdges.remove(v);
        inEdges.remove(v);
    }

    /**
     * Add an edge to this graph.
     * <p>
     * Complexity: O(1)
     *
     * @param source the source of the edge
     * @param target the target of the edge
     * @return {@code true} if there was previously no edge from {@code source} to {@code target}, otherwise
     * {@code false}
     * @throws IndexOutOfBoundsException if {@code source} or {@code target} are outside of {@code [O,V)}
     */
    public boolean putEdge(int source, int target) {
        final boolean a = outEdges.get(source).add(target);
        final boolean b = inEdges.get(target).add(source);
        assert a == b;
        return a;
    }

    /**
     * Remove an edge from this graph.
     * <p>
     * Complexity: O(1)
     *
     * @param source the source of the edge
     * @param target the target of the edge
     * @return {@code true} if there was previously an edge from {@code source} to {@code target}, otherwise
     * {@code false}
     * @throws IndexOutOfBoundsException if {@code source} or {@code target} are outside of {@code [O,V)}
     */
    public boolean removeEdge(int source, int target) {
        final boolean a = outEdges.get(source).remove(target);
        final boolean b = inEdges.get(target).remove(source);
        assert a == b;
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
        if (obj == null || !(obj instanceof MutableDirectedGraph)) {
            return false;
        }
        final MutableDirectedGraph that = (MutableDirectedGraph) obj;
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
