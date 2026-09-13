class Solution {

    public int largestOverlap(int[][] img1, int[][] img2) {

        int n = img1.length;

        // Store coordinates of all 1s in img1.
        List<int[]> ones = new ArrayList<>();

        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (img1[r][c] == 1) {
                    ones.add(new int[]{r, c});
                }
            }
        }

        int maxOverlap = 0;

        /*
         * Try every possible vertical shift.
         *
         * dr =  -(n - 1)  -> move up as much as possible
         * dr =  0         -> no vertical movement
         * dr =  +(n - 1)  -> move down as much as possible
         */
        for (int dr = -(n - 1); dr <= n - 1; dr++) {

            /*
             * Try every possible horizontal shift.
             *
             * dc < 0 -> move left
             * dc = 0 -> no horizontal movement
             * dc > 0 -> move right
             */
            for (int dc = -(n - 1); dc <= n - 1; dc++) {

                int overlap = 0;

                // Check where every 1 from img1 lands after this shift.
                for (int[] cell : ones) {

                    int r = cell[0];
                    int c = cell[1];

                    // Position of this 1 after applying the translation.
                    int newRow = r + dr;
                    int newCol = c + dc;

                    // If it moves outside img2, it cannot overlap.
                    if (newRow < 0 || newRow >= n ||
                        newCol < 0 || newCol >= n) {
                        continue;
                    }

                    // If img2 also has a 1 at this position,
                    // we have one overlap.
                    if (img2[newRow][newCol] == 1) {
                        overlap++;
                    }
                }

                // Keep the best overlap found so far.
                maxOverlap = Math.max(maxOverlap, overlap);
            }
        }

        return maxOverlap;
    }
}