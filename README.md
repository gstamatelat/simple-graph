# Simple Graph

**Simple Graph** is a graph interface for Java 6 that is designed to expose a
very simple API to support working with graphs. The API includes interfaces and
implementations for weighted, unweighted, directed and undirected graphs.
**Simple Graph** does not include algorithm implementations and is meant to be
used as a playground for graph-theoretic abstractions. This package can be used
when Java 6 is required, for assignments, simple projects etc.

### Capabilities

For simplicity, vertices are, for each graph instance, represented using
integers in the range `[0,V)`. There is no inherent edge value notion; all
edges are anonymous but can have a weight assigned on weighted graph types.

Exceptions are used in a fail-fast approach when certain invocations don't make
sense, for example trying to access the adjacent nodes of a vertex that doesn't
exist or when an argument is `null`. These exceptions exist to augment the
robustness of a client program and should not be catched.

All graph classes in this package implement the `Serializable` interface as well
as the `equals`, `hashCode` and `toString` methods in a semantically reasonable
way.

## API Summary

An overview of the public API of **Simple Graph** is presented in the following
tables:

### Base Graphs

| Interface               | Implementation                 | Directed | Weighted |
| :---------------------- | :----------------------------- | :------- | :------- |
| `Graph`                 | `MutableGraph`                 |          |          |
| `DirectedGraph`         | `MutableDirectedGraph`         | &#10004; |          |
| `WeightedGraph`         | `MutableWeightedGraph`         |          | &#10004; |
| `DirectedWeightedGraph` | `MutableDirectedWeightedGraph` | &#10004; | &#10004; |

### Derived Graphs

| Interface               | Implementation                 | Base Type |
| :---------------------- | :----------------------------- | :-------- |
| `BipartiteGraph`        | `MutableBipartiteGraph`        | `Graph`   |
| `Tree`                  | `MutableTree`                  | `Graph`   |

## Getting started

You can add a dependency from your project using Maven:

```xml
<dependency>
  <groupId>gr.james</groupId>
  <artifactId>simple-graph</artifactId>
  <version>0.16</version>
</dependency>
```

Or using Gradle:

```gradle
implementation 'gr.james:simple-graph:0.16' // Runtime
api            'gr.james:simple-graph:0.16' // Public API
```

For some usage examples see the files inside the
[demo](src/main/java/gr/james/simplegraph/demo) directory.
