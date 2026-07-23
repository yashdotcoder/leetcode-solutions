class Solution {

    public List<List<Integer>> combinationSum(int[] candidates, int target) {

        // Stores all the valid combinations.
        List<List<Integer>> combinations = new ArrayList<>();

        // Start backtracking from index 0 with an empty path.
        createCombinations(candidates, target, 0, combinations, new ArrayList<>());

        return combinations;
    }

    /**
     * Backtracking function to generate all possible combinations.
     *
     * @param candidates    Input array of candidate numbers.
     * @param target        Remaining target that needs to be achieved.
     * @param index         Current index from where we can choose numbers.
     *                      We pass the same index again because the same
     *                      number can be used unlimited times.
     * @param combinations  Stores all valid combinations.
     * @param path          Current combination being built.
     */
    private void createCombinations(int[] candidates,
                                    int target,
                                    int index,
                                    List<List<Integer>> combinations,
                                    List<Integer> path) {

        // Base Case:
        // If target becomes 0, we have found one valid combination.
        if (target == 0) {

            // IMPORTANT:
            // Store a COPY of the current path.
            // If we store 'path' directly, all answers will point to
            // the same list and get modified during backtracking.
            combinations.add(new ArrayList<>(path));
            return;
        }

        // If we've reached beyond the array, there is nothing left to choose.
        if (index >= candidates.length) {
            return;
        }

        // Try every candidate starting from the current index.
        // Starting from 'index' avoids generating duplicate combinations
        // like [2,3] and [3,2].
        for (int i = index; i < candidates.length; i++) {

            // Skip this candidate if it exceeds the remaining target.
            if (candidates[i] > target) {
                continue;
            }

            // Choose the current candidate.
            path.add(candidates[i]);

            // Recursive call:
            // Pass 'i' again because we are allowed to reuse
            // the same candidate multiple times.
            createCombinations(
                    candidates,
                    target - candidates[i],
                    i,
                    combinations,
                    path
            );

            // Undo the choice (Backtracking).
            // This restores the path so the next candidate can be explored.
            path.remove(path.size() - 1);
        }
    }
}