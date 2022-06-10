public class MaxPQArray<Key extends Comparable<Key>> implements MaxPQ<Key> {
    Key[] elements;
    int numElements;
    
    /** Create a priority queue **/
    public MaxPQArray() {
	elements = (Key[]) new Comparable[10];
	numElements = 0;
    }

    /** Create a priority queue of initial capacity max **/
    public MaxPQArray(int max) {
	elements = (Key[]) new Comparable[max];
	numElements = 0;
    }

    public MaxPQArray(Key[] a) {
	elements = (Key[]) copy(a);
	numElements = a.length;
    }

    public void insert(Key v) {
	if (numElements == elements.length) resize(numElements * 2);
	
	elements[numElements++] = v;
    }

    public Key max() {
	if (numElements == 0) return null;

	return getMaxWithIndex().y;
    }

    public Key delMax() {
	Pair<Integer, Key> idxAndMax = getMaxWithIndex();
	int idxMax = idxAndMax.x;
	Key res = idxAndMax.y;

	exchange(elements, numElements - 1, idxMax);
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

    private Pair<Integer, Key> getMaxWithIndex() {
	Key res = elements[0];
	int idxMax = 0;
	for (int j = 1; j < numElements; j++) {
	    if (less(res, elements[j])) {
		res = elements[j];
		idxMax = j;
	    }
	}
	return Pair.of(idxMax, res);
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
	MaxPQArray<Integer> priorityQueue = new MaxPQArray(10);
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
	// System.out.println(priorityQueue.max());
	// System.out.println(priorityQueue.delMax());
	// System.out.println(priorityQueue.delMax());
	// System.out.println(priorityQueue.delMax());
	// System.out.println(priorityQueue.delMax());
	
	Integer[] elements = new Integer[]{10, 0, 320, -17, 18, 1241, -12};

	MaxPQArray<Integer> heapSort = new MaxPQArray(elements);
	for (int j = elements.length - 1; j > -1; j--) {
	    elements[j] = heapSort.delMax();
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
