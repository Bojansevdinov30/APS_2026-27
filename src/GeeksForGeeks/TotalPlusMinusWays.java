package GeeksForGeeks;
/*Given an array arr[] and an integer target. We want to build an expression out of arr[] by adding one of the symbols '+' and '-' before each integer in arr[] and then concatenate all the integers. Determine the number of different expressions that can be built, which evaluates to target.

Example:

Input : arr[] = [1, 1, 1, 1, 1], target = 3
Output: 5
Explanation: There are 5 ways for target 3.
-1 + 1 + 1 + 1 + 1 = 3
+1 - 1 + 1 + 1 + 1 = 3
+1 + 1 - 1 + 1 + 1 = 3
+1 + 1 + 1 - 1 + 1 = 3
+1 + 1 + 1 + 1 - 1 = 3

Input: arr[] = [1], target = 1
Output: 1

Input : arr[] = [1, 2, 2, 1], target = 2
Output: 2
Explanation: There are 2 ways for 2
1 -  2  +  2  + 1
1  +  2  -  2  + 1*/
public class TotalPlusMinusWays {
    static int findTotalWays(int[] arr, int target) {

        int n = arr.length;
        int totalSum = 0;

        for (int x : arr) totalSum += x;

        int[][] dp = new int[n + 1][2 * totalSum + 1];

        dp[0][totalSum] = 1;

        for (int i = 1; i <= n; i++) {

            int val = arr[i - 1];

            for (int j = 0; j <= 2 * totalSum; j++) {

                // add current element
                if (j + val <= 2 * totalSum)
                    dp[i][j + val] += dp[i - 1][j];

                // subtract current element
                if (j - val >= 0)
                    dp[i][j - val] += dp[i - 1][j];

            }
        }

        return dp[n][target + totalSum];
    }

    public static void main(String[] args) {

        int[] arr = {1, 1, 1, 1, 1};
        int target = 3;

        System.out.println(findTotalWays(arr, target));
    }
}
