package GeeksForGeeks;
/*Given a number n, find the total number of Derangements of a set of elements from 1 to n.

A Derangement is a permutation of n elements, such that no element appears in its original position. For example, a derangement of [1, 2, 3] is [2, 3, 1].

Examples:

Input: n = 2
Output: 1
Explanation: For [1, 2], there is only one possible derangement [2, 1].

Input: n = 3
Output: 2
Explanation: For [1, 2, 3], there are two possible derangements [3, 1, 2] and [2, 3, 1].*/
public class Dearrangements {
    static int derangeCount(int n) {

        // Create a DP array to store results
        int[] dp = new int[n + 1];

        // Base cases
        dp[1] = 0;
        dp[2] = 1;

        // Fill the DP array using the recursive relation
        for (int i = 3; i <= n; i++) {
            dp[i] = (i - 1) * (dp[i - 1] + dp[i - 2]);
        }

        return dp[n];
    }

    public static void main(String[] args) {
        int n = 5;
        System.out.println(derangeCount(n));
    }
}
