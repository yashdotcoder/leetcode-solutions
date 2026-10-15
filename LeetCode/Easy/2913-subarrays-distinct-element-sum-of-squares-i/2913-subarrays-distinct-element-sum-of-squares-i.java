class Solution {
    public int sumCounts(List<Integer> nums) {
        int ans = 0;
        

        for (int i = 0; i < nums.size(); ++i) {
            int unique = 0;
            int[] count = new int[101];
            for (int j = i; j < nums.size(); ++j) {
                count[nums.get(j)] = 1;

                for (int num = 1; num <= 100; ++num) {
                    if (count[num] == 1) {
                            unique++;
                    }
                }
                
                ans += unique * unique;
                unique = 0;
            }


            
        }

        return ans;
    }
}