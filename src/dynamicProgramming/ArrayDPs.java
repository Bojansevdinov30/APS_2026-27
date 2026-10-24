package dynamicProgramming;

import java.util.Arrays;


import java.util.*;

public class ArrayDPs {
    // ============================================================
    // 1. LONGEST INCREASING SUBSEQUENCE
    // ============================================================
    // Returns the length of the longest strictly increasing subsequence.
    //
    // Example:
    // [10, 9, 2, 5, 3, 7, 101, 18]
    // -> 4
    //
    // Time: O(n^2)
    // Space: O(n)

    public static int longestIncreasingSubsequence(int[] a) {
        int n = a.length;

        if (n == 0) return 0;

        int[] dp = new int[n];
        Arrays.fill(dp, 1);

        int answer = 1;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {

                if (a[j] < a[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }

            answer = Math.max(answer, dp[i]);
        }

        return answer;
    }


    // ============================================================
    // 2. LONGEST INCREASING SUBSEQUENCE - PRINT IT
    // ============================================================

    public static List<Integer> getLongestIncreasingSubsequence(int[] a) {
        int n = a.length;

        if (n == 0) return new ArrayList<>();

        int[] dp = new int[n];
        int[] parent = new int[n];

        Arrays.fill(dp, 1);
        Arrays.fill(parent, -1);

        int bestIndex = 0;

        for (int i = 0; i < n; i++) {

            for (int j = 0; j < i; j++) {

                if (a[j] < a[i] &&
                        dp[j] + 1 > dp[i]) {

                    dp[i] = dp[j] + 1;
                    parent[i] = j;
                }
            }

            if (dp[i] > dp[bestIndex]) {
                bestIndex = i;
            }
        }

        return reconstruct(a, parent, bestIndex);
    }


    // ============================================================
    // 3. MAXIMUM SUM INCREASING SUBSEQUENCE
    // ============================================================
    //
    // dp[i] = maximum sum of an increasing subsequence
    //         ending at i.
    //
    // Example:
    // [1, 101, 2, 3, 100, 4, 5]
    // -> 106
    //
    // [1, 2, 3, 100]
    //
    // Time: O(n^2)
    // Space: O(n)

    public static int maximumSumIncreasingSubsequence(int[] a) {
        int n = a.length;

        if (n == 0) return 0;

        int[] dp = new int[n];

        for (int i = 0; i < n; i++) {
            dp[i] = a[i];

            for (int j = 0; j < i; j++) {

                if (a[j] < a[i]) {
                    dp[i] = Math.max(
                            dp[i],
                            dp[j] + a[i]
                    );
                }
            }
        }

        int answer = dp[0];

        for (int value : dp) {
            answer = Math.max(answer, value);
        }

        return answer;
    }


    // ============================================================
    // 4. MAXIMUM SUM INCREASING SUBSEQUENCE - PRINT IT
    // ============================================================

    public static List<Integer> getMaximumSumIncreasingSubsequence(int[] a) {
        int n = a.length;

        if (n == 0) return new ArrayList<>();

        int[] dp = new int[n];
        int[] parent = new int[n];

        Arrays.fill(parent, -1);

        int bestIndex = 0;

        for (int i = 0; i < n; i++) {

            dp[i] = a[i];

            for (int j = 0; j < i; j++) {

                if (a[j] < a[i] &&
                        dp[j] + a[i] > dp[i]) {

                    dp[i] = dp[j] + a[i];
                    parent[i] = j;
                }
            }

            if (dp[i] > dp[bestIndex]) {
                bestIndex = i;
            }
        }

        return reconstruct(a, parent, bestIndex);
    }


    // ============================================================
    // 5. MINIMUM SUM INCREASING SUBSEQUENCE
    // ============================================================
    //
    // IMPORTANT:
    // This means:
    //
    // "Find the increasing subsequence with the smallest sum."
    //
    // If single elements are allowed, the answer is simply
    // the smallest element!
    //
    // Therefore, this problem is only interesting if we add
    // a condition such as "length must be at least k".
    //
    // We provide that useful version below.


    // ============================================================
    // 6. MINIMUM SUM INCREASING SUBSEQUENCE OF LENGTH K
    // ============================================================
    //
    // Find an increasing subsequence of exactly k elements
    // having minimum possible sum.
    //
    // dp[i][len] =
    // minimum sum of an increasing subsequence of length len
    // ending at i.
    //
    // Time: O(n^2 * k)
    // Space: O(n * k)

    public static int minimumSumIncreasingSubsequence(
            int[] a, int k) {

        int n = a.length;

        if (k <= 0) return 0;
        if (k > n) return -1;

        int INF = Integer.MAX_VALUE / 2;

        int[][] dp = new int[n][k + 1];

        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], INF);

            dp[i][1] = a[i];

            for (int j = 0; j < i; j++) {

                if (a[j] < a[i]) {

                    for (int len = 2; len <= k; len++) {

                        if (dp[j][len - 1] != INF) {

                            dp[i][len] = Math.min(
                                    dp[i][len],
                                    dp[j][len - 1] + a[i]
                            );
                        }
                    }
                }
            }
        }

        int answer = INF;

        for (int i = 0; i < n; i++) {
            answer = Math.min(answer, dp[i][k]);
        }

        return answer == INF ? -1 : answer;
    }


    // ============================================================
    // 7. MAXIMUM PRODUCT INCREASING SUBSEQUENCE
    // ============================================================
    //
    // dp[i] = maximum product of an increasing subsequence
    //         ending at i.
    //
    // WARNING:
    // Products are trickier than sums because negative numbers
    // can change the situation.
    //
    // This version assumes POSITIVE numbers.
    //
    // Example:
    // [1, 2, 3, 4]
    // -> 24

    public static long maximumProductIncreasingSubsequence(
            int[] a) {

        int n = a.length;

        if (n == 0) return 0;

        long[] dp = new long[n];

        long answer = Long.MIN_VALUE;

        for (int i = 0; i < n; i++) {

            dp[i] = a[i];

            for (int j = 0; j < i; j++) {

                if (a[j] < a[i]) {

                    dp[i] = Math.max(
                            dp[i],
                            dp[j] * a[i]
                    );
                }
            }

            answer = Math.max(answer, dp[i]);
        }

        return answer;
    }


    // ============================================================
    // 8. MAXIMUM PRODUCT INCREASING SUBSEQUENCE - PRINT IT
    // ============================================================

    public static List<Integer> getMaximumProductIncreasingSubsequence(
            int[] a) {

        int n = a.length;

        if (n == 0) return new ArrayList<>();

        long[] dp = new long[n];
        int[] parent = new int[n];

        Arrays.fill(parent, -1);

        int bestIndex = 0;

        for (int i = 0; i < n; i++) {

            dp[i] = a[i];

            for (int j = 0; j < i; j++) {

                if (a[j] < a[i] &&
                        dp[j] * a[i] > dp[i]) {

                    dp[i] = dp[j] * a[i];
                    parent[i] = j;
                }
            }

            if (dp[i] > dp[bestIndex]) {
                bestIndex = i;
            }
        }

        return reconstruct(a, parent, bestIndex);
    }


    // ============================================================
    // 9. MAXIMUM SUM SUBARRAY
    // ============================================================
    //
    // This is NOT a subsequence problem.
    //
    // Elements must be CONTIGUOUS.
    //
    // Kadane's algorithm is essentially 1D DP.
    //
    // Example:
    // [-2, 1, -3, 4, -1, 2, 1, -5, 4]
    // -> 6
    //
    // [4, -1, 2, 1]
    //
    // Time: O(n)
    // Space: O(1)

    public static int maximumSubarraySum(int[] a) {

        if (a.length == 0) return 0;

        int current = a[0];
        int answer = a[0];

        for (int i = 1; i < a.length; i++) {

            current = Math.max(
                    a[i],
                    current + a[i]
            );

            answer = Math.max(answer, current);
        }

        return answer;
    }


    // ============================================================
    // 10. MAXIMUM SUBARRAY - PRINT IT
    // ============================================================

    public static List<Integer> getMaximumSubarray(int[] a) {

        List<Integer> result = new ArrayList<>();

        if (a.length == 0) return result;

        int current = a[0];
        int answer = a[0];

        int start = 0;
        int bestStart = 0;
        int bestEnd = 0;

        for (int i = 1; i < a.length; i++) {

            if (a[i] > current + a[i]) {
                current = a[i];
                start = i;
            } else {
                current += a[i];
            }

            if (current > answer) {
                answer = current;
                bestStart = start;
                bestEnd = i;
            }
        }

        for (int i = bestStart; i <= bestEnd; i++) {
            result.add(a[i]);
        }

        return result;
    }


    // ============================================================
    // 11. MINIMUM SUBARRAY SUM
    // ============================================================
    //
    // Same idea as Kadane, but use Math.min.
    //
    // Example:
    // [3, -4, 2, -3, -1, 7, -5]
    // -> -6
    //
    // [-4, 2, -3, -1]

    public static int minimumSubarraySum(int[] a) {

        if (a.length == 0) return 0;

        int current = a[0];
        int answer = a[0];

        for (int i = 1; i < a.length; i++) {

            current = Math.min(
                    a[i],
                    current + a[i]
            );

            answer = Math.min(answer, current);
        }

        return answer;
    }


    // ============================================================
    // 12. MAXIMUM SUM OF NON-ADJACENT ELEMENTS
    // ============================================================
    //
    // You cannot choose neighboring elements.
    //
    // Example:
    // [2, 7, 9, 3, 1]
    //
    // Choose:
    // 2 + 9 + 1 = 12
    //
    // Time: O(n)
    // Space: O(n)

    public static int maximumNonAdjacentSum(int[] a) {

        int n = a.length;

        if (n == 0) return 0;
        if (n == 1) return a[0];

        int[] dp = new int[n];

        dp[0] = a[0];
        dp[1] = Math.max(a[0], a[1]);

        for (int i = 2; i < n; i++) {

            dp[i] = Math.max(
                    dp[i - 1],
                    dp[i - 2] + a[i]
            );
        }

        return dp[n - 1];
    }


    // ============================================================
    // 13. MAXIMUM SUM OF NON-ADJACENT ELEMENTS - PRINT
    // ============================================================

    public static List<Integer> getMaximumNonAdjacentElements(
            int[] a) {

        List<Integer> result = new ArrayList<>();

        int n = a.length;

        if (n == 0) return result;

        if (n == 1) {
            result.add(a[0]);
            return result;
        }

        int[] dp = new int[n];

        dp[0] = a[0];
        dp[1] = Math.max(a[0], a[1]);

        for (int i = 2; i < n; i++) {

            dp[i] = Math.max(
                    dp[i - 1],
                    dp[i - 2] + a[i]
            );
        }

        int i = n - 1;

        while (i >= 0) {

            if (i == 0) {
                result.add(a[0]);
                break;
            }

            if (dp[i] == dp[i - 1]) {
                i--;
            } else {
                result.add(a[i]);
                i -= 2;
            }
        }

        Collections.reverse(result);

        return result;
    }


    // ============================================================
    // 14. MINIMUM COST PATH THROUGH AN ARRAY
    // ============================================================
    //
    // You can move either:
    // i -> i + 1
    // or
    // i -> i + 2
    //
    // Find minimum cost to reach the end.
    //
    // Example:
    // [10, 15, 20]
    //
    // min = 25 (10 + 15)
    //
    // Time: O(n)
    // Space: O(n)

    public static int minimumCostToEnd(int[] cost) {

        int n = cost.length;

        if (n == 0) return 0;
        if (n == 1) return cost[0];

        int[] dp = new int[n];

        dp[0] = cost[0];

        dp[1] = cost[1];

        for (int i = 2; i < n; i++) {

            dp[i] = cost[i] +
                    Math.min(dp[i - 1], dp[i - 2]);
        }

        return dp[n - 1];
    }


    // ============================================================
    // 15. COUNT INCREASING SUBSEQUENCES
    // ============================================================
    //
    // Count ALL increasing subsequences.
    //
    // Every element starts a subsequence of length 1.
    //
    // dp[i] = number of increasing subsequences ending at i.
    //
    // Example:
    // [1, 2, 3]
    //
    // [1]
    // [2]
    // [3]
    // [1,2]
    // [1,3]
    // [2,3]
    // [1,2,3]
    //
    // Answer = 7
    //
    // Time: O(n^2)

    public static long countIncreasingSubsequences(int[] a) {

        int n = a.length;

        if (n == 0) return 0;

        long[] dp = new long[n];

        Arrays.fill(dp, 1);

        long answer = 0;

        for (int i = 0; i < n; i++) {

            for (int j = 0; j < i; j++) {

                if (a[j] < a[i]) {
                    dp[i] += dp[j];
                }
            }

            answer += dp[i];
        }

        return answer;
    }


    // ============================================================
    // 16. COUNT DECREASING SUBSEQUENCES
    // ============================================================

    public static long countDecreasingSubsequences(int[] a) {

        int n = a.length;

        if (n == 0) return 0;

        long[] dp = new long[n];

        Arrays.fill(dp, 1);

        long answer = 0;

        for (int i = 0; i < n; i++) {

            for (int j = 0; j < i; j++) {

                if (a[j] > a[i]) {
                    dp[i] += dp[j];
                }
            }

            answer += dp[i];
        }

        return answer;
    }


    // ============================================================
    // 17. LONGEST DECREASING SUBSEQUENCE
    // ============================================================

    public static int longestDecreasingSubsequence(int[] a) {

        int n = a.length;

        if (n == 0) return 0;

        int[] dp = new int[n];

        Arrays.fill(dp, 1);

        int answer = 1;

        for (int i = 0; i < n; i++) {

            for (int j = 0; j < i; j++) {

                if (a[j] > a[i]) {
                    dp[i] = Math.max(
                            dp[i],
                            dp[j] + 1
                    );
                }
            }

            answer = Math.max(answer, dp[i]);
        }

        return answer;
    }


    // ============================================================
    // 18. LONGEST BITONIC SUBSEQUENCE
    // ============================================================
    //
    // First increasing, then decreasing.
    //
    // Example:
    //
    //       /\
    //      /  \
    //     /    \
    //
    // Compute:
    //
    // LIS[i] = increasing length ending at i
    //
    // LDS[i] = decreasing length starting at i
    //
    // Answer:
    //
    // LIS[i] + LDS[i] - 1
    //
    // Time: O(n^2)

    public static int longestBitonicSubsequence(int[] a) {

        int n = a.length;

        if (n == 0) return 0;

        int[] inc = new int[n];
        int[] dec = new int[n];

        Arrays.fill(inc, 1);
        Arrays.fill(dec, 1);

        // Increasing part
        for (int i = 0; i < n; i++) {

            for (int j = 0; j < i; j++) {

                if (a[j] < a[i]) {
                    inc[i] = Math.max(
                            inc[i],
                            inc[j] + 1
                    );
                }
            }
        }

        // Decreasing part
        for (int i = n - 1; i >= 0; i--) {

            for (int j = n - 1; j > i; j--) {

                if (a[j] < a[i]) {
                    dec[i] = Math.max(
                            dec[i],
                            dec[j] + 1
                    );
                }
            }
        }

        int answer = 1;

        for (int i = 0; i < n; i++) {

            answer = Math.max(
                    answer,
                    inc[i] + dec[i] - 1
            );
        }

        return answer;
    }


    // ============================================================
    // HELPER: RECONSTRUCT A SUBSEQUENCE
    // ============================================================

    private static List<Integer> reconstruct(
            int[] a,
            int[] parent,
            int index) {

        List<Integer> result = new ArrayList<>();

        while (index != -1) {

            result.add(a[index]);

            index = parent[index];
        }

        Collections.reverse(result);

        return result;
    }

    public static void main(String[] args) {
        int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};

        System.out.println(longestIncreasingSubsequence(nums)); // [2, 3, 7, 101]
    }
}
