/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    private int cnt = 0;

    private int[] findValidNodes(TreeNode root) {
        if (root == null) {
            return new int[]{0, 0};
        }

        int leftInfo[]  = findValidNodes(root.left);
        int rightInfo[] = findValidNodes(root.right);

        int nodesLeft   = leftInfo[0];
        int sumLeft = leftInfo[1];

        int nodesRight   = rightInfo[0];
        int sumRight = rightInfo[1];

        int totalNodes = 1 + nodesLeft + nodesRight;

        int totalSum = (root.val + sumLeft + sumRight);

        if (root.val == Math.floor(totalSum * 1.0 / totalNodes)) {
            cnt++;
        }

        return new int[] {totalNodes, totalSum};
    }

    public int averageOfSubtree(TreeNode root) {
        findValidNodes(root);
        return cnt;    
    }
}