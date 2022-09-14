// Find a sort of the graph that makes all vertes to point upwards
// This is the problem that luigi or airflow solves
// Problem: Given a set of tasks, define a linear order in which we can execute
// the task accomplishing their order of precedence
// NOTE: In order to do topological sort, the graph CANNOT have cycles
// In other word: It has to be a directed acyclic graph :)

package chapter4.digraphs;

import java.util.ArrayList;

// Applied DFS with postorder
public class TopologicalSort {
    private final ArrayList<Integer> postOrder;
    private final boolean[] marked;

    public TopologicalSort(Digraph g) {
		postOrder = new ArrayList<Integer>();
		marked = new boolean[g.V()];

		for (int v  = 0; v < g.V(); v++) {
			if (!marked[v]) {
			dfs(g, v);
			}
		}
    }

    public Iterable<Integer> reversePost() {
		return postOrder;
    }

    private void dfs(Digraph g, int s) {
		marked[s] = true;
		for(int w : g.adj(s)) {
			if (!marked[w]) {
			dfs(g, w);
			}
		}
		postOrder.add(0, s);
    }
}
