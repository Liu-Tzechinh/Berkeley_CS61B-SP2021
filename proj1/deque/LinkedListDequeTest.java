package deque;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;


/** Performs some basic linked list tests. */
public class LinkedListDequeTest {

    @Test
    /* Adds a few things to the list, checking isEmpty() and size() are correct,
      finally printing the results.
      && is the "and" operation.
      */
    public void addIsEmptySizeTest() {

        // System.out.println("Make sure to uncomment the lines below (and delete this print statement).");

        LinkedListDeque<String> lld1 = new LinkedListDeque<>();

		assertTrue("A newly initialized LLDeque should be empty", lld1.isEmpty());
		lld1.addFirst("front");

		// The && operator is the same as "and" in Python.
		// It's a binary operator that returns true if both arguments true, and false otherwise.
        assertEquals(1, lld1.size());
        assertFalse("lld1 should now contain 1 item", lld1.isEmpty());

		lld1.addLast("middle");
		assertEquals(2, lld1.size());

		lld1.addLast("back");
		assertEquals(3, lld1.size());

		System.out.println("Printing out deque: ");
		lld1.printDeque();
    }

    @Test
    public void addRemoveTest() {

        // System.out.println("Make sure to uncomment the lines below (and delete this print statement).");
        LinkedListDeque<Integer> lld1 = new LinkedListDeque<>();
		// should be empty
		assertTrue("lld1 should be empty upon initialization", lld1.isEmpty());

		lld1.addFirst(10);
		// should not be empty
		assertFalse("lld1 should contain 1 item", lld1.isEmpty());

		lld1.removeFirst();
		// should be empty
		assertTrue("lld1 should be empty after removal", lld1.isEmpty());
    }

    @Test
    /* Tests removing from an empty deque */
    public void removeEmptyTest() {

        // System.out.println("Make sure to uncomment the lines below (and delete this print statement).");

        LinkedListDeque<Integer> lld1 = new LinkedListDeque<>();
        lld1.addFirst(3);

        lld1.removeLast();
        lld1.removeFirst();
        lld1.removeLast();
        lld1.removeFirst();

        int size = lld1.size();
        String errorMsg = "  Bad size returned when removing from empty deque.\n";
        errorMsg += "  student size() returned " + size + "\n";
        errorMsg += "  actual size() returned 0\n";

        assertEquals(errorMsg, 0, size);

    }

    @Test
    /* Check if you can create Linked List Deque with different parameterized types*/
    public void multipleParamTest() {


        LinkedListDeque<String>  lld1 = new LinkedListDeque<>();
        LinkedListDeque<Double>  lld2 = new LinkedListDeque<>();
        LinkedListDeque<Boolean> lld3 = new LinkedListDeque<>();

        lld1.addFirst("string");
        lld2.addFirst(3.14159);
        lld3.addFirst(true);

        String s = lld1.removeFirst();
        assertTrue(s instanceof String);
        Double d = lld2.removeFirst();
        assertTrue(d instanceof Double);
        Boolean b = lld3.removeFirst();
        assertTrue(b instanceof Boolean);


    }

    @Test
    /* check if null is return when removing from an empty LinkedListDeque. */
    public void emptyNullReturnTest() {

        // System.out.println("Make sure to uncomment the lines below (and delete this print statement).");

        LinkedListDeque<Integer> lld1 = new LinkedListDeque<>();

        assertNull("Should return null when removeFirst is called on an empty Deque,", lld1.removeFirst());
        assertNull("Should return null when removeLast is called on an empty Deque,", lld1.removeLast());


    }

    @Test
    /* Add large number of elements to deque; check if order is correct. */
    public void bigLLDequeTest() {

        // System.out.println("Make sure to uncomment the lines below (and delete this print statement).");

        LinkedListDeque<Integer> lld1 = new LinkedListDeque<>();
        for (int i = 0; i < 1000000; i++) {
            lld1.addLast(i);
        }

        for (double i = 0; i < 500000; i++) {
            assertEquals("Should have the same value", i, (double) lld1.removeFirst(), 0.0);
        }

        for (double i = 999999; i > 500000; i--) {
            assertEquals("Should have the same value", i, (double) lld1.removeLast(), 0.0);
        }
    }

    @Test
    /* Add large number of elements to deque; check if order is correct. */
    // addLast,
    public void addLastTest() {

        // System.out.println("Make sure to uncomment the lines below (and delete this print statement).");

        LinkedListDeque<Integer> lld1 = new LinkedListDeque<>();
        for (int i = 0; i < 1000000; i++) {
            lld1.addLast(i);
        }

        for (double i = 0; i < 500000; i++) {
            assertEquals("Should have the same value", i, (double) lld1.removeFirst(), 0.0);
        }

        for (double i = 999999; i >= 500000; i--) {
            assertEquals("Should have the same value", i, (double) lld1.removeLast(), 0.0);
        }

        assertEquals(lld1.size(), 0);
        assertNull("Should return null when removeFirst is called on an empty Deque,", lld1.removeFirst());
        assertNull("Should return null when removeLast is called on an empty Deque,", lld1.removeLast());
    }

    @Test
    /* Add large number of elements to deque; check if order is correct. */
    // addFirst
    public void addFirstTest() {

        // System.out.println("Make sure to uncomment the lines below (and delete this print statement).");

        LinkedListDeque<Integer> lld1 = new LinkedListDeque<>();
        for (int i = 0; i < 1000000; i++) {
            lld1.addFirst(i);
        }

        // 999999+1 =  1000000
        for (double i = 999999; i > 500000; i--) {
            assertEquals("Should have the same value", i, (double) lld1.removeFirst(), 0.0);
        }

        for (double i = 0; i <= 500000; i++) {
            assertEquals("Should have the same value", i, (double) lld1.removeLast(), 0.0);
        }
        assertEquals(lld1.size(), 0);
        assertNull("Should return null when removeFirst is called on an empty Deque,", lld1.removeFirst());
        assertNull("Should return null when removeLast is called on an empty Deque,", lld1.removeLast());
    }

    // test print deque
    @Test
    public void printDequeTest() {
        LinkedListDeque<String> lld1 = new LinkedListDeque<>();
        lld1.addFirst("a");
        lld1.addFirst("b");
        lld1.addFirst("c");
        lld1.addLast("d");
        lld1.addLast("e");
        lld1.addLast("f");
        // c b a d e f
        lld1.printDeque();
    }

    // test get and getRecursive
    @Test
    public void getTest() {
        LinkedListDeque<String> lld1 = new LinkedListDeque<>();
        lld1.addFirst("a");
        lld1.addFirst("b");
        lld1.addFirst("c");
        lld1.addLast("d");
        lld1.addLast("e");
        lld1.addLast("f");

        assertNull(lld1.getRecursive(-1));
        assertNull(lld1.getRecursive(6));
        assertEquals("c", lld1.getRecursive(0));
        assertEquals("b", lld1.getRecursive(1));
        assertEquals("a", lld1.getRecursive(2));
        assertEquals("d", lld1.getRecursive(3));
        assertEquals("e", lld1.getRecursive(4));
        assertEquals("f", lld1.getRecursive(5));

        assertNull(lld1.get(-1));
        assertNull(lld1.get(6));
        assertEquals("c", lld1.get(0));
        assertEquals("b", lld1.get(1));
        assertEquals("a", lld1.get(2));
        assertEquals("d", lld1.get(3));
        assertEquals("e", lld1.get(4));
        assertEquals("f", lld1.get(5));

        LinkedListDeque<Integer> lld2 = new LinkedListDeque<>();
        int cnt = 10;
        int totalOp = 1000;
        int ops = 4;
        int randomOp;
        int index;
        // Randomized test get and getRecursive
        for (int k = 0; k < cnt; k++) {
            for (int i = 0; i < totalOp; i++) {
                randomOp = StdRandom.uniform(ops);
                if (randomOp == 0) {
                  lld2.addFirst(StdRandom.uniform(totalOp));
                } else if (randomOp == 1) {
                    lld2.addLast(StdRandom.uniform(totalOp));
                } else if (randomOp == 2) {
                    lld2.removeFirst();
                } else if (randomOp == 3) {
                    lld2.removeLast();
                }
                index = StdRandom.uniform(lld2.size()+1);
                assertEquals(lld2.getRecursive(index), lld2.get(index));
            }
        }
    }

    // test equals
    @Test
    public void equalsTest() {
        LinkedListDeque<String> lld1 = new LinkedListDeque<>();
        LinkedListDeque<String> lld2 = new LinkedListDeque<>();
        int[] l = new int[5];
        assertTrue(lld1.equals(lld2));
        assertFalse(lld1.equals(l));

        lld1.addLast("1");
        assertFalse(lld1.equals(lld2));
        lld2.addLast("1");
        assertTrue(lld1.equals(lld2));
        lld1.addLast("2");
        assertFalse(lld2.equals(lld1));
        lld2.addLast("2");
        assertTrue(lld1.equals(lld2));
    }

    @Test
    public void iteratorTest() {
        LinkedListDeque<String> lld = new LinkedListDeque<>();
        lld.addFirst("world");
        lld.addFirst("Hello");
        lld.addFirst("!");
        lld.printDeque();
        for (String t : lld) {
            System.out.println(t);
        }
    }
}
