/**
 * The graph interfaces and the respective implementations.
 * <h2>Vertices and edges</h2>
 * For simplicity, vertices are, for each graph instance, represented using integers in the range {@code [0,V)}. There
 * is no inherent edge object notion; all edges are anonymous but can have a weight assigned on weighted graph types.
 * <h2>Compliance</h2>
 * All graph classes in this package implement the {@link java.io.Serializable} interface as well as the
 * {@link java.lang.Object#equals(java.lang.Object)}, {@link java.lang.Object#hashCode()} and
 * {@link java.lang.Object#toString()} methods in a semantically reasonable way.
 * <h2>Exceptions</h2>
 * Exceptions are used in a fail-fast approach when certain invocations don't make sense, for example trying to access
 * the adjacent nodes of a vertex that doesn't exist or when an argument is {@code null}. These exceptions exist to
 * augment the robustness of a client program and should not be catched.
 * <h2>Mutable/Immutable/Unmodifiable graphs</h2>
 * All interfaces have a {@code Mutable*} implementation that allows mutation of the object, like vertex/edge
 * addition/removal etc. For example, the {@link gr.james.simplegraph.Graph} interface has the
 * {@link gr.james.simplegraph.MutableGraph} implementation. The interfaces themselves do not have mutation methods.
 * Mutable graphs have various copy constructors from compatible types. For
 * example, {@link gr.james.simplegraph.MutableGraph#MutableGraph(WeightedGraph)} will construct a
 * {@link gr.james.simplegraph.MutableGraph} from a copy of the argument.
 * <p>
 * Instances of unmodifiable graphs can be created using the {@code asUnmodifiable()} method of the respective mutable
 * graph. This method will return a decorator graph that throws {@link java.lang.UnsupportedOperationException} when
 * mutation methods are invoked. It is recommended to use when the client does not have access to the original graph
 * reference.
 * <p>
 * Instances of immutable graphs can be created using the {@code toImmutable()} method, which will first copy the graph
 * before decorating it. Due to the underlying copy, this method requires linear time. In essence, the
 * {@code toImmutable()} method is equivalent to performing a deep copy of the graph and then calling the
 * {@code asUnmodifiable()} method. Therefore, the graph produced by this method is completely independent of the
 * original graph, whereas the {@code asUnmodifiable()} method merely returns a wrapper bound to the original graph.
 * <h2>Primitive operations on graphs</h2>
 * There is a class {@link gr.james.simplegraph.Graphs} with static helper methods that operate on graphs.
 */
package gr.james.simplegraph;
