import java.util.Arrays;

public class Regex {

    public static boolean isMatch(String s, String p) {
        if (s == null || p == null){
            return false;
        }
        int n = s.length();
        int m = p.length();

        boolean[][] DP = new boolean[n+1][m+1];
        DP[0][0] = true;

        for (int i=1; i<n+1;i++){
            DP[i][0] = false;
        }

        for (int j=1; j<m+1;j++){
            if (p.charAt(j-1)=='*'){
                DP[0][j] = DP[0][j-2];
            } else {
                DP[0][j] = false;
            }
        }

        for (int i = 1; i<=n; i++){
            for (int j = 1; j <= m; j++){
                if (p.charAt(j-1) == '*'){
                    DP[i][j] = DP[i][j-2];
                    if (s.charAt(i-1) == p.charAt(j-2) || p.charAt(j-2) == '.'){
                        DP[i][j] |= DP[i-1][j];
                    }
                } else {
                    DP[i][j] = ((s.charAt(i-1)==p.charAt(j-1) || p.charAt(j-1)=='.') && DP[i-1][j-1]);
                }
            }
        }
        return DP[n][m];
    }

}
