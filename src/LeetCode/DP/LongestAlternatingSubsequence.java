package LeetCode.DP;

public class LongestAlternatingSubsequence {

    public static int longestAlternating(int[] arr) {
        int n = arr.length;

        int[] positive = new int[n];
        int[] negative = new int[n];

        int max = 0;

        for (int i = 0; i < n; i++) {
            positive[i] = 1;
            negative[i] = 1;

            for (int j = 0; j < i; j++) {

                if (arr[i] > 0 && arr[j] < 0) {
                    positive[i] = Math.max(
                            positive[i],
                            negative[j] + 1
                    );
                }

                if (arr[i] < 0 && arr[j] > 0) {
                    negative[i] = Math.max(
                            negative[i],
                            positive[j] + 1
                    );
                }
            }

            max = Math.max(max,
                    Math.max(positive[i], negative[i]));
        }

        return max;
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, -2, -5, 4, -6, 7, -8};

        System.out.println(longestAlternating(arr));
    }
}
