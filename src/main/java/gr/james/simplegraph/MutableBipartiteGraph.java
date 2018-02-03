package gr.james.simplegraph;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * The {@link BipartiteGraph} implementation using adjacency lists.
 */
public class MutableBipartiteGraph implements BipartiteGraph {
    private static final long serialVersionUID = 1L;

    private final MutableGraph g;
    private final Set<Integer> a;
    private final Set<Integer> b;

    /**
     * Constructs a new empty {@link MutableBipartiteGraph}.
     * <p>
     * Complexity: O(1)
     */
    public MutableBipartiteGraph() {
        this.g = new MutableGraph();
        this.a = new HashSet<Integer>();
        this.b = new HashSet<Integer>();
    }

    /**
     * Constructs a new {@link MutableBipartiteGraph} as a copy of the given graph {@code g}.
     * <p>
     * Complexity: O(V+E)
     *
     * @param g the graph to copy
     * @throws NullPointerException if {@code g} is {@code null}
     */
    public MutableBipartiteGraph(BipartiteGraph g) {
        this.g = new MutableGraph(g);
        this.a = new HashSet<Integer>(g.setA());
        this.b = new HashSet<Integer>(g.setB());
        assert g.equals(this);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int size() {
        assert this.a.size() + this.b.size() == g.size();
        return this.g.size();
    }

    /**
     * {@inheritDoc}
     * <h2>Implementation note</h2>
     * The {@link Set} returned is directly backed by the graph and changes will reflect on that {@link Set}. A side
     * effect of this property is that the iterator, like any other, will throw
     * {@link java.util.ConcurrentModificationException} if elements are modified during iteration.
     *
     * @return {@inheritDoc}
     * @see #setB()
     */
    @Override
    public Set<Integer> setA() {
        return Collections.unmodifiableSet(a);
    }

    /**
     * {@inheritDoc}
     * <h2>Implementation note</h2>
     * The {@link Set} returned is directly backed by the graph and changes will reflect on that {@link Set}. A side
     * effect of this property is that the iterator, like any other, will throw
     * {@link java.util.ConcurrentModificationException} if elements are modified during iteration.
     *
     * @return {@inheritDoc}
     * @see #setA()
     */
    @Override
    public Set<Integer> setB() {
        return Collections.unmodifiableSet(a);
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
        return this.g.adjacent(v);
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
        return this.g.edges();
    }

    /**
     * Adds a vertex to set {@code A}.
     * <p>
     * This method adds a new unconnected vertex in the graph with ID equal to {@code size} and then increases the value
     * of {@code size} by one. The new vertex is also pushed to set {@code A}.
     * <p>
     * Complexity: O(1)
     */
    public void addVertexInA() {
        final int newID = g.size();
        g.addVertex();
        this.a.add(newID);
    }

    /**
     * Adds a vertex to set {@code B}.
     * <p>
     * This method adds a new unconnected vertex in the graph with ID equal to {@code size} and then increases the value
     * of {@code size} by one. The new vertex is also pushed to set {@code B}.
     * <p>
     * Complexity: O(1)
     */
    public void addVertexInB() {
        final int newID = g.size();
        g.addVertex();
        this.b.add(newID);
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
        this.g.removeVertex(v);
        for (int i = v + 1; i < size(); i++) {
            assert a.contains(i) ^ a.contains(i);
            if (a.contains(i)) {
                a.remove(i);
                a.add(i - 1);
            } else if (b.contains(i)) {
                b.remove(i);
                b.add(i - 1);
            }
        }
    }

    /**
     * Adds an edge on this graph.
     * <p>
     * Complexity: O(1)
     *
     * @param v one end of the edge
     * @param w the other end of the edge
     * @return {@code true} if there was previously no edge connecting {@code v} with {@code w}, otherwise {@code false}
     * @throws IndexOutOfBoundsException     if {@code v} or {@code w} are outside of {@code [O,V)}
     * @throws UnsupportedOperationException if {@code v} and {@code w} are both in set {@code A} or both in set {@code B}
     */
    public boolean putEdge(int v, int w) {
        if (a.contains(v) && a.contains(w)) {
            throw new UnsupportedOperationException();
        }
        if (b.contains(v) && b.contains(w)) {
            throw new UnsupportedOperationException();
        }
        return this.g.putEdge(v, w);
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
        return this.g.removeEdge(v, w);
    }

    /**
     * Construct and return a new unmodifiable {@link BipartiteGraph} as a copy of this graph.
     * <p>
     * The object produced by this method is completely independent of this graph.
     * <p>
     * This method is equivalent to
     * <pre><code>
     * return new MutableBipartiteGraph(this).asUnmodifiable();
     * </code></pre>
     * <p>
     * Complexity: O(V+E)
     *
     * @return a copy of this graph as a new unmodifiable {@link BipartiteGraph}
     */
    public final BipartiteGraph toImmutable() {
        return new MutableBipartiteGraph(this).asUnmodifiable();
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
    public final BipartiteGraph asUnmodifiable() {
        return new MutableBipartiteGraph() {
            @Override
            public int size() {
                return MutableBipartiteGraph.this.size();
            }

            @Override
            public Set<Integer> adjacent(int v) {
                return MutableBipartiteGraph.this.adjacent(v);
            }

            @Override
            public Set<Integer> setA() {
                return MutableBipartiteGraph.this.setA();
            }

            @Override
            public Set<Integer> setB() {
                return MutableBipartiteGraph.this.setB();
            }

            @Override
            public void addVertexInA() {
                throw new UnsupportedOperationException();
            }

            @Override
            public void addVertexInB() {
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
                return MutableBipartiteGraph.this.size();
            }

            @Override
            public Set<Integer> adjacentOut(int v) {
                return MutableBipartiteGraph.this.adjacent(v);
            }

            @Override
            public Set<Integer> adjacentIn(int v) {
                return MutableBipartiteGraph.this.adjacent(v);
            }

            @Override
            public void addVertex() {
                throw new UnsupportedOperationException();
            }

            @Override
            public void removeVertex(int v) {
                MutableBipartiteGraph.this.removeVertex(v);
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
                return MutableBipartiteGraph.this.size();
            }

            @Override
            public Set<Integer> getEdges(int v) {
                return MutableBipartiteGraph.this.adjacent(v);
            }

            @Override
            public double getEdgeWeight(int v, int w) {
                Graphs.requireEdgeExists(MutableBipartiteGraph.this, v, w);
                return 1.0;
            }

            @Override
            public void addVertex() {
                throw new UnsupportedOperationException();
            }

            @Override
            public void removeVertex(int v) {
                MutableBipartiteGraph.this.removeVertex(v);
            }

            @Override
            public Double putEdge(int v, int w, double weight) {
                throw new UnsupportedOperationException();
            }

            @Override
            public Double removeEdge(int v, int w) {
                return MutableBipartiteGraph.this.removeEdge(v, w) ? 1.0 : null;
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
                return MutableBipartiteGraph.this.size();
            }

            @Override
            public Set<Integer> getOutEdges(int v) {
                return MutableBipartiteGraph.this.adjacent(v);
            }

            @Override
            public Set<Integer> getInEdges(int v) {
                return MutableBipartiteGraph.this.adjacent(v);
            }

            @Override
            public double getEdgeWeight(int source, int target) {
                Graphs.requireEdgeExists(MutableBipartiteGraph.this, source, target);
                return 1.0;
            }

            @Override
            public void addVertex() {
                throw new UnsupportedOperationException();
            }

            @Override
            public void removeVertex(int v) {
                MutableBipartiteGraph.this.removeVertex(v);
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
        sb.append(String.format("%s(%d,%d+%d) {%n", "BipartiteGraph",
                this.size(),
                this.setA().size(),
                this.setB().size()));
        for (Edge e : edges()) {
            if (setA().contains(e.v())) {
                sb.append(String.format("  %s%n", e));
            } else {
                sb.append(String.format("  %s%n", e.swap()));
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
        return this.g.hashCode();
    }
}
