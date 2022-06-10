import java.util.Comparator;
import java.util.Random;

public class QuestionsMergesort {

    public static void main(String[] args) {
	//exercise1();
	//countInversions();
	shufflingLinkedList();
    }

    public static void shufflingLinkedList() {
	// Given a singly-linked list containing n items, rearrange the items uniformly at random.
	// Your algorithm should consume a logarithmic (or constant) amount of extra memory and run in time proportional to nlogn in the worst case.

	MyList<Integer> myList = new MyList<>();
	myList.addFirst(3);
	myList.addFirst(-12);
	myList.addFirst(99);
	myList.addFirst(0);
	myList.addFirst(8);
	myList.addFirst(-7);
	myList.addFirst(-1);

	myList.print();
	//myList.shuffle();
	myList.hintShuffle();
	myList.print();
    }

    public static void countInversions() {
	// i < j but a[i] > a[j] is an inversion
	// complexity: linearithmic
	// solution: Modify mergesort such that every time we do a comparisson where the right object has to be put first than the
	// first object, count 1
	Integer[] array = new Integer[]{ -13, 0, 3, -2, 1, -12, 0, 0, 7};
	Integer[] aux = new Integer[array.length];
	System.out.println(mergeSort(array, aux, 0, aux.length -1, Comparator.naturalOrder()));
	print(array);
    }

    public static int mergeSort(Object[] array, Object[] aux, int low, int high, Comparator comparator) {
	if (low >= high) return 0;
	int med = low + (high - low)/2;

	int countInv1 = mergeSort(array, aux, low, med, comparator);
	int countInv2 = mergeSort(array, aux, med + 1, high, comparator);

	int countInv3 = merge(array, aux, low, med, high, comparator);

	return countInv1 + countInv2 + countInv3;
    }

    public static int merge(Object[] array, Object[] aux, int low, int med, int high, Comparator comparator) {
	int compare = comparator.compare(array[med], array[med+1]);
	if (compare <= 0) {
	    // if the highest element is smaller or equal than the first element on the right,
	    // We can be sure that these arrays are already sorted !
	    return 0;
	}
	
	for (int i = low; i <= high; i++) aux[i] = array[i];

	int idxLow = low;
	int idxHigh = med + 1;
	int countInversions = 0;
	for (int j = low; j <= high; j++) {
	    if (idxLow == med + 1) {
		array[j] = aux[idxHigh++];
		continue;
	    } else if (idxHigh == high + 1) {
		array[j] = aux[idxLow++];
		continue;
	    }
	    int comparisson = comparator.compare(aux[idxLow], aux[idxHigh]);
	    if (comparisson <= 0) {
		array[j] = aux[idxLow++];
	    } else if (comparisson > 0) {
		countInversions += med + 1 - idxLow;
		array[j] = aux[idxHigh++];
	    }
	}
	return countInversions;
    }

    public static void exercise1() {
	/** Merging with smaller auxiliary array. Suppose that the subarray a[0]\mathtt{a[0]}a[0] 
	 ** to a[n−1]\mathtt{a[n-1]}a[n−1] is sorted and the subarray a[n]\mathtt{a[n]}a[n] to 
	 ** a[2∗n−1]\mathtt{a[2*n-1]}a[2∗n−1] is sorted. How can you merge the two subarrays so that a[0]\mathtt{a[0]}a[0] to a[2∗n−1]\mathtt{a[2*n-1]}a[2∗n−1] is sorted using an auxiliary array of length nnn (instead of 2n2n2n)?
	 **/
	Integer[] smaller = new Integer[]{-12, -3, -1, 0, 1, 3, 5, 8, 9, 12};
	Integer[] larger = new Integer[]{-24 ,-13, -11, -9, -5, 0, 0, 0, 3, 5};

	merge(smaller, larger, Comparator.naturalOrder());
    }

    public static Object[] merge(Object[] array1, Object[] array2, Comparator comparator) {
	if (comparator.compare(array1[array1.length -1], array2[0]) < 0) {
	    // If the greatest element in array1 is smaller than the smallest element in
	    // array2, then these arrays are already sorted; Nothing to do
	    print(array1);
	    print(array2);
	    return array1;
	} else if (comparator.compare(array2[array2.length-1], array1[0]) < 0) {
	    print(array2);
	    print(array1);
	    return array2;
	}

	Object[] smallers = new Object[array1.length]; 

	int idx1 = 0;
	int idx2 = 0;
	for (int idxSmallers = 0; idxSmallers < smallers.length; idxSmallers++) {
	    if (idx1 == array1.length) {
		smallers[idxSmallers] = array2[idx2++];
	    } else if (idx2 == array2.length) {
		smallers[idxSmallers] = array1[idx1++];
	    } else if (comparator.compare(array1[idx1], array2[idx2]) <= 0) {
		smallers[idxSmallers] = array1[idx1++];
	    } else {
		smallers[idxSmallers] = array2[idx2++];
	    }
	}

	// Up to here, we have the smaller N values in smallers; Now we need to sort the bigger
	// ones using as an auxiliary array one of the provided

	// If idx1 is greater, then grab array1 as new auxiliary
	for (int j = 0; j < smallers.length; j++) {
	    if (idx1 == array1.length) {
		array1[j] = array2[idx2++];
	    } else if (idx2 == array2.length) {
		array1[j] = array1[idx1++];
	    } else if (comparator.compare(array1[idx1], array2[idx2]) <= 0) {
		array1[j] = array1[idx1++];
	    } else {
		array1[j] = array2[idx2++];
	    }
	}

	print(smallers);
	print(array1);
	return smallers;
    }

    private static void print(Object[] array) {
	StringBuilder str = new StringBuilder();
	for (Object val : array) {
	    str.append(val);
	    str.append(" ");
	}
	System.out.println(str.toString());
    }

    static class Tuple<First,Second> {
	First elem1;
	Second elem2;

	public static <First, Second> Tuple<First,Second> of(First elem1, Second elem2) {
	    return new Tuple<>(elem1, elem2);
	}

	public Tuple(First elem1, Second elem2) {
	    this.elem1 = elem1;
	    this.elem2 = elem2;
	}

	public First first() {
	    return elem1;
	}

	public Second second() {
	    return elem2;
	}

	public void print() {
	    System.out.println(String.format("(%s, %s)", elem1, elem2));
	}
    }

    static class MyList<Item> {
	Node<Item> first;
	Object[] pointers; // Store log(N) elements, pointing to i^2 from i = 0 to size
	int size;
	Random random;

	MyList() {
	    size = 0;
	    first = null;
	    pointers = new Object[100];
	    random = new Random();
	}

	public Node<Item> getFirst() {
	    return first;
	}

	public void addFirst(Item val) {
	    Node<Item> newElement = new Node<>(val);
	    newElement.setNext(first);
	    first = newElement;
	    size++;

	    recalculatePointers();
	}

	public Item removeLast() {
	    if (isEmpty()) return null;
	    if (size == 1) {
		// first element is the last
		Item res = first.value();
		first = null;
		size = 0;
		return res;
	    }

	    Node<Item> current = first.next();
	    Node<Item> prev = first;
	    while (current.next() != null) {
		prev = current;
		current = current.next();
	    }

	    prev.setNext(null);
	    Item res = current.value();
	    current = null;

	    return res;
	}

	public boolean isEmpty() {
	    return size == 0;
	}

	public int size() {
	    return size;
	}

	//Hint: design a linear-time subroutine that can take two uniformly shuffled linked lists of sizes n1​ and n2​ and combined them into a uniformly shuffled linked lists of size n1+n2
	public void hintShuffle() {
	    first = shuffle(first, size);
	}

	private Node<Item> shuffle(Node<Item> first, int size) {
	    if (size == 1) {
		first.setNext(null);
		return first;
	    }

	    int middleNum = size/2;
	    int sizeFirst = middleNum;
	    int sizeSecond = size - middleNum;
	    Node<Item> middle = getNthNodeFrom(first, middleNum);
	    
	    Node<Item> shuffledFirst = shuffle(first, sizeFirst);
	    Node<Item> shuffledSecond = shuffle(middle, sizeSecond);
	    return mergeRandomly(shuffledFirst, shuffledSecond, sizeFirst, sizeSecond);
	}

	private Node<Item> mergeRandomly(Node<Item> firstList, Node<Item> secondList, int sizeFirst, int sizeSecond) {
	    // Set res for the first time; In this way, we avoid creating a new node in each randomly merge
	    // which means we are using O(N + k) space, where k represent the space of pointing to nodes;
	    Node<Item> res;
	    if (random.nextBoolean()) {
		res = firstList;
		sizeFirst --;
		firstList = firstList.next();
	    } else {
		res = secondList;
		sizeSecond --;
		secondList = secondList.next();
	    }

	    Node<Item> currentRes = res;
	    for (int j = 0; j < sizeFirst + sizeSecond; j++) {
		if (firstList == null) {
		    currentRes.setNext(secondList);
		    secondList = secondList.next();
		} else if (secondList == null) {
		    currentRes.setNext(firstList);
		    firstList = firstList.next();
		} else if(random.nextBoolean()) {
		    currentRes.setNext(firstList);
		    firstList = firstList.next();
		} else {
		    currentRes.setNext(secondList);
		    secondList = secondList.next();
		}
		currentRes = currentRes.next();
	    }
	    return res;
	}

	private Node<Item> getNthNodeFrom(Node<Item> first, int getTo) {
	    Node<Item> current = first;
	    while (getTo > 0) {
		current = current.next();
		getTo--;
	    }
	    return current;
	}
	
	public void shuffle() {
	    if (size < 2) return;

	    Random random = new Random();
	    for (int i = 0; i < size; i++) {
		int nextElem = i + random.nextInt(size - i);
		this.exchange(i,nextElem); 
	    }
	}

	public void exchange(int i, int j) {
	    Tuple<Node<Item>, Integer> firstPower2 = getClosestPower2Node(i);
	    Tuple<Node<Item>, Integer> secondPower2 = getClosestPower2Node(j);
	    
	    Node<Item> first = getPower2Node(firstPower2.first(), firstPower2.second(), i);
	    Node<Item> second = getPower2Node(secondPower2.first(), secondPower2.second(), j);

	    Item temp = first.value();
	    first.setValue(second.value());
	    second.setValue(temp);
	}

	private Node<Item> getPower2Node(Node<Item> myFirst, int pow2Idx, int idx) {
	    Node<Item> current = myFirst;
	    int remaining = idx - pow2Idx;
	    while (remaining > 0) {
		current = current.next();
		remaining --;
	    }
	    return current;
	}

	public Tuple<Node<Item>, Integer> getClosestPower2Node(int i) {
	    if (i == 0) {
		return Tuple.of(first, 0);
	    }

	    int pow2 = 1;
	    int idx = 0;
	    while (pow2 < i) {
		pow2 += pow2;
		idx++;
	    }
	    if (idx == 0) {
		return Tuple.of((Node<Item>) pointers[0], 1);
	    } else {
		return Tuple.of((Node<Item>) pointers[--idx], pow2/2);
	    }
	    
	}

	private void recalculatePointers() {
	    int idxPointers = 0;
	    for (int i = 1; i < size && idxPointers < pointers.length; i += i) {
		pointers[idxPointers++] = getNthNode(i);
	    }
	}

	private Node<Item> getNthNode(int idx) {
	    Node<Item> node = first;
	    while (idx > 0) {
		node = node.next();
		idx --;
	    }
	    return node;
	}

	public void print() {
	    if (isEmpty()) {
		System.out.println(" ");
		return;
	    }
	    StringBuilder str = new StringBuilder();
	    Node<Item> current = first;
	    while (current != null) {
		str.append(current.value());
		str.append(" ");
		current = current.next();
	    }
	    System.out.println(str.toString());
	}
    }

    static class Node<Item> {
	Node<Item> next;
	Item value;

	Node(Item val) {
	    this.value = val;
	}

	public void setValue(Item val) {
	    this.value = val;
	}

	public void setNext(Node<Item> next) {
	    this.next = next;
	}

	public Node<Item> next() {
	    return next;
	}

	public Item value() {
	    return value;
	}

	@Override
	public String toString() {
	    return String.format("Val: %s", value);
	}
    }
}
