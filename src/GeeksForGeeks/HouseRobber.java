package GeeksForGeeks;

import java.util.Scanner;

/*Given an array arr[] where each element shows the amount of money in a row of houses built in a line. A robber wants to steal money, but cannot rob two houses adjacent to each other because it will set off an alarm.
Find the maximum money the robber can steal without robbing two adjacent houses.

Examples:

Input: arr[] = [6, 7, 1, 3, 8, 2, 4]
Output: 19
Explanation: The thief will steal from house 1, 3, 5 and 7, total money = 6 + 1 + 8 + 4 = 19.

Input: arr[] = [5, 3, 4, 11, 2]
Output: 16
Explanation: Thief will steal from house 1 and 4, total money = 5 + 11 = 16.*/
public class HouseRobber {
    static int solution() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] houses = new int[n];
        for (int i = 0; i < n; i++) houses[i] = sc.nextInt();

        if (n == 0) return 0;

        if (n == 1) return houses[0];

        int[] dp = new int[n];

        dp[0] = houses[0];
        dp[1] = Math.max(houses[0], houses[1]);

        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(dp[i - 1], houses[i] + dp[i - 2]);
        }

        return dp[n - 1];

    }

    public static void main(String[] args) {
        System.out.println(solution());
    }
}
