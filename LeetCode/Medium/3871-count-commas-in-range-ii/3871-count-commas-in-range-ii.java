class Solution {
    public long countCommas(long n) {
        long res = 0;
        for (long p = 1000; p <= n; p *= 1000)
            res += n - p + 1;

        return res;
    }
}