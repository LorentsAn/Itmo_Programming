package queue;

public class ArrayQueue extends AbstractQueue {

    private int start;
    private int end;
    private Object[] queue = new Object[5];

    protected void enqueueImpl(Object element) {
        ensureCapacity(size() + 1);
        queue[end] = element;
        end = (end + 1) % queue.length;
    }

    protected void delete() {
        start = (start + 1) % queue.length;
    }

    public Object element() {
        return queue[start];
    }

    private void ensureCapacity(int capacity) {
        if (capacity < queue.length) {
            return;
        }
        end = size();
        int size = 2 * capacity;
        Object[] res = new Object[size];
        for (int i = 0; i < size; i++) {
            res[i] = queue[(start + i) % (queue.length)];
        }
        queue = res;
        start = 0;
    }

    public void clearImpl() {
        start = 0;
        end = 0;
        Object[] step = new Object[5];
        queue = step;
    }

}