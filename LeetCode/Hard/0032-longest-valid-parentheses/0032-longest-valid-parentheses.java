class Solution {
    public int longestValidParentheses(String s) {
        int n = s.length();
        int[] dp = new int[n];
        int ans = 0;

        for (int i = 1; i < n; ++i) {
            if (s.charAt(i) == ')') {
                if (s.charAt(i - 1) == '(') {
                    dp[i] = (i - 2 >= 0 ? dp[i - 2] + 2 : 2);
                } else {
                    int prevVPALength = dp[i - 1];
                    int index = i - prevVPALength - 1;

                    if (index >= 0 && s.charAt(index) == '(') {
                        dp[i] = 2 + prevVPALength + (index - 1 >= 0 ? dp[index - 1] : 0);
                    }
                }
                ans = Math.max(ans, dp[i]);
            }
        }

        return ans;
    }
}