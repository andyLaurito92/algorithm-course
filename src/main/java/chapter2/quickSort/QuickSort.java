import java.util.Comparator;
import java.util.Random;

public class QuickSort {
    public static void main(String[] args) {
	Integer[] array = new Integer[]{3, -5, -1, -12, 8, 7, 12, 28, 0, -24};
	sort(array, Comparator.naturalOrder());
	print(array);

	Integer[] dutchFlag1 = new Integer[]{3, 2, 2, 1, 3};
	dutchFlagProblem(dutchFlag1);
	print(dutchFlag1);

	Integer[] dutchFlag2 = new Integer[]{1, 2, 2, 3, 1, 1, 2, 3};
	dutchFlagProblem(dutchFlag2);
	print(dutchFlag2);
    }

    private static void dutchFlagProblem(Integer[] array) {
	Integer i = 0;
	Integer lt = 0;
	Integer gt = array.length - 1;
	Integer v = 2; // My partition key is 2, I want to order verything < 2, == 2 && > 2

	while (i <= gt) {
	    if (array[i] < v) {
		exchange(array, i, lt);
		lt++;
		i++;
	    } else if (array[i] > v) {
		exchange(array, i, gt);
		gt --;
	    } else {
		i ++;
	    }
	}
    }

    private static void print(Object[] array) {
	StringBuilder str = new StringBuilder();
	for (Object val : array) {
	    str.append(val);
	    str.append(" ");
	}
	System.out.println(str.toString());
    }

    public static void sort(Object[] array, Comparator comparator) {
	shuffle(array);
	sort(array, 0, array.length-1, comparator);
    }

    public static void shuffle(Object[] array) {
	Random random = new Random();
	for (int i = 0; i < array.length; i++) {
	    int j = i + random.nextInt(array.length - i);
	    exchange(array, i, j);
	}
    }

    private static void sort(Object[] array, int low, int high, Comparator comparator) {
	if (high <= low) return;

	int i = partition(array, low, high, comparator);
	sort(array, low, i - 1, comparator);
	sort(array, i + 1, high, comparator);
    }

    private static int partition(Object[] array, int low, int high, Comparator comparator) {
	int i = low + 1;
	int j = high;
	Object partitionKey = array[low];

	while (true) {
	    while (i <= j && less(array[i], partitionKey, comparator)) {
		if (i == high + 1) break;
		i++;
	    }

	    while (j >= i && less(partitionKey, array[j], comparator)) {
		if (j == low) break;
		j--;
	    }

	    if (j < i) break;

	    exchange(array, i, j);
	}

	exchange(array, low, j);

	return j;
    }

    public static boolean less(Object a, Object b, Comparator comparator) {
	return comparator.compare(a,b) < 0;
    }

    public static void exchange(Object[] array, int i, int j) {
	Object temp = array[i];
	array[i] = array[j];
	array[j] = temp;
    }
}
