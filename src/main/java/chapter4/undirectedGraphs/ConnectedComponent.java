package chapter4.undirectedGraphs;

import java.util.Stack;

public class ConnectedComponent {
    boolean[] marked;
    int[] connectedComponents;
    int numCC;
    
    public ConnectedComponent(UndirectedGraph g) {
	connectedComponents = new int[g.V()];
	marked = new boolean[g.V()];
	numCC = 0;

	for (int j = 0; j < g.V(); j++) {
	    connectedComponents[j] = -1;
	    marked[j] = false;
	}

	for (int w = 0; w < g.V(); w++) {
	    if (!marked[w]) dfs(g, w, numCC++);
	}
    }

    private void dfs(UndirectedGraph g, int w, int currentCC) {
	Stack<Integer> unseen = new Stack<Integer>();
	unseen.push(w);
	while (!unseen.isEmpty()) {
	    int current = unseen.pop();
	    marked[current] = true;
	    connectedComponents[current] = currentCC;
	    for (int v : g.adj(current)) {
		if (!marked[v]) {
		    unseen.push(v);
		}
	    }
	}
    }

    public boolean connected(int v, int w) {
	return connectedComponents[v] == connectedComponents[w];
    }

    int count() {
	return numCC;
    }

    int id(int v) {
	return connectedComponents[v];
    }
}
