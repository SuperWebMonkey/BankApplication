package linkedList;

public class Node<T> {
    protected T data;
    protected Node<T> prev;
    protected Node<T> next;

    Node(T data) {
        this.data = data;
        next = null;
    }

    Node(T data, Node prev, Node next) {
        this.data = data;
        this.prev = prev;
        this.next = next;
    }
}
