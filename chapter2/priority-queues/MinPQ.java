public interface MinPQ<Key extends Comparable<Key>> {
    public void insert(Key v);

    public Key min();

    public Key delMin();

    public boolean isEmpty();

    public int size();
}
