
public class InsertionSort extends SortAlgorithm {
    /**
     * Use the insertion sort algorithm to sort the array
     *
     * Best-case runtime: O(n) - if array is already sorted
     * Worst-case runtime: O(n^2)
     * Average-case runtime: O(n^2)
     *
     * Space-complexity: O(1) - sorting in place
     */
    @Override
    public int[] sort(int[] array) {
        for (int i = 0; i<array.length; i++){
            int p = i;
            while ((p > 0) && array[p]<array[p-1]){
                int temp = array[p-1];
                array[p-1] = array[p];
                array[p] = temp;
                p--;
            }
        }
        return array;
    }
}
