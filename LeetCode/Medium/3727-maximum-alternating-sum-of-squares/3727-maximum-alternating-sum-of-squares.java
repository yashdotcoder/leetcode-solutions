class Solution {
    public long maxAlternatingSum(int[] nums) {
        long ans = 0;
        int n = nums.length;

        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        for (int num : nums) {
            if (num < 0) num = -num;

            minHeap.add(num);
            maxHeap.add(num);
        }

        int count = 0;
        boolean even = true;

        while (count < n) {
            long min = minHeap.peek();
            long max = maxHeap.peek();

            if (even) {
                ans +=  max * max;
                maxHeap.poll();
            } else {
                ans -=  min * min;
                minHeap.poll();
            }

            even = !even;
            count++;
        } 

        return ans;
    }
}