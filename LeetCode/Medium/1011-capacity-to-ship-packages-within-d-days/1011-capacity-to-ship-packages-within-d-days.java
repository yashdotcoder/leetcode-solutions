class Solution {

    private int findTotalWeight(int[] weights) {
        int sum = 0;
        for (int weight: weights) {
            sum += weight;
        }
        return sum;
    }

    private boolean isShippingPossible(int capacity, int[] weights, int days) {
        int totalDays = 1;
        int currentWeights = 0;

        for (int i = 0; i < weights.length; ++i) {
            if (currentWeights + weights[i] <= capacity) {
                currentWeights += weights[i];
            } else if (weights[i] > capacity) {
                return false;
            }
            else {
                totalDays++;
                currentWeights = weights[i];
            }
        }

        return totalDays <= days;
    }

    public int shipWithinDays(int[] weights, int days) {
        int low  =  Arrays.stream(weights).max().getAsInt();
        int high = findTotalWeight(weights);

        int leastWeightCapacity = 0;

        while (low <= high) {
            int possibleWeight = (low + high) >> 1;

            if (isShippingPossible(possibleWeight, weights, days)) {
                leastWeightCapacity = possibleWeight;
                high = possibleWeight - 1;
            } else {
                low = possibleWeight + 1;
            }
        }

        return leastWeightCapacity;
    }
}