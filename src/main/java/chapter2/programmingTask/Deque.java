import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Node<Item> first;
    private Node<Item> last; 
    private int size;
    
    // construct an empty deque
    public Deque() {
	first = null;
	last = null;
	size = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
	return size == 0;
    }

    // return the number of items on the deque
    public int size() {
	return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
	assertNotNull(item);
	
	if (size == 0) {
	    first = new Node<>(item);
	    last = first;
	    size = 1;
	} else if (size == 1) {
	    // Last and first points to the same value :)
	    // last keeps the same, and first now points to last
	    first = new Node<>(item);
	    first.setNext(last);
	    last.setPrev(first);
	    size = 2;
	} else {
	    // If size > 1 ==> At least I have 2 nodes in my queue
	    Node<Item> newFirst = new Node<>(item);
	    first.setPrev(newFirst);
	    newFirst.setNext(first);
	    first = newFirst;
	    size++;
	}
    }

    

    // add the item to the back
    public void addLast(Item item) {
	assertNotNull(item);
	
	if (size == 0) {
	    addFirst(item);
	} else if (size == 1) {
	    last = new Node<>(item);
	    first.setNext(last);
	    last.setPrev(first);
	    size = 2;
	} else {
	    Node<Item> newLast = new Node<>(item);
	    last.setNext(newLast);
	    newLast.setPrev(last);
	    last = newLast;
	    size++;
	}
    }

    // remove and return the item from the front
    public Item removeFirst() {
	if (size > 1) {
	    Item res = first.getValue();
	    first = first.getNext();
	    first.setPrev(null);
	    size--;
	    return res;
	} else if (size == 1) {
	    // Removing last element
	    Item res = first.getValue();
	    first = null;
	    last = null;
	    size = 0;
	    return res;
	} else {
	    throw new NoSuchElementException(); 
	} 			       
    }

    // remove and return the item from the back
    public Item removeLast() {
	if (size > 1) {
	    Item res = last.getValue();
	    last = last.getPrev();
	    last.setNext(null);
	    size--;
	    return res;
	} else if (size == 1) {
	    return removeFirst();
	} else {
	    throw new NoSuchElementException(); 
	}
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
	return new Iterator<>() {
	    Node<Item> current = first;

	    public boolean hasNext() {
		return current != null;
	    }

	    public void remove() {
		throw new UnsupportedOperationException();
	    }

	    public Item next() {
		if (current == null) throw new NoSuchElementException();
		
		Item res = current.getValue();
		current = current.getNext();
		return res;
	    }
	};
    }

    private void assertNotNull(Item item) {
	if (item == null) throw new IllegalArgumentException();
    }

    // unit testing (required)
    public static void main(String[] args) {
	Deque<String> deck = new Deque<>();
	deck.addFirst("Hey");
	deck.addLast("Ciaoo");
	deck.addLast("Bella");
	deck.addFirst("Holaa");

	System.out.println(print(deck));
	System.out.println(deck.size());

	System.out.println(deck.isEmpty());
	System.out.println(deck.removeFirst());
	System.out.println(deck.removeLast());
	System.out.println(deck.removeFirst());
	System.out.println(deck.removeLast());
	System.out.println(deck.isEmpty());
    }

    private static <Item> String print(Deque<Item> deck) {
	StringBuilder str = new StringBuilder();
	for (Item val : deck) {
	    str.append(val);
	    str.append(" ");
	}
	return str.toString();
    }

    private static class Node<Item> {
	Node<Item> next;
	Node<Item> prev;
	Item value;

	Node(Item value) {
	    this.value = value;
	}

	public void setNext(Node<Item> next) {
	    this.next = next;
	}

	public Node<Item> getNext() {
	    return next;
	}

	public void setPrev(Node<Item> prev) {
	    this.prev = prev;
	}

	public Node<Item> getPrev() {
	    return prev;
	}

	public Item getValue() {
	    return value;
	}
    }
}

