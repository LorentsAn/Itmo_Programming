package queue;

public abstract class AbstractQueue implements Queue {

    protected int size;

    public void enqueue(Object element) {
        assert element != null;
        enqueueImpl(element);
        size++;
    }

    protected abstract void enqueueImpl(Object element);

    public Object dequeue() {
        assert size > 0;
        Object value = element();
        delete();
        size--;
        return value;
    }

    protected abstract void delete();

    protected abstract Object element();

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        clearImpl();
        size = 0;
    }

    protected abstract void clearImpl();

    public Object[] toArray() {
        Object[] res = new Object[size];
        for (int i = 0; i < size; i++) {
            res[i] = dequeue();
            enqueue(res[i]);
        }
        return res;
    }

}
