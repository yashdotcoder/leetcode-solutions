class Solution {
    private Set<String> res = new HashSet<>();
    private int minInvalidParenthesis = 100;

    private void buildValidParenthesis(String s, int idx, int open, int close, StringBuilder sb) {
        if (close > open) {
            return;
        }

        if (idx == s.length()) {
            if (open == close) {
                res.add(sb.toString());
                minInvalidParenthesis = Math.min(minInvalidParenthesis, s.length() - sb.length());
            }
            
            return;
        }

        if (s.charAt(idx) == '(' || s.charAt(idx) == ')') {

            // take
            if (s.charAt(idx) == '(') open++;
            else close++;

            buildValidParenthesis(s, idx + 1, open, close, sb.append(s.charAt(idx)));
            sb.deleteCharAt(sb.length() - 1);

            if (s.charAt(idx) == '(') open--;
            else close--;

            // not take
            buildValidParenthesis(s, idx + 1, open, close, sb);
        } else {
            buildValidParenthesis(s, idx + 1, open, close, sb.append(s.charAt(idx)));
            sb.deleteCharAt(sb.length() - 1);
        }

    }

    public List<String> removeInvalidParentheses(String s) {
        buildValidParenthesis(s, 0, 0, 0, new StringBuilder());
        List<String> ans = new ArrayList<>();
        System.out.println(minInvalidParenthesis);

        for (String string : res) {
            if (s.length() - string.length() == minInvalidParenthesis)
            ans.add(string);
        }

        return ans;
    }
}