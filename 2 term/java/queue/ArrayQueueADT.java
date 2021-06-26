package queue;

public class ArrayQueueADT {
    /*
model:
 [a0, a2, ..., a(n - 1)]
 n -- размер стека
Inv:
 n > 0
 for all i = 0 ... n - 1: a[i] != null
 ... для end и start

Immutable: n == n' && forall i = 1..n: a[i] == a'[i] (очередь не была изменена)
*/
    // :NOTE: (03.03.2021, MA) -- "Immutable" definition
    private int start;
    private int end;
    private Object[] storage = new Object[5];

    // pred: elem != null && queue != null
    // post: n = n' && a[end] = elem && end' = end
    public static void enqueue(ArrayQueueADT queue, Object element) {
        assert element != null;
        ensureCapacity(queue, size(queue) + 1);
        queue.storage[queue.end] = element;
        queue.end = (queue.end + 1) % queue.storage.length;
    }

    private static void ensureCapacity(ArrayQueueADT queue, int capacity) {
        if (capacity < queue.storage.length) {
            return;
        }
        queue.end = size(queue);
        queue.storage = copy(queue, 2 * capacity);
        queue.start = 0;
    }

    // pre: queue != null
    //  post: ((R = end - start && start <= end) || (R = n - start + end && start > end)) && Immutable
    public static int size(ArrayQueueADT queue) {
        if (queue.start <= queue.end)
            return queue.end - queue.start;
        else
            return queue.storage.length - queue.start + queue.end;
    }


    // pred: queue != null && size > 0
    // post: R = a[start] && n' = n
    // :NOTE: (03.03.2021, MA) -- wrong contract!!
    public static Object dequeue(ArrayQueueADT queue) {
        assert size(queue) > 0;
        Object ans = queue.storage[queue.start];
        queue.start = (queue.start + 1) % queue.storage.length;
        return ans;
    }

    // pre: queue != null && size > 0
    // :NOTE: (03.03.2021, MA) -- null check everywhere!!
    //  post: R = a[start] && Immutable
    public static Object element(ArrayQueueADT queue) {
        return queue.storage[queue.start];
    }

    //  pred: queue != null
    //  post: R = size() == 0 && Immutable
    public static boolean isEmpty(ArrayQueueADT queue) {
        return (queue.start == queue.end);
    }

    // pred: queue != null
    // post: R = (size == n) && Immutable
    public static boolean isFull(ArrayQueueADT queue) {
        return size(queue) == queue.storage.length;
    }

    // pred: queue != null
    // post: a[i] = 0 for all i = 0 .. n - 1 && start' = -1 && end' = 0
    public static void clear(ArrayQueueADT queue) {
        queue.start = 0;
        queue.end = 0;
        queue.storage = new Object[5];
    }

    // pre: queue != null
    // post: R = "[a1, a2, ... ai, a(i+1), ... an]", где ai добавилось раньше чем a(i+1) для всех i
    public static Object[] toArray(ArrayQueueADT queue) {
        return copy(queue, size(queue));
    }

    private static Object[] copy(ArrayQueueADT queue, int size) {
        Object[] res = new Object[size];
        for (int i = 0; i < size; i++) {
            res[i] = queue.storage[(queue.start + i) % (queue.storage.length)];
        }
        return res;
    }

}
