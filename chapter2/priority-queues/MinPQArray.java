public class MinPQArray<Key extends Comparable<Key>> implements MinPQ<Key> {
    Key[] elements;
    int numElements;
    
    /** Create a priority queue **/
    public MinPQArray() {
	elements = (Key[]) new Comparable[10];
	numElements = 0;
    }

    /** Create a priority queue of initial capacity min **/
    public MinPQArray(int min) {
	elements = (Key[]) new Comparable[min];
	numElements = 0;
    }

    public MinPQArray(Key[] a) {
	elements = (Key[]) copy(a);
	numElements = a.length;
    }

    public void insert(Key v) {
	if (numElements == elements.length) resize(numElements * 2);
	
	elements[numElements++] = v;
    }

    public Key min() {
	if (numElements == 0) return null;

	return getMinWithIndex().y;
    }

    public Key delMin() {
	Pair<Integer, Key> idxAndMin = getMinWithIndex();
	int idxMin = idxAndMin.x;
	Key res = idxAndMin.y;

	exchange(elements, numElements - 1, idxMin);
	numElements--;

	if (numElements == elements.length / 4) {
	    resize(elements.length / 2);
	}
	
	return res;
    }

    public boolean isEmpty() {
	return numElements == 0;
    }

    public int size() {
	return numElements;
    }

    private Pair<Integer, Key> getMinWithIndex() {
	Key res = elements[0];
	int idxMin = 0;
	for (int j = 1; j < numElements; j++) {
	    if (less(elements[j], res)) {
		res = elements[j];
		idxMin = j;
	    }
	}
	return Pair.of(idxMin, res);
    }

    private void resize(int newLength) {
	Key[] newArray = (Key[]) new Comparable[newLength];
	for (int j = 0; j < numElements; j++) {
	    newArray[j] = elements[j];
	}
	elements = newArray;
    }
    
    private boolean less(Key elem1, Key elem2) {
	return elem1.compareTo(elem2) < 0;
    }

    private void exchange(Comparable[] array, int i, int j) {
	Comparable temp = array[i];
	array[i] = array[j];
	array[j] = temp;
    }

    private Object copy(Comparable[] a) {
	Comparable[] res = new Comparable[a.length];
	for (int j = 0; j < a.length; j++) res[j] = a[j];
	return res;
    }


    public static void main(String[] args) {
	MinPQArray<Integer> priorityQueue = new MinPQArray(10);
	// priorityQueue.insert("Heey");
	// priorityQueue.insert("I'm bigger than before");
	// priorityQueue.insert("Sma");
	// priorityQueue.insert("The biggest one is me man hehehe");
	// priorityQueue.insert("Are you sure about your last statement man?");

	// priorityQueue.insert(10);
	// priorityQueue.insert(0);
	// priorityQueue.insert(320);
	// priorityQueue.insert(-17);
	// priorityQueue.insert(18);
	// priorityQueue.insert(1241);
	

	// System.out.println(priorityQueue.isEmpty());
	// System.out.println(priorityQueue.size());
	// System.out.println(priorityQueue.min());
	// System.out.println(priorityQueue.delMin());
	// System.out.println(priorityQueue.delMin());
	// System.out.println(priorityQueue.delMin());
	// System.out.println(priorityQueue.delMin());
	
	Integer[] elements = new Integer[]{10, 0, 320, -17, 18, 1241, -12};

	MinPQArray<Integer> heapSort = new MinPQArray(elements);
	for (int j = 0; j < elements.length; j++) {
	    elements[j] = heapSort.delMin();
	}
	print(elements);
    }

    private static void print(Object[] array) {
	StringBuilder str = new StringBuilder();
	for (Object val : array) {
	    str.append(val);
	    str.append(" ");
	}
	System.out.println(str.toString());
    }

    static class Pair<Item1, Item2> {
	public final Item1 x;
	public final Item2 y;

	private Pair(Item1 x, Item2 y) {
	    this.x = x;
	    this.y = y;
	}

	public static <Item1, Item2> Pair<Item1, Item2> of(Item1 x, Item2 y) {
	    return new Pair<>(x, y);
	}
    }
}
