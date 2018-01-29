# Simple Graph

**Simple Graph** is a graph interface for Java 6 that is designed to expose a
very simple API to support working with graphs. The API includes mutable and
immutable graph classes for weighted, unweighted, directed and undirected
graphs. **Simple Graph** does not include algorithm implementations and is meant
to be used as a playground for graph-theoretic abstractions. This package can be
used when Java 6 is required, for assignments, simple projects etc.

For simplicity, vertices are, for each graph instance, represented using
integers in the range `[0,V)`. There is no inherent edge object notion; all
edges are anonymous but can have a weight assigned on weighted graph types.

Exceptions are used in a fail-fast approach when certain invocations don't make
sense, for example trying to access the adjacent nodes of a vertex that doesn't
exist or when an argument is `null`. These exceptions exist to augment the
robustness of a client program and should not be catched.

All graph classes in this package implement the `Serializable` interface as well
as the `equals`, `hashCode` and `toString` methods in a semantically reasonable
way.

## API Summary

An overview of the classes in the public API of **Simple Graph** is presented in
the following table:

| Class                          | Directed | Weighted | Mutable  |
| :----------------------------- | :------- | :------- | :------- |
| `Graph`                        |          |          |          |
| `MutableGraph`                 |          |          | &#10004; |
| `DirectedGraph`                | &#10004; |          |          |
| `MutableDirectedGraph`         | &#10004; |          | &#10004; |
| `WeightedGraph`                |          | &#10004; |          |
| `MutableWeightedGraph`         |          | &#10004; | &#10004; |
| `DirectedWeightedGraph`        | &#10004; | &#10004; |          |
| `MutableDirectedWeightedGraph` | &#10004; | &#10004; | &#10004; |

## Using

Simple Graph is published to
[jcenter](https://bintray.com/gstamatelat/simple-graph/simple-graph). You can
add a dependency from your project using Maven:

```xml
<dependency>
  <groupId>gr.james</groupId>
  <artifactId>simple-graph</artifactId>
  <version>0.10</version>
</dependency>
```

Or using Gradle:

```
compile 'gr.james:simple-graph:0.10'
```

## Getting Started

For some usage examples see the files inside the
[examples](src/main/java/gr/james/simplegraph/examples) directory.
