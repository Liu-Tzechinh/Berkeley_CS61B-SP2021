package deque;

import java.util.Iterator;

/** A circular sentinel linked list based Deque of type T. */
public class LinkedListDeque<T> implements Iterable<T>, Deque<T> {

    private class TNode {
        private T item;
        private TNode prev;
        private TNode next;
        TNode(T item, TNode prev, TNode next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }

    }
    /* The first item (if it exists) is at sentinel.next. */
    private TNode sentinel;
    private int size;

    /** Creates an empty deque.LinkedListDeque */
    public LinkedListDeque() {
        sentinel = new TNode(null, null, null);

        sentinel.next = this.sentinel;
        sentinel.prev = this.sentinel;
        size = 0;
    }

    @Override
    /** Adds an item of type T to the front of the deque. Item should not be null. */
    public void addFirst(T item) {
        if (item == null) {
            // TODO raises exception
            // System.out.println("null");
            return;
        }
        size += 1;
        // consider two case
        // sentinel the LinkedListDeque is not empty
        sentinel.next.prev = new TNode(item, sentinel, sentinel.next);
        sentinel.next = sentinel.next.prev;
        // second the LinkedListDeque is empty
    }

    @Override
    /** Adds an item of type T to the back of the Deque. Item should not be null. */
    public void addLast(T item) {
        if (item == null) {
            // TODO raises exception
            // System.out.println("null");
            return;
        }
        size += 1;
        // consider two case
        // sentinel the LinkedListDeque is not empty
        sentinel.prev.next = new TNode(item, sentinel.prev, sentinel);
        sentinel.prev = sentinel.prev.next;
        // second the LinkedListDeque is empty
    }


    @Override
    /** Returns the number of items in the deque. */
    public int size() {
        return size;
    }


    /** Removes and returns the item at the front of the deque.
     * If no such item exists, return nulls.
     */
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        TNode temp = sentinel.next;
        temp.next.prev = sentinel;
        sentinel.next = temp.next;
        size--;
        return temp.item;
    }

    /** Removes and returns the item at the back of the deque.
     * If no such item exists, return null.
     */
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        TNode temp =  sentinel.prev;
        temp.prev.next = sentinel;
        sentinel.prev = temp.prev;
        size--;
        return temp.item;
    }

    /** Checks whether the index out of bound or not. */
    private boolean checkOutOfBound(int index) {
        if (index < 0 || index >= size) {
            // raise exception here?
            return true;
        }
        return false;
    }

    @Override
    /** Gets the item at the given index, where 0 is the front, 1 is the next item,
     * and so forth. If no such item exists, returns null. Must not alter the deque. */
    public T get(int index) {
        if (checkOutOfBound(index)) {
            // TODO raise exception;
            return null;
        }
        TNode temp = sentinel;
        for (int i = 0; i <= index; i++) {
            temp = temp.next;
        }
        return temp.item;
    }


    private T getRecursive(TNode temp, int index) {
        if (index == 0) {
            return temp.item;
        }
        return getRecursive(temp.next, index - 1);
    }
    /** Same as get, but uses recursion. */
    public T getRecursive(int index) {
        if (checkOutOfBound(index)) {
            // TODO raise exception;
            return null;
        }
        return getRecursive(sentinel.next, index);
    }

    /* The deque objects we'll make are iterable, so we must provide this method
    to return an iterator.
     */
    public Iterator<T> iterator() {
        return new LinkedListDequeIterator();
    }

    private class LinkedListDequeIterator implements Iterator<T> {
        private int position;

        LinkedListDequeIterator() {
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
        if (o instanceof Deque) {
            Deque object = (Deque) o;
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
    /** Prints the items in the deque from first to last, separated
     * by a space. Once all the items have been printed, print out a new line.
     */
    public void printDeque() {
        for (int i = 0; i < size(); i++) {
            System.out.print(get(i));
            if (i < size() - 1) {
                System.out.print(" ");
            }
        }
        System.out.println();
    }
}
