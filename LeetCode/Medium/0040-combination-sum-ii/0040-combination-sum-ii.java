class Solution {

    // Stores all unique valid combinations
    private List<List<Integer>> ans = new ArrayList<>();

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {

        // Sorting is the key optimization.
        // 1. Makes every combination naturally sorted.
        // 2. Allows us to skip duplicates easily.
        // 3. Enables early pruning when candidate > remaining target.
        Arrays.sort(candidates);

        // Start DFS from index 0.
        backtrack(candidates, target, 0, new ArrayList<>());

        return ans;
    }

    /**
     * @param candidates  Sorted input array
     * @param remaining   Remaining target to achieve
     * @param start       Current index from where we can choose elements
     * @param path        Current combination being built
     */
    private void backtrack(int[] candidates,
                           int remaining,
                           int start,
                           List<Integer> path) {

        // Base Case:
        // We have successfully formed the target.
        if (remaining == 0) {

            // VERY IMPORTANT:
            // Store a COPY of the current path.
            // Never store 'path' directly because it is mutable.
            ans.add(new ArrayList<>(path));
            return;
        }

        // Try every possible candidate starting from 'start'
        for (int i = start; i < candidates.length; i++) {

            // -----------------------------
            // Duplicate Skipping
            // -----------------------------
            //
            // Example:
            // [1,1,2,5]
            //
            // At the SAME recursion level,
            // choosing the second '1' first would generate
            // exactly the same combinations as choosing
            // the first '1'.
            //
            // Therefore skip it.
            //
            // Notice:
            // i > start
            //
            // means "skip duplicates only at the same level".
            //
            if (i > start && candidates[i] == candidates[i - 1]) {
                continue;
            }

            // -----------------------------
            // Pruning
            // -----------------------------
            //
            // Since array is sorted,
            // if current number is already bigger than remaining,
            // every number after it will also be bigger.
            //
            // So stop exploring.
            //
            if (candidates[i] > remaining) {
                break;
            }

            // Choose current element
            path.add(candidates[i]);

            // Move to next index.
            //
            // i + 1 because every element
            // can be used only once.
            backtrack(candidates,
                      remaining - candidates[i],
                      i + 1,
                      path);

            // Undo the choice.
            //
            // This restores the list
            // exactly as it was before recursion.
            path.remove(path.size() - 1);
        }
    }
}