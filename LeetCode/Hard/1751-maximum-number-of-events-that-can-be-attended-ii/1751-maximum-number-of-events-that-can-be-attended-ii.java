class Solution {
    public int maxValue(int[][] events, int k) {

        Arrays.sort(events, (a, b) -> a[1] - b[1]);

        int n = events.length;
        int[][] dp = new int[n + 1][k + 1];

        for (int event = 1; event <= n; ++event) {
            int[] currEvent = events[event - 1];
            int prev = binarySearch(events, currEvent[0]);

            for (int j = 1; j <= k; ++j) {
                dp[event][j] = Math.max(dp[event - 1][j], dp[prev + 1][j - 1] + currEvent[2]);
            } 

        }

        return dp[n][k];
    }

    private int binarySearch(int[][] events, int start) {
        int left = 0, right = events.length - 1;
        int res = -1;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (events[mid][1] < start) { // valid
                res = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return res;
    }
}