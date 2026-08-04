class Solution {
    public String getPermutation(int n, int k) {
        StringBuilder ans = new StringBuilder();
        List<Integer> digits = new ArrayList<>();

        // fact stores (remainingDigits - 1)!
        int fact = 1;

        // Compute (n-1)! and initialize available digits.
        for (int i = 1; i < n; i++) {
            fact *= i;
            digits.add(i);
        }
        digits.add(n);

        // Convert to 0-based indexing.
        k--;

        // Construct the answer one digit at a time.
        for (int i = 0; i < n; i++) {

            // Each digit contributes 'fact' permutations.
            // Find the block containing the kth permutation.
            int idx = k / fact;

            // Pick that digit and remove it from the available digits.
            ans.append(digits.get(idx));
            digits.remove(idx);

            // No more updates needed after placing the last digit.
            if (i == n - 1) {
                break;
            }

            // Position of the permutation within the chosen block.
            k %= fact;

            // Update factorial for the remaining digits.
            // Example: 3! -> 2! -> 1!
            fact /= (n - i - 1);
        }

        return ans.toString();
    }
}