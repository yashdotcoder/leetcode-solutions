class Solution {

    public int longestValidParentheses(String s) {
        int n = s.length();

        // Count of '(' and ')' in the current window.
        int left = 0;
        int right = 0;

        int maxLength = 0;

        /*
         * PASS 1: Scan from LEFT -> RIGHT
         *
         * We look for substrings where:
         *
         *     left == right
         *
         * because equal numbers of '(' and ')' indicate that
         * the current window has the correct number of brackets.
         */
        for (int i = 0; i < n; ++i) {

            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }

            // Equal number of opening and closing brackets.
            // Since we haven't encountered an invalid imbalance,
            // this window is a valid parentheses substring.
            if (left == right) {
                maxLength = Math.max(maxLength, left + right);
            }

            /*
             * If right > left, we have more ')' than '('.
             *
             * Example:
             *     ())
             *
             * Such a window can NEVER become valid by extending it
             * to the right, because the unmatched ')' cannot be fixed.
             *
             * Therefore, discard this window and start fresh.
             */
            if (right > left) {
                left = right = 0;
            }
        }

        // Reset counters before the second pass.
        left = right = 0;

        /*
         * PASS 2: Scan from RIGHT -> LEFT
         *
         * Why do we need a second pass?
         *
         * Consider:
         *
         *     (()
         *
         * There is a valid "()" inside it, but during the
         * left-to-right scan we finish with:
         *
         *     left = 2
         *     right = 1
         *
         * Therefore, left == right never occurs for the complete
         * window, and we can miss the valid substring.
         *
         * The reverse scan handles this situation.
         */
        for (int i = n - 1; i >= 0; --i) {

            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }

            // Equal number of '(' and ')' means we found a
            // balanced parentheses window.
            if (left == right) {
                maxLength = Math.max(maxLength, left + right);
            }

            /*
             * In the reverse direction, the invalid situation is
             * the opposite.
             *
             * If left > right, there are more '(' than ')' in the
             * current window.
             *
             * Those unmatched '(' cannot be fixed by extending
             * further to the LEFT, so discard this window.
             */
            if (left > right) {
                left = right = 0;
            }
        }

        return maxLength;
    }

    public int longestValidParenthesesDP(String s) {
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