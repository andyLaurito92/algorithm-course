package chapter1.bagsQueuesStacks;

public class Node<Item> {
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