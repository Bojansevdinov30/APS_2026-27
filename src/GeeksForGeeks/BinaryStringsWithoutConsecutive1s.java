package GeeksForGeeks;

import java.util.Scanner;

/*Given a positive integer n, count all possible distinct binary strings of length n such that there are no consecutive 1's.

Examples:

Input: n = 3
Output: 5
Explanation: 5 strings are ("000", "001", "010", "100", "101").

Input: n = 2
Output: 3
Explanation: 3 strings are ("00", "01", "10").*/
public class BinaryStringsWithoutConsecutive1s {
    static int solution(){
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        if (n == 1) return 2;
        if (n == 2) return 3;

        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 2;
        dp[2] = 3;
        for (int i = 3; i <= n; i++){
            // demek i-1 se site sto pocnuvaat na 0, a i-2 se site sto pocnuvaat na 10 oti ako imase 11 toa veke nemase da vazi
            dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[n];
    }
    public static void main(String[] args) {
        System.out.println(solution());
    }
}
