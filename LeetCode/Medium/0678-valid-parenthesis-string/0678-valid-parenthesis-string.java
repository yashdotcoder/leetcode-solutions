class Solution {
    public boolean checkValidString(String s) {
        int formed = 0;
        int wildCards = 0;

        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            if (c == '(') {
                formed++;
            } else if (c == ')') {
                formed--;
            } else {
                wildCards++;
            }

            if (formed < 0) {
                if (wildCards == 0) {
                    return false;
                }
                wildCards--;
                formed = 0;
            }
        }

        if (formed > wildCards) {
            return false;
        }

        formed = wildCards = 0;

        for (int i = s.length() - 1; i >= 0; --i) {
            char c = s.charAt(i);

            if (c == '(') {
                formed--;
            } else if (c == ')') {
                formed++;
            } else {
                wildCards++;
            }

            if (formed < 0) {
                if (wildCards == 0) {
                    return false;
                }
                wildCards--;
                formed = 0;
            }
        }

        if (formed > wildCards) {
            return false;
        }

        return true;
    }
}