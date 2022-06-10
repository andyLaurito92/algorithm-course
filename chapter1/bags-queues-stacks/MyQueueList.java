import java.util.Iterator;

public class MyQueueList<Item> implements MyQueue<Item> {
    private Node<Item> first;
    private Node<Item> last;
    private int size = 0;

    public void enqueue(Item item) {
	if (first == null) {
	    first = new Node<>(item);
	    last = first;
	} else {
	    Node<Item> newLast = new Node<>(item);
	    last.setNext(newLast);
	    last = newLast;
	}
	size++;
    }

    public Item dequeue() {
	if (first == null) {
	    size = 0;
	    last = null;
	    return null;
	}

	Item res = first.value();
	first = first.next();
	size --;
	return res;
    }

    public boolean isEmpty() {
	return size == 0;
    }

    public String toString() {
	StringBuilder str = new StringBuilder();
	for (Item val : this) {
	    str.append(val);
	}
	return str.toString();
    }

    public Iterator<Item> iterator() {
	return new Iterator() {
	    private Node<Item> current = first;

	    public boolean hasNext() {
		return current != null;
	    }

	    public Item next() {
		Item res = current.value();
		current = current.next();
		return res;
	    }
	};
    }
}
