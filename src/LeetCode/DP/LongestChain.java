package LeetCode.DP;

import java.util.Arrays;
/*You are given an array of n pairs pairs where pairs[i] = [lefti, righti] and lefti < righti.

A pair p2 = [c, d] follows a pair p1 = [a, b] if b < c. A chain of pairs can be formed in this fashion.

Return the length longest chain which can be formed.

You do not need to use up all the given intervals. You can select pairs in any order.



Example 1:

Input:
int[][] pairs = new int[][]{{1, 2}, {2, 3}, {3, 4}};

Output: 2
Explanation: The longest chain is [1,2] -> [3,4].
Example 2:

Input:
int[][] pairs = new int[][]{{1, 2}, {7, 8}, {4, 5}};

Output: 3
Explanation: The longest chain is [1,2] -> [4,5] -> [7,8].


Constraints:

n == pairs.length
1 <= n <= 1000
-1000 <= lefti < righti <= 1000*/
public class LongestChain {
    public static int findLongestChain(int[][] pairs) {
        int n = pairs.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);

        Arrays.sort(pairs, (a, b) -> {
            return a[1] - b[1];
        });
        int max = 1;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (pairs[i][0] > pairs[j][1]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                    if(dp[i] > max){
                        max = dp[i];
                    }
                }
            }
        }

        return max;

    }

    public static void main(String[] args) {
        int[][] pairs = new int[][]{
                {-2, 1},
                {3, 7},
                {0, 10},
                {-1, 8},
                {0, 7},
                {8, 9},
                {7, 10},
                {-9, -3},
                {-4, -3},
                {0, 6}
        };

        System.out.println(findLongestChain(pairs));
    }

}
