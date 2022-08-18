package fenwick_tree;

//Fenwick node
public class FenwickNode {
    public int value;
    public FenwickNode left;
    public FenwickNode right;
    
    public FenwickNode(int value) {
        this.value = value;
        this.left = null;
        this.right = null;
    }
}
