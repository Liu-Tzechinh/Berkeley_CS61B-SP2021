package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
  // YOUR TESTS HERE
  @Test
  public void testThreeAddThreeRemove() {
      AListNoResizing<Integer> correct = new AListNoResizing<>();
      BuggyAList<Integer> broken = new BuggyAList<>();

      correct.addLast(5);
      correct.addLast(10);
      correct.addLast(15);

      broken.addLast(5);
      broken.addLast(10);
      broken.addLast(15);

      assertEquals(correct.size(), broken.size());

      assertEquals(correct.removeLast(), broken.removeLast());
      assertEquals(correct.removeLast(), broken.removeLast());
      assertEquals(correct.removeLast(), broken.removeLast());
  }
  @Test
  public void randomizedTest() {
      AListNoResizing<Integer> correct = new AListNoResizing<>();
      BuggyAList<Integer> broken = new BuggyAList<>();

      int N = 5000;
      for (int i = 0; i < N; i += 1) {
          int operationNumber = StdRandom.uniform(0, 4);
          if (operationNumber == 0) {
              int randVal = StdRandom.uniform(0, 100);
              correct.addLast(randVal);
              broken.addLast(randVal);
              // System.out.println("addLast(" + randVal + ")");
          } else if (operationNumber == 1) {
              int size = correct.size();
              assertEquals(correct.size(), broken.size());
              // System.out.println("size: " + size);
          } else if (operationNumber == 2) {
              assertEquals(correct.size(), broken.size());
              if (correct.size() > 0) {
                  // System.out.println("get last(" + last + ")");
                  assertEquals(correct.getLast(), broken.getLast());
              }
          } else {
              assertEquals(correct.size(), broken.size());
              if (correct.size() > 0) {
                  int last = correct.removeLast();
                  int bl = broken.removeLast();
                  assertEquals(last, bl);
                  // System.out.println("remove last(" + last + ")");
              }
          }
      }
  }
}
