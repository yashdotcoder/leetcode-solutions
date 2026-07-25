class Solution {

    int totalSum;
    
    public int findTargetSumWays(int[] nums, int target) {
        totalSum = Arrays.stream(nums).sum();

        int[][] memo = new int[nums.length][2 * totalSum + 1];

        for (int i = 0; i < nums.length; ++i) {
            Arrays.fill(memo[i], Integer.MIN_VALUE);
        }

        return calculateWays(nums, target, 0, 0, memo);
    }

    private int calculateWays(int[] nums, int target, int index, int sum, int[][] memo) {
        if (index == nums.length) {
            if (sum == target) {
                return 1;
            }
            return 0;
        }

        if (memo[index][sum + totalSum] != Integer.MIN_VALUE) {
            return memo[index][sum + totalSum];
        }

        int add =  calculateWays(nums, target , index + 1, sum - nums[index], memo);
        
        int subtract = calculateWays(nums, target, index + 1, sum + nums[index], memo);

        return memo[index][sum + totalSum] = add + subtract;
    } 
}