class Solution {
    /***********************************************************************
    * ------------------------------------------------------------
    * Approach              TC          SC
    * ------------------------------------------------------------
    * Recursion             O(2^n)      O(n)
    * Memoization           O(n²)       O(n)
    * Tabulation            O(n²)       O(n)
    *
    * Final Submitted Approach : Tabulation
    ***********************************************************************/

    /***********************************************************************
     * APPROACH 1 : RECURSION
     * ---------------------------------------------------------------------
     * Intuition:
     * We try to partition the string starting from the current index.
     * At every position, we keep extending the current word one character
     * at a time. Whenever the current word exists in the dictionary,
     * we recursively check whether the remaining suffix can also be
     * segmented.
     *
     * If any recursive path reaches the end of the string, then the entire
     * string can be segmented.
     *
     * Example:
     *
     * s = "leetcode"
     * dict = {"leet", "code"}
     *
     * solve(0)
     *      |
     *      ---> "l"
     *      ---> "le"
     *      ---> "lee"
     *      ---> "leet" ✓
     *                    |
     *                    ---> solve(4)
     *                              |
     *                              ---> "code" ✓
     *                                          |
     *                                          ---> solve(8)
     *                                                   |
     *                                                   ---> true
     *
     * Time Complexity : O(2^n)
     * Space Complexity: O(n)
     * (Recursion stack)
     ***********************************************************************/
    private boolean solveRecursion(String s, Set<String> dict, int index) {

        // If we've consumed the entire string,
        // then a valid segmentation has been found.
        if (index == s.length()) {
            return true;
        }

        // Used to build the current word character by character.
        StringBuilder word = new StringBuilder();

        // Try every possible ending position for the current word.
        for (int i = index; i < s.length(); i++) {

            // Extend the current word.
            word.append(s.charAt(i));

            /*
             * If the current word exists in the dictionary,
             * recursively check whether the remaining suffix
             * can also be segmented.
             */
            if (dict.contains(word.toString())
                    && solveRecursion(s, dict, i + 1)) {

                // If any recursive call succeeds,
                // the answer is true.
                return true;
            }
        }

        // No valid segmentation found.
        return false;
    }

    /***********************************************************************
     * APPROACH 2 : MEMOIZATION (Top-Down DP)
     * ---------------------------------------------------------------------
     * Observation:
     * During recursion, the same starting index may be visited multiple
     * times, causing repeated computations.
     *
     * Therefore, store the answer for every starting index.
     *
     * DP State:
     *
     * memo[i]
     *
     *  1  -> substring s[i...n-1] can be segmented
     *  0  -> cannot be segmented
     * -1  -> not computed yet
     *
     * Time Complexity : O(n²)
     * Space Complexity: O(n)
     * (DP array + recursion stack)
     ***********************************************************************/
    private int[] memo;

    private boolean solveMemoization(String s, Set<String> dict, int index) {

        // Entire string has been successfully segmented.
        if (index == s.length()) {
            return true;
        }

        // Return previously computed result.
        if (memo[index] != -1) {
            return memo[index] == 1;
        }

        // Build current word.
        StringBuilder word = new StringBuilder();

        // Try every possible word starting from 'index'.
        for (int i = index; i < s.length(); i++) {

            word.append(s.charAt(i));

            /*
             * If current word exists and remaining suffix
             * can also be segmented,
             * store and return true.
             */
            if (dict.contains(word.toString())
                    && solveMemoization(s, dict, i + 1)) {

                memo[index] = 1;
                return true;
            }
        }

        // No valid segmentation possible from this index.
        memo[index] = 0;
        return false;
    }

    /***********************************************************************
     * APPROACH 3 : TABULATION (Bottom-Up DP)
     * ---------------------------------------------------------------------
     * DP State:
     *
     * dp[i] represents whether the first 'i' characters
     * can be segmented using dictionary words.
     *
     * Example:
     *
     * s = "leetcode"
     *
     * dp[0] -> ""
     * dp[4] -> "leet"
     * dp[8] -> "leetcode"
     *
     * Transition:
     *
     * For every ending position i,
     * try every possible previous cut j.
     *
     *           j          i
     *           |----------|
     * s = leetcode
     *
     * If
     *      dp[j] == true
     * and
     *      s.substring(j, i) exists in dictionary
     *
     * then
     *      dp[i] = true
     *
     * Time Complexity : O(n²)
     * Space Complexity: O(n)
     ***********************************************************************/
    private boolean solveTabulation(String s, Set<String> dict) {

        // Length of input string.
        int n = s.length();

        // DP array storing whether first 'i' characters
        // can be segmented.
        boolean[] dp = new boolean[n + 1];

        // Empty string is always segmentable.
        dp[0] = true;

        // Compute answer for every prefix length.
        for (int i = 1; i <= n; i++) {

            // Try every possible previous cut.
            for (int j = 0; j < i; j++) {

                /*
                 * Two conditions:
                 *
                 * 1. Prefix till j is already segmentable.
                 * 2. Remaining substring exists in dictionary.
                 */
                if (dp[j] && dict.contains(s.substring(j, i))) {

                    // Current prefix can also be segmented.
                    dp[i] = true;

                    // One valid partition is sufficient.
                    break;
                }
            }
        }

        // Whether the complete string can be segmented.
        return dp[n];
    }

    public boolean wordBreak(String s, List<String> wordDict) {

        /*
         * Convert dictionary into HashSet.
         *
         * Lookup becomes O(1) on average,
         * compared to O(n) in a List.
         */
        Set<String> dict = new HashSet<>(wordDict);

        /*
         * Uncomment any approach while revising.
         */

        // ---------------- Recursion ----------------
        // return solveRecursion(s, dict, 0);

        // ---------------- Memoization ----------------
        // memo = new int[s.length()];
        // Arrays.fill(memo, -1);
        // return solveMemoization(s, dict, 0);

        // ---------------- Tabulation (Submitted) ----------------
        return solveTabulation(s, dict);
    }
}