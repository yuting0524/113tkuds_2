import java.util.*;

public class TreePathProblems {

    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    public static void main(String[] args) {
        /*
                10
              /    \
             5      12
            / \       \
           3   7      15
        */
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(12);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(7);
        root.right.right = new TreeNode(15);

        System.out.println("1️⃣ 所有根到葉路徑：");
        List<List<Integer>> allPaths = new ArrayList<>();
        findPaths(root, new ArrayList<>(), allPaths);
        for (List<Integer> path : allPaths) System.out.println(path);

        int targetSum = 22;
        System.out.println("\n2️⃣ 是否存在和為 " + targetSum + " 的路徑？ " + hasPathSum(root, targetSum));

        System.out.println("\n3️⃣ 最大總和根到葉路徑總和：" + maxRootToLeafSum(root));

        System.out.println("\n4️⃣ 任意兩節點最大路徑總和（樹的直徑）：" + maxPathSum(root));
    }

    // 1. 所有根到葉路徑
    public static void findPaths(TreeNode node, List<Integer> current, List<List<Integer>> result) {
        if (node == null) return;
        current.add(node.val);
        if (node.left == null && node.right == null) {
            result.add(new ArrayList<>(current));
        } else {
            findPaths(node.left, current, result);
            findPaths(node.right, current, result);
        }
        current.remove(current.size() - 1);
    }

    // 2. 是否存在和為 target 的根到葉路徑
    public static boolean hasPathSum(TreeNode root, int target) {
        if (root == null) return false;
        if (root.left == null && root.right == null)
            return root.val == target;
        return hasPathSum(root.left, target - root.val) || hasPathSum(root.right, target - root.val);
    }

    // 3. 最大根到葉總和
    public static int maxRootToLeafSum(TreeNode root) {
        if (root == null) return Integer.MIN_VALUE;
        if (root.left == null && root.right == null) return root.val;
        return root.val + Math.max(maxRootToLeafSum(root.left), maxRootToLeafSum(root.right));
    }

    // 4. 任意兩節點最大總和路徑（包含 root→leaf、leaf→leaf、跨子樹）
    static int maxSum = Integer.MIN_VALUE;

    public static int maxPathSum(TreeNode root) {
        maxSum = Integer.MIN_VALUE;
        maxPathDown(root);
        return maxSum;
    }

    private static int maxPathDown(TreeNode node) {
        if (node == null) return 0;
        int left = Math.max(0, maxPathDown(node.left));
        int right = Math.max(0, maxPathDown(node.right));
        maxSum = Math.max(maxSum, left + right + node.val);
        return node.val + Math.max(left, right);
    }
}
