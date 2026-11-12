package GeeksForGeeks;
/**/
public class MinimumSuperSequence {
    static int minSuperSeq(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        int[][] dp = new int[m + 1][n + 1];

        // If s2 is empty, take all of s1
        for (int i = 0; i <= m; i++)
            dp[i][0] = i;

        // If s1 is empty, take all of s2
        for (int j = 0; j <= n; j++)
            dp[0][j] = j;

        // Fill the dp table iteratively
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {

                // If last chars match, include once
                if (s1.charAt(i - 1) == s2.charAt(j - 1))
                    dp[i][j] = 1 + dp[i - 1][j - 1];

                    // If not match, take min of both options
                else
                    dp[i][j] = 1 + Math.min(dp[i - 1][j], dp[i][j - 1]);
            }
        }

        return dp[m][n];
    }

    //Driver Code Starts
    public static void main(String[] args) {
        String s1 = "AGGTAB";
        String s2 = "GXTXAYB";

        int res = minSuperSeq(s1, s2);

        System.out.println(res);
    }
}
