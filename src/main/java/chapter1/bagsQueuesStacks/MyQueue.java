package chapter1.bagsQueuesStacks;

public interface MyQueue<Item> extends Iterable<Item> {
    public void enqueue(Item item);

    public Item dequeue();

    public boolean isEmpty();

    public String toString();
}
