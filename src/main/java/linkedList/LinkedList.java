package linkedList;

public class LinkedList<T> {
    private Node<T> head;
    private Node<T> tail;
    private int length;

    public LinkedList() {
        this.head = null;
        this.tail = null;
        length = 0;
    }

    public int length() {
        return length;
    }

    public boolean isEmpty() {
        return length == 0;
    }

    public void add(T data) {
        Node<T> newNode = new Node<T>(data, tail, null);
        if (isEmpty()) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        length++;
    }

    public void remove() {
        if (isEmpty() || head == null)
            return;
        if (length == 1) {
            head = null;
            tail = null;
        } else if (length > 1) {
            Node node = tail;
            tail = tail.prev;
            tail.next = null;
        }
        length--;
    }

    public void print() {
        Node<T> node = head;
        if (isEmpty()) {
            System.out.println("There are no nodes in the linked list.");
        } else {
            while (node != null) {
                System.out.print(node.data + " -> ");
                node = node.next;
            }
            System.out.println("null");
            System.out.println();
        }
    }
}
