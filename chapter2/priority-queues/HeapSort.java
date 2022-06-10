public class HeapSort {
    public static void main(String[] args) {
	Integer[] a = new Integer[]{-12, 3, 0, 0, -8, -7, 99, 132, 281, -200, 7, 20};
	print(a);
	heapsort(a);
	print(a);
    }

    public static void heapsort(Comparable[] array) {
	// Build the heapsort
	int heapsortNumElements = array.length;
	for (int k = heapsortNumElements/2; k >= 1; k--)
	    reheapifyTopDown(array, k, heapsortNumElements);

	for (int k = 1; k <= array.length; k++) {
	    exchange(array, 1, heapsortNumElements--);
	    reheapifyTopDown(array, 1, heapsortNumElements);
	}

    }

    private static void print(Comparable[] array) {
	StringBuilder str = new StringBuilder();
	for (Comparable val : array) {
	    str.append(val);
	    str.append(" ");
	}
	System.out.println(str.toString());
    }

    private static void reheapifyTopDown(Comparable[] array, int k, int numElements) {
	while (2*k <= numElements) {
	    int j = 2*k;
	    if (j + 1 <= numElements && less(array, j, j + 1)) j++;
	    if (!less(array, k, j)) break;
	    exchange(array, k, j);
	    k = j;
	}
    }

    private static boolean less(Comparable[] array, int j, int k) {
	return array[j-1].compareTo(array[k-1]) < 0;
    }

    private static void exchange(Comparable[] array, int i, int j) {
	Comparable temp = array[i-1];
	array[i-1] = array[j-1];
	array[j-1] = temp;
    }
}
