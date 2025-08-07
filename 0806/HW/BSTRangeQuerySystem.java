import java.util.*;

public class BSTRangeQuerySystem {

    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {
        /*
               15
             /    \
           10      25
          /  \    /  \
         5   12  20  30
        */

        TreeNode root = new TreeNode(15);
        root.left = new TreeNode(10);
        root.right = new TreeNode(25);
        root.left.left = new TreeNode(5);
        root.left.right = new TreeNode(12);
        root.right.left = new TreeNode(20);
        root.right.right = new TreeNode(30);

        int min = 10, max = 25;
        System.out.println("1️⃣ 範圍內的節點值：");
        List<Integer> range = new ArrayList<>();
        rangeQuery(root, min, max, range);
        System.out.println(range);

        System.out.println("\n2️⃣ 範圍內節點數量：" + rangeCount(root, min, max));
        System.out.println("3️⃣ 範圍內節點總和：" + rangeSum(root, min, max));

        int target = 18;
        System.out.println("\n4️⃣ 最接近 " + target + " 的節點：" + findClosest(root, target));
    }

    // 1. 範圍查詢
    public static void rangeQuery(TreeNode root, int min, int max, List<Integer> result) {
        if (root == null) return;
        if (root.val > min) rangeQuery(root.left, min, max, result);
        if (root.val >= min && root.val <= max) result.add(root.val);
        if (root.val < max) rangeQuery(root.right, min, max, result);
    }

    // 2. 範圍節點數
    public static int rangeCount(TreeNode root, int min, int max) {
        if (root == null) return 0;
        if (root.val < min) return rangeCount(root.right, min, max);
        if (root.val > max) return rangeCount(root.left, min, max);
        return 1 + rangeCount(root.left, min, max) + rangeCount(root.right, min, max);
    }

    // 3. 範圍總和
    public static int rangeSum(TreeNode root, int min, int max) {
        if (root == null) return 0;
        if (root.val < min) return rangeSum(root.right, min, max);
        if (root.val > max) return rangeSum(root.left, min, max);
        return root.val + rangeSum(root.left, min, max) + rangeSum(root.right, min, max);
    }

    // 4. 最接近值查詢
    public static int findClosest(TreeNode root, int target) {
        int closest = root.val;
        while (root != null) {
            if (Math.abs(root.val - target) < Math.abs(closest - target)) {
                closest = root.val;
            }
            root = (target < root.val) ? root.left : root.right;
        }
        return closest;
    }
}
