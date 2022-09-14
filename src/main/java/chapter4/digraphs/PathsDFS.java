package chapter4.digraphs;

import java.util.ArrayList;

public class PathsDFS implements Paths {
    private boolean[] marked;
    private int[] edgeTo;
    private ArrayList<Integer>[] paths;
    private Digraph g;
    private int s;

    public static Paths createFrom(Digraph g, int s) {
		return new PathsDFS(g, s);
    }

    public PathsDFS(Digraph g, int s) {
		this.g = g;
		this.s = s;
		paths = (ArrayList<Integer>[]) new ArrayList[g.V()];
		marked = new boolean[g.V()];
		edgeTo = new int[g.V()];

		dfs(g, s);
    }

    public boolean hasPathTo(int w) {
		return marked[w];
    }

    public Iterable<Integer> pathTo(int w) {
		if (!marked[w]) return null;
		if (paths[w] != null) return paths[w];

		ArrayList<Integer> pathTo = new ArrayList<>();

		int currentVertex = w;
		while (currentVertex != s) {
			pathTo.add(0, currentVertex);
			currentVertex = edgeTo[currentVertex];
		}
		pathTo.add(0, currentVertex);

		paths[w] = pathTo;
		return pathTo;
    }

    private void dfs(Digraph g, int s) {
		marked[s] = true;
		for (int w : g.adj(s)) {
			if (!marked[w]) {
			dfs(g, w);
			edgeTo[w] = s;
			}
		}
    }

    private void printArray(int[] array) {
		StringBuilder str = new StringBuilder();
		for (int v = 0; v < array.length; v++) {
			str.append(String.format("%d edgeTo is %d", v, array[v]));
			str.append("\n");
		}
		System.out.println(str.toString());
    }
}
