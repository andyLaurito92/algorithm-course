package chapter2.elementarySorts;

public class InsertionSort {
    public static void main(String[] args) {
	Integer[] array = new Integer[]{0, -2, -5, 3, 18, 9, -9, -43, 12, 1993, 37, 28, 1};
	//sort(array);
	//sortWithSentinel(array);
	sortWithoutExchanges(array);
	System.out.println(print(array));
    }

    /** This implementation is much lower than selection sort so far. Why??**/
    public static void sort(Comparable[] array) {
	for (int j = 1; j < array.length; j++) {
	    int i = j;
	    while ( i > 0 && less(array[i], array[i-1])) {
		exchange(array, i - 1, i);
		i --;
	    }
	}
    }

    /** Same insertion sort, but with sentinel: This means that we will 
	remove the j > 0 check by finding first the min value in the array
     **/
    public static void sortWithSentinel(Comparable[] array) {
	int minIdx = 0;
	for (int j = 1; j < array.length; j++) {
	    if (less(array[j], array[minIdx])) {
		minIdx = j;
	    }
	}
	exchange(array, minIdx, 0);
	
	for (int j = 1; j < array.length; j++) {
	    int i = j;
	    // We don't have to check whether i > 0, given that we know that
	    // in a[0] we have the minimun. This implies that less(a[k], a[0])
	    // is false for all k in {0..N}
	    while (less(array[i], array[i-1])) {
		exchange(array, i - 1, i);
		i --;
	    }
	}
    }

    public static void sortWithoutExchanges(Comparable[] array) {
	for (int j = 1; j < array.length; j++) {
	    int i = j;
	    Comparable newElem = array[i];
	    while ( i > 0 && less(newElem, array[i-1])) {
		array[i] = array[i-1];
		i --;
	    }
	    array[i] = newElem;
	}
    }

    public static boolean less(Comparable a, Comparable b) {
	return a.compareTo(b) < 0;
    }

    public static void exchange(Comparable[] array, int i, int j) {
	Comparable temp = array[i];
	array[i] = array[j];
	array[j] = temp;
    }

    public static String print(Comparable[] array) {
	StringBuilder str = new StringBuilder();
	for (Comparable value : array) {
	    str.append(value);
	    str.append(" ");
	}
	return str.toString();
    }
}
