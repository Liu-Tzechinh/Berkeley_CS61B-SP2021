package deque;

import static javax.swing.UIManager.get;

public interface Deque<T> {

    void addFirst(T item);

    void addLast(T item);


    /** Returns true if deque is empty, false otherwise. */
    default boolean isEmpty() {
        return size()==0;
    };

    int size();

    T get(int index);

    void set(T item, int index);

    /** Prints the items in the deque from first to last, separated
     * by a space. Once all the items have been printed, print out a new line.
     */
    default void printDeque() {
        for (int i = 0; i < size(); i++) {
            System.out.print(get(i));
            if (i < size() - 1) {
                System.out.print(" ");
            }
        }
        System.out.println();
    }

    T removeFirst();

    T removeLast();
}
