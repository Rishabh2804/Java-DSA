package segment_tree;

import java.util.Scanner;

public class SegmentTreeArray {

    static int[] tree;

    interface SegmentEvaluator {
        static int NOT_FOUND = Integer.MAX_VALUE;

        private static int evaluate(int left, int right) {
            return Math.min(left, right);
        }
    }

    public static void build(int[] arr) {
        tree = new int[4 * arr.length];
        build(0, 0, arr.length - 1, arr);
    }

    private static void build(int index, int low, int high, int[] arr) {
        if (low == high) {
            tree[index] = arr[low];
            return;
        }

        int mid = (low + high) / 2;
        build(2 * index + 1, low, mid, arr);
        build(2 * index + 2, mid + 1, high, arr);

        tree[index] = SegmentEvaluator.evaluate(tree[2 * index + 1], tree[2 * index + 2]);
    }

    public static int range(int left, int right, int n) {
        return range(0, 0, n - 1, left, right);
    }

    private static int range(int index, int low, int high, int left, int right) {
        if (right < low || left > high) // no overlap
            return Integer.MAX_VALUE;

        if (low == left && high == right) // complete overlap
            return tree[index];

        int mid = (low + high) / 2;
        int leftAns = range(2 * index + 1, low, mid, Math.max(low, left), Math.min(mid, right));
        int rightAns = range(2 * index + 2, mid + 1, high, Math.max(mid + 1, left), Math.min(high, right));

        return SegmentEvaluator.evaluate(leftAns, rightAns);
    }

    static final Scanner s = new Scanner(System.in);

    public static int[] nextArr(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < n; ++i)
            arr[i] = s.nextInt();

        return arr;
    }

    public static void main(String[] args) {

        int n = s.nextInt();
        int[] arr = nextArr(n);

        build(arr);
        int q = s.nextInt();

        for (int i = 0; i < q; ++i) {
            int l = s.nextInt();
            int r = s.nextInt();

            System.out.println(range(l, r, arr.length));
        }

    }
}
