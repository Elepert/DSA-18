import java.util.*;
public class MergeSort extends SortAlgorithm {

    private static final int INSERTION_THRESHOLD = 10;
    public InsertionSort insertion = new InsertionSort();

    /**
     * This is the recursive step in which you split the array up into
     * a left and a right portion, sort them, and then merge them together.
     * Use Insertion Sort if the length of the array is <= INSERTION_THRESHOLD
     *
     * TODO
     * Best-case runtime: O(n logn)
     * Worst-case runtime: O(n logn)
     * Average-case runtime: O(n logn)
     *
     * Space-complexity: O(n)
     */
    @Override
    public int[] sort(int[] array) {

        if (array.length <= INSERTION_THRESHOLD){
            array = insertion.sort(array);
            return array;
        }

        int middle;
        if (array.length%2 ==0 ) {
            middle = array.length / 2;
        } else {
            middle =(int) ((array.length / 2.0) + 0.5);
        }
        int[] left = Arrays.copyOfRange(array, 0, middle);
        int[] right = Arrays.copyOfRange(array, middle, array.length);

        left = sort(left);
        right = sort(right);

        return merge(left, right);

    }

    /**
     * Given two sorted arrays a and b, return a new sorted array containing
     * all elements in a and b. A test for this method is provided in `SortTest.java`
     */
    public int[] merge(int[] a, int[] b) {

        int aLength = a.length;
        int bLength = b.length;

        int i = 0;
        int j = 0;

        int[] mergedA = new int[aLength+bLength];
        int m = 0;

        while (i<aLength && j<bLength){
            if (a[i] < b[j]){
                mergedA[m] = a[i];
                i++;
            } else {
                mergedA[m] = b[j];
                j++;
            }
            m++;
        }
        while (i<aLength){
            mergedA[m] = a[i];
            i++;
            m++;
        }

        while (j<bLength){
            mergedA[m] = b[j];
            j++;
            m++;
        }

        return mergedA;
    }

}
