class Solution {
    public int[] searchRange(int[] nums, int target) {
        int[] position = new int[2];

        position[0] = binarySearch(nums, target, true);
        position[1] = binarySearch(nums, target, false);

        return position;
    }

    private int binarySearch(int[] nums, int target, boolean searchingLeft) {
        int left = 0;
        int right = nums.length - 1;
        int idx = -1;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                idx = mid;

                if (searchingLeft) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
        }

        return idx;
    }
}