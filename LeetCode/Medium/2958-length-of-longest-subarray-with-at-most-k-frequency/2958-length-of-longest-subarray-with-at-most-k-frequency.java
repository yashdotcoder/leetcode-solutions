class Solution {
    public int maxSubarrayLength(int[] nums, int k) {

        // Stores the frequency of each number in the current window [left ... right].
        Map<Integer, Integer> freq = new HashMap<>();

        // Stores the maximum length of a valid subarray found so far.
        int ans = 1;

        // Left boundary of the sliding window.
        int left = 0;

        // Expand the window by moving the right pointer.
        for (int right = 0; right < nums.length; ++right) {

            // Add nums[right] to the current window and update its frequency.
            freq.put(nums[right], freq.getOrDefault(nums[right], 0) + 1);

            // If nums[right] occurs more than k times,
            // the current window is invalid.
            //
            // Shrink the window from the left until the frequency
            // of nums[right] becomes <= k again.
            while (freq.get(nums[right]) > k) {

                // Remove nums[left] from the current window.
                freq.put(nums[left], freq.get(nums[left]) - 1);

                // Move the left boundary forward.
                left++;
            }

            // At this point, [left ... right] is a valid subarray.
            // Calculate its length and update the maximum answer.
            ans = Math.max(ans, right - left + 1);
        }

        return ans;
    }
}