import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Arrays;

public class Problems {

    private static PriorityQueue<Integer> minPQ() {
        return new PriorityQueue<>(11);
    }

    private static PriorityQueue<Integer> maxPQ() {
        return new PriorityQueue<>(11, Collections.reverseOrder());
    }

    private static double getMedian(List<Integer> A) {
        double median = (double) A.get(A.size() / 2);
        if (A.size() % 2 == 0)
            median = (median + A.get(A.size() / 2 - 1)) / 2.0;
        return median;
    }

    // Runtime of this algorithm is O(N^2). Sad! We provide it here for testing purposes
    public static double[] runningMedianReallySlow(int[] A) {
        double[] out = new double[A.length];
        List<Integer> seen = new ArrayList<>();
        for (int i = 0; i < A.length; i++) {
            int j = 0;
            while (j < seen.size() && seen.get(j) < A[i])
                j++;
            seen.add(j, A[i]);
            out[i] = getMedian(seen);
        }
        return out;
    }


    /**
     *
     * @param inputStream an input stream of integers
     * @return the median of the stream, after each element has been added
     */
    public static double[] runningMedian(int[] inputStream) {
        double[] runningMedian = new double[inputStream.length];
        if (inputStream.length==0){
            return runningMedian;
        }

        PriorityQueue<Integer> afterMedian = minPQ();
        PriorityQueue<Integer> beforeMedian = maxPQ();

        double median =(double) inputStream[0];
        double beforeM = inputStream[0];
        double afterM = 0;
        beforeMedian.offer(inputStream[0]);
        runningMedian[0] = inputStream[0];

        int counter=1;
        for (int i = 1; i<inputStream.length;i++){

            if (inputStream[i] > median){
                afterMedian.offer(inputStream[i]);

            } else if (inputStream[i] <= median) {
                beforeMedian.offer(inputStream[i]);

            }

            counter++;
            int difference = afterMedian.size() - beforeMedian.size();
            while (difference != 0 && difference != -1) {

                if (difference > 0) {
                    beforeMedian.offer(afterMedian.poll());
                } else if (difference < -1) {
                    afterMedian.offer(beforeMedian.poll());
                }
                difference = afterMedian.size() - beforeMedian.size();
            }

            if (counter%2 == 0){

                afterM = afterMedian.peek();
                beforeM = beforeMedian.peek();

                median = (afterM+beforeM)/2;
            } else {
                median = beforeMedian.peek();
            }

            runningMedian[counter-1] = median;
        }
        return runningMedian;
    }

}
