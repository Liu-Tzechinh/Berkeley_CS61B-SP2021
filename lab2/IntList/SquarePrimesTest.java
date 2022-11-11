package IntList;

import static org.junit.Assert.*;
import org.junit.Test;

public class SquarePrimesTest {

    /**
     * Here is a test for isPrime method. Try running it.
     * It passes, but the starter code implementation of isPrime
     * is broken. Write your own JUnit Test to try to uncover the bug!
     */
    @Test
    public void testSquarePrimesSimple() {
        IntList lst = IntList.of(14, 15, 16, 17, 18);
        boolean changed = IntListExercises.squarePrimes(lst);
        assertEquals("14 -> 15 -> 16 -> 289 -> 18", lst.toString());
        assertTrue(changed);
    }

    @Test
    public void testSquarePrimes1() {
        IntList lst = IntList.of(1);
        boolean changed = IntListExercises.squarePrimes(lst);
        assertEquals("1", lst.toString());
        assertFalse(changed);
    }


    @Test
    public void testSquarePrimes2() {
        IntList lst = IntList.of(2);
        boolean changed = IntListExercises.squarePrimes(lst);
        assertEquals("4", lst.toString());
        assertTrue(changed);
    }

    @Test
    public void testSquarePrimes3() {
        IntList lst = IntList.of(7, 8, 9, 10);
        boolean changed = IntListExercises.squarePrimes(lst);
        assertEquals("49 -> 8 -> 9 -> 10", lst.toString());
        assertTrue(changed);
    }

    @Test
    public void testSquarePrimes4() {
        IntList lst = IntList.of(8, 9, 10, 11);
        boolean changed = IntListExercises.squarePrimes(lst);
        assertEquals("8 -> 9 -> 10 -> 121", lst.toString());
        assertTrue(changed);
    }

    @Test
    public void testSquarePrimes5() {
        IntList lst = IntList.of(8, 9, 10, 11, 12, 13);
        boolean changed = IntListExercises.squarePrimes(lst);
        assertEquals("8 -> 9 -> 10 -> 121 -> 12 -> 169", lst.toString());
        assertTrue(changed);
    }
}
