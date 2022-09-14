public class UndergradedQuestions {
    public static void main(String[] args) {
	dynamicMedian();
    }

    /** Dynamic median. Design a data type that supports insert in logarithmic time, find-the-median 
     ** in constant time, and remove-the-median in logarithmic time. If the number of keys in the data 
     ** type is even, find/remove the lower median.
     **/
    public static void dynamicMedian() {
	MedianHeap<Integer> medianHeap = new MedianHeap<>();
	medianHeap.insert(2);
	medianHeap.insert(7);
	medianHeap.insert(6);
	medianHeap.insert(3);
	medianHeap.insert(1);
	medianHeap.insert(0);
	
	System.out.println(medianHeap.median());
	System.out.println(medianHeap.delMedian());
	System.out.println(medianHeap.median());
	System.out.println(medianHeap.delMedian());
	System.out.println(medianHeap.median());
	System.out.println(medianHeap.delMedian());
	System.out.println(medianHeap.median());
    }

    static class MedianHeap<Key extends Comparable<Key>> {
	private MinPQ<Key> minHeap; // Elements that are bigger than the current median;
	private MaxPQ<Key> maxHeap; // Elements that are smaller than the current median;
	private Key median;

	MedianHeap() {
	    minHeap = new MinPQArray<>();
	    maxHeap = new MaxPQHeap<>();
	    median = null;
	}

	public void insert(Key elem) {
	    // Inserting first element
	    if (median == null) {
		median = elem;
		return;
	    }

	    if (less(elem, median)) {
		maxHeap.insert(elem);
	    } else {
		minHeap.insert(elem);
	    }
	    calculateMedian();
	}

	private void calculateMedian() {
	    int smallerElements = maxHeap.size();
	    int biggerElements = minHeap.size();
	    int total = smallerElements + biggerElements + 1;

	    if (total % 2 == 0) {
		if (smallerElements < biggerElements) {
		    // do nothing
		} else {
		    // Median move to the left!
		    Key newMedian = maxHeap.delMax();
		    minHeap.insert(median);
		    median = newMedian;
		}
	    } else {
		if (smallerElements == biggerElements) return;
		
		if (smallerElements < biggerElements) {
		    // Median move to the right!
		    Key newMedian = minHeap.delMin();
		    maxHeap.insert(median);
		    median = newMedian;
		} else {
		    // Median move to the left!
		    Key newMedian = maxHeap.delMax();
		    minHeap.insert(median);
		    median = newMedian;
		}
	    }
	}

	public Key median() {
	    return median;
	}

	public Key delMedian() {
	    Key res = median;
	    int smaller = maxHeap.size();
	    int bigger = minHeap.size();

	    if (smaller < bigger) {
		median = minHeap.delMin();
	    } else {
		median = maxHeap.delMax();
	    }
	    calculateMedian();
	    return res;
	}

	private boolean less(Comparable elem1, Comparable elem2) {
	    return elem1.compareTo(elem2) < 0;
	}
    }
}
