import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private static final int INITIAL_SIZE = 30;

    private Item[] array;
    private int size;
    // construct an empty randomized queue
    public RandomizedQueue() {
	array = (Item[]) new Object[INITIAL_SIZE];
	for (int j = 0; j < array.length; j++) array[j] = null;
	size = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
	return size == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
	return size;
    }

    // add the item
    public void enqueue(Item item) {
	assertNotNull(item);
	if (size == array.length) resize(array.length * 2);

	int current = 0;
	while (array[current] != null) current++;
	array[current] = item;
	size++;
    }

    // remove and return a random item
    public Item dequeue() {
	assertNotEmpty();
	if (size < array.length / 3) resize(array.length / 2);

	int idxToRemove = getNextRandomIdx();
	while (array[idxToRemove] == null) idxToRemove = getNextRandomIdx();
	Item res = array[idxToRemove];
	array[idxToRemove] = null;
	size--;

	return res;
    }

    // return a random item (but do not remove it)
    public Item sample() {
	assertNotEmpty();

	int idxToRemove = getNextRandomIdx();
	while (array[idxToRemove] == null) idxToRemove = getNextRandomIdx();
	return array[idxToRemove];
    }

    private int getNextRandomIdx() {
	return StdRandom.uniform(array.length);
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
	return new RandomIterator<Item>();
    }

    private class RandomIterator<Item> implements Iterator<Item> {
	RandomizedQueue<Item> queue;
	Item[] copy;
	int size;

	public RandomIterator() {
	    queue = new RandomizedQueue<>();
	    for (int j = 0; j < array.length; j++) {
		if (array[j] != null) queue.enqueue((Item) array[j]);
	    }
	}
	public boolean hasNext() {
	    return !queue.isEmpty();
	}

	public void remove() {
	    throw new UnsupportedOperationException();
	}

	public Item next() {
	    if (!hasNext()) throw new NoSuchElementException();

	    return queue.dequeue();
	}
    }

    private void resize(int newSize) {
	Item[] newArray = (Item[]) new Object[newSize];

	for (int i = 0; i < newArray.length && i < array.length; i++) newArray[i] = array[i];

	array = newArray;
    }

    private void assertNotNull(Item item) {
	if (item == null) throw new IllegalArgumentException();
    }

    private void assertNotEmpty() {
	if (isEmpty()) throw new NoSuchElementException();
    }

    // unit testing (required)
    public static void main(String[] args) {
	RandomizedQueue<Integer> queue = new RandomizedQueue<>();
	queue.enqueue(3);
	queue.enqueue(6);
	queue.enqueue(2);
	queue.enqueue(8);

	System.out.println(print(queue));

	System.out.println(queue.sample());
	System.out.println(queue.size());

	System.out.println(queue.dequeue());
	System.out.println(queue.dequeue());

	System.out.println(queue.size());
	System.out.println(print(queue));
    }

    private static <Item> String print(Iterable<Item> iterable) {
	StringBuilder str = new StringBuilder();
	for (Item val : iterable) {
	    str.append(val);
	    str.append(" ");
	}
	return str.toString();
    }
}
