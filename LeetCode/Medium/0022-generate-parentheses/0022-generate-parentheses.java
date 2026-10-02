class Solution {
    private List<String> combinations = new ArrayList<>();

    private void createAllParenthesis(int n, StringBuilder sb) {
        if (n == 0) {
            if (isValidFormedParenthesis(sb.toString())) {
                combinations.add(sb.toString());
            }
            return;
        }

        createAllParenthesis(n - 1, new StringBuilder(sb).append('('));
        createAllParenthesis(n - 1, new StringBuilder(sb).append(')'));
    }

    public List<String> generateParenthesis(int n) {
        
        createAllParenthesis(2 * n, new StringBuilder());

        return combinations;
    }

    private boolean isValidFormedParenthesis(String s) {
        int formed = 0;

        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);

            if (c == '(') {
                formed++;
            } else {
                formed--;
            }

            if (formed < 0) {
                return false;
            }
        }

        return formed == 0;
    }
}