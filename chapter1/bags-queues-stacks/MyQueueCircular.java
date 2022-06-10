import java.util.Iterator;

public class MyQueueCircular<Item> implements MyQueue<Item> {
    private Node<Item> last;
    private int size = 0;

    public void enqueue(Item item) {
	// Empty list
	if (last == null) {
	    last = new Node<Item>(item);
	    last.setNext(last);
	} else {
	    // The invariant here is that the last elements has always as next the first elem
	    Node<Item> prevLast = last;
	    Node<Item> first = prevLast.next();
	    last = new Node<Item>(item);
	    last.setNext(first);
	    prevLast.setNext(last);
	}
	size++;
    }


    public Item dequeue() {
	// Empty list
	if (last == null) return null;
	// The last.next() will always contain the first element
	if (last.next() == last) {
	    // Only 1 element in the list. Becoming empty queue
	    Item item = last.value();
	    last = null;
	    size = 0;
	    return item;
	} else {
	    Item item = last.next().value();
	    last.setNext(last.next().next());
	    size--;
	    return item;
	}
    }

    public boolean isEmpty() {
	return size == 0;
    }

    public Iterator<Item> iterator() {
	return new Iterator<Item>() {
	    private Node<Item> current = last.next();
	    private Node<Item> first = last.next();
	    private boolean firstTime = true;

	    public boolean hasNext() {
		if (current == null) return false;
		if (current == first && firstTime) {
		    firstTime = false;
		    return true;
		}

		return current != first;
	    }

	    public Item next() {
		Item res = current.value();
		current = current.next();
		return res;
	    }
	};
    }

    public String toString() {
	StringBuilder strBuilder = new StringBuilder();
	if (size == 1) {
	    strBuilder.append(last.value());
	} else {
	    Node<Item> current = last.next(); // First elem
	    while (current != last) {
		strBuilder.append(current.value());
		strBuilder.append(" ");
		current = current.next();
	    }
	    // Append last value and exit
	    strBuilder.append(current.value());
	}
	return strBuilder.toString();
    }
}
