/**
 ** We will be using ints to represent nodes so we can do an index-based search. 
 ** Remember that we should always be able to map general vertexs to ints by 
 ** using a map
 ** Note: We are decoupling the graph representation from the graph processing
 **/
package chapter4.undirectedGraphs;

public interface UndirectedGraph {
    void addEdge(int v, int w);

    Iterable<Integer> adj(int v); // Vertices adjacent to v

    int V(); // All vertices

    int E(); // Number of edges

    boolean edgeBetween(int v, int w);

    int degree(int v);

    String toString(); // String representation
}
