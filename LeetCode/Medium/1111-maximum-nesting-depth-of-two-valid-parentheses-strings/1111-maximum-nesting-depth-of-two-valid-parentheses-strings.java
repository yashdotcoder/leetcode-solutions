class Solution {
    public int[] maxDepthAfterSplit(String seq) {
        int n = seq.length();
        int[] res = new int[n];
        int open = 0;

        for (int i = 0; i < n; ++i) {
            if (seq.charAt(i) == '(') {
                open++;
                if (open % 2 == 0) {
                    res[i] = 1;
                } else {
                    res[i] = 0;
                }
            } else {
                open--;

                if (open % 2 == 0) {
                    res[i] = 0;
                } else {
                    res[i] = 1;
                }
            }
        }

        return res;
    }
}