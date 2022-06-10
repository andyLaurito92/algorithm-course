public class UnionFindWeighted implements UnionFind {
    int[] id;
    int[] size;
    int count;
    
    public UnionFindWeighted(int N) {
	id = new int[N];
	size = new int[N];
	count = N;

	for (int j = 0; j < N; j++) {
	    id[j] = j;
	    size[j] = 1;
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

	int pComponent = find(p);
	int qComponent = find(q);
	if (size[pComponent] < size[qComponent]) {
	    // Add p to the connected component of q
	    id[pComponent] = id[qComponent];
	    
	    size[qComponent] = Math.max(size[pComponent] + 1, size[qComponent]);
	    size[pComponent] = -1; // New size is in qComponent
	} else {
	    id[qComponent] = id[pComponent];

	    size[pComponent] = Math.max(size[qComponent] + 1, size[pComponent]);
	    size[qComponent] = -1; // New size is in pComponent
	}
	count --;
    }

    public int find(int p) {
	int current = p;
	// Path compression: We reduce depth of all seen nodes to 1
	// This is: We make all nodes to point directly to root
	int[] pathToRoot = new int[id.length];
	int indexPath = 0;
	while (current != id[current]) {
	    pathToRoot[indexPath++] = current;
	    current = id[current];
	}

	// All objects in pathToRoot belong to connected component current
	indexPath --;
	while (indexPath > -1) {
	    int objectToChange = pathToRoot[indexPath];
	    id[objectToChange] = current;
	    indexPath--;
	}
	return current;
    }

}
