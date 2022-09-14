package chapter2.elementarySorts;

import java.util.Scanner;

public class SelectionSort {
    public static void main(String[] arguments) {
		Integer[] array = new Integer[]{-3, 5, -8, 1, 0, 2, 2, 9, 18, -5, -3, -1, -9};
		sort(array);
		System.out.println(printArray(array));
    }

    public static void sort(Comparable[] array) {
		for (int j = 0; j < array.length; j++) {
			int minIdx = j;
			for (int i = j + 1; i < array.length; i++) {
				if (less(array[i], array[minIdx])) {
					minIdx = i;
				}
			}
			exchange(minIdx, j, array);
		}
    }

    public static boolean less(Comparable a, Comparable b) {
		return a.compareTo(b) < 0;
    }

    public static void exchange(int j, int i, Comparable[] array) {
		Comparable temp = array[j];
		array[j] = array[i];
		array[i] = temp;
    }

    public static String printArray(Comparable[] array) {
		StringBuilder str = new StringBuilder();
		for (Comparable value: array) {
			str.append(value);
			str.append(" ");
		}
		return str.toString();
    }
}
