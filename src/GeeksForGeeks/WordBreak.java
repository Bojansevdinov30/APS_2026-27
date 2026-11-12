package GeeksForGeeks;
/*Given a string s and a list dictionary[] of words. Find whether the string s can be formed by concatenating one or more words from the dictionary[].

Note: From dictionary[], any word can be taken any number of times and in any order.

Examples:

Input: s = "ilike", dictionary[] = ["i", "like", "gfg"]
Output: true
Explanation: s can be breakdown as "i like".

Input: s = "ilikegfg", dictionary[] = ["i", "like", "man", "india", "gfg"]
Output: true
Explanation: s can be breakdown as "i like gfg".

Input: s = "ilikemangoes", dictionary[] = ["i", "like", "man", "india", "gfg"]
Output: false
Explanation: s cannot be formed using dictionary[] words.*/

import java.util.*;

public class WordBreak {

    static boolean solution() {
        Scanner sc = new Scanner(System.in);

        String s = sc.nextLine();

        int m = sc.nextInt();
        Set<String> dictionary = new HashSet<>();

        for (int i = 0; i < m; i++) {
            dictionary.add(sc.next());
        }

        int n = s.length();
        boolean[] dp = new boolean[n + 1];

        dp[0] = true;

        for (int i = 1; i <= n; i++) {

            for (int j = 0; j < i; j++) {

                if (dp[j] && dictionary.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[n];
    }

    public static void main(String[] args) {
        System.out.println(solution());
    }
}