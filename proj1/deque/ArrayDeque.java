package deque;

import java.lang.reflect.Array;

/** A circular array based Double Ended Queue (Deque).
 * @author Liu-Tzechinh
 * @param <T>
 */

public class ArrayDeque<T> {
    // The original CAPACITY of the underlying array.
    private final int CAPACITY = 8;
    // For arrays of length 16 or more, your usage factor should always be at least 25%.
    private final double USAGERATIO = 0.25;
    // Resize factor
    private final double EXPAND_FACTOR = 1.5;
    private final double CONTRACT_FACTOR = 2 * USAGERATIO;

    private int size;
    private T[] items;

    // firstNext 0,1,2, ..., items.length - 1
    // lastNext 0, 1,2, ..., items.length - 1
    private int firstNext;
    private int lastNext;


    /** Creates an empty Deque. */
    public ArrayDeque() {
        size = 0;
        items = (T []) new Object[CAPACITY];
        firstNext = CAPACITY / 2;
        lastNext = CAPACITY / 2 + 1;
    }

    /** Resizes the underlying array to the target capacity. */
    // Not the way we actully resize the array
    private void resize_1(int capacity) {
        T[] temp =(T []) new Object[capacity];
        // two case
        // .... firstNext, lastNext ....
        System.arraycopy(items, 0, temp, 0, lastNext);
        // length from firstNext + 1 to the back of the array.
        int length = size - firstNext - 1;
        System.arraycopy(items, firstNext+1, temp, capacity - length, length);
        // update lastNext and firstNext
        // lastNext = lastNext;
        firstNext = capacity - length - 1;
        // firstNext = -1,  lastNext = 0, the same as the first case.
        items = temp;
    }

    // Another way to resize.
    // Not the way we actully resize the array
    private void resize_2(int capacity) {
        T[] temp = (T[]) new Object[capacity];
        // two case
        // firstNext = -1, lastNext = 0, firstNext % capacity = -1
        int i;
        for (i = firstNext + 1; i % items.length < lastNext + items.length; i++) {
            temp[i%capacity] = items[i % items.length];
        }
        // update firstNext and lastNext
        lastNext = i % capacity;
        firstNext = firstNext % capacity;
        // firstNext != -1
        items = temp;
    }
    /** Downsizes the underlying array to satifying 0.0 * size / capacity > USAGERATIO
     * Not the way we actully downsize the array
     * */
    private void downsize_(int capacity) {
        T[] temp = (T[]) new Object[capacity];
        // two case
        // ... firstNext ... lastNext ...
        int i;
        for (i = firstNext + 1; i % items.length < lastNext; i++) {
            temp[i%capacity] = items[i % items.length];
        }
        // update firstNext and lastNext
        lastNext = i % capacity;
        firstNext = firstNext % capacity;
        items = temp;

        // ...lastNext ... firstNext ...

        // int i;
        for (i = firstNext + 1; i % items.length < lastNext + items.length; i++) {
            temp[i%capacity] = items[i % items.length];
        }
        // update firstNext and lastNext
        lastNext = i % capacity;
        firstNext = firstNext % capacity;
        items = temp;
    }

    /** Copies the items to new underlying array. */
    private void copy(int capacity, int first, int last) {
        T[] temp = (T[]) new Object[capacity];

        int i = first + 1;
        for (i = first + 1; i % items.length < last; i++) {
            temp[i % capacity] = items[i % items.length];
        }
        lastNext = i % capacity;
        firstNext = i % capacity;
        items = temp;
    }

    /** Resizes the underlying array to the target capacity. */
    private void resize(int capacity) {
        copy(capacity, firstNext, lastNext + items.length);
    }

    /**Downsizes the underlying array to satifying 0.0 * size / capacity > USAGERATIO. */
    private void downsize(int capacity) {
        if (firstNext < lastNext) {
            copy(capacity, firstNext, lastNext);
        } else {
            copy(capacity, firstNext, lastNext + items.length);
        }
    }

    /** Adds an item of type T to the front of the deque. Item should not be null. */
    public void addFirst(T item) {
        if (item == null) {
            return;
        }
        if (size == items.length) {
            resize((int) (size * EXPAND_FACTOR));
        }
        items[firstNext] = item;
        firstNext = (firstNext - 1) % items.length;
        size++;
    }

    /** Adds an item of type T to the back of the deque. Item should not be null. */
    public void addLast(T item) {
        if (size == items.length) {
            resize((int) (size * EXPAND_FACTOR));
        }
        items[lastNext] = item;
        lastNext = (lastNext + 1) % items.length;
        size++;
    }

    /** Returns true if deque is empty, false otherwise. */
    public boolean isEmpty() {
        return size == 0;
    }

    /** Returns the number of items in the deque. */
    public int size() {
        return size;
    }

    /** Prints the items in the deque from first to last, separated
     * by a space. Once all the items have been printed, print out a new line.
     */
    public void printDeque() {
        int idx = 1;
        while (idx <= size) {
            System.out.print(items[(firstNext + idx) % items.length]);
            idx++;
        }
    }

    /** Returns true if underlying array's length is bigger than 16,
     * and (size / items.length) < USAGERATION.
     */
    private boolean downsize() {
        return (items.length > 16) && ((1.0 * size / items.length) < USAGERATIO);
    }

    /** Returns true if index out of bound, false otherwise. */
    private boolean checkOutOfBound(int index) {
        return index < 0 || index >= size;
    }

    /** Removes and returns the items at the given index, where 0 is the front, 1 is the
     * next item, and so forth. If no such item exists, return null.*/
    public T remove(int index) {
        if (checkOutOfBound(index)) {
            return null;
        }
        return null;
    }

    /** Removes and returns the item at the front(or back) of the deque.
     * if no such item exists, return null.
     * */
    private T removeFirstLast(int index) {
        size--;
        if (downsize()) {
            downsize((int) (items.length * CONTRACT_FACTOR));
        }
        return items[index];
    }
    /** Removes and returns the item at the front of the deque.
     * If no such item exists, return null.
     * @return
     */
    public T removeFirst_() {
        if (isEmpty()) {
            return null;
        }
        firstNext = (firstNext + 1) % items.length;
        size--;
        if (downsize()) {
            downsize((int) (items.length * CONTRACT_FACTOR));
        }
        return items[firstNext];
    }

    /** Removes and returns the item at the front of the deque.
     * if no such item exists, return null.
     * */
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        firstNext = (firstNext + 1) % items.length;
        return removeFirstLast(firstNext);
    }

    /** Removes and returns the item at the back of the deque.
     * if no such item exists, return null. */
    public T removeLast_() {
        if (isEmpty()) {
            return null;
        }
        // edge case lastNext = 0,
        lastNext = (lastNext - 1 + items.length) % items.length;
        size--;
        if (downsize()) {
            downsize((int) (items.length * CONTRACT_FACTOR));
        }
        return items[lastNext];
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        lastNext = (lastNext - 1 + items.length) % items.length;
        return removeFirstLast(lastNext);
    }

    /** Gets the item at the given index, where 0 is the front, 1 is the
     * next item, and so forth. If no such item exists, return null. Must
     * not alter the deque. */
    public T get(int index) {

        return items[(firstNext + index + 1) % items.length];
    }

    /** The deques objects we’ll make are iterable, so we must provide this method
     * to return an iterator.
     * @return
     */
//    public Iterator<T> iterator() {
//        return null;
//    }

    /** Returns whether the parameter O is equal to the deque or not.
     * o is considered equal if it is a deque and if it contains the same
     * contents(as determined by the generic T's equals method) in the same order.
     * @param o
     * @return
     */
    public boolean equals(Object o) {
        if (o instanceof ArrayDeque) {
            ArrayDeque object = (ArrayDeque) o;
            if (object.size() == size) {
                for (int i = 0; i < size; i++) {
                    if (!object.get(i).equals(get(i))) {
                        return false;
                    }
                }
                return true;
            } else {
                return false;
            }
        }
        return false;
    }
}
