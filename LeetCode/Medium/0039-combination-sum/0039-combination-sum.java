class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> combinations = new ArrayList<>();

        createCombinations(candidates, target, 0, combinations, new ArrayList<>());

        return combinations;
    }

    private void createCombinations(int[] candidates, int target, int index, List<List<Integer>> combinations, List<Integer> path) {
        if (index > candidates.length - 1) {
            return;
        }

        if (target == 0) {
            combinations.add(new ArrayList<>(path));
        } else {
            for (int i = index; i < candidates.length; ++i) {
                if (candidates[i] <= target) {
                    path.add(candidates[i]);
                    createCombinations(candidates, target - candidates[i], i, combinations, path);
                    path.remove(path.size() - 1);
                }
            }
        }
    }
}