package PrethodniIspitni._2026;
/* Се внесуваат N кутии. За секоја кутија се внесува тежина и вредност едноподруго, на пример 10 5.
           10 - тежина, 5 - вредност за таа кутија. Да се најде максималниот збир од вредности на „стабилни“ кутии.
           Кутијата е стабилна ако тежината е еднаква или помала на таа под неа. Longest Decreasing Subsequence (LDS)
Input:
5
10 5
8 6
9 100
7 4
6 3

Output:
112  ->  (најдобра е 10->9->7->6 = 5+100+4+3 = 112)    */

import java.util.Scanner;

public class PrvKolokvium_2026_Kutii_DP {

    public static class Box{
        int weight;
        int value;

        public Box(int weight, int value){
            this.weight = weight;
            this.value = value;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Box[] boxes = new Box[n];
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            boxes[i] = new Box(sc.nextInt(), sc.nextInt());
        }
        for (int i = 0; i < n; i++) {
            dp[i] = boxes[i].value;
        }
        int maximum = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            for(int j=0; j < i; j++){
                if(boxes[i].weight <= boxes[j].weight){
                    dp[i] = Math.max(dp[i], dp[j] + boxes[i].value);
                    maximum = Math.max(maximum, dp[i]);
                }
            }
        }
        System.out.println(maximum);

    }
}
