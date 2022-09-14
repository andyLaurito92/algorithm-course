package chapter2.elementarySorts;

public class BubbleSort {
    public static void main(String[] args) {
		Integer[] array = new Integer[]{0, -2, -5, 3, 18, 9, -9, -43, 12, 1993, 37, 28, 1};

		//sort(array);
		combSort(array);
		System.out.println(print(array));
    }

    public static void sort(Comparable[] array) {
		for (int i = 0; i < array.length; i++) {
			for (int j = 1; j < array.length; j++) {
			if (less(array[j], array[j-1])) {
				exchange(array, j - 1, j);
			}
			}
		}
    }

    /** This is an optimization on the bubble sort to make smaller
     elements to move faster to the end of the list. The idea is similar
     to the improvement shell-sort does to insertion sort 
     It still has a worst case of O(n^2), but it's average performance get's a bit better
     Despite, is not an algorithm to use at least definitely necessary.
    **/
    public static void combSort(Comparable[] array) {
		int gap = array.length;
		float shrink = 1.3f;
		boolean sorted = false;

		while (!sorted) {
			gap = (int) (gap / shrink);
			if (gap <= 1) {
			// At this moment, we start doing bubble sort again
			sorted = true;
			gap = 1;
			}

			for (int i = 0; i + gap < array.length; i++) {
			if (less(array[i+gap], array[i])) {
				exchange(array, i, i+gap);
				sorted = false;
			}
			}
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
