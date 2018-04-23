import java.util.Arrays;

public class LongestIncreasingSubsequence {

    public static int LIS(int[] A) {
        System.out.println(Arrays.toString(A));

        return loopLIS(A, A.length);
    }

    // Runtime: O(N^2)
    // Space: n
    static int loopLIS(int arr[],int n)
    {
        int lis[] = new int[n];
        int i,j,max = 0;

        for ( i = 0; i < n; i++ )
            lis[i] = 1;

        for ( i = 1; i < n; i++ )
            for ( j = 0; j < i; j++ )
                if ( arr[i] > arr[j] && lis[i] < lis[j] + 1)
                    lis[i] = lis[j] + 1;

        for ( i = 0; i < n; i++ )
            if ( max < lis[i] )
                max = lis[i];

        return max;
    }

    static int recurseLIS(int arr[], int n){
        return 0;
    }

}