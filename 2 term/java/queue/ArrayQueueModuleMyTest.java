package queue;


public class ArrayQueueModuleMyTest {
    public static void fill(int n) {
        for (int i = 0; i < n; i++) {
            ArrayQueueModule.enqueue(i);
        }
    }

    public static void dump(int n) {
        for (int i = 0; i < n; i++) {
            ArrayQueueModule.dequeue();
            System.out.println(ArrayQueueModule.size() + " " +
                    ArrayQueueModule.element() + " " + ArrayQueueModule.dequeue());

        }
    }

    public static void main(String[] args) {

        fill(10);
        dump(2);
    }
}
