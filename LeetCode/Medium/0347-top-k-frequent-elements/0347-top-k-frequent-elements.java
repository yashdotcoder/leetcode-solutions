class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        // Step 1: Frequency map
        Map<Integer, Integer> freqMap = new HashMap<>();

        for (int num: nums) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }
        
        // Step 2: Min Heap based on frequency
        PriorityQueue<Map.Entry<Integer, Integer>> minHeap =
            new PriorityQueue<>(Comparator.comparingInt(Map.Entry::getValue));

        // Step 3: Keep only top k elements in heap
        for (Map.Entry<Integer, Integer> entry: freqMap.entrySet()) {
            minHeap.offer(entry);

            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }

        // Step 4: Build result array
        int [] result = new int[k];

        for (int i = 0; i < k; ++i) {
            result[i] = minHeap.poll().getKey();
        }

        return result;
    }
}