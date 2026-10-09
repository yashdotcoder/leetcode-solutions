class Solution {
    public int minInsertions(String s) {
        // Tracks unmatched '(' characters.
        // Each '(' requires two consecutive ')' characters.
        int open = 0, ans = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                // Found an opening parenthesis.
                // It needs to be matched with a pair of '))'.
                open++;
            } else {
                // Step 1: Ensure this ')' has a second consecutive ')'.
                if (i + 1 < s.length() && s.charAt(i + 1) == ')') {
                    // The required pair '))' already exists.
                    // Skip the second ')' because both form one pair.
                    i++;
                } else {
                    // The second ')' is missing, so insert it.
                    ans++;
                }

                // Step 2: Match this pair '))' with an opening '('.
                if (open > 0) {
                    // An unmatched '(' is available; use it.
                    open--;
                } else {
                    // No opening '(' is available.
                    // Insert one to match this pair '))'.
                    ans++;
                }
            }
        }

        // Every remaining unmatched '(' needs two ')' insertions.
        ans += open * 2;

        // Return the total number of insertions required.
        return ans;
    }
}