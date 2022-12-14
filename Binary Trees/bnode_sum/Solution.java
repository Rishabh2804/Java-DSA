package bnode_sum;

public class Solution {

  public static int getSum(BinaryTreeNode<Integer> root) {
    if (root == null)
      return 0;

    return root.data + getSum(root.left) + getSum(root.right);
  }

}
