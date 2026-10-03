class Solution {
    public int longestConsecutive(int[] nums) {
        // HashSet gives O(1) average-time lookup for checking
        // whether a number exists in the array.
        Set<Integer> numSet = new HashSet<>();

        for (int num : nums) {
            numSet.add(num);
        }

        int longestStreak = 0;

        // Iterate over unique numbers instead of the original array.
        // This automatically avoids processing duplicate values.
        for (int num : numSet) {

            // A number can be the START of a consecutive sequence
            // only if its previous number does not exist.
            //
            // Example:
            // 1 -> start of 1,2,3,4
            // 2 -> not a start because 1 exists
            if (!numSet.contains(num - 1)) {

                int currentNum = num;
                int currentStreak = 1;

                // Keep extending the sequence while the next number exists.
                while (numSet.contains(currentNum + 1)) {
                    currentNum++;
                    currentStreak++;
                }

                longestStreak = Math.max(longestStreak, currentStreak);
            }
        }

        return longestStreak;
    }
}