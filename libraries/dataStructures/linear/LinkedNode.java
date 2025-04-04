package libraries.dataStructures.linear;

class LinkedNode<E> {
    protected E data;
    protected LinkedNode<E> next;

    LinkedNode() {
        this(null);
    }

    LinkedNode(E data) {
        this(data, null);
    }

    LinkedNode(E data, LinkedNode<E> next) {
        this.data = data;
        this.next = next;
    }
}
