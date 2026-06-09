class Solution {

   /*
    * Binary Search on Rotated Sorted Array
    *
    * Key Insight:
    * In every iteration, at least one half of the current search
    * space is sorted. Identify the sorted half and check whether
    * the target lies within its range. Discard the other half.
    *
    * Time Complexity: O(log n)
    * Space Complexity: O(1)
    */
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            // Target found
            if (nums[mid] == target) {
                return mid;
            }

            /*
             * At least one half of the array is always sorted.
             *
             * Example:
             * [4,5,6,7,0,1,2]
             *
             * If nums[left] <= nums[mid], then the left half
             * [left ... mid] is sorted.
             */
            if (nums[left] <= nums[mid]) {

                /*
                 * Check whether target lies within the sorted
                 * left half.
                 *
                 * Since the left half is sorted, a simple range
                 * check tells us whether the target can be there.
                 */
                if (target >= nums[left] && target < nums[mid]) {
                    // Search in left half
                    right = mid - 1;
                } else {
                    // Target cannot be in left half
                    left = mid + 1;
                }
            } else {

                /*
                 * Left half is not sorted, therefore the right
                 * half [mid ... right] must be sorted.
                 */
                if (target > nums[mid] && target <= nums[right]) {
                    // Search in right half
                    left = mid + 1;
                } else {
                    // Target cannot be in right half
                    right = mid - 1;
                }
            }
        }

        // Target not present
        return -1;
    }
}