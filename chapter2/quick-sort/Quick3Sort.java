import java.util.Comparator;
import java.util.Random;

public class Quick3Sort {
    public class void main(String[] args) {
	Integer[] array = new Integer[]{3, -5, -1, -12, 8, 7, 12, 28, 0, -24};
	sort(array, Comparator.naturalOrder());
	print(array);
    }

    private static void sort(Object[] array, Comparator comparator) {
	//Avoiding worst case by performing Knuth shuffle
	shuffle(array);
	sort(array, 0, array.length-1, comparator);
    }

    private static void sort(Object[] array, int low, int high, Comparator comparator) {
	if (high <= low) return;
	
	Object key = array[low];
	int i = low + 1;
	int lo = low + 1;
	int gt = high;
	while (i <= gt) {
	    int comparisson = comparator.compare(array[i], key);
	    if (comparisson < 0) {
		exchange(array, i++, lo++);
	    } else if (comparisson > 0) {
		exchange(array, i, gt--);
	    } else {
		i++;
	    }
	}

	sort(array, low, lo - 1, comparator);
	sort(array,  + 1, high, comparator);
    }

    private static void shuffle(Object[] array) {
	Random random = new Random();
	for (int i = 0; i < array.length; i++) {
	    int j = i + random.nextInt(array.length - i);
	    exchange(array, i, j);
	}
    }

    private static exchange(Object[] array, int i, int j) {
	Object temp = array[i];
	array[i] = array[j];
	array[j] = temp;
    }

    private static void print(Object[] array) {
	StringBuilder str = new StringBuilder();
	for (Object val : array) {
	    str.append(val);
	    str.append(" ");
	}
	System.out.println(str.toString());
    }
}
