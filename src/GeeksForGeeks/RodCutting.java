package GeeksForGeeks;
/*Given a rod of length n inches and an array price[], where price[i] denotes the value of a piece of length i (1-based Index). Determine the maximum value obtainable by cutting up the rod and selling the pieces.

Note: The value of n is equal to the size of price array.

Examples:

Input: price[] = [1, 5, 8, 9, 10, 17, 17, 20]
Output: 22
Explanation: The maximum obtainable value is 22 by cutting in two pieces of lengths 2 and 6, i.e., 5 + 17 = 22.

Input: price[] = [3, 5, 8, 9, 10, 17, 17, 20]
Output: 24
Explanation: The maximum obtainable value is 24 by cutting the rod into 8 pieces of length 1, i.e, 8 *price[1] = 8 * 3 = 24.

Input: price[] = [3]
Output: 3
Explanation: There is only 1 way to pick a piece of length 1.*/
public class RodCutting {
    public static int cutRod(int[] price)
    {
        int n = price.length;
        int[] dp = new int[n + 1];

        // Find maximum value for each rod length
        for (int i = 1; i <= n; i++) {

            // Try every possible first cut
            for (int j = 1; j <= i; j++) {
                dp[i] = Math.max(dp[i],
                        price[j - 1] + dp[i - j]);
            }
        }

        return dp[n];
    }

    public static void main(String[] args)
    {
        int[] price = { 1, 5, 8, 9, 10, 17, 17, 20 };
        System.out.println(cutRod(price));
    }
}
