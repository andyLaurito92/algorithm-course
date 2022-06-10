public class MaxPQHeap<Key extends Comparable<Key>> implements MaxPQ<Key> {
    Key[] heap;
    int numElements;
    int currentIdx;

    MaxPQHeap() {
	currentIdx = 1;
	numElements = 0;
	// First element in heap is not used
	heap = (Key[]) new Comparable[10];
    }

    public void insert(Key v) {
	heap[currentIdx++] = v;
	bottomUpReheapify(currentIdx - 1);
	numElements++;
    }

    public Key max() {
	return heap[1];
    }

    public Key delMax() {
	if (currentIdx == 1) return null; // heap is empty

	Key res = heap[1];
	numElements--;
	exchange(1, --currentIdx);
	heap[currentIdx + 1] = null; // Avoid loitering
	topDownReheapify(1);
	

	return res;
    }

    public boolean isEmpty() {
	return numElements == 0;
    }

    public int size() {
	return numElements;
    }

    private void bottomUpReheapify(int k) {
	while (k >= 2 && less(heap[k/2], heap[k])) {
	    exchange(k/2, k);
	    k = k/2;
	}
    }

    private void topDownReheapify(int k) {
	while ( k < numElements && 2*k < numElements && 
		2*k + 1 < numElements &&
		(less(heap[k], heap[2* k]) ||
		 less(heap[k], heap[2* k + 1]))) {
	    int greatestChild = less(heap[2*k+1], heap[2*k]) ? 2*k : 2*k + 1;
	    exchange(k, greatestChild);
	    k = greatestChild;
	}

	// Got to a leaf!. If I'm here, it's bc there is a child which is null
	if (2*k > numElements) return;
	if (less(heap[k], heap[2*k])) {
	    exchange(k, 2*k);
	}
    }

    private void print() {
	StringBuilder str = new StringBuilder();
	for (Key val : heap) {
	    str.append(val);
	    str.append(" ");
	}
	System.out.println(str.toString());
    }

    private boolean less(Key elem1, Key elem2) {
	return elem1.compareTo(elem2) < 0;
    }

    private void exchange(int i, int j) {
	Key temp = heap[i];
	heap[i] = heap[j];
	heap[j] = temp;
    }

    public static void main(String[] args) {
	MaxPQHeap<Integer> pq = new MaxPQHeap<>();
	pq.insert(8);
	pq.insert(-2);
	pq.insert(10);
	pq.insert(29);

	System.out.println(pq.isEmpty());
	System.out.println(pq.size());
	System.out.println(pq.max());
	System.out.println(pq.delMax());
	System.out.println(pq.delMax());
	System.out.println(pq.delMax());

	pq.insert(3002);
	pq.insert(2999);
	pq.insert(30);
	pq.insert(30);
	pq.insert(3002);
	pq.insert(150);

	System.out.println(pq.delMax());
	System.out.println(pq.delMax());
	System.out.println(pq.delMax());
	System.out.println(pq.delMax());
    }
}
