import java.util.Arrays;

public class DiceRollSum {

    // Runtime: TODO
    // Space: TODO
    public static int diceRollSum(int N) {
        System.out.println("Dice is: ");
        System.out.println(N);
        int[] memo = new int[N+1];

        recurseDice(memo, N);
        System.out.println(Arrays.toString(memo));
        return memo[N];
    }

    private static int recurseDice(int[] memo, int N){
        System.out.println(Arrays.toString(memo));
        if (N<=0){
            memo[0] = 1;
            return 0;
        } else if (N==1){
            memo[N] = 1;
            return 1;
        } else if (memo[N] != 0) {
            System.out.println("Memo found:");
            System.out.println(memo[N]);
            return memo[N];
        }
        int res = recurseDice(memo, N-1) + recurseDice(memo, N-2)+ recurseDice(memo, N-3) + recurseDice(memo, N-4) + recurseDice(memo, N-5) + recurseDice(memo, N-6) + 1;
        memo[N] = res;
        return res;

    }


}
