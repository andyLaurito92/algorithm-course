package chapter4.digraphs;

import java.util.Queue;
import java.util.ArrayDeque;
import java.util.ArrayList;

public class PathsBFS implements Paths {
    boolean marked[];
    int[] edgeTo;
    ArrayList<Integer>[] paths;
    Digraph g;
    int s;

    public PathsBFS(Digraph d, int s) {
		this.g = d;
		this.s = s;
		marked = new boolean[d.V()];
		edgeTo = new int[d.V()];
		paths = (ArrayList<Integer>[]) new ArrayList[d.V()];

		bfs(d, s);
    }

    public boolean hasPathTo(int w) {
		return marked[w];
    }

    public Iterable<Integer> pathTo(int w) {
		if (!hasPathTo(w)) return null;
		if (paths[w] != null) return paths[w];

		ArrayList<Integer> path = new ArrayList<>();
		int current = w;
		while (current != s) {
			path.add(0, current);
			current = edgeTo[current];
		}
		path.add(0, current);
		paths[w] = path;
		return path;
    }

    private void bfs(Digraph d, int s) {
		Queue<Integer> toVisit = new ArrayDeque<Integer>();
		toVisit.add(s);
		while (!toVisit.isEmpty()) {
			int current = toVisit.remove();
			marked[current] = true;
			for (int w : d.adj(current)) {
			if (!marked[w]) {
				toVisit.add(w);
				edgeTo[w] = current;
			}
			}
		}
    }
}
