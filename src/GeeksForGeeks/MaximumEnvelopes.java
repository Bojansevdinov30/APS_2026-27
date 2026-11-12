package GeeksForGeeks;

import java.util.Arrays;
/*Given N envelopes, as {W, H} pair, where W as the width and H as the height. One envelope can fit into another if and only if both the width and height of one envelope is smaller than the width and height of the other envelope. Find the maximum number of envelopes that can be put inside another envelope and so on. Rotation of envelope is not allowed.

Click to enlarge

Examples:

Input: envelope[] = {{4, 3}, {5, 3}, {5, 6}, {1, 2}}
Output: 3
Explanation: The maximum number of envelopes that can be put into another envelope
is 3. ({1, 2}, {4, 3}, {5, 6})

Input: envelope[] = {{3, 6}, {5, 4}, {4, 8}, {6, 9}, {10, 7}, {12, 12}}
Output: 4
Explanation:The maximum number of envelopes that can be put into another envelope is 4. ({3, 6}, {4, 8}, {6, 9}, {12, 12})*/
public class MaximumEnvelopes {
    static int maxenvelopes(int[][] envelopes)
    {
        // Number of envelopes
        int n = envelopes.length;

        if (n == 0)
            return 0;

        // Sort the envelopes in
        // non-decreasing order
        Arrays.sort(envelopes, (a, b) -> {
            if (a[0] != b[0])
                return a[0] - b[0];
            return a[1] - b[1];
        });

        // Initialize dp[] array
        int[] dp = new int[n];
        Arrays.fill(dp, 1);

        // To store the result
        int max_envelope = 1;

        // Loop through the array
        for (int i = 1; i < n; ++i) {
            // Find envelopes count for
            // each envelope
            for (int j = 0; j < i; ++j) {
                if (envelopes[i][0] > envelopes[j][0]
                        && envelopes[i][1] > envelopes[j][1]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            max_envelope = Math.max(max_envelope, dp[i]);
        }

        return max_envelope;
    }
//Driver Code Starts

    public static void main(String[] args)
    {
        // Given the envelopes
        int[][] envelopes
                = { { 4, 3 }, { 5, 3 }, { 5, 6 }, { 1, 2 } };

        // Function call
        System.out.println(maxenvelopes(envelopes));
    }
}
