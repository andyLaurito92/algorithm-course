public class TopDownMergeSort {
    public static void main(String[] args) {
	Integer[] array = new Integer[]{0, -2, -5, 3, 18, 9, -9, -43, 12, 1993, 37, 28, 1};
	sort(array);
	System.out.println(print(array));
    }

    public static void sort(Comparable[] array) {
	int low = 0;
	int high = array.length - 1;

	Comparable[] aux = new Comparable[array.length];
	mergeSort(array, aux, low, high);
    }

    public static void mergeSort(Comparable[] array, Comparable[] aux, int low, int high) {
	if (high <= low) return;
	int mid = low + (high - low)/2;
	mergeSort(array, aux, low, mid);
	mergeSort(array, aux, mid+1, high);
	merge(array, aux, low, mid, high);
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

    public static void swap(Comparable[] array, int i, int j) {
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
