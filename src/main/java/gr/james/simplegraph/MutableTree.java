package gr.james.simplegraph;

import java.util.Set;

/**
 * The {@link Tree} implementation using adjacency lists.
 */
public class MutableTree implements Tree {
    private static final long serialVersionUID = 1L;

    private final MutableGraph g;

    /**
     * Constructs a new {@link MutableTree} with one unconnected vertex.
     * <p>
     * Complexity: O(1)
     */
    public MutableTree() {
        this.g = new MutableGraph(1);
    }

    /**
     * Constructs a new {@link MutableTree} as a copy of the given graph {@code g}.
     * <p>
     * Complexity: O(V+E)
     *
     * @param g the graph to copy
     * @throws NullPointerException if {@code g} is {@code null}
     */
    public MutableTree(Tree g) {
        this.g = new MutableGraph(g);
        assert g.equals(this);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int size() {
        return this.g.size();
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public final int edgeCount() {
        return size() - 1;
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
     * Adds a vertex to the graph and connected it with a given existing vertex.
     * <p>
     * Complexity: O(1)
     *
     * @param v the existing vertex with which to connect the new one
     * @throws IndexOutOfBoundsException if {@code v} is not an existing vertex
     */
    public void addVertex(int v) {
        this.g.addVertex();
        this.g.putEdge(g.size() - 1, v);
    }

    /**
     * Removes a vertex along with all of its edges from the graph.
     * <p>
     * In order to preserve the tree data structure invariants, this method can only remove leaf vertices, that is
     * vertices with exactly one adherent edge because this is the only way for the graph to remain a tree. The method
     * will return {@code true} if {@code v} is a leaf vertex and was able to be removed, otherwise will return
     * {@code false}.
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
     * @return {@code true} if {@code v} was removed from the graph, otherwise {@code false}
     * @throws IndexOutOfBoundsException if {@code v} is outside of {@code [O,V)}
     */
    public boolean removeVertex(int v) {
        if (adjacent(v).size() == 1) {
            this.g.removeVertex(v);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Construct and return a new unmodifiable {@link Tree} as a copy of this graph.
     * <p>
     * The object produced by this method is completely independent of this graph.
     * <p>
     * This method is equivalent to
     * <pre><code>
     * return new MutableTree(this).asUnmodifiable();
     * </code></pre>
     * <p>
     * Complexity: O(V+E)
     *
     * @return a copy of this graph as a new unmodifiable {@link Tree}
     */
    public final Tree toImmutable() {
        return new MutableTree(this).asUnmodifiable();
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
    public final Tree asUnmodifiable() {
        return new MutableTree() {
            @Override
            public int size() {
                return MutableTree.this.size();
            }

            @Override
            public Set<Integer> adjacent(int v) {
                return MutableTree.this.adjacent(v);
            }

            @Override
            public void addVertex(int v) {
                throw new UnsupportedOperationException();
            }

            @Override
            public boolean removeVertex(int v) {
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
                return MutableTree.this.size();
            }

            @Override
            public Set<Integer> adjacentOut(int v) {
                return MutableTree.this.adjacent(v);
            }

            @Override
            public Set<Integer> adjacentIn(int v) {
                return MutableTree.this.adjacent(v);
            }

            @Override
            public void addVertex() {
                throw new UnsupportedOperationException();
            }

            @Override
            public void removeVertex(int v) {
                if (!MutableTree.this.removeVertex(v)) {
                    throw new UnsupportedOperationException();
                }
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
                return MutableTree.this.size();
            }

            @Override
            public Set<Integer> adjacent(int v) {
                return MutableTree.this.adjacent(v);
            }

            @Override
            public double getEdgeWeight(int v, int w) {
                Graphs.requireEdgeExists(MutableTree.this, v, w);
                return 1.0;
            }

            @Override
            public void addVertex() {
                throw new UnsupportedOperationException();
            }

            @Override
            public void removeVertex(int v) {
                if (!MutableTree.this.removeVertex(v)) {
                    throw new UnsupportedOperationException();
                }
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
                return MutableTree.this.size();
            }

            @Override
            public Set<Integer> adjacentOut(int v) {
                return MutableTree.this.adjacent(v);
            }

            @Override
            public Set<Integer> adjacentIn(int v) {
                return MutableTree.this.adjacent(v);
            }

            @Override
            public double getEdgeWeight(int source, int target) {
                Graphs.requireEdgeExists(MutableTree.this, source, target);
                return 1.0;
            }

            @Override
            public void addVertex() {
                throw new UnsupportedOperationException();
            }

            @Override
            public void removeVertex(int v) {
                if (!MutableTree.this.removeVertex(v)) {
                    throw new UnsupportedOperationException();
                }
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
        sb.append(String.format("%s(%d) {%n", "Tree", this.size()));
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
        return this.g.hashCode();
    }
}
