package chapter2.elementarySorts;

public class ShellSort {
    public static void main(String[] args) {
		Integer[] array = new Integer[]{0, -2, -5, 3, 18, 9, -9, -43, 12, 1993, 37, 28, 1};
		sort(array);
		System.out.println(print(array));
    }

    /** This implementation is much lower than selection sort so far. Why??**/
    public static void sort(Comparable[] array) {
		int h = 1;
		while (h < array.length / 3) h = 3*h + 1;
		while (h >= 1) {
			for (int j = h; j < array.length; j++) {
			int i = j;
			while (i - h >= 0 && less(array[i], array[i-h])) {
				exchange(array, i, i - h);
				i -= h;
			}
			}
			h = h/3;
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
