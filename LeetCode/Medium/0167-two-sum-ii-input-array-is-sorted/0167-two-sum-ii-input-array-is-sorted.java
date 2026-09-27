class Solution {
    public int[] twoSum(int[] numbers, int target) {
        int n = numbers.length;

        for (int i = 0; i < n; ++i) {
            int rem = target - numbers[i];
            int left = i + 1;
            int right = n - 1;

            while (left <= right) {
                int mid = (left + right) / 2;

                if (numbers[mid] < rem) {
                    left = mid + 1;
                } else if (numbers[mid] > rem) {
                    right = mid - 1;
                } else {
                    return new int[] {i + 1, mid + 1};
                }
            }
        }

        return new int[] {-1, -1};
    }
}