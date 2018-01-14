# Simple Graph

A simple graph interface for Java 6.

Useful when Java 6 is required, for assignments, simple projects etc.

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

Simple Graph is published to [jcenter](https://bintray.com/gstamatelat/simple-graph/simple-graph). You can add a
dependency from your project as follows:

Using Maven

```xml
<dependency>
  <groupId>gr.james</groupId>
  <artifactId>simple-graph</artifactId>
  <version>0.3</version>
</dependency>
```

Using Gradle

```
compile 'gr.james:simple-graph:0.3'
```

## Examples

For some usage examples see the files inside the
[examples](https://github.com/gstamatelat/simple-graph/tree/master/src/main/java/gr/james/simplegraph/examples)
directory.
