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
 final class TreeInfo {
    public final int height;
    public final boolean isBalanced;

    TreeInfo(int height, boolean isBalanced) {
        this.height = height;
        this.isBalanced = isBalanced;
    }
 }
class Solution {

    private TreeInfo isBalancedTreeHelper(TreeNode root) {

        if (root == null) {
            return new TreeInfo(-1 , true);
        }
        
        TreeInfo left = isBalancedTreeHelper(root.left);
        TreeInfo right = isBalancedTreeHelper(root.right);
        
        int height = 1 + Math.max(left.height, right.height);
        boolean isBalanced = left.isBalanced && 
                             right.isBalanced && 
                             (Math.abs(left.height - right.height) <= 1);

        return new TreeInfo(height, isBalanced);
    }

    public boolean isBalanced(TreeNode root) {
        return isBalancedTreeHelper(root).isBalanced;    
    }
}