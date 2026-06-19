class Solution {

    /**
     * Maximum possible ship capacity:
     * Ship all packages in a single day.
     */
    private int findTotalWeight(int[] weights) {
        int sum = 0;
        for (int weight : weights) {
            sum += weight;
        }
        return sum;
    }

    /**
     * Checks whether all packages can be shipped within the given number of days
     * using the provided ship capacity.
     *
     * Greedy Strategy:
     * - Load packages in order until the next package exceeds capacity.
     * - Start a new day and continue.
     *
     * If any package itself exceeds the capacity, shipping is impossible.
     */
    private boolean isShippingPossible(int capacity, int[] weights, int days) {
        int totalDays = 1;
        int currentWeights = 0;

        for (int i = 0; i < weights.length; ++i) {

            // Keep loading packages for the current day.
            if (currentWeights + weights[i] <= capacity) {
                currentWeights += weights[i];
            }

            // A single package cannot fit into the ship.
            else if (weights[i] > capacity) {
                return false;
            }

            // Start a new day and load the current package.
            else {
                totalDays++;
                currentWeights = weights[i];
            }
        }

        return totalDays <= days;
    }

    public int shipWithinDays(int[] weights, int days) {

        /**
         * Search Space:
         *
         * Minimum capacity = max(weights)
         * - Ship must be able to carry the heaviest package.
         *
         * Maximum capacity = sum(weights)
         * - Ship everything in one day.
         */
        int low = Arrays.stream(weights).max().getAsInt();
        int high = findTotalWeight(weights);

        /**
         * Binary Search on Answer.
         *
         * Capacity -> Shipping Possible?
         *
         * Example:
         * Capacity : 10 11 12 13 14 15 16
         * Possible :  F  F  F  T  T  T  T
         *
         * We need the FIRST capacity that returns true.
         */
        int leastWeightCapacity = 0;

        while (low <= high) {
            int possibleWeight = (low + high) >> 1;

            if (isShippingPossible(possibleWeight, weights, days)) {

                /**
                 * Current capacity works.
                 * Store it as a candidate answer and try
                 * finding a smaller valid capacity.
                 */
                leastWeightCapacity = possibleWeight;
                high = possibleWeight - 1;
            } else {

                /**
                 * Current capacity is insufficient.
                 * Search in larger capacities.
                 */
                low = possibleWeight + 1;
            }
        }

        return leastWeightCapacity;
    }
}