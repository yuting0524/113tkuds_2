import java.util.*;

public class BinaryTreeBasicOperations {

    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {
        // 建立測試樹：
        //       10
        //      /  \
        //     5    15
        //    / \     \
        //   2   7     20
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(15);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(7);
        root.right.right = new TreeNode(20);

        System.out.println("1️⃣ 節點總和：" + sum(root));
        System.out.println("   平均值：" + average(root));

        System.out.println("\n2️⃣ 最大值：" + findMax(root));
        System.out.println("   最小值：" + findMin(root));

        System.out.println("\n3️⃣ 樹的最大寬度：" + getWidth(root));

        System.out.println("\n4️⃣ 是否為完全二元樹？ " + isCompleteTree(root));
    }

    // 1. 節點總和
    public static int sum(TreeNode root) {
        if (root == null) return 0;
        return root.val + sum(root.left) + sum(root.right);
    }

    // 平均值 = 總和 / 節點數
    public static double average(TreeNode root) {
        int[] result = nodeCountAndSum(root);
        return (double) result[1] / result[0];
    }

    private static int[] nodeCountAndSum(TreeNode node) {
        if (node == null) return new int[]{0, 0};
        int[] left = nodeCountAndSum(node.left);
        int[] right = nodeCountAndSum(node.right);
        int count = 1 + left[0] + right[0];
        int sum = node.val + left[1] + right[1];
        return new int[]{count, sum};
    }

    // 2. 最大與最小值
    public static int findMax(TreeNode root) {
        if (root == null) return Integer.MIN_VALUE;
        return Math.max(root.val, Math.max(findMax(root.left), findMax(root.right)));
    }

    public static int findMin(TreeNode root) {
        if (root == null) return Integer.MAX_VALUE;
        return Math.min(root.val, Math.min(findMin(root.left), findMin(root.right)));
    }

    // 3. 最大寬度（使用 BFS）
    public static int getWidth(TreeNode root) {
        if (root == null) return 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int maxWidth = 0;

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            maxWidth = Math.max(maxWidth, levelSize);

            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
        }

        return maxWidth;
    }

    // 4. 判斷是否為完全二元樹
    public static boolean isCompleteTree(TreeNode root) {
        if (root == null) return true;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean end = false;

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node == null) {
                end = true;
            } else {
                if (end) return false;
                queue.offer(node.left);
                queue.offer(node.right);
            }
        }

        return true;
    }
}
