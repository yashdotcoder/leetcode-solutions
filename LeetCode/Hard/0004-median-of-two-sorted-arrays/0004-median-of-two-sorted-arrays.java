class Solution {

    /*
     * Problem: Median of Two Sorted Arrays
     *
     * Approach: Binary Search on Partition
     * -----------------------------------
     * Instead of merging both arrays (O(m+n)), we find a partition
     * in the two arrays such that:
     *
     *      Left Half <= Right Half
     *
     * and the left half contains exactly:
     *
     *      (m + n + 1) / 2 elements
     *
     * Key Observations:
     * 1. Perform binary search on the smaller array to achieve
     *    O(log(min(m, n))) complexity.
     *
     * 2. Let:
     *      cut1 = partition in nums1
     *      cut2 = partition in nums2
     *
     *    Then:
     *      cut1 + cut2 = (m + n + 1) / 2
     *
     * 3. Partition is valid when:
     *
     *      left1 <= right2
     *      left2 <= right1
     *
     *    where:
     *      left1  = element just left of cut1
     *      right1 = element just right of cut1
     *      left2  = element just left of cut2
     *      right2 = element just right of cut2
     *
     * 4. If left1 > right2:
     *      We have taken too many elements from nums1.
     *      Move partition left.
     *
     * 5. If left2 > right1:
     *      We have taken too few elements from nums1.
     *      Move partition right.
     *
     * Median Calculation:
     * -------------------
     * Even total elements:
     *      (max(left1, left2) + min(right1, right2)) / 2
     *
     * Odd total elements:
     *      max(left1, left2)
     *
     * Boundary Handling:
     * ------------------
     * When partition lies at array boundaries:
     *      left  -> Integer.MIN_VALUE
     *      right -> Integer.MAX_VALUE
     *
     * Time Complexity:
     *      O(log(min(m, n)))
     *
     * Space Complexity:
     *      O(1)
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }

        int left = 0;
        int right = nums1.length;
        int totalElements = nums1.length + nums2.length;

        while (left <= right) {
            int cut1 = (left + right) >> 1;
            int cut2 = ((totalElements + 1) >> 1) - cut1;

            int left1 = (cut1 == 0) ? Integer.MIN_VALUE : nums1[cut1 - 1];
            int left2 = (cut2 == 0) ? Integer.MIN_VALUE : nums2[cut2 - 1];

            int right1 = (cut1 == nums1.length) ? Integer.MAX_VALUE : nums1[cut1];
            int right2 = (cut2 == nums2.length) ? Integer.MAX_VALUE : nums2[cut2];

            if (left1 <= right2 && left2 <= right1) {
                if (totalElements % 2 == 0) {
                    return (Math.max(left1, left2) + Math.min(right1, right2)) / 2.0;
                }
                return Math.max(left1, left2);
            } else if (left1 > right2) {
                right = cut1 - 1;
            } else {
                left = cut1 + 1;
            }
        }

        return 0.0;
    }
}