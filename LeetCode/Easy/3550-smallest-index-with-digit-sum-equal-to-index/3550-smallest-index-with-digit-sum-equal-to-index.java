class Solution {
    static int[] precomp;
    static int MAX_VAL = 1000;
    static {
        precomp = new int[MAX_VAL + 1];
        for (int i = 0; i <= MAX_VAL; ++i) {
            precomp[i] = precomp[i / 10] + (i % 10);
        }
    }
    public int smallestIndex(int[] nums) {
        for (int i = 0; i < nums.length; ++i) {
            int num = nums[i];

            if (precomp[num] == i) {
                return i;
            }
        }

        return -1;
    }
}