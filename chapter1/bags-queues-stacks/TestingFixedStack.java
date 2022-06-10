import java.util.Iterator;

public class TestingFixedStack {
    public static void main(String[] args) {
	MyStack<Double> myStackDoubles = new MyStack<Double>();
	myStackDoubles.push(2.0);
	myStackDoubles.push(3.3);
	myStackDoubles.push(-3.2);
	myStackDoubles.push(-3.2);
	myStackDoubles.push(-3.2);
	myStackDoubles.push(-3.2);
	myStackDoubles.push(-3.2);
	myStackDoubles.push(-3.2);
	myStackDoubles.push(-3.2);
	myStackDoubles.push(-3.2);
	myStackDoubles.push(-3.2);
	myStackDoubles.push(-3.2);
	myStackDoubles.push(-3.2);
	myStackDoubles.push(-3.2);
	myStackDoubles.push(-3.2);
	myStackDoubles.push(-3.2);
	myStackDoubles.push(-3.2);
	myStackDoubles.push(-3.2);
	myStackDoubles.push(-3.2);
	myStackDoubles.push(-3.2);
	myStackDoubles.push(8.8);
	myStackDoubles.push(-3.2);

	for (Double val : myStackDoubles) {
	    System.out.println(val);
	}
    }
}

class MyStack<Item> implements Iterable<Item> {
    Item[] internalStack;
    int FIRST_SIZE = 10;
    int elements;

    public MyStack() {
	elements = 0;
	internalStack = (Item[]) new Object[FIRST_SIZE];
    }

    public void push(Item item) {
	if (elements == internalStack.length) resize(internalStack.length * 2);
	internalStack[elements++] = item;
    }

    public Item pop() {
	// If stack is empty
	if (elements < 0) {
	    return null;
	}

	Item res = internalStack[--elements];
	// Avoid loitering; If we don't do this, the element will remain orphan and the garbage collector won't know that it can be free
	internalStack[elements] = null;
	
	if (elements < internalStack.length / 4) {
	    resize(internalStack.length / 2);
	}

	return res;
    }

    public Iterator<Item> iterator() {
	return new Iterator<>() {
	    private int current = elements - 1;
	    
	    @Override
	    public boolean hasNext() {
		return current > -1;
	    }

	    @Override
	    public Item next() {
		return internalStack[current--];
	    }
	};
    }

    private void resize(int resizeFactor) {
	Item[] temp = (Item[]) new Object[resizeFactor];
	for (int j = 0; j < elements; j++) {
	    temp[j] = internalStack[j];
	}
	internalStack = temp;
    }

}
