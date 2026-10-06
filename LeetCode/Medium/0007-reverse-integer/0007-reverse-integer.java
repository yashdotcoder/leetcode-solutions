class Solution {
    public int reverse(int x) {

        int rev = 0;

        while (x != 0) {

            // Extract the last digit.
            // For example: 123 -> pop = 3
            int pop = x % 10;

            // Remove the last digit from x.
            x /= 10;

            /*
             * Before doing:
             *
             *      rev = rev * 10 + pop
             *
             * we must make sure it won't overflow an int.
             *
             * INT_MAX =  2,147,483,647
             * INT_MIN = -2,147,483,648
             *
             * Think:
             * "Divide the limit by 10, then check the last digit."
             */

            // Positive overflow
            if (rev > Integer.MAX_VALUE / 10 ||
                (rev == Integer.MAX_VALUE / 10 && pop > 7)) {
                return 0;
            }

            // Negative overflow
            if (rev < Integer.MIN_VALUE / 10 ||
                (rev == Integer.MIN_VALUE / 10 && pop < -8)) {
                return 0;
            }

            // Safe to perform the operation now.
            rev = rev * 10 + pop;
        }

        return rev;
    }
}