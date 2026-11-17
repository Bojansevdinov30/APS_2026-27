package LeetCode.DP;

public class LongestAlternatingSubarray {

    public static int longestAlternating(int[] arr) {
        int n = arr.length;

        int[] dp = new int[n];

        dp[0] = 1;
        int max = 1;

        for (int i = 1; i < n; i++) {

            if ((arr[i] > 0 && arr[i - 1] < 0) ||
                    (arr[i] < 0 && arr[i - 1] > 0)) {

                dp[i] = dp[i - 1] + 1;

            } else {
                dp[i] = 1;
            }

            max = Math.max(max, dp[i]);
        }

        return max;
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, -2, -5, 4, -6, 7, -8};

        System.out.println(longestAlternating(arr));
    }
}

