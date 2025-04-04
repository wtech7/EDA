package libraries.dataStructures.linear;

import libraries.dataStructures.models.ListPOI;

public class LinkedListPOI<E> implements ListPOI<E> {
    private LinkedNode<E> first = new LinkedNode<>();
    private LinkedNode<E> last = first;
    private LinkedNode<E> prev = first;

    private int size = 0;

    @Override
    public void add(E e) {
        prev.next = new LinkedNode<>(e, prev.next);
        if (prev == last)
            last = prev.next;
        prev = prev.next;
        size++;
    }

    @Override
    public void remove() {
        if (prev.next == last)
            last = prev;
        prev.next = prev.next.next;
        size--;
    }

    @Override
    public void begin() {
        prev = first;
    }

    @Override
    public void next() {
        prev = prev.next;
    }

    @Override
    public void end() {
        prev = last;
    }

    @Override
    public E get() {
        return prev.next.data;
    }

    @Override
    public boolean isEnd() {
        return prev == last;
    }

    @Override
    public boolean isEmpty() {
        return first == last;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append('[');
        LinkedNode<E> aux = first.next;
        for (int i = 0; i < size - 1; i++, aux = aux.next)
            s.append(aux.data).append(", ");
        if (size != 0)
            s.append(aux.data);
        s.append(']');
        return s.toString();
    }
}
