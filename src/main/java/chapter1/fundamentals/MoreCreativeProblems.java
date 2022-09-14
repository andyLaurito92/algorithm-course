package chapter1.fundamentals;

public class MoreCreativeProblems {
    public static void main(String[] args) {
	int val = 4;
	System.out.println(String.format("Smaller than %d: %d", val, smallerThanBinaryKey(val, new int[]{1, 1, 1, 2, 2, 3, 4, 5, 6, 6})));

	val = 6;
	System.out.println(String.format("Count %d: %d", val, count(val, new int[]{1, 1, 1, 2, 2, 3, 4, 5, 6, 6})));
	val = 1;
	System.out.println(String.format("Count %d: %d", val, count(val, new int[]{1, 1, 1, 2, 2, 3, 4, 5, 6, 6})));
	val = 2;
	System.out.println(String.format("Count %d: %d", val, count(val, new int[]{1, 1, 1, 2, 2, 3, 4, 5, 6, 6})));
	val = 5;
	System.out.println(String.format("Count %d: %d", val, count(val, new int[]{1, 1, 1, 2, 2, 3, 4, 5, 6, 6})));

	System.out.println(String.format("gcm 49, 21: %d", gcm(49, 21)));

	System.out.println(String.format("coprimesMatrix %s", print(coprimesMatrix(3))));
	System.out.println(String.format("coprimesMatrix %s", print(coprimesMatrix(2))));
	System.out.println(String.format("coprimesMatrix %s", print(coprimesMatrix(4))));
	System.out.println(String.format("coprimesMatrix %s", print(coprimesMatrix(5))));
    }

    public void randomConnections(int N, double p) {
	// Draw a circle.
    }

    public static String print(boolean[][] matrix) {
	StringBuilder str = new StringBuilder();
	for (int i = 0; i < matrix.length; i++) {
	    for (int j = 0; j < matrix.length; j++) {
		str.append(matrix[i][j]);
		str.append(" ");
	    }
	    str.append("\n");
	}
	return str.toString();
    }

    /**
     ** F T F
     ** T T T 
     ** F T F
     **/
    public static boolean[][] coprimesMatrix(int n) {
	boolean[][] res = new boolean[n][n];
	for (int i = 0; i < n; i++) {
	    for (int j = i; j > -1; j--) {
		boolean areCoprimes = gcm(i, j) == 1 ? true: false;
		res[i][j] = areCoprimes;
		if (i == j) continue;
		res[j][i] = areCoprimes;
	    }
	}
	return res;
    }

    /** Euclidean algorithm to calculate greatest common divisor **/
    public static int gcm(int p, int q) {
	if (p == 0) return q;
	if (q == 0) return p;
	
	int r = 1;
	while (q != 0) {
	    r = p % q;
	    p = q;
	    q = r;
	}

	return p;
    }

    // Because the array is sorted, we can provide a solution in O(lg(N) + k) where k should be small in the average case
    public static int smallerThanBinaryKey(int key, int[] array) {
	if (array.length < 2) {
	    return 0;
	}

	// Do a binary search to find the element
	int lo = 0;
	int hi = array.length - 1;
	int med = -1;
	while (lo <= hi) {
	    med = lo + (hi - lo)/2;
	    if (array[med] == key) break;
	    if (array[med] > key) hi = med - 1;
	    if (array[med] < key) lo = med + 1;
	}

	// If the key was not found, return 0
	if (array[med] != key) {
	    return 0;
	}

	//We have found the key, we just need to count until we reach the begginning of the array!
	int count = 0;
	for (int j = med; j > -1; j--) {
	    if (array[j] < key) count++;
	}

	return count;
    }

    public static int count(int key, int[] array) {
	if (array.length < 2) {
	    if (array.length == 0) return 0;
	    if (array.length == 1) return array[0] == key ? 1 : 0;
	}

	// Let's find first the element in the array!
	int lo = 0;
	int hi = array.length - 1;
	int med = -1;
	while (lo <= hi) {
	    med = lo + (hi - lo)/2;
	    if (array[med] == key) break;
	    if (array[med] > key) hi = med - 1;
	    if (array[med] < key) lo = med + 1;
	}

	// If the element not found, return 0
	if (array[med] != key) return 0;

	// If we found the element, we need to count the occurrences
	// going back and forth
	int current = med - 1; 
	int count = 1; // The first element is the one we found!
	while (current > -1 && array[current] == key) {
	    count++;
	    current--;
	}

	current = med + 1;
	while (current < array.length && array[current] == key) {
	    count++;
	    current++;
	}

	return count;
    }

}
