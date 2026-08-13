class Solution {

    // Original string stored as a character array so that we can
    // easily update a character at a particular index.
    private char[] sArr;

    /*
     * For every segment-tree node:
     *
     * pre     = length of the longest same-character sequence
     *           starting from the LEFT boundary of this segment.
     *
     * suf     = length of the longest same-character sequence
     *           ending at the RIGHT boundary of this segment.
     *
     * maxLen  = longest same-character sequence anywhere
     *           inside this segment.
     */
    private int[] pre, suf, maxLen;

    /*
     * leftChar  = first character of this segment.
     * rightChar = last character of this segment.
     *
     * We need these two characters to determine whether
     * the suffix of the left child can be joined with
     * the prefix of the right child.
     */
    private char[] leftChar, rightChar;


    public int[] longestRepeating(
        String s,
        String queryCharacters,
        int[] queryIndices
    ) {

        int n = s.length();

        sArr = s.toCharArray();

        /*
         * A segment tree is stored in an array.
         *
         * We allocate 4 * n positions because a segment tree
         * for n elements needs O(n) nodes, and 4n is a safe
         * upper bound for the number of nodes.
         */
        pre = new int[4 * n];
        suf = new int[4 * n];
        maxLen = new int[4 * n];

        leftChar = new char[4 * n];
        rightChar = new char[4 * n];


        // Build the segment tree for the original string.
        build(1, 0, n - 1);


        int k = queryIndices.length;
        int[] ans = new int[k];

        /*
         * Process every query.
         *
         * Each query changes exactly one character.
         * After the update, maxLen[1] contains the answer
         * for the complete string because node 1 is the root.
         */
        for (int i = 0; i < k; i++) {

            update(
                1,
                0,
                n - 1,
                queryIndices[i],
                queryCharacters.charAt(i)
            );

            // Root represents the complete string.
            ans[i] = maxLen[1];
        }

        return ans;
    }


    /*
     * Recalculate the information of node 'u'
     * using information from its left and right children.
     *
     * This is the most important function in the solution.
     */
    private void pushUp(int u, int l, int r) {

        int mid = (l + r) >> 1;

        // Length of left and right child segments.
        int leftLen = mid - l + 1;
        int rightLen = r - mid;

        // Children of node u in the segment tree.
        int left = u << 1;
        int right = (u << 1) | 1;


        /*
         * The first character of the parent segment
         * comes from the left child.
         */
        leftChar[u] = leftChar[left];

        /*
         * The last character of the parent segment
         * comes from the right child.
         */
        rightChar[u] = rightChar[right];


        /*
         * Calculate prefix length.
         *
         * Initially, the parent's prefix is the prefix
         * of the left child.
         */
        pre[u] = pre[left];

        /*
         * The prefix can extend into the right child only when:
         *
         * 1. The ENTIRE left child is part of its prefix.
         *    Therefore pre[left] == leftLen.
         *
         * 2. The last character of LEFT == first character of RIGHT.
         *
         * Example:
         *
         * LEFT  = "aaa"
         * RIGHT = "aabb"
         *
         * Parent = "aaaaabb"
         *
         * Parent prefix = 3 + 2 = 5.
         */
        if (pre[left] == leftLen &&
            rightChar[left] == leftChar[right]) {

            pre[u] = pre[left] + pre[right];
        }


        /*
         * Calculate suffix length.
         *
         * Initially, the parent's suffix is the suffix
         * of the right child.
         */
        suf[u] = suf[right];

        /*
         * The suffix can extend into the left child only when:
         *
         * 1. The ENTIRE right child is part of its suffix.
         *    Therefore suf[right] == rightLen.
         *
         * 2. The last character of LEFT == first character of RIGHT.
         *
         * Example:
         *
         * LEFT  = "aabb"
         * RIGHT = "bbb"
         *
         * Parent = "aabbbbb"
         *
         * Parent suffix = 2 + 3 = 5.
         */
        if (suf[right] == rightLen &&
            rightChar[left] == leftChar[right]) {

            suf[u] = suf[right] + suf[left];
        }


        /*
         * The longest sequence can be:
         *
         * 1. Completely inside LEFT.
         * 2. Completely inside RIGHT.
         * 3. Crossing the boundary between LEFT and RIGHT.
         *
         * Start with the best answer from either child.
         */
        maxLen[u] = Math.max(maxLen[left], maxLen[right]);


        /*
         * Check whether a sequence crosses the boundary.
         *
         * If:
         *
         * LEFT ends with 'x'
         * RIGHT starts with 'x'
         *
         * then:
         *
         * LEFT.suffix + RIGHT.prefix
         *
         * forms one continuous sequence.
         */
        if (rightChar[left] == leftChar[right]) {

            maxLen[u] = Math.max(
                maxLen[u],
                suf[left] + pre[right]
            );
        }
    }


    /*
     * Build the segment tree recursively.
     *
     * Node u represents the interval [l, r].
     */
    private void build(int u, int l, int r) {

        /*
         * Base case:
         * This node represents exactly one character.
         */
        if (l == r) {

            // A single character has a repeating sequence of length 1.
            pre[u] = 1;
            suf[u] = 1;
            maxLen[u] = 1;

            // Both boundaries contain the same character.
            leftChar[u] = sArr[l];
            rightChar[u] = sArr[l];

            return;
        }


        // Divide the current interval into two halves.
        int mid = (l + r) >> 1;


        // Build left child: [l, mid]
        build(u << 1, l, mid);

        // Build right child: [mid + 1, r]
        build((u << 1) | 1, mid + 1, r);


        /*
         * Children are now ready.
         * Calculate the current node from its children.
         */
        pushUp(u, l, r);
    }


    /*
     * Update the character at position 'pos' to 'ch'.
     *
     * We only need to visit the nodes on the path
     * from the root to that particular position.
     */
    private void update(
        int u,
        int l,
        int r,
        int pos,
        char ch
    ) {

        /*
         * We have reached the leaf corresponding to pos.
         */
        if (l == r) {

            // Update the character stored at this leaf.
            leftChar[u] = ch;
            rightChar[u] = ch;

            return;
        }


        int mid = (l + r) >> 1;


        /*
         * Decide whether pos lies in the left or right half.
         */
        if (pos <= mid) {

            // Position is in [l, mid].
            update(u << 1, l, mid, pos, ch);

        } else {

            // Position is in [mid + 1, r].
            update((u << 1) | 1, mid + 1, r, pos, ch);
        }


        /*
         * One child has changed.
         *
         * Recalculate the current node using the updated
         * information from both children.
         */
        pushUp(u, l, r);
    }
}