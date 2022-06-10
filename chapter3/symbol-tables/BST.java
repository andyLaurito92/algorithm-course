public class BST<Key extends Comparable<Key>, Value> implements STOrdered<Key, Value> {
    private Node<Key, Value> root;
    private int size;

    public BST() {
	root = null;
	size = 0;
    }

    public void put(Key key, Value val) {
	if (size == 0) {
	    root = new Node<>(key, val);
	    size++;
	    return;
	}

	Node<Key, Value> current = root;
	Node<Key, Value> prev = null;
	while (current != null) {
	    prev = current;
	    int comparisson = key.compareTo(current.key());
	    if (comparisson == 0) {
		current.updateVal(val);
		return;
	    } else if (comparisson < 0) {
		current = current.left();
	    } else {
		current = current.right();
	    }
	}

	if (lessOrEqual(key, prev.key())) {
	    prev.addLeft(Node.of(key, val));
	} else {
	    prev.addRight(Node.of(key, val));
	}
	size++;
    }

    public Value get(Key key) {
	if (isEmpty()) return null;

	Node<Key, Value> current = root;
	while (current != null && !current.key().equals(key)) {
	    if (lessOrEqual(key, current.key())) {
		current = current.left();
	    } else {
		current = current.right();
	    }
	}
	return current.val();
    }

    public void delete(Key key) {

    }

    public boolean contains(Key key) {
	return get(key) != null;
    }

    public boolean isEmpty() {
	return size == 0;
    }

    public int size() {
	return size;
    }

    public Key min() {
	Node<Key, Value> current = root;
	Node<Key, Value> prev = null;
	while (current != null) {
	    prev = current;
	    current = current.left();
	}
	return prev.key();
    }

    public Key max() {
	Node<Key, Value> current = root;
	Node<Key, Value> prev = null;
	while (current != null) {
	    prev = current;
	    current = current.right();
	}
	return prev.key();
    }

    //largest key less than or equal to key
    public Key floor(Key key) {
	return floor(root, key);
    }

    private Key floor(Node<Key, Value> current, Key key) {
	if (current == null) return null;

	int comparisson = key.compareTo(current.key());
	if (comparisson == 0) return current.key();
	if (comparisson < 0) return floor(current.left(), key);
	else {
	    Key resRight = floor(current.right(), key);
	    if (resRight != null) return resRight;
	    else return current.key();
	}
    }

    // smallest key greater than or equal to key
    public Key ceiling(Key key){
	return ceiling(root, key);
    }

    private Key ceiling(Node<Key, Value> current, Key key) {
	if (current ==  null) return null;
	int comparisson = key.compareTo(current.key());
	if (comparisson == 0) return key;
	else if (comparisson > 0) return ceiling(current.right(), key);
	else {
	    Key resLeft = ceiling(current.left(), key);
	    if (resLeft != null) return resLeft;
	    else return current.key();
	}
    }

    // number of keys less than key
    public int rank(Key key) {
	return 0;
    }

    // key of rank k
    public Key select(int k) {
	return null;
    }

    public void deleteMin() {
	
    }

    public void deleteMax() {

    }

    public int size(Key lo, Key hi) {
	return 0;
    }

    public Iterable<Key> keys(Key lo, Key hi) {
	return null;
    }

    public Iterable<Key> keys() {
	return null;
    }

    public String toString() {
	return root.toString();
    }

    private boolean lessOrEqual(Key key1, Key key2) {
	return key1.compareTo(key2) <= 0;
    }

    private static class Node<Key, Value> {
	private Key key;
	private Value val;
	private Node<Key, Value> left;
	private Node<Key, Value> right;

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

	public int count() {
	    int res = 0;
	    if (left != null) res += left.count();
	    if (right != null) res += right.count();
	    return 1 + res;
	}

	public Node<Key, Value> left() {
	    return left;
	}

	public Node<Key, Value> right() {
	    return right;
	}

	public void addLeft(Node<Key, Value> left) {
	    this.left = left;
	}

	public void addRight(Node<Key, Value> right) {
	    this.right = right;
	}

	public String toString() {
	    StringBuilder str = new StringBuilder();
	    if (left != null) str.append(left.toString());
	    str.append(String.format("(k: %s, v: %s) ", key, val));
	    if (right != null) str.append(right.toString());
	    return str.toString();
	}
    }

    public static void main(String[] args) {
	STOrdered<Integer, String> myOrderedMap = new BST<>();
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

	print("Max min");
	System.out.println(myOrderedMap.max());
	System.out.println(myOrderedMap.min());

	print("Floor, Ceiling");
	System.out.println(myOrderedMap.floor(8));
	System.out.println(myOrderedMap.floor(3));
	System.out.println(myOrderedMap.floor(25));
	System.out.println(myOrderedMap.floor(23));
	System.out.println(myOrderedMap.floor(1));
	System.out.println(myOrderedMap.floor(-31));
	System.out.println(myOrderedMap.ceiling(-31));

	print("Select 4");
	System.out.println(myOrderedMap.select(4));

	System.out.println(myOrderedMap.rank(-12));

	// for (Integer key : myOrderedMap.keys(0, 15)) {
	//     System.out.println(String.format("Key %s Val %s", key, myOrderedMap.get(key)));
	// }
    }

    public static void print(String str) {
	System.out.println(str);
    }
}
