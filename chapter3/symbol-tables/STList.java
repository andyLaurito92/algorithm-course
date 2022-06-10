import java.util.ArrayList;

public class STList<Key, Value> implements ST<Key, Value> {
    Node<Key, Value> first;
    int size;
    
    public STList() {
	first = null;
	size = 0;
    }

    public void put(Key key, Value val) {
	Node<Key, Value> node = lookForNodeWith(key);
	if (node == null) {
	    Node<Key, Value> newFirst = new Node<>(key, val);
	    newFirst.next(first);
	    first = newFirst;
	    size++;
	} else {
	    node.updateVal(val);
	}
    }

    public Value get(Key key) {
	Node<Key, Value> node = lookForNodeWith(key);
	if (node != null) return node.val();
	return null;
    }

    public void delete(Key key) {
	if (size == 0) return;
	
	Node<Key, Value> current = first;
	Node<Key, Value> prev = null;
	while (current != null &&
	       current.key() != key) {
	    prev = current;
	    current = current.getNext();
	}
	if (current == null) return; // Key not found
	
	if (prev == null) {
	    // Deleting first node
	    first = first.getNext();
	    size--;
	}
	prev.next(current.getNext());
    }

    public boolean contains(Key key) {
	return lookForNodeWith(key) != null;
    }

    public boolean isEmpty() {
	return size != 0;
    }

    public int size() {
	return size;
    }

    public Iterable<Key> keys() {
	ArrayList<Key> keys = new ArrayList<>();
	for (Node<Key, Value> current = first;
	     current != null; current = current.getNext()) {
	    keys.add(current.key());
	}
	return keys;
    }

    private Node<Key, Value> lookForNodeWith(Key key) {
	Node<Key, Value> current = first;
	while (current != null &&
	       !current.key().equals(key)) {
	    current = current.getNext();
	}
	return current;
    }

    private static class Node<Key, Value> {
	Key key;
	Value val;
	Node<Key, Value> next;

	Node(Key key, Value val) {
	    this.key = key;
	    this.val = val;
	}

	void next(Node<Key, Value> next) {
	    this.next = next;
	}

	Node<Key, Value> getNext() {
	    return next;
	}

	Key key() {
	    return key;
	}

	Value val() {
	    return val;
	}

	void updateVal(Value val) {
	    this.val = val;
	}
    }
}
