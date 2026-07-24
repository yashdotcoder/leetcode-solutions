class Solution {
    private List<List<Integer>> permutations = new ArrayList<>();

    public List<List<Integer>> permute(int[] nums) {
        
        recurse(nums, 0);

        return permutations;
    }

    private void recurse(int[] nums, int start) {
        if (start == nums.length) {
            permutations.add(Arrays.stream(nums).boxed().toList());
            return;
        }

        for (int i = start; i < nums.length; ++i) {
            swap(nums, i, start);
            recurse(nums, start + 1);
            swap(nums, i, start);
        }
    }

    private void swap(int[] nums, int idx1, int idx2) {
        int temp = nums[idx1];
        nums[idx1] = nums[idx2];
        nums[idx2] = temp;
    }


    /*
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
    */
}