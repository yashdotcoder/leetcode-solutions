class Solution {
    public boolean uniformArray(int[] nums1) {
        int minOdd = Integer.MAX_VALUE;

        for (int i = 0; i < nums1.length; ++i) {
            if (nums1[i] % 2 != 0) {
                minOdd = Math.min(nums1[i], minOdd);
            }
        }

        if (minOdd == Integer.MAX_VALUE) {
            return true;
        }

        for (int i = 0; i < nums1.length; ++i) {
            if (nums1[i] % 2 == 0 && nums1[i] < minOdd) {
                return false;
            }
        }

        return true;
    }
}