class Solution {
    public List<List<Integer>> threeSum(int[] nums) {

        // Sorting enables:
        // 1. Two-pointer approach
        // 2. Easy duplicate skipping
        Arrays.sort(nums);

        List<List<Integer>> ans = new ArrayList<>();
        int n = nums.length;

        // Fix nums[i] as the first element of the triplet.
        // We need at least two elements after i, hence i < n - 2.
        for (int i = 0; i < n - 2; ++i) {

            // Skip duplicate values for the fixed element.
            // Otherwise, we would generate the same triplets again.
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            // We need:
            // nums[i] + nums[left] + nums[right] = 0
            //
            // Therefore:
            // nums[left] + nums[right] = -nums[i]
            int sum = -nums[i];

            int left = i + 1;
            int right = n - 1;

            // Search for a pair whose sum equals -nums[i].
            while (left < right) {

                if (sum > nums[left] + nums[right]) {

                    // Current pair sum is too small.
                    // Since the array is sorted, moving left forward
                    // increases nums[left] and therefore increases the pair sum.
                    left++;

                } else if (sum < nums[left] + nums[right]) {

                    // Current pair sum is too large.
                    // Moving right backward decreases nums[right]
                    // and therefore decreases the pair sum.
                    right--;

                } else {

                    // Found a valid triplet.
                    ans.add(Arrays.asList(
                        nums[i],
                        nums[left],
                        nums[right]
                    ));

                    // Move both pointers because this pair has already
                    // been used for the current nums[i].
                    left++;
                    right--;

                    // Skip duplicate left values.
                    // Otherwise, the same triplet could be added again.
                    while (left < right && nums[left] == nums[left - 1]) {
                        left++;
                    }
                }
            }
        }

        return ans;
    }
}