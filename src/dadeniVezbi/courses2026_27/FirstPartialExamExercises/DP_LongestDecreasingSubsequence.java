package dadeniVezbi.courses2026_27.FirstPartialExamExercises;

import java.util.Arrays;
import java.util.Scanner;


public class DP_LongestDecreasingSubsequence {


    private static int najdolgaOpagackaSekvenca(int[] a) {

        int[] dp  = new int[a.length];
        Arrays.fill(dp, 1);
        int maxD = 1;
        for(int i = 0; i < a.length; i++){
            for(int j = 0; j < i; j++){
                if(a[i] < a[j]){
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                    maxD = Math.max(maxD, dp[i]);
                }
            }
        }
        return maxD;

    }

    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);

        int n = stdin.nextInt();
        int a[] = new int[n];
        for (int i = 0; i < a.length; i++) {
            a[i] = stdin.nextInt();
        }
        System.out.println(najdolgaOpagackaSekvenca(a));
    }


}

