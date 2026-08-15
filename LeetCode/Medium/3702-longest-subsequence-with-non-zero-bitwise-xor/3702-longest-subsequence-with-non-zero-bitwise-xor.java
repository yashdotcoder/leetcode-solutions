class Solution {
    public int longestSubsequence(int[] nums) {
        int res = 0;

        for (int bit = 0; bit < 32; ++bit) {
            int bit1 = 0, bit0 = 0;
            int count = 0;
        
            for (int i = 0; i < nums.length; ++i) {
                boolean isSet = (nums[i] & (1 << bit)) != 0;
                if (isSet) {
                    bit1++;
                } else {
                    bit0++;
                }
            }

            if (bit1 != 0) {
                if (bit1 % 2 == 0) {
                    count = bit1 - 1 + bit0;
                } else {
                    count = bit1 + bit0;
                }
            }

            res = Math.max(res, count);
        }

        return res;
    }
}