package GeeksForGeeks;
/*Given an array arr of non-negative integers and an integer target, the task is to count all subsets of the array whose sum is equal to the given target.

Examples:

Input: arr[] = [5, 2, 3, 10, 6, 8], target = 10
Output: 3
Explanation: The subsets {5, 2, 3}, {2, 8}, and {10} sum up to the target 10.

Input: arr[] = [2, 5, 1, 4, 3], target = 10
Output: 3
Explanation: The subsets {2, 1, 4, 3}, {5, 1, 4}, and {2, 5, 3} sum up to the target 10.

Input: arr[] = [5, 7, 8], target = 3
Output: 0
Explanation: There are no subsets of the array that sum up to the target 3.

Input: arr[] = [35, 2, 8, 22], target = 0
Output: 1
Explanation: The empty subset is the only subset with a sum of 0.*/
public class NumberOfSubsetsToSum {
    // Function to count the number of subsets
    // with a sum equal to the target using tabular DP
    static int perfectSum(int[] arr, int target) {
        int n = arr.length;

        // Create a 2D DP table
        int[][] dp = new int[n + 1][target + 1];

        // Base case: There's one way to achieve a
        // sum of 0 (by selecting no elements)
        dp[0][0] = 1;

        // Fill the DP table
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= target; j++) {

                // Exclude the current element
                dp[i][j] = dp[i - 1][j];

                // Include the current element if
                // it doesn't exceed the current sum
                if (j >= arr[i - 1]) {
                    dp[i][j] += dp[i - 1][j - arr[i - 1]];
                }
            }
        }

        // Return the number of ways to achieve the target
        // sum
        return dp[n][target];
    }

    public static void main(String[] args) {
        int[] arr = {35, 2, 8, 22};
        int target = 0;
        System.out.println(perfectSum(arr, target));
    }
}
