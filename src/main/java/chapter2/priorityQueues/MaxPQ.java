public interface MaxPQ<Key extends Comparable<Key>> {
    // /** Create a priority queue **/
    // public MaxPQ<Key> MaxPQ();

    // /** Create a priority queue of initial capacity max **/
    // public MaxPQ<Key> MaxPQ(int max);

    // public MaxPQ<Key> MaxPQ(Key[] a);
    public void insert(Key v);

    public Key max();

    public Key delMax();

    public boolean isEmpty();

    public int size();
}
