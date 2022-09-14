package chapter1.bagsQueuesStacks;

import java.util.Iterator;

public class MyStackList<Item> implements MyStack<Item> {
    private Node<Item> top;
    private int size = 0;

    public void push(Item item) {
		Node<Item> oldTop = top;
		top = new Node<>(item);
		top.setNext(oldTop);
		size++;
    }

    public Item pop() {
		if (top == null) {
			return null;
		} else {
			Item res = top.value();
			top = top.next();
			size --;
			return res;
		}
    }

    public boolean isEmpty() {
		return top == null;
    }

    public Item peek() {
		return top.value();
    }

    public int size() {
		return size;
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
			private Node<Item> current = top;

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
