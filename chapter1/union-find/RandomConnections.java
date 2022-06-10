import java.util.Scanner;
import java.util.Random;

public class RandomConnections {
    public static void main(String[] args) {
	Scanner myScanner = new Scanner(System.in);
	while (myScanner.hasNextLine()) {
	    String line = myScanner.nextLine();
	    int N = Integer.parseInt(line); // Number of vertices
	    System.out.println(String.format("ErdosRenyi graph of n-1 edges is: %s", createErdosRenyiGraph(N)));
	}
    }

    public static String createErdosRenyiGraph(int vertices) {
	Random random = new Random();
	WeightedUnionFind myUnionFind = new WeightedUnionFind(vertices);


	while (!myUnionFind.allConnected()) {
	    int vertex1 = random.nextInt(vertices);
	    int vertex2 = random.nextInt(vertices);

	    if (!myUnionFind.connected(vertex1, vertex2)) {
		myUnionFind.union(vertex1, vertex2);
	    }
	}
	return myUnionFind.toString();
    }

    static class WeightedUnionFind {
	private int[] ids;
	private int[] depths;
	private int components;

	WeightedUnionFind(int N) {
	    components = N;
	    ids = new int[N];
	    depths = new int[N];

	    for (int j = 0; j < N; j++) {
		ids[j] = j;
		depths[j] = 1;
	    }
	}

	public boolean connected(int p, int q) {
	    return find(p) == find(q);
	}

	public int getComponents() {
	    return components;
	}

	public void union(int p, int q) {
	    int pComponent = find(p);
	    int qComponent = find(q);
	    if (pComponent == qComponent) return;

	    if (depths[pComponent] < depths[qComponent]) {
		ids[pComponent] = qComponent;

		depths[qComponent] = Math.max(depths[pComponent] + 1, depths[qComponent]);
		depths[pComponent] = -1;
		
	    } else {
		ids[qComponent] = pComponent;

		depths[pComponent] = Math.max(depths[qComponent] + 1, depths[pComponent]);
		depths[qComponent] = -1;
		
	    }
	    components--;
	}

	public boolean allConnected() {
	    return components == 1;
	}

	public String toString() {
	    StringBuilder strBuilder = new StringBuilder();
	    for (int j = 0; j < ids.length; j++) {
		strBuilder.append(ids[j]);
		strBuilder.append(" ");
	    }
	    return strBuilder.toString();
	}

	private int find(int p) {
	    int current = p;
	    while (current != ids[current]) {
		current = ids[current];
	    }
	    return current;
	}
    }
}


