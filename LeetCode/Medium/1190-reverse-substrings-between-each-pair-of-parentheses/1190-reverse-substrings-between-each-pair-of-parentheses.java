class Solution {
    public String reverseParentheses(String s) {
        int n = s.length();
        Stack<Integer> stack = new Stack<>();
        int [] link = new int[n];

        for (int i = 0; i < n; ++i) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else if (s.charAt(i) == ')') {
                link[i] = stack.pop();
                link[link[i]] = i;
            }
        }

        StringBuilder sb = new StringBuilder();
        int dir = 1;

        for (int i = 0; i < n; i += dir) {
            if (s.charAt(i) >= 'a') {
                sb.append(s.charAt(i));
            } else {
                dir = -dir;
                i = link[i];
            }
        }

        return sb.toString();
    }
}