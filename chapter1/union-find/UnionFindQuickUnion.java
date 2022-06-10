public class UnionFindQuickUnion implements UnionFind {
    public int[] id;
    public int count;
    
    public UnionFindQuickUnion(int N) {
	count = N;
	id = new int[N];

	for (int j = 0; j < N; j++) {
	    id[j] = j;
	}
    }

    public boolean connected(int p, int q) {
	return find(p) == find(q);
    }

    public int count() {
	return count;
    }

    public void union(int p, int q) {
	// Don't care about updating all connected components
	// Just update the current object and leave the work
	// of finding the connected component to find
	int qComponent = find(q);
	int pComponent = find(p);
	if (qComponent == pComponent) return;
	id[qComponent] = id[pComponent]; // Set as the connected component of q, the connected component of p
	count --;
    }

    public int find(int p) {
	int current = p;
	while (current != id[current]) {
	    current = id[current];
	}
	return current;
    }
}
