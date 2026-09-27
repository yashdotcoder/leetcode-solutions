class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        Map<Integer, Integer> seen = new HashMap<>();
        List<Integer> res = new ArrayList<>();

        for (int i : nums1) {
            if (!seen.containsKey(i)) {
                seen.put(i, 1);
            }
        }

        for (int i : nums2) {
            if (seen.containsKey(i) && seen.get(i) == 1) {
                res.add(i);
                seen.put(i, 0);
            }
        }


        return res.stream().mapToInt(i -> i).toArray();
    }
}