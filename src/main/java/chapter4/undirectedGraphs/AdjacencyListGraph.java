package chapter4.undirectedGraphs;

public class AdjacencyListGraph implements UndirectedGraph {
    Bag<Integer>[] graph;
    int numEdges;
    
    public AdjacencyListGraph(int V) {
		graph = new Bag[V];
		numEdges = 0;
		for (int j = 0; j < V; j++) graph[j] = new Bag<>();
    }

    //AdjacencyListGraph(Stream str); // Create graph from input stream

    public void addEdge(int v, int w) {
		numEdges++;
		graph[v].add(w);
		if (v != w) graph[w].add(v);
    }

    // Vertices adjacent to v
    public Iterable<Integer> adj(int v){
		return graph[v];
    }

    public int V() {
		return graph.length;
    }

    public int E() {
		return numEdges;
    }

    public int degree(int v) {
		int degree = 0;
		for (int k : adj(v)) degree++;
		return degree;
    }

    public boolean edgeBetween(int v, int w) {
		for (int k : adj(v)) {
			if (k == w) return true;
		}
		return false;
    }

    public String toString() {
		StringBuilder str = new StringBuilder();
		for (int j = 0; j < graph.length; j++) {
			Bag<Integer> adjacents = graph[j];

			if (adjacents.isEmpty()) {
			str.append(j);
			str.append("\n");
			}
			for (int w : graph[j]) {
			str.append(String.format("%d - %d", j, w));
			str.append("\n");
			}
		}
		return str.toString();
    }

    public static void main(String[] args) {
		UndirectedGraph myGraph = new AdjacencyListGraph(10);
		myGraph.addEdge(1, 3);
		myGraph.addEdge(3, 3);
		myGraph.addEdge(5, 2);
		myGraph.addEdge(3, 7);
		myGraph.addEdge(8, 7);

		print(myGraph.edgeBetween(1, 3));
		print(myGraph.edgeBetween(0, 2));
		print(myGraph.V());
		print(myGraph.E());
		print(myGraph.toString());
		print(myGraph.degree(0));
		print(myGraph.degree(3));


		//Paths pathProcessor = new DepthFirstPaths(myGraph, 1);
		Paths pathProcessor = new BreadthFirstPaths(myGraph, 1);
		print(pathProcessor.hasPathTo(3));
		Iterable<Integer> path = pathProcessor.pathTo(8);
		print("Path from 1 to 8");
		for (int v : path) {
			print(v);
		}

		print("Connected comopnents");
		ConnectedComponent cc = new ConnectedComponent(myGraph);
		print(cc.count());
		print(cc.connected(1, 8));
    }

    private static void print(Object str) {
		System.out.println(str);
    }
}
