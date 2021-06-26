package queue;


public class LinkedQueue extends AbstractQueue {

    private Node tail;
    private Node head;

    public void enqueueImpl(Object element) {
        Node old = tail;
        tail = new Node(element, null);
        if (isEmpty()) {
            head = tail;
        } else {
            old.next = tail;
        }
    }

    protected void delete() {
        head = head.next;
        if (isEmpty()) {
            tail = null;
        }
    }

    public Object element() {
        return head.value;
    }


    public void clearImpl() {
        head = tail = null;
    }


    private static class Node {
        private final Object value;
        private Node next;

        public Node(Object value, Node next) {
            assert value != null;
            this.value = value;
            this.next = next;
        }
    }


}

