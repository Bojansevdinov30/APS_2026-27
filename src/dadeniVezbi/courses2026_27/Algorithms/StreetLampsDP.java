
package dadeniVezbi.courses2026_27.Algorithms;

import java.util.Arrays;
import java.util.Scanner;

public class StreetLampsDP {

    public static int solve(int[] lights, int n, int m) {

        int INF = n + 1;

        // dp[i] = minimum lamps needed to cover positions 1...i
        int[] dp = new int[m + 1];
        Arrays.fill(dp, INF);

        // No positions need to be covered
        dp[0] = 0;

        for (int i = 0; i < m; i++) {

            // Cannot extend an impossible state
            if (dp[i] == INF) {
                continue;
            }

            // The next position that needs illumination
            int nextPosition = i + 1;

            // Try every possible lamp
            for (int j = 0; j < n; j++) {

                int p = lights[j];

                // Does this lamp cover nextPosition?
                if (p - 2 <= nextPosition
                        && p + 2 >= nextPosition) {

                    // Furthest position this lamp can illuminate
                    int end = Math.min(m, p + 2);

                    // Only keep transitions that extend coverage
                    if (end > i) {
                        dp[end] = Math.min(
                                dp[end],
                                dp[i] + 1
                        );
                    }
                }
            }
        }

        return dp[m] == INF ? -1 : dp[m];
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();

        int[] lights = new int[n];

        for (int i = 0; i < n; i++) {
            lights[i] = sc.nextInt();
        }

        System.out.println(solve(lights, n, m));
    }
}

