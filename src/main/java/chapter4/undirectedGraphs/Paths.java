package chapter4.undirectedGraphs;

public interface Paths {
    boolean hasPathTo(int v);

    Iterable<Integer> pathTo(int v);
}
