package chapter4.digraphs;

public interface Paths {
    boolean hasPathTo(int w);
    Iterable<Integer> pathTo(int w);
}
