class Solution {
    public int maximumLengthSubstring(String s) {
        int res = 1;
        int n = s.length();
        int left = 0;
        int[] count = new int[26];

        for (int right = left; right < n; ++right) {
            int ch = s.charAt(right) - 'a';

            count[ch]++;

            while (count[ch] > 2) {
                int chLeft = s.charAt(left) - 'a';
                count[chLeft]--;
                left++;
            }

            res = Math.max(res, right - left + 1);
        }

        return res;
    }
}