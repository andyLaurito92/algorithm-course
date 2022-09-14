package chapter4.digraphs;

// Implementation is same as Graph, using adjacency lists
// Note that when using adjacency lists, in each vertex you
// store the vertices that v points at;
// This means that if you want to look for all vertices that point TO
// v, you have to go trought all edges, all vertices ==> O(V + E)
public interface Digraph {
    void addEdge(int v, int w);

    Iterable<Integer> adj(int v);

    int V();

    int E();

    Digraph reverse();

    String toString();
}
