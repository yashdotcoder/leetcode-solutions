class Solution {

    public int longestSubsequence(int[] nums) {

        int n = nums.length;

        // XOR of all elements in the array.
        //
        // Important XOR properties:
        // x ^ x = 0
        // x ^ 0 = x
        //
        // Therefore, if the XOR of the complete array is non-zero,
        // the complete array itself is a valid subsequence.
        int totalXor = 0;

        // Used to check whether every element is 0.
        boolean allZero = true;


        // Calculate XOR of all elements.
        for (int num : nums) {

            totalXor ^= num;

            // If we find even one non-zero element,
            // then the array is not an all-zero array.
            if (num != 0) {
                allZero = false;
            }
        }


        /*
         * CASE 1:
         * XOR of the complete array is non-zero.
         *
         * Since the complete array is itself a subsequence,
         * we can take all n elements.
         */
        if (totalXor != 0) {
            return n;
        }


        /*
         * CASE 2:
         * XOR of the complete array is zero.
         *
         * If the array contains at least one non-zero element,
         * removing ANY one non-zero element will make the XOR
         * of the remaining elements non-zero.
         *
         * Why?
         *
         * Suppose:
         *
         *     a ^ b ^ c = 0
         *
         * Then:
         *
         *     a ^ b = c
         *
         * If we remove c:
         *
         *     a ^ b = c != 0
         *
         * Therefore, we can always construct a valid
         * subsequence of length n - 1.
         */
        if (!allZero) {
            return n - 1;
        }


        /*
         * CASE 3:
         * Every element is zero.
         *
         * XOR of any non-empty subsequence will still be zero:
         *
         *     0 ^ 0 ^ 0 = 0
         *
         * Therefore, there is no valid non-empty subsequence.
         *
         * Answer = 0.
         */
        return 0;
    }
}