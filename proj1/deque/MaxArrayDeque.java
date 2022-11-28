package deque;
import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private Comparator<T> cmp;

    /**
     * Create a MaxArrayDeque with the given Comparator
     * @param c
     */
    public MaxArrayDeque(Comparator<T> c) {
        super();
        cmp = c;
    }

    /**
     * reutrns the maximum element in the deque as governed by the given Comparator.
     * If MaxArrayDeque is empty, simply return null.
     */
    public T max() {
        return max(cmp);
    }

    /**
     * returns the maximum element in the deque as governed by the parameter Comparator c.
     * If MaxArrayDeque is empty, simply return null.
     * @param c
     * @return
     */
    public T max(Comparator<T> c) {
        if (isEmpty()) {
            return null;
        }
        int maxIdx = 0;
        for (int i = 0; i < size(); i++) {
            if (c.compare(get(i), get(maxIdx)) > 0) {
                maxIdx = i;
            }
        }
        return get(maxIdx);
    }
}
