class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        PriorityQueue<Pair<Integer, Integer>> pq = new PriorityQueue<>((p1, p2) -> p1.getValue() - p2.getValue());
        Map<Integer, Integer> freq = new HashMap<>();
        for (int num: nums) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }

        for (Map.Entry<Integer, Integer> mp: freq.entrySet()) {
            pq.offer(new Pair(mp.getKey(), mp.getValue()));

            if (pq.size() > k) {
                pq.poll();
            }
        }

        int[] ans = new int[k--];

        while (!pq.isEmpty()) {
            ans[k--] = pq.poll().getKey();
        }


        return ans;
    }
}