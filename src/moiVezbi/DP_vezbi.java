package moiVezbi;

import PrethodniIspitni._2026.PrvKolokvium_2026_Kutii_DP;

import java.util.*;

public class DP_vezbi {

    static void squares() {
        //    Колку најмалку пати треба да одземеме квадрат на цел број за некој број Х да стане 0?
//
//    Влез: Цел број Х од 1 до 10^5.
//
//    Излез: Бараниот резултат.
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        int[] dp = new int[x + 1];
        Arrays.fill(dp, 1000);
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= x; i++) {
            for (int j = 1; j * j <= i; j++) {
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
            }
        }
        System.out.println(dp[x]);
    }

    static void knapsack01() {
        int C = 50;
        int[] p = {60, 100, 120};
        int[] w = {10, 20, 30};
        int m = p.length;
        int[][] dp = new int[m + 1][C + 1];

        for (int i = 0; i <= C; i++) {
            dp[0][i] = 0;
        }
        for (int i = 0; i <= m; i++) {
            dp[i][0] = 0;
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= C; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j >= w[i - 1]) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - w[i - 1]] + p[i - 1]);
                }
            }
        }
        System.out.println(dp[m][C]);

    }

    public static class Box {
        int weight;
        int value;

        public Box(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }
    }
    static void kutii() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Box[] boxes = new Box[n];
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            boxes[i] = new Box(sc.nextInt(), sc.nextInt());
        }
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            dp[i] = boxes[i].value;
            max = Math.max(dp[i], max);
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (boxes[i].weight <= boxes[j].weight) {
                    dp[i] = Math.max(dp[i], dp[j] + boxes[i].value);
                    max = Math.max(dp[i], max);
                }
            }
        }
        System.out.println(max);


    }

    static void longestDecreasingSequence() {
        Scanner stdin = new Scanner(System.in);
        int n = stdin.nextInt();
        int a[] = new int[n];
        for (int i = 0; i < a.length; i++) {
            a[i] = stdin.nextInt();
        }

        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        int max = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if(a[j] > a[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                    max = Math.max(dp[i], max);
                }
            }
        }
        System.out.println(max);

    }

    static void biggestSumInSubarray() {
        /*Да се напише низа од зборови и да се најде најголемата подсума од низата т.ш. вредноста на еден збор е еднаков на неговата должина.
        Ако зборот ја има буквата f да де додели негативна вредност. Да се реши задачата со комплексност од О(n*logn)
Пр.
hello
good
f
example
wonderful
->[5,4,-1,7,-9]
output: 15*/
        Scanner input = new Scanner(System.in);

        int n = input.nextInt();

        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {

            String word = input.next();

            int value = word.length();

            if (word.contains("f")) {
                value = -value;
            }

            arr[i] = value;
        }
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            dp[i] = arr[i];
        }
        int maxSum = dp[0];
        for (int i = 1; i < n; i++) {
            dp[i] = Math.max(arr[i], dp[i-1] + arr[i]);
            maxSum = Math.max(dp[i], maxSum);
        }
        System.out.println(maxSum);
    }

    static void longestSubstringInAString(){
        Scanner input = new Scanner(System.in);
        String source, target;
        source = input.next();
        target = input.next();

        int m = source.length();
        int n = target.length();

        int[][] dp = new int[m + 1][n + 1];

        // demek moze empty target sekogas da se formira ako izberes nisto
        for (int i = 0; i <= m; i++) {
            dp[i][0] = 1;
        }

        for (int i = 0; i <= n; i++) {
            dp[0][i] = 0;
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                dp[i][j] = dp[i - 1][j];
                if(source.charAt(i - 1) == target.charAt(j - 1)) {
                    dp[i][j] += dp[i - 1][j - 1];
                }
            }
        }
        System.out.println(dp[m][n]);

    }

    static void stairs(){
        /*2. Даден е цел број N. Се наоѓате на скалило 0 и треба да стигнете до скалило N. Во еден чекор можете да се поместите: 1) за 1 скалило, или 2) за 2 скалила. Да се пресмета бројот на различни начини на кои може да се стигне до скалило N.

Влез: Еден цел број N (0 ≤ N ≤ 10^7)

Излез: Испечати еден цел број — бројот на начини да се стигне до скалило N.

Пример

Влез: 5

Излез: 8*/
        Scanner sc  = new Scanner(System.in);
        int n = sc.nextInt();
        double[] dp = new double[n+1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        System.out.println(dp[n]);

    }

    static void BestTimeToBuyAndSellStock(){
        /*You are given an array prices where prices[i] is the price of a given stock on the ith day.

You want to maximize your profit by choosing a single day to buy one stock and choosing a different day in the future to sell that stock.

Return the maximum profit you can achieve from this transaction. If you cannot achieve any profit, return 0.



Example 1:

Input: prices = [7,1,5,3,6,4]
Output: 5
Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
Note that buying on day 2 and selling on day 1 is not allowed because you must buy before you sell.
Example 2:

Input: prices = [7,6,4,3,1]
Output: 0
Explanation: In this case, no transactions are done and the max profit = 0.


Constraints:

1 <= prices.length <= 105
0 <= prices[i] <= 104*/
        int[] prices = {7,6,4,3,1};
        int minimumBuy = prices[0];
        int maxProfit = 0;
        int length = prices.length;
        for(int i=1; i<length; i++){
            if(prices[i] - minimumBuy > maxProfit){
                maxProfit = prices[i] - minimumBuy;
            }
            if(prices[i] <  minimumBuy){
                minimumBuy = prices[i];
            }
        }
    }

    static void bestTimeToBuyAndSellStock2(){
        /*You are given an integer array prices where prices[i] is the price of a given stock on the ith day.

On each day, you may decide to buy and/or sell the stock. You can only hold at most one share of the stock at any time. However, you can sell and buy the stock multiple times on the same day, ensuring you never hold more than one share of the stock.

Find and return the maximum profit you can achieve.



Example 1:

Input: prices = [7,1,5,3,6,4]
Output: 7
Explanation: Buy on day 2 (price = 1) and sell on day 3 (price = 5), profit = 5-1 = 4.
Then buy on day 4 (price = 3) and sell on day 5 (price = 6), profit = 6-3 = 3.
Total profit is 4 + 3 = 7.
Example 2:

Input: prices = [1,2,3,4,5]
Output: 4
Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
Total profit is 4.
Example 3:

Input: prices = [7,6,4,3,1]
Output: 0
Explanation: There is no way to make a positive profit, so we never buy the stock to achieve the maximum profit of 0.


Constraints:

1 <= prices.length <= 3 * 104
0 <= prices[i] <= 104*/
        int[] prices = {1,2,3,4,5};
        int profit = 0;

        for (int i = 1; i < prices.length; i++) {

            if (prices[i] > prices[i - 1]) {
                profit += prices[i] - prices[i - 1];
            }
        }

        System.out.println(profit);
    }

    static void DistinctSubsequences(){
        String s = "babgbag";
        String t = "bag";
        int n = s.length();
        int m = t.length();
        int dp[][] = new int[n+1][m+1];
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
                }else{
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        int i =n;
        int j = m;
        String result = "";
        while (i != 0 && j != 0){
            if(s.charAt(i - 1) == t.charAt(j - 1)){
                result += s.charAt(i - 1);
                i--;
                j--;
            }else if(dp[i][j] == dp[i - 1][j]){
                i--;
            }else {
                j--;
            }
        }
        System.out.println(result); // reversed
    }

    public static void main(String[] args) {
        // squares();
        // knapsack01();
        // kutii();
        // longestDecreasingSequence();
        // biggestSumInSubarray();
        // longestSubstringInAString();
        // stairs();
        // BestTimeToBuyAndSellStock();
        // DistinctSubsequences();
    }
}
