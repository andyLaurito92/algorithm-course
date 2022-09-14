package chapter4.undirectedGraphs;

import java.util.Queue;
import java.util.ArrayDeque;
import java.util.Stack;

public class BreadthFirstPaths implements Paths {
    private UndirectedGraph graph;
    private int source;
    private int[] distanceTo;
    private int[] edgeTo;

    public BreadthFirstPaths(UndirectedGraph g, int s) {
	graph = g;
	source = s;
	distanceTo = new int[g.V()];
	edgeTo = new int[g.V()];
	for (int j = 0; j < g.V(); j++) {
	    distanceTo[j] = -1;
	    edgeTo[j] = -1;
	}

	bfs(g, s);
    }

    private void bfs(UndirectedGraph g, int s) {
	Queue<Integer> queue = new ArrayDeque();
	queue.add(s);
	int current = s;
	distanceTo[s] = 0;
	while (!queue.isEmpty()) {
	    current = queue.remove();
	    for (int w : g.adj(current)) {
		if (distanceTo[w] == -1) {
		    queue.add(w);
		    edgeTo[w] = current;
		    distanceTo[w] = 1 + distanceTo[current];
		}
	    }
	}
    }

    public boolean hasPathTo(int v) {
	return distanceTo[v] != -1;
    }

    public Iterable<Integer> pathTo(int v) {
	Stack<Integer> path = new Stack<>();
	int current = v;
	while (current != source) {
	    path.push(current);
	    current = edgeTo[current];
	}
	path.push(current);
	return path;
    }
}
