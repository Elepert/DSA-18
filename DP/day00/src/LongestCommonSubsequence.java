//import static sun.swing.MenuItemLayoutHelper.max;

import static java.lang.Integer.max;

public class LongestCommonSubsequence {

    // Runtime: O(mn)
    // Space: m+n
    public static int LCS(String S1, String S2) {
        int n = S1.length();
        int m = S2.length();
        int[][] memo = new int[n][m];
        return recurseLCS(S1, S2, memo);
    }



    public static int recurseLCS(String S1, String S2, int[][] memo){
        int n = S1.length()-1;
        int m = S2.length()-1;

        if (n<0 || m<0){
            return 0;
        }
        if (memo[n][m] != 0){
            return memo[n][m];
        }

        if (S1.charAt(n) != S2.charAt(m)){
            int res1 = 0;
            int res2 = 0;
            if (n!=0) {

                res1 = recurseLCS(S1.substring(0, n), S2, memo);
                memo[n - 1][m] = res1;
            }
            if (m!=0) {

                res2 = recurseLCS(S1, S2.substring(0, m), memo);
                memo[n][m - 1] = res2;
            }

            return max(res2, res1);
        } else {
            memo[n][m] = recurseLCS(S1.substring(0,n), S2.substring(0,m), memo)+1;
            return memo[n][m];
        }
    }
}