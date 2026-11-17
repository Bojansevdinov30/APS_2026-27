package PrethodniIspitni._2021;

import java.util.Scanner;

/*Игор има многу парички од по еден и два денари. Со дел од овие парички сака да купи чоколадо кое чини n денари. Ако Игор на почеток има барем по n парички од по еден и два денари, на колку начини тој може да избере и да ги нареди една врз друга паричките со кои ќе го плати чоколадото?
Влез

Колку денари чини чоколадото (n).
Излез

Бројот на начини за купување на чоколадото.
Пример

8

34

Пример од текстот: (1, 2, 1), (1, 1, 2), (2, 1, 1), (1, 1, 1, 1) и (2, 2) се 5 различни начини за чоколадо од 4 денари. Име на класата: Chocolate. Делумно решение: 7 тест примери.*/
public class PrvKolokvium_NapredenDel_Cokolado_DP {

    static int solve(int n) {
        int[] dp = new int[n + 1];

        // Base case: one way to pay 0 denars (use no coins)
        dp[0] = 1;

        for (int i = 1; i <= n; i++) {

            // Last coin is worth 1 denar
            dp[i] += dp[i - 1];

            // Last coin is worth 2 denars
            if (i >= 2) {
                dp[i] += dp[i - 2];
            }
        }

        return dp[n];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        System.out.println(solve(n));
    }
}
