package datastructures;

public final class Pair<E, V> {
    private final E first;
    private final V second;
    public Pair(E first, V second) {
        this.first = first;
        this.second = second;
    }

    public E first() {
        return first;
    }

    public V second() {
        return second;
    }
}
