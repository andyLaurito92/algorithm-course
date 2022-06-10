import java.util.ArrayList;
import java.util.Arrays;

public class STArray<Key extends Comparable<Key>, Value> implements STOrdered<Key, Value> {
    private Node<Key, Value>[] elements;
    private int size;
    
    public STArray() {
	size = 0;
	elements = (Node<Key, Value>[]) new Node[20];
    }
    
    public void put(Key key, Value val) {
	if (isEmpty()) {
	    elements[size++] = Node.of(key, val);
	}

	int position = rank(key);
	if (isDefined(position, key)) {
	    elements[position].updateVal(val);
	} else {
	    if (size == elements.length) resize(size*2);

	    for (int j = size; j > position; j--) {
		elements[j] = elements[j-1];
	    }
	    elements[position] = Node.of(key, val);
	    size++;
	}
    }

    public Value get(Key key) {
	if (isEmpty()) return null;
	
	int position = rank(key);
	if (isDefined(position, key)) {
	    return elements[position].val();
	}
	return null;
    }

    public void delete(Key key) {
	if (isEmpty()) return;
	
	int position = rank(key);
	if (!isDefined(position, key)) return;

	for (int j = position; j < size - 1; j++) {
	    elements[j] = elements[j+1];
	}
	size--;
    }

    public boolean contains(Key key) { 
	if (isEmpty()) return false;
	
	int position = rank(key);
	return isDefined(position, key); 
    }

    public boolean isEmpty() { 
	return size == 0;
    }

    public int size() { 
	return size;
    }

    public Key min() { 
	if (isEmpty()) return null;
	return elements[0].key();
    }

    public Key max() { 
	if (isEmpty()) return null;
	return elements[size-1].key();
    }

    // Largest key less than or equal to key
    public Key floor(Key key) {
	return elements[Math.max(0, rank(key) - 1)].key();
    } 

    // smallest key greater than or equal to key
    public Key ceiling(Key key) { 
	return elements[rank(key)].key();
    } 

    // number of keys less than key
    public int rank(Key key) { 
	int lo = 0; int hi = size - 1;
	while (lo <= hi) {
	    int med = lo + (hi - lo)/2;
	    Node<Key, Value> current = elements[med];
	    Key currentKey = current.key();
	    int comparisson = key.compareTo(currentKey);
	    if (comparisson == 0) return med;
	    else if (comparisson < 0) hi = med - 1;
	    else lo = med + 1;
	}
	return lo;
    } 

    // key of rank k
    public Key select(int k) { 
	if (k > size || k < 0) {
	    return null;
	}
	return elements[k].key();
    }

    public void deleteMin() {
	if (isEmpty()) return;
	delete(elements[0].key());
    }

    public void deleteMax() { 
	if (isEmpty()) return;
	delete(elements[size-1].key());
    }

    public int size(Key lo, Key hi) {
	int posLo = rank(lo);
	int posHi = rank(hi);

	if (!isDefined(posLo, lo) || !isDefined(posHi, hi)) {
	    return -1;
	}

	return posHi - posLo + 1;
    }

    public Iterable<Key> keys(Key lo, Key hi) { 
	int posLo = rank(lo);
	int posHi = rank(hi);
	if (hi.compareTo(elements[posHi].key()) < 0) posHi = Math.max(0, posHi - 1);
	if (lo.compareTo(elements[posLo].key()) > 0) posLo = Math.min(size, posLo + 1);

	ArrayList<Key> res = new ArrayList<>();
	for (int j = posLo; j < posHi + 1; j++) {
	    res.add(elements[j].key());
	}
	return res;
    }

    public Iterable<Key> keys() { 
	ArrayList<Key> res = new ArrayList<>();
	for (int j = 0; j < size; j++) {
	    res.add(elements[j].key());
	}

	return res;

    }

    public String toString() {
	StringBuilder str = new StringBuilder();
	for (int j = 0; j < elements.length; j++) {
	    str.append(elements[j]);
	    str.append(" ");
	}
	return str.toString();
    }

    private boolean isDefined(int pos, Key key) {
	return elements[pos] != null && elements[pos].key().equals(key);
    }

    private void resize(int newLength) {
	Node<Key, Value>[] res = (Node<Key, Value>[]) new Node[newLength];
	for (int i = 0; i < size; i++) {
	    res[i] = elements[i];
	}
	elements = res;
    }

    private static class Node<Key, Value> {
	private final Key key;
	private Value val;

	public static <Key, Value> Node<Key, Value> of(Key key, Value val) {
	    return new Node<>(key, val);
	}

	private Node(Key key, Value val) {
	    this.key = key;
	    this.val = val;
	}

	public Key key() {
	    return key;
	}

	public Value val() {
	    return val;
	}

	public void updateVal(Value val) {
	    this.val = val;
	}

	public String toString() {
	    return String.format("(Key: %s, Val: %s)", key, val);
	}
    }

    public static void main(String[] args) {
	STOrdered<Integer, String> myOrderedMap = new STArray<>();
	myOrderedMap.put(3, "Hola");
	myOrderedMap.put(2, "Andres");
	myOrderedMap.put(0, "Eduardo");
	myOrderedMap.put(26, "Gabriela");
	myOrderedMap.put(-9, "Mila");
	myOrderedMap.put(20, "Cristian");
	myOrderedMap.put(-33, "Patricia");
	myOrderedMap.put(-32, "Ramon");
	myOrderedMap.put(-12, "Chapulin");
	myOrderedMap.put(200, "Diablo");
	myOrderedMap.put(199, "Romina");

	System.out.println(myOrderedMap);

	System.out.println(myOrderedMap.max());
	System.out.println(myOrderedMap.min());
	System.out.println(myOrderedMap.floor(8));
	System.out.println(myOrderedMap.ceiling(52));
	System.out.println(myOrderedMap.select(4));

	System.out.println(myOrderedMap.rank(-12));

	for (Integer key : myOrderedMap.keys(0, 15)) {
	    System.out.println(String.format("Key %s Val %s", key, myOrderedMap.get(key)));
	}
    }
}
