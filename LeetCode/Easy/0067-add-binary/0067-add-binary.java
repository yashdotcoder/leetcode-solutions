class Solution {
    public String addBinary(String a, String b) {
        if (b.length() > a.length()) {
            return addBinary(b, a);
        }

        int carry = 0;
        int indexA = a.length() - 1;
        StringBuilder sb = new StringBuilder();

        for (int i = b.length() - 1; i >= 0; --i, indexA--) {
            int digitB = b.charAt(i) - '0';
            int digitA = a.charAt(indexA) - '0';

            int digitSb = digitA + digitB + carry;

            if (digitSb >= 2) {
                carry = 1;
            } else {
                carry = 0;
            }

            sb.append(digitSb % 2);
        }

        // Append remaining digits of a
         while (indexA >= 0) {
            int digitA = a.charAt(indexA) - '0';
            int digitSb = digitA + carry;

            if (digitSb >= 2) {
                carry = 1;
            } else {
                carry = 0;
            }

            sb.append(digitSb % 2);
            indexA--;
        }

        if (carry != 0) {
            sb.append(carry);
        }

        return sb.reverse().toString();
    }
}