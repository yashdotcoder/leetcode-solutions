class Solution {
    public String getPermutation(int n, int k) {
        StringBuilder sb = new StringBuilder();
        List<Integer> digits = new ArrayList<>();
        int fact = 1;

        for (int i = 1; i < n; ++i) {
            fact *= i;
            digits.add(i);
        }

        digits.add(n);

        k -= 1;
        
        for (int i = 0; i < n; ++i) {
            int idx = k / fact;
            sb.append(digits.get(idx));
            digits.remove(idx);

            if (k == 0) {
                continue;
            }

            k = k % fact;

            fact = fact / (n - i - 1);
        } 

        return sb.toString();
    }
}