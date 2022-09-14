package chapter1.bagsQueuesStacks;

import java.util.Iterator;

public class MyStackArray<Item> implements MyStack<Item> {
    private Item[] myStack;
    private int size = 30;
    private int numElements;

    public MyStackArray() {
		myStack = (Item[]) new Object[size];
		numElements = 0;
    }

    public void push(Item item) {
		if (numElements == myStack.length) {
			resize(numElements * 2);
		}
		myStack[numElements++] = item;
    }

    public Item pop() {
		if (numElements == 0) {
			return null;
		}

		if (numElements == myStack.length / 4) {
			resize(numElements / 2);
		}

		return myStack[--numElements];
    }

    public boolean isEmpty() {
		return numElements == 0;
    }

    public Item peek() {
		return myStack[numElements - 1];
    }

    public int size() {
		return numElements;
    }

    public String toString() {
		StringBuilder res = new StringBuilder();
		for (int j = 0; j < numElements; j++) {
			res.append(myStack[j]);
			res.append(" ");
		}
		return res.toString();
    }

    private void resize(int newLength) {
		Item[] newArray = (Item[]) new Object[newLength];

		for (int j = 0; j < numElements; j++) {
			newArray[j] = myStack[j];
		}

		myStack = newArray;
    }

    @Override
    public Iterator<Item> iterator() {
		return new Iterator<Item>() {
			private int current = numElements - 1;

			@Override
			public boolean hasNext() {
			return current > -1;
			}

			@Override
			public Item next() {
			return myStack[current];
			}
		};
    }
}
