package chapter2.mergeSort;

public class MergeSortExercises {
    public static void main(String[] args) {
		sort(new int[]{3, -10, 8, -9, 12, 28, 0, 3, -7, 11});
		sort(new int[]{24, 13, 1, 16, 27, -8, 0, -3, -5, -8, -1, 0, -10});
    }

    private static void sort(int[] array) {
		//topDownMergeSort(array);
		bottomUpMergeSort(array);
		printArray(array);
    }

    private static void bottomUpMergeSort(int[] array) {
		int[] copy = new int[array.length];
		for (int k = 0; k < array.length; k++) copy[k] = array[k];

		for (int size = 1; size < array.length; size = 2 * size) {
			for (int lo = 0; lo < array.length - size; lo += 2 *size) {
				int hi = Math.min(lo + 2*size - 1, array.length-1);
				int med = lo + size - 1;
				//merge(lo, med, hi, copy, array);
				fasterMerge(lo, med, hi, copy, array);
			}
		}
    }

    private static void topDownMergeSort(int[] array) {
		int[] copy = new int[array.length];
		divide(0, array.length - 1, copy, array);
    }

    private static void divide(int lo, int hi, int[] copy, int[] array) {
		if (hi <= lo) return;
		int med = lo + (hi - lo)/2;
		divide(lo, med, copy, array);
		divide(med + 1, hi, copy, array);

		merge(lo, med, hi, copy, array);
    }

    private static void merge(int lo, int med, int hi, int[] copy, int[] array) {
		int idxFirstHalf = lo;
		int idxSecondHalf = med + 1;
		int current = lo;

		// Subarrays already sorted
		if (array[med] < array[med+1]) return;

		for (int j = lo; j <= hi; j++) copy[j] = array[j];

		while (current <= hi) {
			if (idxFirstHalf > med) array[current++] = copy[idxSecondHalf++];
			else if (idxSecondHalf > hi) array[current++] = copy[idxFirstHalf++];
			else if (copy[idxFirstHalf] < copy[idxSecondHalf]) array[current++] = copy[idxFirstHalf++];
			else array[current++] = copy[idxSecondHalf++];
		}
    }

    // Implement a version of merge that copies the second half of a[] to
    // aux[] in decreasing order and then does the merge back to a[]. This
    // change allows you to remove the code to test that each of the halves
    // has been exhausted from the inner loop. Note: The resulting sort is not
    // stable
    public static void fasterMerge(int lo, int med, int hi, int[] aux, int[] array) {
		int current = lo;
		while (current < med + 1) {
			aux[current] = array[current];
			current++;
		}

		for (int j = hi; j >= med + 1; j--) {
			aux[current++] = array[j];
		}

		int idxFirst = lo;
		int idxSecond = hi;
		current = lo;
		while (current <= hi) {
			if (aux[idxFirst] < aux[idxSecond]) array[current++] = aux[idxFirst++];
			else array[current++] = aux[idxSecond--];
		}
    }

    private static void print(String str) {
		System.out.println(str);
    }

    private static void printArray(int[] array) {
		StringBuilder str = new StringBuilder();
		for (int j = 0; j < array.length; j++) {
			str.append(array[j]);
			str.append(" ");
		}
		print(str.toString());
    }
}
