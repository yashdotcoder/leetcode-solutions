class Solution {
    
    // =========================================================================
    // APPROACH 1: SWAPPING (IN-PLACE BACKTRACKING)
    // Space Optimization: O(1) extra space (excluding recursion stack) because 
    // it mutates the input array directly instead of tracking visited states.
    // =========================================================================
    
    private List<List<Integer>> permutations = new ArrayList<>();

    public List<List<Integer>> permute(int[] nums) {
        // Start the recursive swap from index 0
        recurse(nums, 0);
        return permutations;
    }

    private void recurse(int[] nums, int start) {
        // Base Case: If the start pointer reaches the end, a valid permutation is formed.
        if (start == nums.length) {
            // Box primitive ints to Integer objects and capture a snapshot copy of the array.
            permutations.add(Arrays.stream(nums).boxed().toList());
            return;
        }

        // 'start' is the specific slot we are currently trying to fill.
        // 'i' scans all available elements from 'start' to the end of the array.
        for (int i = start; i < nums.length; ++i) {
            
            // 1. PLACE: Swap the candidate element at index 'i' into the 'start' slot.
            swap(nums, i, start);
            
            // 2. EXPLORE: Move to the next slot (start + 1) to continue building.
            recurse(nums, start + 1);
            
            // 3. UNPLACE (Backtrack): Swap back to restore the array to its original state.
            // This ensures subsequent loop iterations start with a clean slate.
            swap(nums, i, start);
        }
    }

    private void swap(int[] nums, int idx1, int idx2) {
        int temp = nums[idx1];
        nums[idx1] = nums[idx2];
        nums[idx2] = temp;
    }


    // =========================================================================
    // APPROACH 2: FREQUENCY / VISITED ARRAY BACKTRACKING (Your Commented Code)
    // Intuition: Builds a "collection" from scratch. It checks all elements 
    // from index 0 to N every time, but uses a frequency map to skip already-used items.
    // =========================================================================
    
    /*
    public List<List<Integer>> permuteWithFreq(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        // Tracks whether an element at index 'i' is already part of the current permutation
        int[] freq = new int[nums.length]; 
        
        recurseWithFreq(nums, freq, new ArrayList<>(), ans);
        return ans;
    }

    private void recurseWithFreq(int[] nums, int[] freq, List<Integer> collection, List<List<Integer>> ans) {
        // Base Case: If the collection size matches the input array size, 
        // we have gathered enough elements for a complete permutation.
        if (collection.size() == nums.length) {
            // We must create a deep copy (new ArrayList) because 'collection' 
            // will continue to change during backtracking.
            ans.add(new ArrayList<>(collection));
            return;
        }

        // Scan all possible elements in the array from scratch (always index 0)
        for (int i = 0; i < nums.length; ++i) {
            // If the element at index 'i' has not been used yet in this path
            if (freq[i] == 0) {
                
                // 1. PLACE: Mark it as used and append it to our active collection
                freq[i] = 1;
                collection.add(nums[i]);
                
                // 2. EXPLORE: Recursively add more elements to the remaining slots
                recurseWithFreq(nums, freq, collection, ans);
                
                // 3. UNPLACE (Backtrack): Revert choices for the next branch
                freq[i] = 0;                              // Unmark index 'i'
                collection.remove(collection.size() - 1); // Remove the last added element
            }
        }
    }
    */
}