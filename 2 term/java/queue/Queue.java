package queue;

public interface Queue {
      /*
    model:
        [a0, a2, ..., a(n - 1)]
        n -- размер очереди
        start = a0 - самый первый внесенный элемент
        end = a(n-1) - самый последний внесенный элемент
    Inv:
        n > 0
        for all i = 0 ... n - 1: a[i] != null

    Immutable - [a1, a2, ... ai, ... an]' = [a1, a2, ... ai, ... an] for all ai (очередь не была изменена)
 */

    // pred: elem != null && queue != null
    // post: n = n' + 1 && a[n'] = elem && for i in 1..n': a[i] == a'[i]
    void enqueue(Object element);

    // pred: queue != null && size() > 0
    // post: R = a[1] && n = n' - 1 && for i in 2.. n': a[i - 1] == a'[i]
    Object dequeue();

    //  pred: queue != null
    //  post: R = n == 0 && Immutable
    boolean isEmpty();

    //  pred: queue != null
    //  post: R = n  && Immutable
    int size();

    // pred: queue != null
    // post: [] - очередь не содержит элментов
    void clear();

    // pred: queue != null
    // post: [a1, a2, .. an] && Immutable
    Object[] toArray();

}

