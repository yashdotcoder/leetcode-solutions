class Solution {
    public int minAddToMakeValid(String s) {
        int res = 0;
        int formed = 0;

        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) == '(') {
                formed++;
            } else {
                formed--;
                if (formed < 0) {
                    res++;
                    formed = 0;
                }
            }
        }

        res += formed;
        
        return res;
    }
}