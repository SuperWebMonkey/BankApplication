package linkedList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LinkedList<T> {
    private Node<T> head;
    private Node<T> tail;
    private int length;
    private final static Logger LOGGER = LogManager.getLogger(LinkedList.class);


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

    public void addInPos(T data, int pos) {
        Node newNode = new Node(data);
        Node cur = head;

        if (pos <= 0 || pos > length) {
            LOGGER.info("invalid positions");
            return;
        }

        if (pos == 1) {
            newNode.next = head;
            head.prev = newNode;
            newNode.prev = null;
            head = newNode;
        } else {
            for (int i = 1; i < pos - 1; i++) {
                cur = cur.next;
            }

            Node store_next = cur.next;
            cur.next = newNode;
            newNode.prev = cur;
            newNode.next = store_next;
            store_next.prev = newNode;
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

    public void removeInPos(int pos) {
        if (pos <= 0 || pos > length) {
            LOGGER.info("Invalid Index");
            return;
        }

        if (head == null)
            return;
        else {
            Node cur = head;

            for (int i = 1; i < pos; i++) {
                cur = cur.next;
            }

            if (cur == head) {
                head = cur.next;
            } else if (cur == tail) {
                tail = tail.prev;
            } else {
                cur.prev.next = cur.next;
                cur.next.prev = cur.prev;
            }
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
