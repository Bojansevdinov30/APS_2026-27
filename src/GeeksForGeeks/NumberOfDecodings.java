package GeeksForGeeks;
/*Let 1 maps to 'A', 2 maps to 'B', ..., 26 to 'Z'. Given a digit sequence, count the number of possible decodings of the given digit sequence.

Consider the input string "123". There are three valid ways to decode it:

"ABC": The grouping is (1, 2, 3) → 'A', 'B', 'C'
"AW": The grouping is (1, 23) → 'A', 'W'
"LC": The grouping is (12, 3) → 'L', 'C'
Note: Groupings that contain invalid codes (e.g., "0" by itself or numbers greater than "26") are not allowed.
For instance, the string "230" is invalid because "0" cannot stand alone, and "30" is greater than "26", so it cannot represent any letter. The task is to find the total number of valid ways to decode a given string.

Examples:

Input: digits = "121"
Output: 3
Explanation: The possible decodings are "ABA", "AU", "LA"

Input: digits = "1234"
Output: 3
Explanation: The possible decodings are "ABCD", "LCD", "AWD"*/
public class NumberOfDecodings {
    public int numDecodings(String s) {
        int n = s.length();
        // dp[i]=number of ways to decode the first i digits
        int[] dp = new int[n + 1];

        dp[0] = 1;
        for (int i = 1; i <= n; i++) {

            // Use the last digit by itself
            if (s.charAt(i - 1) != '0') {
                dp[i] += dp[i - 1];
            }

            // Use the last two digits together
            if (i >= 2) {
                int number = Integer.parseInt(s.substring(i - 2, i));

                if (number >= 10 && number <= 26) {
                    dp[i] += dp[i - 2];
                }
            }
        }

        return dp[n];
    }

    public static void main(String[] args){
        // something
    }
}
