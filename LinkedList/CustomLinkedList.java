package LinkedList;

public class CustomLinkedList {
    private Node head;
    private int size;

    public CustomLinkedList() {
        this.head = null;
        this.size = 0;
    }

    public void addFirst(int data) {
        Node newNode = new Node(data);
        newNode.next = head;
        head = newNode;
        size++;
    }

    public void addLast(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
    }

    public void addAt(int index, int data) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Invalid index.");
        }

        if (index == 0) {
            addFirst(data);
            return;
        }

        Node newNode = new Node(data);
        Node current = head;
        for (int i = 0; i < index - 1; i++) {
            current = current.next;
        }
        newNode.next = current.next;
        current.next = newNode;
        size++;
    }

    public int removeFirst() {
        if (head == null) {
            throw new IllegalStateException("The list is empty. Nothing to delete.");
        }

        int removedData = head.data;
        head = head.next;
        size--;
        return removedData;
    }

    public int removeLast() {
        if (head == null) {
            throw new IllegalStateException("The list is empty. Nothing to delete.");
        }

        if (head.next == null) {
            int removedData = head.data;
            head = null;
            size--;
            return removedData;
        }

        Node current = head;
        while (current.next.next != null) {
            current = current.next;
        }

        int removedData = current.next.data;
        current.next = null;
        size--;
        return removedData;
    }

    public int removeAt(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Invalid index.");
        }

        if (index == 0) {
            return removeFirst();
        }

        Node current = head;
        for (int i = 0; i < index - 1; i++) {
            current = current.next;
        }

        int removedData = current.next.data;
        current.next = current.next.next;
        size--;
        return removedData;
    }

    public boolean contains(int data) {
        Node current = head;
        while (current != null) {
            if (current.data == data) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public int size() {
        return size;
    }

    public void printList() {
        Node current = head;
        System.out.print("[");
        while (current != null) {
            System.out.print(current.data);
            if (current.next != null) {
                System.out.print(", ");
            }
            current = current.next;
        }
        System.out.println("]");
    }
}