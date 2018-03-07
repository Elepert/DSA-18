import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Collections;

public class Problems {

    /**
     * Use counting sort to sort non-negative integer array A.
     * Runtime: TODO
     *
     * k: maximum element in array A
     */
    static int[] countingSort(int[] A) {
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
        return A;
    }


    public static int leastSum(int[] A) {
        A = countingSort(A);

        int final1 = 0;
        int final2 = 0;

        int temp;

        int caseS = 1;

        int lengthA = A.length;

        for (int i=0; i<A.length; i++){
            temp = A[i];
            if (lengthA % 2 == 1 && caseS == 1) {
                lengthA -= 1;
                final1 = 10*final1 + temp;
                caseS = 1;
            } else if (caseS>0){
                final1 = 10*final1 + temp;
                caseS = -1;
            } else if (caseS<0){
                final2 = 10*final2 + temp;
                caseS = 1;
            }
        }


        return final1+ final2;
    }
}
