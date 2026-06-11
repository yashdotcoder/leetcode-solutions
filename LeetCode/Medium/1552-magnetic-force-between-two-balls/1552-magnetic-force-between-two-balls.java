class Solution {

    boolean canPlaceBalls(int[] position, int distance, int m) {
        int placed = 1;
        int prevPos = position[0];

        for (int i = 1; i < position.length; ++i) {
            if (position[i] - prevPos >= distance) {
                placed++;
                prevPos = position[i];
            }
        }

        return placed >= m;
    }


    public int maxDistance(int[] position, int m) {
        Arrays.sort(position);
        int N = position.length;
        int low = 1;
        int high = (int) Math.ceil((position[N - 1] - position[0]) * 1.0 / (m - 1));
        int ans = 0;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (canPlaceBalls(position, mid, m)) {
                ans = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return ans;
    }
}