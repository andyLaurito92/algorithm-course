package chapter2.mergeSort;

public class BottomUpMergeSort {
    public static void main(String[] args) {
		Integer[] array = new Integer[]{0, -2, -5, 3, 18, 9, -9, -43, 12, 1993, 37, 28, 1};
		sort(array);
		System.out.println(print(array));
    }

    public static void sort(Comparable[] array) {
		Comparable[] aux = new Comparable[array.length];
		for (int size = 1; size < array.length; size = 2 * size) { // Increment size in power of 2
			for (int lo = 0; lo < array.length - size; lo += size + size) { // In each iteration, we have 2 arrays of size 2; Increment lo in 2 sizes
			merge(array, aux, lo, lo + size - 1, Math.min(lo + size + size -1, array.length - 1));
			}
		}
    }

    public static void merge(Comparable[] array, Comparable[] aux, int low, int mid, int high) {
		int idxFirstPart = low;
		int idxSecondPart = mid + 1;

		for (int k = low; k <= high; k++) aux[k] = array[k];

		for (int j = low; j <= high; j++) {
			if (idxFirstPart == mid + 1) {
			array[j] = aux[idxSecondPart++];
			} else if (idxSecondPart == high + 1) {
			array[j] = aux[idxFirstPart++];
			} else if (less(aux[idxFirstPart], aux[idxSecondPart])) {
			array[j] = aux[idxFirstPart++];
			} else  {
			array[j] = aux[idxSecondPart++];
			}
		}
    }

    public static boolean less(Comparable a, Comparable b) {
		return a.compareTo(b) < 0;
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
