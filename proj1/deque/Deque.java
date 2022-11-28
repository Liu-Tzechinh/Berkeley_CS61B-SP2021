package deque;

import static javax.swing.UIManager.get;

public interface Deque<T> {

    void addFirst(T item);

    void addLast(T item);


    /** Returns true if deque is empty, false otherwise. */
    default boolean isEmpty() {
        return size() == 0;
    };

    int size();

    T get(int index);



    T removeFirst();

    T removeLast();
}
