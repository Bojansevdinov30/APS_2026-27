package GeeksForGeeks;
/*Given an array arr[] of non-negative integers and a value sum, the task is to check if there is a subset of the given array whose sum is equal to the given sum.

Examples:

Input: arr[] = [3, 34, 4, 12, 5, 2], sum = 9
Output: True
Explanation: There is a subset (4, 5) with sum 9.

Input: arr[] = [3, 34, 4, 12, 5, 2], sum = 30
Output: False
Explanation: There is no subset that add up to 30.*/
public class SubsetSum {
    public static void main(String[] args) {
        int arr[] = {3, 34, 4, 12, 5, 2};
        int sum = 38;
        int n = arr.length;
        boolean[][] dp = new boolean[n+1][sum+1];
        dp[0][0] = true;
        for (int i = 1; i <= n; i++) {
            dp[i][0] = true;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= sum; j++) {
                dp[i][j] = dp[i-1][j];
                if(j >= arr[i-1]){
                    dp[i][j] = dp[i][j] || dp[i-1][j-arr[i-1]];
                }
            }
        }
        System.out.println(dp[n][sum]);
    }
}
