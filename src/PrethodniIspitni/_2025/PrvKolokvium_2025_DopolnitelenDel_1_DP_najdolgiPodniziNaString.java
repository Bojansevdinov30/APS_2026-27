package PrethodniIspitni._2025;

import java.util.Scanner;

/*DP

Најди го бројот на wow поднизи во стринг

Input
wwoww
Output
4

Explanitaion:
w-ow-
w-o-w
-wow-
-wo-w

Input
ooooowwwwwoooo
Output
0

Input
woowow
Output
6

Начин:
wo-w--
wo---w
w-ow--
w-o--w
w---ow
---wow


----

*/
public class PrvKolokvium_2025_DopolnitelenDel_1_DP_najdolgiPodniziNaString {

    public static long countSubsequences(String source, String target) {

        int n = source.length();
        int m = target.length();

        long[][] dp = new long[n + 1][m + 1];

        // Empty target can always be formed once:
        // by choosing nothing.
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1;
        }

        for (int i = 1; i <= n; i++) {

            for (int j = 1; j <= m; j++) {

                // Don't use source[i - 1]
                dp[i][j] = dp[i - 1][j];

                // Use source[i - 1]
                if (source.charAt(i - 1) == target.charAt(j - 1)) {

                    dp[i][j] += dp[i - 1][j - 1];
                }
            }
        }

        return dp[n][m];
    }

    public static long countSubsequencesOptimized(
            String source,
            String target) {

        int m = target.length();

        long[] dp = new long[m + 1];

        // Empty target
        dp[0] = 1;

        for (int i = 0; i < source.length(); i++) {

            char c = source.charAt(i);

            // IMPORTANT:
            // Go backwards so we don't use the current
            // character multiple times.
            for (int j = m; j >= 1; j--) {

                if (c == target.charAt(j - 1)) {
                    dp[j] += dp[j - 1];
                }
            }
        }

        return dp[m];
    }

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        String s = input.nextLine();

        long w = 0;
        long wo = 0;
        long wow = 0;

        for (int i = 0; i < s.length(); i++) {

            char c = s.charAt(i);

            if (c == 'w') {
                wow += wo;
                w++;
            }
            else if (c == 'o') {
                wo += w;
            }
        }

        System.out.println(wow);
    }
}
