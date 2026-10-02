class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> answer = new ArrayList<>();

        // Start building the string with 0 opening and 0 closing brackets.
        backtracking(answer, new StringBuilder(), 0, 0, n);

        return answer;
    }

    private void backtracking(
        List<String> answer,
        StringBuilder curString,
        int leftCount,
        int rightCount,
        int n
    ) {
        // A valid parentheses string contains exactly 2*n characters.
        // At this point, we have constructed one complete valid combination.
        if (curString.length() == 2 * n) {
            answer.add(curString.toString());
            return;
        }

        // We can always add '(' as long as we haven't used all n opening brackets.
        if (leftCount < n) {
            curString.append("(");

            // Explore the choice of adding an opening bracket.
            backtracking(answer, curString, leftCount + 1, rightCount, n);

            // BACKTRACK:
            // Remove the '(' so that curString is restored to its previous state
            // before we try the next possible choice.
            curString.deleteCharAt(curString.length() - 1);
        }

        // We can add ')' only when there is an unmatched '('.
        // leftCount > rightCount means we currently have an opening bracket
        // available to be closed.
        if (leftCount > rightCount) {
            curString.append(")");

            // Explore the choice of adding a closing bracket.
            backtracking(answer, curString, leftCount, rightCount + 1, n);

            // BACKTRACK:
            // Remove the ')' that we just added.
            // This restores curString to the state before this branch,
            // allowing the parent call to continue exploring other choices.
            curString.deleteCharAt(curString.length() - 1);
        }
    }
}
