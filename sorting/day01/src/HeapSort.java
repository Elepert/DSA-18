import java.util.*;

public class HeapSort extends SortAlgorithm {
    int size;
    int[] heap;

    private int parent(int i) {
        return (i-1) / 2;
    }

    private int leftChild(int i) {
        return 2*i + 1;
    }

    private int rightChild(int i) {
        return 2 * (i + 1);
    }

    // Check children, and swap with larger child if necessary.
    // Corrects the position of element indexed i by sinking it.
    // Use either recursion or a loop to then sink the child
    public void sink(int i) {
        int leftChild = leftChild(i);
        int rightChild = rightChild(i);
        if (leftChild > heap.length-1 || leftChild < 0){
            leftChild = i;
        }
        if (rightChild > heap.length-1 || rightChild < 0){
            rightChild = i;
        }
        int max;
        if (heap[i] < heap[leftChild] || heap[i] < heap[rightChild]) {

            if (heap[leftChild] > heap[rightChild]){
                max = leftChild;
            } else {
                max = rightChild;
            }
            swap(i, max);
            sink(max);
        }
    }

    public void swap(int i1, int i2){
        int temp = heap[i1];
        heap[i1] = heap[i2];
        heap[i2] = temp;
    }

    // Given the array, build a heap by correcting every non-leaf's position, starting from the bottom, then
    // progressing upward
    public void heapify(int[] array) {
        this.heap = array;
        this.size = array.length;
        for (int i=this.size / 2 - 1; i>=0; i--) {
            sink(i);
        }
    }

    public void sinkSort(int i, int hsize) {
        int leftChild = leftChild(i);
        int rightChild = rightChild(i);
        if (leftChild > hsize-1 || leftChild < 0){
            leftChild = i;
        }
        if (rightChild > hsize-1 || rightChild < 0){
            rightChild = i;
        }
        int max;
        if (heap[i] < heap[leftChild] || heap[i] < heap[rightChild]) {
            if (heap[leftChild] > heap[rightChild]){
                max = leftChild;
            } else {
                max = rightChild;
            }
            swap(i, max);
            sinkSort(max, hsize);
        }
    }

    /**
     * Best-case runtime: nlogn
     * Worst-case runtime: nlogn
     * Average-case runtime: nlogn
     *
     * Space-complexity: logn
     */
    @Override
    public int[] sort(int[] array) {
        heapify(array);
        int hsize = size;
        for (int i=size-1; i>0; i--) {
            hsize--;
            swap(0, hsize);

            sinkSort(0, hsize);

        }
        return heap;
    }
}
