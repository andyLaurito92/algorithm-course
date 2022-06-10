import java.util.Iterator;

public class MyLinkedList<Item> implements Iterable<Item> {
    private Node<Item> current;

    public void add(Item item) {
	Node<Item> next = new Node<>(item);
	
	// Adding the first element
	if (current == null) {
	    current = next;
	} else {
	    Node<Item> now = current;
	    // While I haven't found the node that doesn't have next
	    while (now.next() != null) {
		now = now.next();
	    }
	    now.setNext(next);
	}
    }

    public void remove(Item item) {
	if (current.value() == item) {
	    current = current.next();
	} else {
	    Node<Item> prev = current;
	    while (prev.next() != null && prev.next().value() != item) {
		prev = prev.next();
	    }

	    if (prev.next() == null) {
		System.out.println("Element not found");
	    } else {
		System.out.println("Element found!");
		prev.setNext(prev.next().next());
	    }
	}
    }

    public void removeLastElem() {
	if (current == null) {
	    // empty list
	    return;
	}
	
	if (current.next() == null) {
	    // this is the last element
	    current = null;
	    return;
	}

	Node<Item> now = current;
	while (now.next().next() != null) {
	    now = now.next(); 
	}
	now.setNext(null);
    }

    public void removeKth(int k) {
	if (current == null) {
	    // remove kth from empty list
	    return;
	}
	
	if (k == 0) {
	    // remove first element
	    current = current.next();
	}

	Node<Item> now = current;
	// Iterate to kth - 1 node if it exists
	while (k > 1 && now.next() != null) {
	    now = now.next();
	    k--;
	}

	if (now.next() != null && k == 1) {
	    // We are at the kth - 1 element. Next element has to be removed
	    now.setNext(now.next().next());
	}
    }

    // In order to freely implement this method, Item needs to implement Comparable
    // public Item max() {
    // 	Item currentMax = null;
    // 	Node<Item> now = current;
    // 	while (now != null) {
    // 	    if (now.value () > currentMax) currentMax = now.value();
    // 	    now = now.next();
    // 	}

    // 	return currentMax;
    // }

    public void insertAfter(Item inList, Item toBeInserted) {
	Node<Item> newNode = new Node<>(toBeInserted);
	Node<Item> now = current;

	while (now != null && !now.value().equals(inList)) {
	    now = now.next();
	}

	if (now != null && now.value().equals(inList)) {
	    if (now.next() == null) {
		now.setNext(newNode);
	    } else {
		Node<Item> prevNext = now.next();
		now.setNext(newNode);
		newNode.setNext(prevNext);
	    }
	}
    }

    public void removeNodeAfter(Item val) {
	Node<Item> now = current;
	while (now != null && !now.value().equals(val)) {
	    now = now.next();
	}

	if (now != null && now.value().equals(val)) {
	    if (now.next() != null) {
		now.setNext(now.next().next());
	    }
	}
    }

    public boolean find(Item k) {
	Node<Item> now = current;
	while (now != null && !now.value().equals(k)) {
	    now = now.next();
	}

	if (now != null && now.value().equals(k)) {
	    return true;
	} else {
	    return false;
	}
    }

    public void reverse() {
	Node<Item> first = current;
	Node<Item> reverse = null;
	while (first != null) {
	    Node<Item> second = first.next();
	    first.setNext(reverse);
	    reverse = first;
	    first = second;
	}
	return reverse;
    }

    public Iterator iterator() {
	return new Iterator<Item>() {
	    Node<Item> now = current;
	    
	    @Override
	    public boolean hasNext() {
		return now != null;
	    }

	    @Override
	    public Item next() {
		Item res = now.value();
		now = now.next();
		return res;
	    }
	};
    }
}

class Node<Item> {
    private Item val;
    private Node<Item> next;

    public Node(Item value) {
	this.val = value;
	this.next = null;
    }

    public void setNext(Node<Item> next) {
	this.next = next;
    }

    public Node<Item> next() {
	return this.next;
    }

    public Item value() {
	return val;
    }
}
