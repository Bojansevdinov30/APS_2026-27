package GeeksForGeeks;

import java.util.Scanner;

/*Given an array of integers cost[] of length n, where cost[i] is the cost of the ith step on a staircase. Once the cost is paid, we can either climb one or two steps.
We can either start from the step with index 0, or the step with index 1. The task is to find the minimum cost to reach the top.

Examples:

Input: cost[] = [10, 15, 20]
Output: 15
Explanation: The cheapest option is to start from step with index 1, pay cost[1] and go to the top.

Climbing-Stairs-1


Input: cost[] = [1, 100, 1, 1, 1, 100, 1, 1, 100, 1]
Output: 6
Explanation: The cheapest option is to start from step with index 1on cost[0], and only step on 1s, skipping cost[3].*/
public class WeightedStairs {
    static int solution() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] cost = new int[n];
        for (int cost_i = 0; cost_i < n; cost_i++) {
            cost[cost_i] = sc.nextInt();
        }
        if (n == 1) return cost[0];
        int[] dp = new int[n + 1];
        dp[0] = cost[0];
        dp[1] = cost[1];
        for (int i = 2; i < n; i++) {
            dp[i] = cost[i] + Math.min(dp[i - 1], dp[i - 2]);
        }
        dp[n] = Math.min(dp[n - 1], dp[n - 2]);
        return dp[n];
    }

    public static void main(String[] args) {
        System.out.println(solution());
    }
}
