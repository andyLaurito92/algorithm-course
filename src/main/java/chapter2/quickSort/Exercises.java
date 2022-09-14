package chapter2.quickSort;

import java.util.Random;
import java.util.Comparator;

public class Exercises {
    public static void main(String[] args) {
		//nutsAndBolts();
		findRankK();
    }

    /**
     ** Selection in two sorted arrays. Given two sorted arrays a[] and b[], of lengths n1​ and n2​ and an integer 0≤k<n1+n2 
     ** design an algorithm to find a key of rank k. The order of growth of the worst case running time of your algorithm should be log⁡n,
     ** where n=n1+n2​.
     ** 
     ** Version 1: n1=n2 (equal length arrays) and k=n/2 (median).
     **
     ** Version 2: k=n/2 (median).
     **
     ** Version 3: no restrictions.
     **/
    public static void findRankK() {
		Integer[] a = new Integer[]{-12, -7, -7, -7, -2, 0, 0, 1, 3, 5, 7, 9, 33};
		Integer[] b = new Integer[]{-33, -28, -7, -7, 0, 0, 2, 8, 9, 11};

		int median = (a.length + b.length)/2;
		int initK = a.length/2;

		System.out.println(findElementsSmallerOrEqualThan(a, 2));
		System.out.println(findElementsSmallerOrEqualThan(b, 1));
		System.out.println(findElementsSmallerOrEqualThan(b, -6));
    }

    public static Object findRankk(Integer[] a, Integer[] b, int k, int current, int idxCurrent) {
		int size1 = findElementsSmallerOrEqualThan(a, current);
		int size2 = findElementsSmallerOrEqualThan(b, current);
		int elementsSmallerThanCurrent = size1 + size2;

		if (elementsSmallerThanCurrent == k) return current;
//		else if (elementsSmallerThanCurrent < k) {
//
//		}
		return null;
    }

    // Array a is sorted
    private static int findElementsSmallerOrEqualThan(Integer[] a, int k) {
		if (a.length == 0 || a[0] > k) return 0;
		else if (a[a.length - 1] <= k) return a.length;

		// Do a binary search
		int low = 0;
		int high = a.length - 1;
		int med = low + (high - low)/2;
		while (low <= high) {
			med = low + (high - low)/2;
			if (a[med] < k) low = med + 1;
			else if (a[med] > k) high = med - 1;
			else return med + 1;
		}
		if (a[med] <= k) {
			return med + 1;
		} else {
			return med;
		}
    }

    /**
     ** Nuts and bolts. A disorganized carpenter has a mixed pile of n nuts and n bolts. The goal is to find the corresponding pairs of nuts and bolts.
     ** Each nut fits exactly one bolt and each bolt fits exactly one nut. By fitting a nut and a bolt together, the carpenter can see which one is bigger 
     ** (but the carpenter cannot compare two nuts or two bolts directly). Design an algorithm for the problem that uses at most proportional to nlog⁡nn nlgon 
     ** compares (probabilistically).
     **/ 
    public static void nutsAndBolts() {
		Integer[] nuts = new Integer[]{3, 15, 8, 9, 1, 2, 7};
		Integer[] bolts = new Integer[]{7, 15, 2, 1, 3, 8, 9};

		Integer[] nutsAndBolts = new Integer[nuts.length + bolts.length];
		int idxNutsAndBolts = 0;
		for (int j = 0; j < nuts.length; j++) nutsAndBolts[idxNutsAndBolts++] = nuts[j];
		for (int j = 0; j < bolts.length; j++) nutsAndBolts[idxNutsAndBolts++] = bolts[j];

		// Do this in O(nlogn)
		Quick.sort(nutsAndBolts, Comparator.naturalOrder());
		print(nutsAndBolts);
		Pair<Integer,Integer>[] nutsAndBoltsOrdered = new Pair[nuts.length];
		int idxNutsAndBoltsOrdered = 0;
		for (int j = 0; j < nutsAndBolts.length - 1; j += 2) {
			if (nutsAndBolts[j] == nutsAndBolts[j+1]) { // Given that there are pairs, this condition should always match
			nutsAndBoltsOrdered[idxNutsAndBoltsOrdered++] = Pair.of(nutsAndBolts[j], nutsAndBolts[j+1]);
			} else {
			System.out.println(String.format("Either nut or bolt missing: %d", nutsAndBolts[j]));
			}
		}
		print(nutsAndBoltsOrdered);
    }

    private static void print(Object[] array) {
		StringBuilder str = new StringBuilder();
		for (Object val : array) {
			str.append(val);
			str.append(" ");
		}
		System.out.println(str.toString());
    }

    static class Quick {
		public static void sort(Object[] array, Comparator comparator) {
			shuffle(array);
			sort3way(array, 0, array.length - 1, comparator);
			//sort(array, 0, array.length - 1, comparator);
		}

		private static void sort(Object[] array, int low, int high, Comparator comparator) {
			if (low >= high) return;

			Object k = array[low];
			int i = low + 1;
			int j = high;
			int e = i;

			while (e <=j) {
			while (e <= j && less(array[e], k, comparator)) {
				exchange(array, i, e);
				i++;
				e++;
			}

			if (e <= j && equals(array[e], k, comparator)) {
				e++;
				continue;
			}

			while (e <= j && less(k, array[j], comparator)) j--;

			if (e > j) break;

			exchange(array, e, j);
			}

			exchange(array, j, low);

			sort(array, low, i - 1, comparator);
			sort(array, j + 1, high, comparator);
		}

		private static void sort3way(Object[] array, int low, int high, Comparator comparator) {
			if (low >= high) return;

			Object k = array[low];
			int lt = low;
			int i = lt + 1;
			int gt = high;

			while (i <= gt) {
			int comparisson = comparator.compare(array[i], k);
			if (comparisson < 0) exchange(array, lt++, i++);
			else if (comparisson > 0) exchange(array, i, gt--);
			else i++;
			}
			sort3way(array, low, lt -1, comparator);
			sort3way(array, gt + 1, high, comparator);
		}

		private static boolean less(Object elem1, Object elem2, Comparator comparator) {
			return comparator.compare(elem1, elem2) < 0;
		}

		private static boolean equals(Object elem1, Object elem2, Comparator comparator) {
			return comparator.compare(elem1, elem2) == 0;
		}

		private static void shuffle(Object[] array) {
			Random random = new Random();
			for (int i = 0; i < array.length; i++) {
			int next = i + random.nextInt(array.length - i);
			exchange(array, i, next);
			}
		}

		private static void exchange(Object[] array, int i, int j) {
			Object temp = array[i];
			array[i] = array[j];
			array[j] = temp;
		}
    }

    static class Pair<Item, Another> {
		Item x;
		Another y;

		public static <Item, Another> Pair<Item,Another> of(Item x, Another y) {
			return new Pair<>(x,y);
		}

		Pair(Item first, Another second) {
			this.x = first;
			this.y = second;
		}

		public Item x() {
			return x;
		}

		public Another y() {
			return y;
		}

		@Override
		public String toString() {
			return String.format("(%s, %s)", x, y);
		}
    }
}
