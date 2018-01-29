/**
 * The graph interfaces and the respective mutable and immutable implementations.
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
 * <h2>Mutable and immutable types</h2>
 * All interfaces have a {@code Mutable*} and an {@code Immutable*} implementation.
 * <p>
 * Mutable graphs should mainly be treated as graph builders because they have methods that support mutation, like
 * vertex/edge addition/removal etc. Instances of immutable graphs can be created only using the method
 * {@code toImmutable()} of the respective mutable graph. For example
 * {@link gr.james.simplegraph.MutableGraph#toImmutable()} will create a copy of the graph and return it as a
 * {@link gr.james.simplegraph.ImmutableGraph}. Mutable graphs have various copy constructors from compatible types. For
 * example, {@link gr.james.simplegraph.MutableGraph#MutableGraph(WeightedGraph)} will construct a
 * {@link gr.james.simplegraph.MutableGraph} from a copy of the argument.
 * <p>
 * Immutable graphs do not have mutation methods and are, for example, appropriate to use as arguments to algorithms.
 * Immutable graphs guarantee thread safety and deterministic iteration and behavior.
 */
package gr.james.simplegraph;
