class Solution {
    public int maximumLengthSubstring(String s) {
        int res = 1;
        int n = s.length();

        for (int left = 0; left < n; ++left) {
            int[] count = new int[26];

            for (int right = left; right < n; ++right) {
                int ch = s.charAt(right) - 'a';
                count[ch]++;

                if (count[ch] <= 2) {
                    res = Math.max(res, right - left + 1);
                } else {
                    break;
                }
            }
        }

        return res;
    }
}