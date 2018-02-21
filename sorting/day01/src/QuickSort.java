import java.util.concurrent.ThreadLocalRandom;
import java.util.*;

public class QuickSort extends SortAlgorithm {

    private static final int INSERTION_THRESHOLD = 10;
    private void shuffleArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int randIndex = ThreadLocalRandom.current().nextInt(i+1);
            swap(array, i, randIndex);
        }
    }

    /**
     * TODO
     * Best-case runtime: nlogn
     * Worst-case runtime: nlogn
     * Average-case runtime: nlogn
     *
     * Space-complexity: logn
     */
    @Override
    public int[] sort(int[] array) {
        shuffleArray(array);
        quickSort(array, 0,array.length-1);

        return array;
    }

    /**
     * Partition the array around a pivot, then recursively sort the left and right
     * portions of the array. A test for this method is provided in `SortTest.java`
     * Optional: use Insertion Sort if the length of the array is <= INSERTION_THRESHOLD
     *
     * @param lo The beginning index of the subarray being considered (inclusive)
     * @param hi The ending index of the subarray being considered (inclusive)
     */
    public void quickSort(int[] a, int lo, int hi) {
        if (lo < hi) {
            int p = partition(a, lo, hi);
            quickSort(a, lo, p-1);
            quickSort(a,p+1, hi);
        }
    }


    /**
     * Given an array, choose the array[low] element as the "pivot" element.
     * Place all elements smaller than "pivot" on "pivot"'s left, and all others
     * on its right. Return the final position of "pivot" in the partitioned array.
     *
     * @param lo The beginning index of the subarray being considered (inclusive)
     * @param hi The ending index of the subarray being considered (inclusive)
     */
    public int partition(int[] array, int lo, int hi) {

        int loIndex = lo+1;
        for (int i = lo+1; i <= hi; i++){
            if (array[i] < array[lo]){
                int temp = array[i];
                array[i] = array[loIndex];
                array[loIndex] = temp;

                loIndex++;
            }

        }
        int temp = array[lo];
        array[lo] = array[loIndex-1];

        array[loIndex-1] = temp;

        return loIndex-1;
    }

}
