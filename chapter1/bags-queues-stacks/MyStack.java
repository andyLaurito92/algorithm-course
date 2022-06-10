public interface MyStack<Item> extends Iterable<Item> {
    public void push(Item item);

    public Item pop();

    public boolean isEmpty();

    public String toString();

    public Item peek();

    public int size();
}
