class Solution {
    public int findTargetSumWays(int[] nums, int target) {
        return count(nums, target, 0);
    }

    private int count(int[] nums, int target, int index) {
        if (index == nums.length) {
            if (target == 0) {
                return 1;
            }
            return 0;
        }

        return count(nums, target - nums[index], index + 1) 
                + count(nums, target + nums[index], index + 1);

    } 
}