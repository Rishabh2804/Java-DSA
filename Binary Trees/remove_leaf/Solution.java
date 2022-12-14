package remove_leaf;

public class Solution {

    public static BinaryTreeNode<Integer> removeAllLeaves(BinaryTreeNode<Integer> root) {
        if (root == null || (root.left == null && root.right == null))
            return null;

        root.left = removeAllLeaves(root.left);
        root.right = removeAllLeaves(root.right);

        return root;
    }

}
