import java.util.*;

public class TreeReconstruction {

    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    public static void main(String[] args) {
        // 前序 + 中序
        int[] preorder = {3, 9, 20, 15, 7};
        int[] inorder =  {9, 3, 15, 20, 7};

        TreeNode root1 = buildFromPreIn(preorder, inorder);
        System.out.println("1️⃣ 前序+中序重建後中序輸出：");
        printInOrder(root1);
        System.out.println();

        // 後序 + 中序
        int[] postorder = {9, 15, 7, 20, 3};
        TreeNode root2 = buildFromPostIn(postorder, inorder);
        System.out.println("2️⃣ 後序+中序重建後中序輸出：");
        printInOrder(root2);
        System.out.println();

        // 層序重建（完全二元樹）
        Integer[] levelOrder = {1, 2, 3, 4, 5, 6, 7};
        TreeNode root3 = buildFromLevelOrder(levelOrder);
        System.out.println("3️⃣ 層序重建後中序輸出：");
        printInOrder(root3);
    }

    // 1. 從前序 + 中序重建
    public static TreeNode buildFromPreIn(int[] preorder, int[] inorder) {
        return buildPreIn(preorder, 0, preorder.length - 1,
                          inorder, 0, inorder.length - 1);
    }

    private static TreeNode buildPreIn(int[] pre, int ps, int pe, int[] in, int is, int ie) {
        if (ps > pe) return null;
        TreeNode root = new TreeNode(pre[ps]);
        int index = is;
        while (in[index] != pre[ps]) index++;
        int leftSize = index - is;

        root.left = buildPreIn(pre, ps + 1, ps + leftSize, in, is, index - 1);
        root.right = buildPreIn(pre, ps + leftSize + 1, pe, in, index + 1, ie);
        return root;
    }

    // 2. 從後序 + 中序重建
    public static TreeNode buildFromPostIn(int[] postorder, int[] inorder) {
        return buildPostIn(postorder, 0, postorder.length - 1,
                           inorder, 0, inorder.length - 1);
    }

    private static TreeNode buildPostIn(int[] post, int ps, int pe, int[] in, int is, int ie) {
        if (ps > pe) return null;
        TreeNode root = new TreeNode(post[pe]);
        int index = is;
        while (in[index] != post[pe]) index++;
        int leftSize = index - is;

        root.left = buildPostIn(post, ps, ps + leftSize - 1, in, is, index - 1);
        root.right = buildPostIn(post, ps + leftSize, pe - 1, in, index + 1, ie);
        return root;
    }

    // 3. 從層序重建完全二元樹（含 null）
    public static TreeNode buildFromLevelOrder(Integer[] level) {
        if (level.length == 0 || level[0] == null) return null;
        TreeNode root = new TreeNode(level[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int i = 1;

        while (i < level.length) {
            TreeNode current = queue.poll();
            if (level[i] != null) {
                current.left = new TreeNode(level[i]);
                queue.offer(current.left);
            }
            i++;
            if (i < level.length && level[i] != null) {
                current.right = new TreeNode(level[i]);
                queue.offer(current.right);
            }
            i++;
        }
        return root;
    }

    // 輔助：中序印樹
    public static void printInOrder(TreeNode node) {
        if (node == null) return;
        printInOrder(node.left);
        System.out.print(node.val + " ");
        printInOrder(node.right);
    }
}
