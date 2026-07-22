class Solution {
    private List<List<Integer>> combinations = new ArrayList<>();

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {

        Arrays.sort(candidates);

        backTrack(candidates, target, 0, new ArrayList<>());    

        return combinations;
    }

    private void backTrack(int[] candidates, int target, int start, List<Integer> path) {
        if (target == 0) {
            combinations.add(new ArrayList<Integer>(path));
            return;
        }

        for (int i = start; i < candidates.length; ++i) {

            if (i > start && candidates[i] == candidates[i - 1]) {
                continue;
            }

            if (candidates[i] > target) {
                break;
            }

            path.add(candidates[i]);

            backTrack(candidates, target - candidates[i], i + 1, path);

            path.remove(path.size() - 1);
        }
    }
}