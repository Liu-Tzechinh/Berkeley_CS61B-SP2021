package deque;
import java.util.Iterator;

/** A circular array based Double Ended Queue (Deque).
 * @author Liu-Tzechinh
 * @param <T>
 */
public class ArrayDeque<T> implements Iterable<T>, Deque<T> {
    // The original CAPACITY of the underlying array.
    private final int CAPACITY = 8;
    // For arrays of length 16 or more, your usage factor should always be at least 25%.
    private final double USAGE_RATIO = 0.25;
    // Resize factor
    private final double EXPAND_FACTOR = 1.5;
    private final double CONTRACT_FACTOR = 2 * USAGE_RATIO;

    private int size;
    private T[] items;

    // firstNext 0,1,2, ..., items.length - 1
    // lastNext 0, 1,2, ..., items.length - 1
    private int firstNext;
    private int lastNext;

    /** Creates an empty Deque. */
    public ArrayDeque() {
        size = 0;
        items = (T[]) new Object[CAPACITY];
        firstNext = CAPACITY / 2;
        lastNext = CAPACITY / 2 + 1;
    }

    public ArrayDeque(int c) {
        size = 0;
        items = (T[]) new Object[c];
        firstNext = c / 2;
        lastNext = c / 2 + 1;
    }

    /** Copies the items to new underlying array.
     *  Uses for-loop
     *  Leftmost
     *  firstNext, (firstNext+1) % items.length, ... ,(firstNext + size) % items.length
     * capacity -1, 0, 1, 2, ..., size - 1
     */
    private void copyForLoop(int capacity) {
        T[] temp = (T[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            temp[i] = items[(firstNext + i + 1) % items.length];
        }
        lastNext = size;
        firstNext = capacity - 1;
        items = temp;
    }

    /** Copies the items to new underlying array.
     * Uses arraycopy
     * Leftmost
     * firstNext, (firstNext+1) % items.length, ... ,(firstNext + size) % items.length
     * capacity -1, 0, 1, 2, ..., size - 1
     */
    private void copyArrayCopy(int capacity) {
        T[] temp = (T[]) new Object[capacity];
        int srcPos = (firstNext + 1) % items.length;
        if (srcPos + size <= items.length) {
            System.arraycopy(items, srcPos, temp, 0, size);
        } else {
            int length = items.length - srcPos;
            System.arraycopy(items, srcPos, temp, 0, length);
            System.arraycopy(items, 0, temp, length, lastNext);
            // System.arraycopy(items, 0, temp, length, size - length);
        }
        firstNext = capacity - 1;
        lastNext = size;
        items = temp;
    }

    /** Resizes the underlying array to the target capacity. */
    private void resize() {
        if (size == items.length) {
            copyForLoop((int) (size * EXPAND_FACTOR));
//            copyArrayCopy((int) (size * EXPAND_FACTOR));
        }
    }

    /**Downsizes the underlying array to satifying 0.0 * size / capacity > USAGERATIO. */
    private void downsize() {
        if (items.length > 16 && (1.0 * size / items.length) < USAGE_RATIO) {
            copyArrayCopy((int) (items.length * CONTRACT_FACTOR));
        }
    }

    @Override
    public void addFirst(T item) {
        if (item != null) {
            resize();
            items[firstNext] = item;
            size++;
            firstNext = (firstNext - 1 + items.length) % items.length;
        }
    }

    @Override
    public void addLast(T item) {
        if (item != null) {
            resize();
            items[lastNext] = item;
            size++;
            lastNext = (lastNext + 1 + items.length) % items.length;
        }
    }


    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        firstNext = (firstNext + 1) % items.length;
        size--;
        T tmp = items[firstNext];
        downsize();
        return tmp;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        lastNext = (lastNext - 1 + items.length) % items.length;
        size--;
        T tmp = items[lastNext];
        downsize();
        return tmp;
    }

    /** Returns true if index out of bound, false otherwise. */
    private boolean checkOutOfBound(int index) {
        return index < 0 || index >= size;
    }

    @Override
    public T get(int index) {
        if (!checkOutOfBound(index)) {
            return items[(firstNext + index + 1) % items.length];
        }
        return null;
    }

    @Override
    public void set(T item, int index) {
        if (!checkOutOfBound(index)) {
            items[(firstNext + index + 1) % items.length] = item;
        }
    }
    /** The deques objects we’ll make are iterable, so we must provide this method
     * to return an iterator.
     * @return
     */
    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    private class ArrayDequeIterator implements Iterator<T> {
        private int position;

        ArrayDequeIterator() {
            position = 0;
        }

        public boolean hasNext() {
            return position < size;
        }

        public T next() {
            T returnItem = get(position);
            position += 1;
            return returnItem;
        }
    }

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
