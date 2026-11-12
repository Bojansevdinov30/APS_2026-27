package GeeksForGeeks;

/*Given an array arr[], check if it can be partitioned into two parts such that the sum of elements in both parts is the same.
Note: Each element is present in either the first subset or the second subset, but not in both.

Examples:

Input: arr[] = [1, 5, 11, 5]
Output: true
Explanation: The array can be partitioned as [1, 5, 5] and [11] and the sum of both the subsets are equal.

Input: arr[] = [1, 5, 3]
Output: false
Explanation: The array cannot be partitioned into equal sum sets.*/
public class PartitionSum {
    static boolean equalPartition(int[] arr) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        if(sum%2!=0){
            return false;
        }
        int target = sum / 2;
        boolean[][] dp = new boolean[arr.length + 1][target + 1];

        // tehnicki treba cela prva kolona da e true oti target od 0 sekogas mozes da dobies bez biranje nisto, ama toa
        // implicitno e reseno kaj mene so dp[i][j] = dp[i][j] || dp[i - 1][j];
        dp[0][0] = true;

        for (int i = 1; i <= arr.length; i++) {
            for (int j = 1; j <= target; j++) {
                dp[i][j] = dp[i][j] || dp[i - 1][j];
                if (j >= arr[i - 1]) {
                    dp[i][j] = dp[i][j] || dp[i - 1][j - arr[i - 1]];
                }
            }

        }


        return dp[arr.length][target];
    }

    public static void main(String[] args) {
        int[] arr = {1, 5, 3};
        if (equalPartition(arr)) {
            System.out.println("True");
        } else {
            System.out.println("False");
        }
    }
}
