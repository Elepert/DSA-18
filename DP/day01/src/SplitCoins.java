import java.util.Arrays;

public class SplitCoins {

    /*
    Runtime: O(n*sum)
    Space: n*sum
     */
    public static int splitCoins(int[] coins) {
        int sum =0;
        for (int s=0; s<coins.length; s++){
            sum+=coins[s];
        }
        int length = coins.length;

        int[][] dp = new int[length+1][sum+1];

        for (int i=0; i<=length; i++){
            for (int j=0; j<=sum; j++){
                if (j==0){
                    dp[i][j] = 1;
                } else if (i==0){
                    dp[i][j] = 0;
                } else {
                    dp[i][j] = dp[i-1][j];

                    if (coins[i-1]<=j && dp[i][j]!=1){
                        dp[i][j] += dp[i-1][j-coins[i-1]];
                    }
                }
            }
            //System.out.println(Arrays.toString(dp[i]));
        }

        int diff = Integer.MAX_VALUE;
        for (int d= sum/2; d>=0; d--){
            if (dp[length][d] > 0){
                diff = sum - 2*d;
                break;
            }
        }
        return diff;
    }
}
