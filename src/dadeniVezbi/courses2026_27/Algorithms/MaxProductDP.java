package dadeniVezbi.courses2026_27.Algorithms;

import java.util.Scanner;

/*За дадена низа од броеви да се најде максималниот производ кој се формира со множење на броевите од некоја растечка подниза на таа низа.

Влез: На влез во првиот ред е даден бројот на броеви во низата, а во вториот ред е дадена низата од броеви.

Излез: На излез треба да се испечати максималниот производ кој се бара во задачата.

Пример:

Влез:

6

3 100 4 5 150 6

Излез: 45000

Максималниот производ се формира од растечката подниза 3, 100, 150. Да се забележи дека најдолгата растечка подниза е друга, 3, 4, 5, 6.
*/
public class MaxProductDP {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();           // number of elements
        long[] arr = new long[n];       // input array
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextLong();
        }

        long[] dp = new long[n];        // dp[i] = max product of increasing subsequence ending at i
        long maxProduct = arr[0];       // keeps the global maximum

        // initialize dp
        for (int i = 0; i < n; i++) {
            dp[i] = arr[i];             // each element alone is a subsequence
        }

        // fill dp
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i]) {
                    dp[i] = Math.max(dp[i], dp[j] * arr[i]);
                }
            }
            maxProduct = Math.max(maxProduct, dp[i]); // update global max
        }

        System.out.println(maxProduct);
    }
}
