public class UnionFindQuickFind implements UnionFind {
    private int[] id;
    private int count;

    public UnionFindQuickFind(int N) {
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
	if (connected(p, q)) return;
	// Set all elements from q component into p
	int firstComponent = id[p];
	int secondComponent = id[q];
	for (int j = 0; j < id.length; j++) {
	    if (id[j] == secondComponent) {
		id[j] = firstComponent;
	    }
	}
	count --;
    }

    public int find(int p) {
	return id[p];
    }
}
