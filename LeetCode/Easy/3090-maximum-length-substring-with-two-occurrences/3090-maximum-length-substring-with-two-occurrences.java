class Solution {
    public int maximumLengthSubstring(String s) {
        int ans = 1;

        for (int i = 0; i < s.length(); ++i) {
            int[] freq = new int[26];
            char a = s.charAt(i);

            freq[a - 'a'] = 1;

            for (int j = i + 1; j < s.length(); ++j) {
                char b = s.charAt(j);
                freq[b - 'a']++;

                if (isValid(freq)) {
                    ans = Math.max(ans, j - i + 1);
                } else {
                    break;
                }
            }

            Arrays.fill(freq, 0);
        }

        return ans;
    }

    private boolean isValid(int[] freq) {
        for (int i = 0; i < 26; ++i) {
            if (freq[i] > 2) {
                return false;
            }
        }

        return true;
    }
}