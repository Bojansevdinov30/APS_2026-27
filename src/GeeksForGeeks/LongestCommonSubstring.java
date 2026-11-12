package GeeksForGeeks;
/*Given two strings s1 and s2, find the length of the longest common substring. A substring is a sequence of characters that appears contiguously in a string.

Example:

Input: s1 = "GeeksforGeeks", s2 = "GeeksQuiz"
Output : 5
Explanation: The longest common substring is "Geeks" and is of length 5.

Input: s1 = "abcdxyz", s2 = "xyzabcd"
Output : 4
Explanation: The longest common substring is "abcd" and is of length 4.

Input: s1 = "abc", s2 = ""
Output : 0*/
public class LongestCommonSubstring {

    public static int longCommSubstr(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();

        // Create a table to store lengths of longest
        // common suffixes of substrings
        int[][] dp = new int[m + 1][n + 1];
        int res = 0;

        // Build dp[m+1][n+1] in bottom up fashion.
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    res = Math.max(res, dp[i][j]);
                } else {
                    dp[i][j] = 0;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {

        String s1 = "GeeksforGeeks";
        String s2 = "GeeksQuiz";
        System.out.println(longCommSubstr(s1, s2));
    }
}
