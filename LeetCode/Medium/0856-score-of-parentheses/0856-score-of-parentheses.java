class Solution {
    public int scoreOfParentheses(String s) {
        int formed = 0;
        int res = 0;
        int factor = 1;

        for (int i = 0; i < s.length(); ) {
            char c = s.charAt(i);

            if (c == '(') {
                formed++;
                factor *= 2;
                i++;
            } else {
                factor /= 2;
                res += factor;
                i++;
                while (i < s.length() && s.charAt(i) == ')') {
                    factor /= 2;
                    i++;
                }
            }
        }

        return res;
    }
}