class Solution {

    // Check if we can place 'm' balls at 'position'
    // with each ball having at least 'distance' gap.
    private boolean canPlaceBalls(int[] position, int distance, int m) {
        // Place the first ball at first position
        int ballsPlaced = 1;
        int prevPos = position[0];

        // Iterate on each position and place a ball there is we can place it.
        for (int i = 1; i < position.length; ++i) {
            int currPos = position[i];

            if (currPos - prevPos >= distance) {
                // Increment the ballsPlaced and update the prevPos
                ballsPlaced++;
                prevPos = position[i];
            }
        }

        // If we can place balls greater than required balls
        return ballsPlaced >= m;
    }


    public int maxDistance(int[] position, int m) {
        Arrays.sort(position);
        int N = position.length;

        // Initial search space
        int low = 1;
        int high = (int) Math.ceil((position[N - 1] - position[0]) * 1.0 / (m - 1));
        int ans = 0;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            // If we can place all balls having a gap at least 'mid'
            if (canPlaceBalls(position, mid, m)) {
                // mid is our answer
                ans = mid;

                // discard the left search space
                low = mid + 1;
            } else {

                // discard the right half search space
                high = mid - 1;
            }
        }

        return ans;
    }
}