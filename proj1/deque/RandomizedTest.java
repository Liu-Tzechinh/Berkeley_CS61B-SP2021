package deque;
import edu.princeton.cs.algs4.StdRandom;
import static org.junit.Assert.*;

public class RandomizedTest {

    public static void main(String[] args) {
        for (int i = 0; i < 500; i++) {
            LinkedListDeque<Integer> lld = new LinkedListDeque<>();
            ArrayDeque<Integer> ad = new ArrayDeque<>();
            for (int j = 0; j < 500; j++) {
                int opNumber = StdRandom.uniform(5);
                if (opNumber == 0) {
                    int rval = StdRandom.uniform(1000);
                    lld.addFirst(rval);
                    ad.addFirst(rval);
                } else if (opNumber == 1) {
                    int rval = StdRandom.uniform(1000);
                    lld.addLast(rval);
                    ad.addLast(rval);
                    assertTrue(ad.equals(lld));
                    assertTrue(lld.equals(ad));
                    // System.out.println(ad.equals(lld));
                } else if (opNumber == 2) {
                    assertEquals(lld.size(), ad.size());
                } else if (opNumber == 3) {
                    int index = StdRandom.uniform(lld.size() + 1);
                    assertEquals(lld.get(index), ad.get(index));
                } else if (opNumber == 4) {
                    assertEquals(lld.removeFirst(), ad.removeFirst());
                } else {
                    assertEquals(lld.removeLast(), ad.removeLast());
                }
            }

        }
    }

}
