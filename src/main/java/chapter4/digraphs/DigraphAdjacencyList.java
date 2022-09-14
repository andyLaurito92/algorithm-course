package chapter4.digraphs;

import java.util.ArrayList;

public class DigraphAdjacencyList implements Digraph {
    ArrayList<Integer>[] V;
    int E;
    Digraph reversed;
    
    public DigraphAdjacencyList(int numVertices) {
		V = (ArrayList<Integer>[]) new ArrayList[numVertices];
		E = 0;

		for (int j = 0; j < V.length; j++) V[j] = new ArrayList<>();
    }

    public void addEdge(int v, int w) {
		V[v].add(w);
		E++;
    }

    public Iterable<Integer> adj(int v) {
		return V[v];
    }

    public int V() {
		return V.length;
    }

    public int E() {
		return E;
    }

    // This is used for computing the strong components of a digraph
    public Digraph reverse() {
		if (reversed != null) return reversed;

		reversed = new DigraphAdjacencyList(V());
		for (int v = 0; v < V(); v++) {
			for (int w : adj(v)) {
			reversed.addEdge(w, v);
			}
		}
		return reversed;
    }

    public String toString() {
		StringBuilder str = new StringBuilder();
		for (int v = 0; v < V.length; v++) {
			for (int w : adj(v)) {
			str.append(String.format("%d -> %d", v, w));
			str.append("\n");
			}
		}
		return str.toString();
    }

    public static void main(String[] args) {
		Digraph digraph = new DigraphAdjacencyList(10);
		digraph.addEdge(2, 3);
		digraph.addEdge(0, 3);
		digraph.addEdge(1, 2);
		digraph.addEdge(4, 7);
		digraph.addEdge(1, 7);
		digraph.addEdge(7, 4);
		digraph.addEdge(4, 8);
		digraph.addEdge(8, 0);
		digraph.addEdge(0, 1);
		digraph.addEdge(2, 7);
		print("Digraph is");
		print(digraph.toString());

		Paths myPaths = new PathsBFS(digraph, 1);
		print(String.format("Path to 3 is %s", myPaths.pathTo(3)));
		print(String.format("Path to 7 is %s", myPaths.pathTo(7)));
		print(String.format("Path to 8 is %s", myPaths.pathTo(8)));

		TopologicalSort airflow = new TopologicalSort(digraph);
		print(String.format("Topological sort is %s", airflow.reversePost()));

		print("Reverse digraph");
		print(digraph.reverse().toString());
    }

    private static void printPath(Iterable<Integer> path) {
		StringBuilder str = new StringBuilder();
		for (int v : path) {
			str.append(v);
			str.append(" ");
		}
		print(str.toString());
    }

    private static void print(String str) {
		System.out.println(str);
    }
}
