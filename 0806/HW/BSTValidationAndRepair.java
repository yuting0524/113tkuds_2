import java.util.*;

public class BSTValidationAndRepair {

    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    public static void main(String[] args) {
        /*
               10
              /  \
             5    15
                /  \
              6     20   ← 6 放錯地方（應該在左邊）
        */
        TreeNode broken = new TreeNode(10);
        broken.left = new TreeNode(5);
        broken.right = new TreeNode(15);
        broken.right.left = new TreeNode(6);  // 應為 BST 中右子樹 >10，但這裡有個 6 不合規
        broken.right.right = new TreeNode(20);

        System.out.println("1️⃣ 是否為合法 BST？ " + isValidBST(broken));

        System.out.println("\n2️⃣ 錯誤節點（中序結果）：");
        List<Integer> inOrderBefore = new ArrayList<>();
        inOrder(broken, inOrderBefore);
        System.out.println(inOrderBefore); // 顯示中序錯亂

        System.out.println("\n3️⃣ 修復 BST 中兩個錯誤節點...");
        recoverBST(broken);

        System.out.println("   修復後是否為合法 BST？ " + isValidBST(broken));
        System.out.print("   修復後中序：");
        inOrder(broken, new ArrayList<>()).forEach(val -> System.out.print(val + " "));
        
        // 進階挑戰：最少刪除節點
        TreeNode invalid = new TreeNode(10);
        invalid.left = new TreeNode(5);
        invalid.right = new TreeNode(8); // 這裡應該 >10，卻是 8 → 不合法
        System.out.println("\n\n4️⃣ 最少需移除節點數才能為 BST：" + minRemovalsToBST(invalid));
    }

    // 1. 驗證 BST
    public static boolean isValidBST(TreeNode root) {
        return validate(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private static boolean validate(TreeNode node, long min, long max) {
        if (node == null) return true;
        if (node.val <= min || node.val >= max) return false;
        return validate(node.left, min, node.val) &&
               validate(node.right, node.val, max);
    }

    // 2. 中序走訪（抓錯誤）
    public static List<Integer> inOrder(TreeNode node, List<Integer> result) {
        if (node == null) return result;
        inOrder(node.left, result);
        result.add(node.val);
        inOrder(node.right, result);
        return result;
    }

    // 3. 修復 BST（假設只有兩個節點錯誤）
    public static void recoverBST(TreeNode root) {
        TreeNode[] nodes = new TreeNode[2]; // [first, second]
        TreeNode[] prev = new TreeNode[1]; // last visited
        dfsFix(root, prev, nodes);

        if (nodes[0] != null && nodes[1] != null) {
            int tmp = nodes[0].val;
            nodes[0].val = nodes[1].val;
            nodes[1].val = tmp;
        }
    }

    private static void dfsFix(TreeNode node, TreeNode[] prev, TreeNode[] nodes) {
        if (node == null) return;
        dfsFix(node.left, prev, nodes);
        if (prev[0] != null && node.val < prev[0].val) {
            if (nodes[0] == null) nodes[0] = prev[0];
            nodes[1] = node;
        }
        prev[0] = node;
        dfsFix(node.right, prev, nodes);
    }

    // 4. 最少移除節點讓樹合法（簡化版：只考慮最長合法中序）
    public static int minRemovalsToBST(TreeNode root) {
        List<Integer> inOrder = new ArrayList<>();
        inOrder(root, inOrder);
        return inOrder.size() - longestIncreasingSubsequence(inOrder);
    }

    // 最長遞增子序列（LIS）
    private static int longestIncreasingSubsequence(List<Integer> arr) {
        List<Integer> dp = new ArrayList<>();
        for (int num : arr) {
            int i = Collections.binarySearch(dp, num);
            if (i < 0) i = -(i + 1);
            if (i == dp.size()) dp.add(num);
            else dp.set(i, num);
        }
        return dp.size();
    }
}
