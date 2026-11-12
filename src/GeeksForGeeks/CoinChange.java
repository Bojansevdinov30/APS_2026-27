package GeeksForGeeks;
/*Given an integer array coins[ ] representing different denominations of currency and an integer sum, find the number of ways you can make sum by using different combinations from coins[ ].

Note: Assume that you have an infinite supply of each type of coin. Therefore, you can use any coin as many times as you want. Answers are guaranteed to fit into a 32-bit integer.

Examples:

Input: coins[] = [1, 2, 3], sum = 4
Output: 4
Explanation: Four Possible ways are: [1, 1, 1, 1], [1, 1, 2], [2, 2], [1, 3].

Input: coins[] = [2, 5, 3, 6], sum = 10
Output: 5
Explanation: Five Possible ways are: [2, 2, 2, 2, 2], [2, 2, 3, 3], [2, 2, 6], [2, 3, 5] and [5, 5].

Input: coins[] = [5, 10], sum = 3
Output: 0
Explanation: Since all coin denominations are greater than sum, no combination can make the target sum.*/
public class CoinChange {
    static int count(int[] coins, int sum)
    {
        int n = coins.length;

        int[][] dp = new int[n + 1][sum + 1];

        dp[0][0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= sum; j++) {

                // Add the number of ways to make change
                // without using the current coin,
                dp[i][j] += dp[i - 1][j];

                if (j - coins[i - 1] >= 0) {

                    // Add the number of ways to make change
                    // using the current coin
                    dp[i][j] += dp[i][j - coins[i - 1]];
                }
            }
        }
        return dp[n][sum];
    }
    public static void main(String[] args)
    {
        int[] coins = { 1, 2, 3 };
        int sum = 5;
        System.out.println(count(coins, sum));
    }
}
