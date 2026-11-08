package labs.Lab4;

import java.util.*;

public class Lab1_DPVerision {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int k = sc.nextInt();

        int[] t = new int[n + 1];
        int[] w = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            t[i] = sc.nextInt();
            w[i] = sc.nextInt();
        }

        int[][] dp = new int[n + 1][k + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= k; j++) {

                // Skip task i
                dp[i][j] = dp[i - 1][j];

                // Execute task i
                if (t[i] <= j) {
                    dp[i][j] = Math.max(
                            // if it was unbounded knapsack -> dp[i][j]
                            dp[i-1][j],
                            dp[i - 1][j - t[i]] + w[i]
                    );
                }
            }
        }

        System.out.println(dp[n][k]);
    }

}
