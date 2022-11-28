package deque;
import edu.princeton.cs.algs4.Stopwatch;
import org.junit.Test;

/**
 * Timing test
 * add and remove operation of LinkedListDeque.java must take "conStant time",
 * i.e. execution time should not depend on the size of the deque.
 * get operation of LinkedListDeque.java should take time proportional to
 * the number of items
 * reference: CS61B prof hug
 */
public class LinkedListDequeTimingTest {
    private static void printTimingTable(int[] nS, double[] times, int[] opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < nS.length; i += 1) {
            int N = nS[i];
            double time = times[i];
            int opCount = opCounts[i];
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }



    @Test
    public void timeLinkedListDequeAddFirst() {
        System.out.println("---------Timing LinkedListDeque.java addFirst method-------");
        int[] nS = {1000, 2000, 4000, 8000, 16000, 32000, 64000, 128000, 1000000};
        int[] opCounts = new int[nS.length];
        double[] times = new double[nS.length];

        for (int i = 0; i < nS.length; i++) {
            opCounts[i] = nS[i];
            LinkedListDeque<String> lld = new LinkedListDeque<>();
            Stopwatch sw = new Stopwatch();
            for (int j = 0; j < nS[i]; j++) {
                lld.addFirst("a");
            }
            times[i] = sw.elapsedTime();
        }
        printTimingTable(nS, times, opCounts);
    }

    @Test
    public void timeLinkedListDequeAddLast() {
        System.out.println("---------Timing LinkedListDeque.java addLast method-------");
        int[] nS = {1000, 2000, 4000, 8000, 16000, 32000, 64000, 128000, 1000000};
        int[] opCounts = new int[nS.length];
        double[] times = new double[nS.length];

        for (int i = 0; i < nS.length; i++) {
            opCounts[i] = nS[i];
            LinkedListDeque<String> lld = new LinkedListDeque<>();
            Stopwatch sw = new Stopwatch();
            for (int j = 0; j < nS[i]; j++) {
                lld.addLast("a");
            }
            times[i] = sw.elapsedTime();
        }
        printTimingTable(nS, times, opCounts);
    }


    @Test
    public void timeLinkedListDequeRemoveFirst() {
        System.out.println("---------Timing LinkedListDeque.java removeFirst method-------");
        int[] nS = {1000, 2000, 4000, 8000, 16000, 32000, 64000, 128000, 1000000};
        int[] opCounts = new int[nS.length];
        double[] times = new double[nS.length];

        for (int i = 0; i < nS.length; i++) {
            // ConStruction of varied size Deque
            LinkedListDeque<String> lld = new LinkedListDeque<>();
            for (int j = 0; j < nS[i]; j++) {
                lld.addLast("a");
            }

            opCounts[i] = nS[i];
            Stopwatch sw = new Stopwatch();
            for (int j = 0; j < opCounts[i]; j++) {
                lld.removeFirst();
            }
            times[i] = sw.elapsedTime();
        }
        printTimingTable(nS, times, opCounts);
    }


    @Test
    public  void timeLinkedListDequeRemoveLast() {
        System.out.println("---------Timing LinkedListDeque.java removeLast method-------");
        int[] nS = {1000, 2000, 4000, 8000, 16000, 32000, 64000, 128000, 1000000};
        int[] opCounts = new int[nS.length];
        double[] times = new double[nS.length];

        for (int i = 0; i < nS.length; i++) {
            // ConStruction of varied size Deque
            LinkedListDeque<String> lld = new LinkedListDeque<>();
            for (int j = 0; j < nS[i]; j++) {
                lld.addLast("a");
            }

            opCounts[i] = nS[i];
            Stopwatch sw = new Stopwatch();
            for (int j = 0; j < opCounts[i]; j++) {
                lld.removeLast();
            }
            times[i] = sw.elapsedTime();
        }
        printTimingTable(nS, times, opCounts);
    }

    @Test
    public  void timeLinkedListDequeGet() {
        System.out.println("---------Timing LinkedListDeque.java get method-------");
        int[] nS = {1000, 2000, 4000, 8000, 16000, 32000, 64000, 128000, 1000000};
        int[] opCounts = new int[nS.length];
        double[] times = new double[nS.length];

        for (int i = 0; i < nS.length; i++) {
            // ConStruction of varied size Deque
            LinkedListDeque<String> lld = new LinkedListDeque<>();
            for (int j = 0; j < nS[i]; j++) {
                lld.addLast("a");
            }

            opCounts[i] = 1000;
            Stopwatch sw = new Stopwatch();
            for (int j = 0; j < opCounts[i]; j++) {
                lld.get(nS[i]/2);
                // lld.get(1000);
            }
            times[i] = sw.elapsedTime();
        }
        printTimingTable(nS, times, opCounts);
    }

    @Test
    public void timeLinkedListDequeGetRecursive() {
        System.out.println("---------Timing LinkedListDeque.java getRecursive method-------");
        int[] nS = {1000, 2000, 4000, 8000, 16000, 32000, 64000, 128000, 1000000};
        int[] opCounts = new int[nS.length];
        double[] times = new double[nS.length];

        for (int i = 0; i < nS.length; i++) {
            // ConStruction of varied size Deque
            LinkedListDeque<String> lld = new LinkedListDeque<>();
            for (int j = 0; j < nS[i]; j++) {
                lld.addLast("a");
            }

            opCounts[i] = 1000;
            Stopwatch sw = new Stopwatch();
            for (int j = 0; j < opCounts[i]; j++) {
                // lld.getRecursive(nS[i]/2); StackOverflowError
                lld.getRecursive(1000);
            }
            times[i] = sw.elapsedTime();
        }
        printTimingTable(nS, times, opCounts);
    }

}
