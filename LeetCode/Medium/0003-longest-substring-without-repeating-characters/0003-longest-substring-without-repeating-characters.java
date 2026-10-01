class Solution {
    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> freq = new HashMap<>();
        int left = 0, right = 0;
        int n = s.length();
        int ans = 0;

        while (right < n) {
            char c = s.charAt(right);

            int count = freq.getOrDefault(c, 0) + 1;
            
            freq.put(c, count);

            while (freq.get(c) > 1) {
                char leftC = s.charAt(left);
                count = freq.get(leftC);
                freq.put(leftC, count - 1);
                left++;
            }

            ans = Math.max(ans, right - left + 1);

            ++right;
        }

        return ans;
    }
}