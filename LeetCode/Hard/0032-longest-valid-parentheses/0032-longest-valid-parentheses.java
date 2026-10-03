class Solution {
    public int longestValidParentheses(String s) {
        int n = s.length();

        // dp[i] = length of the longest valid parentheses substring
        // that ends exactly at index i.
        //
        // If s[i] == '(', dp[i] will always remain 0 because
        // a valid parentheses substring cannot end with '('.
        int[] dp = new int[n];

        int maxLength = 0;

        // Start from index 1 because we always look at i - 1.
        for (int i = 1; i < n; ++i) {

            // A valid parentheses substring must end with ')'.
            if (s.charAt(i) == ')') {

                // CASE 1: Current pair is directly "()"
                //
                // Example:
                //        ... ( )
                //             i-1 i
                //
                // The "()" contributes 2 characters.
                // If there was a valid substring ending at i-2,
                // it can be directly attached to this "()" pair.
                if (s.charAt(i - 1) == '(') {

                    dp[i] = (i - 2 >= 0)
                            ? dp[i - 2] + 2
                            : 2;

                } else {

                    // CASE 2: Current ending is "))"
                    //
                    // The previous ')' may already be part of a
                    // valid parentheses substring.
                    //
                    // Example:
                    //      (  (  )  )
                    //      ↑     ↑  ↑
                    //      matching   current ')'

                    int previousValidLength = dp[i - 1];

                    // The valid substring ending at i-1 starts at:
                    // i - previousValidLength.
                    //
                    // Therefore, the character immediately before
                    // that valid substring is at:
                    // i - previousValidLength - 1
                    int matchingOpenIndex =
                            i - previousValidLength - 1;

                    // If that character is '(',
                    // it can match the current ')'.
                    if (matchingOpenIndex >= 0
                            && s.charAt(matchingOpenIndex) == '(') {

                        // We now have three possible parts:
                        //
                        // 1. The previous valid substring
                        //    -> previousValidLength
                        //
                        // 2. The new matching "()"
                        //    -> 2
                        //
                        // 3. A valid substring immediately before
                        //    the matching '('
                        //    -> dp[matchingOpenIndex - 1]
                        //
                        // Example:
                        //
                        //   [valid] ( [valid] )
                        //            ↑       ↑
                        //      matching   current
                        //        '('        ')'
                        //
                        dp[i] = 2
                                + previousValidLength
                                + (matchingOpenIndex - 1 >= 0
                                    ? dp[matchingOpenIndex - 1]
                                    : 0);
                    }
                }

                // dp[i] only represents a valid substring ending at i.
                // Keep track of the maximum over all possible ending positions.
                maxLength = Math.max(maxLength, dp[i]);
            }
        }

        return maxLength;
    }
}