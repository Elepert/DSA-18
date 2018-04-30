import java.util.Arrays;

import static java.lang.Integer.max;
import static java.lang.Integer.min;
import static java.lang.Math.abs;

public class DungeonGame {

    /*
    O(n*m)
     */
    public static int minInitialHealth(int[][] map) {
        int n = map.length;
        int m = map[0].length;
        int[][] dp = new int[n][m];

        int init = 0;
        if (map[m-1][n-1] > 0){
            init = 1;
        } else {
            init = abs(map[m-1][n-1]) +1;
        }
        dp[n-1][m-1] = init;

        for (int i=n-1;i>=0;i--){

            for (int j=m-1;j>=0;j--){

                if (j<m-1 && i==n-1) {
                    dp[i][j] = max(dp[i][j+1] -map[i][j],1);
                } else if (i<n-1 && j==m-1){
                    dp[i][j] = max(dp[i+1][j] -map[i][j],1);
                } else if (i<n-1 && j<m-1){
                    int min_points_on_exit = min(dp[i+1][j], dp[i][j+1]);
                    dp[i][j] = max(min_points_on_exit - map[i][j], 1);

                }
            }
        }

        return dp[0][0];
    }
}
