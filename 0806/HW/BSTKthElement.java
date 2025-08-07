import java.util.*;

public class BSTKthElement {

    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    public static void main(String[] args) {
        /*
               20
             /    \
           10      30
          /  \    /  \
         5   15  25  35
        */
        TreeNode root = new TreeNode(20);
        root.left = new TreeNode(10);
        root.right = new TreeNode(30);
        root.left.left = new TreeNode(5);
        root.left.right = new TreeNode(15);
        root.right.left = new TreeNode(25);
        root.right.right = new TreeNode(35);

        System.out.println("1️⃣ 第 3 小的元素：" + kthSmallest(root, 3));
        System.out.println("2️⃣ 第 2 大的元素：" + kthLargest(root, 2));
        System.out.println("3️⃣ 第 2 小到第 5 小的元素：" + getRange(root, 2, 5));

        // 4️⃣ 動態插入 + 查詢
        root = insert(root, 12);
        root = insert(root, 17);
        System.out.println("4️⃣ 新增後的第 4 小元素：" + kthSmallest(root, 4));
    }

    // 1. 第 k 小
    public static int kthSmallest(TreeNode root, int k) {
        List<Integer> inorder = new ArrayList<>();
        inOrder(root, inorder);
        return inorder.get(k - 1);
    }

    // 2. 第 k 大
    public static int kthLargest(TreeNode root, int k) {
        List<Integer> inorder = new ArrayList<>();
        inOrder(root, inorder);
        return inorder.get(inorder.size() - k);
    }

    // 3. k 小到 j 小之間元素
    public static List<Integer> getRange(TreeNode root, int k, int j) {
        List<Integer> inorder = new ArrayList<>();
        inOrder(root, inorder);
        return inorder.subList(k - 1, j); // k-1 ~ j-1
    }

    // 中序走訪
    public static void inOrder(TreeNode node, List<Integer> result) {
        if (node == null) return;
        inOrder(node.left, result);
        result.add(node.val);
        inOrder(node.right, result);
    }

    // 4. 插入（動態支援 insert）
    public static TreeNode insert(TreeNode root, int val) {
        if (root == null) return new TreeNode(val);
        if (val < root.val) root.left = insert(root.left, val);
        else root.right = insert(root.right, val);
        return root;
    }
}
