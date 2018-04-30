import java.util.Arrays;

public class BalloonPopping {

    public static void printDP(int[][] dp){
        for (int i = 0; i<dp.length; i++){
            System.out.println(Arrays.toString(dp[i]));
        }
    }

    public static int maxPoints(int[] B) {
        int[] nums = new int[B.length + 2];
        int n = 1;
        for (int x : B) if (x > 0) nums[n++] = x;
        nums[0] = nums[n++] = 1;


        int[][] dp = new int[n][n];
        for (int k = 2; k < n; ++k) {
            for (int left = 0; left < n - k; ++left) {
                int right = left + k;
                for (int i = left + 1; i < right; ++i) {
                    dp[left][right] = Math.max(dp[left][right],
                            nums[left] * nums[i] * nums[right] + dp[left][i] + dp[i][right]);
                    System.out.print("I is: ");
                    System.out.println(i);
                    System.out.print("Left is: ");
                    System.out.println(left);
                    System.out.print("Right is: ");
                    System.out.println(right);
                    printDP(dp);
                    System.out.println("--------------------");
                }

            }
        }

        return dp[0][n - 1];
    }
}
