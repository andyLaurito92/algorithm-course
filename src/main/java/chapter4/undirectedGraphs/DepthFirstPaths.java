package chapter4.undirectedGraphs;

import chapter4.digraphs.Paths;

import java.util.Stack;

public class DepthFirstPaths implements Paths {
    private UndirectedGraph graph;
    private int source;
    private boolean[] marked;
    private int[] edgeTo;
    
    public DepthFirstPaths(UndirectedGraph g, int s) {
		graph = g;
		source = s;

		marked = new boolean[g.V()];
		edgeTo = new int[g.V()];
		for (int j = 0; j < graph.V(); j++) {
			marked[j] = false;
			edgeTo[j] = -1;
		}
		dfs(g, s);
    }

    private void dfs(UndirectedGraph g, int source) {
		marked[source] = true;
		for (int w : g.adj(source)) {
			if (!marked[w]) {
			dfs(g, w);
			edgeTo[w] = source;
			}
		}
    }

    private void iterativeDfs(UndirectedGraph g, int source) {
		Stack<Integer> unseen = new Stack<>();
		unseen.push(source);
		while (!unseen.isEmpty()) {
			int current = unseen.pop();
			marked[current] = true;
			for (int w : g.adj(current)) {
			if (!marked[w]) {
				unseen.push(w);
				edgeTo[w] = current;
			}
			}
		}
    }

    public boolean hasPathTo(int v) {
		return marked[v];
    }

    public Iterable<Integer> pathTo(int v) {
		if (!hasPathTo(v)) return null;

		Stack<Integer> path = new Stack<>();
		path.push(v);
		int current = edgeTo[v];
		while (current != source) {
			path.push(current);
			current = edgeTo[current];
		}
		path.push(current);
		return path;
    }
}
