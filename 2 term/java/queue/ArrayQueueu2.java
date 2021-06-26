package queue;

public class ArrayQueueu2 {
    /*
 model:
     [a0, a2, ..., a(n - 1)]
     n -- размер стека
 Inv:
     n > 0
     for all i = 0 ... n - 1: a[i] != null
     ... для end и start

 Immutable - [a1, a2, ... ai, ... an]' = [a1, a2, ... ai, ... an] for all ai (очередь не была изменена)
*/
    private int start;
    private int end;
    private int size;
    private Object[] queue = new Object[5];

    // pred: queue != null && elem != null
    // post: n = n' && a[end] = elem && end' = end
    public void enqueue(Object element) {
        assert element != null;
        ensureCapacity(size() + 1);
        queue[end] = element;
        end = (end + 1) % queue.length;
        size++;
    }

    private void ensureCapacity(int capacity) {
        if (capacity < queue.length) {
            return;
        }
        end = size();
        queue = copy(2 * capacity);
        start = 0;
    }

    //  pred: queue != null
    //  post: ((R = end - start && start <= end) || (R = n - start + end && start > end)) && Immutable
    public int size() {
        if (start <= end) {
            size = end - start;
        } else {
            size = queue.length - start + end;
        }
        return size;

    }

    // pred: queue != null && size > 0
    // post: R = a[start]
    public Object dequeue() {
        assert size > 0;
        Object ans = queue[start];
        start = (start + 1) % queue.length;
        size--;
        return ans;
    }

    //  pred: queue != null && size > 0
    //  post: R = a[start] && Immutable
    public Object element() {
        return queue[start];
    }

    //  pred: queue != null
    //  post: R = size() == 0 && Immutable
    public boolean isEmpty() {
        return (start == end);
    }

    // pred: queue != null
    // post: R = (size == n) && Immutable
    public boolean isFull() {
        return size() == queue.length;
    }

    // pred: queue != null
    // post: a[i] = 0 for all i = 0 .. n - 1 && start' = -1 && end' = 0
    public void clear() {
        start = 0;
        end = 0;
        queue = new Object[5];
    }

    // pre: queue != null
    // post: R = "[a1, a2, ... ai, a(i+1), ... an]", где ai добавилось раньше чем a(i+1) для всех i
    public Object[] toArray() {
        return copy(size());
    }

    private Object[] copy(int size) {
        Object[] res = new Object[size];
        for (int i = 0; i < size; i++) {
            res[i] = queue[(start + i) % (queue.length)];
        }
        return res;
    }
}

