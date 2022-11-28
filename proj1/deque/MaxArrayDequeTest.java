package deque;
import org.junit.Test;
import org.junit.Assert;


public class MaxArrayDequeTest {
    @Test
    public void maxTest() {
        StringComparator scmp = new StringComparator();
        MaxArrayDeque<String> stringdeque = new MaxArrayDeque<>(scmp);
        stringdeque.addFirst("a");
        stringdeque.addFirst("bcd");
        stringdeque.addFirst("efg");
        stringdeque.printDeque();
        System.out.println(stringdeque.max());
        System.out.println(stringdeque.max(scmp));
    }


}
