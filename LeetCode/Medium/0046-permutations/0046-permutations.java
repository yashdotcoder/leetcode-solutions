class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        int[] freq = new int[nums.length];
        recurse(nums, freq, new ArrayList<>(), ans);
        return ans;
    }

    private void recurse(int[] nums, int[] freq, List<Integer> collection, List<List<Integer>> ans) {
        if (collection.size() == nums.length) {
            ans.add(new ArrayList<>(collection));
            return;
        }

        for (int i = 0; i < nums.length; ++i) {
            if (freq[i] == 0) {
                freq[i] = 1;
                collection.add(nums[i]);
                recurse(nums, freq, collection, ans);
                freq[i] = 0;
                collection.remove(collection.size() - 1);
            }
        }
    }
    
}