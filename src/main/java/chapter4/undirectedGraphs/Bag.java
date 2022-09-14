package chapter4.undirectedGraphs;

import java.util.Iterator;

public class Bag<Element> implements Iterable<Element> {
    private Element[] elements;
    private int size;

    public Bag() {
	elements = (Element[]) new Object[30];
	size = 0;
    }

    public void add(Element e) {
	if (size == elements.length) resize(size * 2);
	elements[size++] = e;
    }

    public int count() {
	return size;
    }

    public boolean isEmpty() {
	return size == 0;
    }

    public Iterator<Element> iterator() {
	return new Iterator<>() {
	    int current = 0;
	    public boolean hasNext() {
		return current < size;
	    }

	    public Element next() {
		return elements[current++];
	    }
	};
    }

    private void resize(int newSize) {
	Element[] newArray = (Element[]) new Object[newSize];
	for (int j = 0; j < size; j++) newArray[j] = elements[j];
	elements = newArray;
    }

    public static void main(String[] args) {
	Bag<String> elements = new Bag<>();
	elements.add("Hey");
	elements.add("Hola");
	print(elements.count());

	for (String str : elements) print(str);
    }

    private static void print(Object str) {
	System.out.println(str);
    }
}
