import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class CountingSort {

    /**
     * Use counting sort to sort positive integer array A.
     * Runtime: TODO
     *
     * k: maximum element in array A
     */
    static void countingSort(int[] A) {
        int max = 0;
        for (int i = 0; i<A.length;i++){
            if (A[i] > max){
                max = A[i];
            }
        }
        int[] temp = new int[max+1];

        for (int j=0; j<A.length;j++){
            temp[A[j]] += 1;
        }
        int index = 0;
        for (int h=0; h<max+1;h++){
            while (temp[h]!=0){
                temp[h]--;
                A[index] = h;
                index ++;
            }
        }
    }

}
