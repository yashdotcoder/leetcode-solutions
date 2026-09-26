class Solution {
    public int longestSubarray(int[] nums, int k) {
        int n = nums.length;
        int res = 0;

        for (int left = 0; left < n; ++left) {
            int sum = 0;
            int freq[] = new int[k];

            for (int right = left; right < n; ++right) {
                sum = (sum + nums[right] % k) % k;

                if (sum < 0) {
                    sum += k;
                }

                int x = (2 * nums[right]) % k;

                if (x < 0) {
                    x += k;
                }

                freq[x]++;

                if (sum == 0 || freq[sum] > 0) {
                    res = Math.max(res, right - left + 1);
                }
            }
        }

        return res;
    }
}