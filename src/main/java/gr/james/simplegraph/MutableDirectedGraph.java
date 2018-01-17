package gr.james.simplegraph;

import java.io.Serializable;
import java.util.*;

/**
 * Represents a directed and unweighted graph implemented using adjacency lists.
 * <p>
 * The graph allows self loops but does not allow more than one edge from any set of endpoints.
 * <p>
 * Memory Complexity: O(V+E)
 */
public class MutableDirectedGraph implements Serializable {
    private static final long serialVersionUID = 1L;

    private final List<Set<Integer>> outEdges;
    private final List<Set<Integer>> inEdges;

    /**
     * Construct a new empty {@link MutableDirectedGraph} without any vertices.
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
    public MutableDirectedGraph(ImmutableWeightedDirectedGraph g) {
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
    public MutableDirectedGraph(ImmutableWeightedGraph g) {
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
    public MutableDirectedGraph(ImmutableDirectedGraph g) {
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
    public MutableDirectedGraph(ImmutableGraph g) {
        this(g.size());
        for (int v = 0; v < g.size(); v++) {
            for (int w : g.getEdges(v)) {
                putEdge(v, w);
            }
        }
    }

    private void checkVertex(int... x) {
        for (int i : x) {
            if (i < 0 || i >= size()) {
                throw new IndexOutOfBoundsException();
            }
        }
    }

    /**
     * Construct and return a new {@link DirectedGraph} as a copy of this graph.
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
     * Get the number of vertices in the graph.
     * <p>
     * Complexity: O(1)
     *
     * @return how many vertices there are in the graph
     */
    public int size() {
        assert this.inEdges.size() == this.outEdges.size();
        return this.inEdges.size();
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
        checkVertex(v);
        for (int i = 0; i < size(); i++) {
            Set<Integer> previousOut = outEdges.get(i);
            Set<Integer> newOut = new HashSet<Integer>();
            for (Integer e : previousOut) {
                if (e > v) {
                    newOut.add(e - 1);
                } else if (e < v) {
                    newOut.add(e);
                }
            }
            outEdges.set(i, newOut);

            Set<Integer> previousIn = inEdges.get(i);
            Set<Integer> newIn = new HashSet<Integer>();
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
     * Get the outbound edges of a vertex.
     * <p>
     * Complexity: O(1)
     *
     * @param v the vertex index to get the outbound edges of
     * @return an {@link Set} that holds all the outbound adjacent vertices of {@code v}
     * @throws IndexOutOfBoundsException if {@code v} is outside of {@code [O,V)}
     */
    public Set<Integer> getOutEdges(int v) {
        final Set<Integer> edges = this.outEdges.get(v);
        return Collections.unmodifiableSet(edges);
    }

    /**
     * Get the inbound edges of a vertex.
     * <p>
     * Complexity: O(1)
     *
     * @param v the vertex index to get the inbound edges of
     * @return an {@link Set} that holds all the inbound adjacent vertices of {@code v}
     * @throws IndexOutOfBoundsException if {@code v} is outside of {@code [O,V)}
     */
    public Set<Integer> getInEdges(int v) {
        final Set<Integer> edges = this.inEdges.get(v);
        return Collections.unmodifiableSet(edges);
    }

    /**
     * Returns a string representation of the graph.
     * <p>
     * Complexity: O(V+E)
     *
     * @return a string representation of the graph
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(String.format("%s(%d) {%n", this.getClass().getSimpleName(), size()));
        for (int i = 0; i < size(); i++) {
            for (int adj : getOutEdges(i)) {
                sb.append(String.format("  %d -> %d%n", i, adj));
            }
        }
        sb.append("}");
        return sb.toString();
    }

    /**
     * Indicates whether some other object is equal to this graph.
     * <p>
     * Two graphs are equal if they are of same type, have the same number of vertices and their edges represent the
     * same mapping.
     * <p>
     * Complexity: O(V+E)
     *
     * @param obj the reference object with which to compare
     * @return {@code true} if this graph is equal to the {@code obj} argument, otherwise {@code false}
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final MutableDirectedGraph that = (MutableDirectedGraph) obj;
        return outEdges.equals(that.outEdges) && inEdges.equals(that.inEdges);
    }

    /**
     * Returns a hash code value for this graph.
     * <p>
     * Complexity: O(V+E)
     *
     * @return a hash code value for this graph
     */
    @Override
    public int hashCode() {
        return Arrays.hashCode(new Object[]{outEdges, inEdges});
    }
}
