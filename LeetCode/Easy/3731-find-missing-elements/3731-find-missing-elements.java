class Solution {
    public List<Integer> findMissingElements(int[] nums) {
        int min = 101;
        int max = 0;
        boolean[] arr = new boolean[101];
        List<Integer> ans = new ArrayList<>();

        for (int num: nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
            arr[num] = true;
        }

        for (int i = min; i <= max; ++i) {
            if (arr[i] == false) {
                ans.add(i);
            }
        }

        return ans;
    }
}