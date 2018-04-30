import java.util.Arrays;
import java.util.Collections;

public class EditDistance {

    public static int minEditDist(String a, String b) {
        int[][] dp = new int[a.length()+1][b.length()+1];
        recurseDist(a, b, dp, a.length(), b.length());
        return dp[a.length()][b.length()];
    }

    /*
    O(n*m)
     */
    public static int recurseDist(String a, String b, int[][] dp, int n, int m){
        if (n ==0){
            dp[n][m] = m;
        } else if (m==0){
            dp[n][m] = n;
        } else if (dp[n][m] == 0){
            if (a.charAt(n-1) == b.charAt(m-1)){
                dp[n][m] = recurseDist(a, b, dp, n-1, m-1);
            } else {
                Integer[] num = {recurseDist(a, b, dp, n - 1, m), recurseDist(a, b, dp, n - 1, m - 1), recurseDist(a, b, dp, n, m - 1)};
                dp[n][m] = Collections.min(Arrays.asList(num)) + 1;
            }
        }
        return dp[n][m];
    }

}
