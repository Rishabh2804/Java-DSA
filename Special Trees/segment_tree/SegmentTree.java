package segment_tree;

public class SegmentTree {

    SegmentTreeNode root;

    interface SegmentEvaluator {
        static int NOT_FOUND = 0;

        private static int evaluate(int left, int right) {
            return left + right;
        }
    }

    public SegmentTree(int[] arr) {
        this.root = constructTree(arr, 0, arr.length - 1);
    }

    private SegmentTreeNode constructTree(int[] arr, int si, int ei) {
        if (si == ei) {
            SegmentTreeNode leafNode = new SegmentTreeNode();
            leafNode.data = arr[si];
            leafNode.startInterval = si;
            leafNode.endInterval = ei;
            return leafNode;
        }

        SegmentTreeNode node = new SegmentTreeNode();
        node.startInterval = si;
        node.endInterval = ei;

        int mid = (si + ei) / 2;
        node.left = this.constructTree(arr, si, mid);
        node.right = this.constructTree(arr, mid + 1, ei);

        node.data = SegmentEvaluator.evaluate(node.left.data, node.right.data);
        return node;
    }

    public void display() {
        this.display(this.root);
    }

    private void display(SegmentTreeNode node) {
        String str = "";
        if (node.left != null)
            str += "Interval = [" + node.left.startInterval + "-" + node.left.endInterval + "] and Data = "
                    + node.left.data + "   =>   ";
        else
            str += "No left child   =>   ";
        str += "Interval=[" + node.startInterval + "-" + node.endInterval + "] and Data = " + node.data;
        if (node.right != null)
            str += "   <=   Interval = [" + node.right.startInterval + "-" + node.right.endInterval
                    + "] and Data = " + node.right.data;
        else
            str += "   <=   No right child";
            
        System.out.println(str);
        if (node.left != null)
            this.display(node.left);
        if (node.right != null)
            this.display(node.right);
    }

    public int query(int qsi, int qei) {
        return query(root, qsi, qei);
    }

    private int query(SegmentTreeNode node, int qsi, int qei) {
        if (node.startInterval >= qsi && node.endInterval <= qei)
            return node.data;
        else if (node.startInterval > qei || node.endInterval < qsi)
            return SegmentEvaluator.NOT_FOUND;
        else {
            int leftAns = query(node.left, qsi, qei);
            int rightAns = query(node.right, qsi, qei);
            return leftAns + rightAns;
        }
    }

    public void update(int index, int newValue) {
        this.root.data = this.update(this.root, index, newValue);
    }

    private int update(SegmentTreeNode node, int index, int newValue) {
        if (index >= node.startInterval && index <= node.endInterval) {
            if (index == node.startInterval && index == node.endInterval)
                node.data = newValue;
            else {
                int leftValue = update(node.left, index, newValue);
                int rightValue = update(node.right, index, newValue);
                node.data = leftValue + rightValue;
            }
        }
        return node.data;
    }
}
