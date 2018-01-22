# Simple Graph

A simple graph interface for Java 6.

Useful when Java 6 is required, for assignments, simple projects etc.

Graph classes in this package properly implement the `Serializable` interface as
well as the `equals`, `hashCode` and `toString` methods.

## API Summary

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
  <version>0.7</version>
</dependency>
```

Or using Gradle:

```
compile 'gr.james:simple-graph:0.7'
```

## Getting Started

For some usage examples see the files inside the
[examples](src/main/java/gr/james/simplegraph/examples) directory.
