import java.math.BigInteger;

class Solution {
    public String addBinary(String a, String b) {
        BigInteger x = new BigInteger(a, 2);
        BigInteger y = new BigInteger(b, 2);
        BigInteger carry, ans;
        BigInteger zero = new BigInteger("0", 2);

        while (y.compareTo(zero) != 0) {
            ans = x.xor(y);
            carry = x.and(y).shiftLeft(1);

            x = ans;
            y = carry;
        }

        return x.toString(2);
    }
}