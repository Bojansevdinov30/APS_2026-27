package PrethodniIspitni._2021;

import java.util.Arrays;
import java.util.Scanner;

/*Дадена е низа од N позитивни природни броеви и еден број K. Да се изберат точно K броеви од низата, така што кога ќе се спојат во една низа, следната сума:

1·прв број + 2·втор број + … + K·K-ти број

е максимална. Да се испечати оваа сума.
Влез

Во првата линија се дадени два броја N (1 ≤ N ≤ 100) и K (1 ≤ K ≤ 100, K ≤ N). Во втората линија се дадени N позитивни природни броеви, секој помал од 1 000.
Излез

Бараната максимална тежинска сума.
Пример

10 3
1 9 2 3 6 1 3 2 1 3

37

За овој пример секвенцата е 1 9 6 (1·1 + 2·9 + 3·6 = 37). Изборот го задржува редоследот од низата. Име на класата: WeightedSum. Делумно решение: 5 тест примери.*/
public class PrvKolokvium_NapredenDel_WeightedSum_DP {

    static int solve(int[] numbers, int n, int k) {
        int[][] dp = new int[n + 1][k + 1];

        // Initialize all states as impossible
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], Integer.MIN_VALUE);
        }

        // Base case: selecting 0 numbers gives sum 0
        dp[0][0] = 0;

        for (int i = 1; i <= n; i++) {
            dp[i][0] = 0;

            for (int j = 1; j <= Math.min(i, k); j++) {

                // Option 1: Skip numbers[i-1]
                dp[i][j] = dp[i - 1][j];

                // Option 2: Select numbers[i-1] as the j-th number
                if (dp[i - 1][j - 1] != Integer.MIN_VALUE) {
                    dp[i][j] = Math.max(
                            dp[i][j],
                            dp[i - 1][j - 1] + j * numbers[i - 1]
                    );
                }
            }
        }

        return dp[n][k];
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int k = input.nextInt();
        int[] numbers = new int[n];
        for (int i = 0; i < n; i++) {
            numbers[i] = input.nextInt();
        }
        System.out.println(solve(numbers, n, k));
    }
}
