package PrethodniIspitni._2021;

import java.util.Scanner;

/*Даден е еден стринг S и број K. Да се одреди бројот на начини да се изберат K букви од овој стринг така што кога ќе се спојат не постои пар од соседни букви
кои се самогласки.
Влез

Во првата линија е стрингот S (само мали латинични букви од a до z, должина најмногу 100). Во втората линија е бројот K (1 ≤ K ≤ 100).
Излез

Бројот на начини да се изберат K букви така што условот е задоволен. Бидејќи бројот може да биде многу голем, се печати по модул 1 000 007, т.е. C = (A + B)
% 1 000 007.
Пример
finki
3
9
Самогласки се a, e, i, o, u. За примерот, секвенците се: fin, fik, fni, fki, fnk, ini, iki, ink, nki. Име на класата: Letters. Делумно решение: 5 тест примери.*/
public class PrvKolokvium_NapredenDel_Letters_DP {
    static final int MOD = 1000007;

    static boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i'
                || c == 'o' || c == 'u';
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String s = sc.next();
        int k = sc.nextInt();

        int n = s.length();

        int[][] dpC = new int[n + 1][k + 1];
        int[][] dpV = new int[n + 1][k + 1];

        dpC[0][0] = 1;

        for (int i = 1; i <= n; i++) {

            char c = s.charAt(i - 1);

            for (int j = 0; j <= k; j++) {

                // Don't select current character
                dpC[i][j] = dpC[i - 1][j];
                dpV[i][j] = dpV[i - 1][j];

                if (j > 0) {

                    if (isVowel(c)) {
                        // Select vowel: previous must be consonant
                        dpV[i][j] = (dpV[i][j] + dpC[i - 1][j - 1]) % MOD;
                    } else {
                        // Select consonant: previous can be either
                        dpC[i][j] = (
                                dpC[i][j]
                                        + dpC[i - 1][j - 1]
                                        + dpV[i - 1][j - 1]
                        ) % MOD;
                    }
                }
            }
        }

        int ans = (dpC[n][k] + dpV[n][k]) % MOD;

        System.out.println(ans);
    }

}
