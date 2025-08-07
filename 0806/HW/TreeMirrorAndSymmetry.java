public class TreeMirrorAndSymmetry {

    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {
        // 建立對稱樹：
        //       1
        //     /   \
        //    2     2
        //   / \   / \
        //  3   4 4   3
        TreeNode symmetricRoot = new TreeNode(1);
        symmetricRoot.left = new TreeNode(2);
        symmetricRoot.right = new TreeNode(2);
        symmetricRoot.left.left = new TreeNode(3);
        symmetricRoot.left.right = new TreeNode(4);
        symmetricRoot.right.left = new TreeNode(4);
        symmetricRoot.right.right = new TreeNode(3);

        System.out.println("1️⃣ 是否為對稱樹？ " + isSymmetric(symmetricRoot));

        System.out.println("\n2️⃣ 鏡像轉換結果（in-order）：");
        mirror(symmetricRoot);
        printInOrder(symmetricRoot); // 會印出已鏡像的結果

        // 比較兩棵是否互為鏡像
        TreeNode A = new TreeNode(1);
        A.left = new TreeNode(2);
        A.right = new TreeNode(3);

        TreeNode B = new TreeNode(1);
        B.left = new TreeNode(3);
        B.right = new TreeNode(2);

        System.out.println("\n\n3️⃣ A 和 B 是否互為鏡像？ " + isMirror(A, B));

        // 判斷是否為子樹
        TreeNode big = new TreeNode(1);
        big.left = new TreeNode(2);
        big.right = new TreeNode(3);
        big.left.left = new TreeNode(4);

        TreeNode small = new TreeNode(2);
        small.left = new TreeNode(4);

        System.out.println("\n4️⃣ small 是否為 big 的子樹？ " + isSubtree(big, small));
    }

    // 1. 判斷是否為對稱樹
    public static boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return isMirror(root.left, root.right);
    }

    // 2. 鏡像轉換
    public static void mirror(TreeNode root) {
        if (root == null) return;
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        mirror(root.left);
        mirror(root.right);
    }

    // 3. 判斷兩棵樹是否為鏡像
    public static boolean isMirror(TreeNode a, TreeNode b) {
        if (a == null && b == null) return true;
        if (a == null || b == null) return false;
        return a.val == b.val &&
               isMirror(a.left, b.right) &&
               isMirror(a.right, b.left);
    }

    // 4. 判斷是否為子樹
    public static boolean isSubtree(TreeNode root, TreeNode sub) {
        if (root == null) return false;
        if (isSame(root, sub)) return true;
        return isSubtree(root.left, sub) || isSubtree(root.right, sub);
    }

    private static boolean isSame(TreeNode a, TreeNode b) {
        if (a == null && b == null) return true;
        if (a == null || b == null) return false;
        return a.val == b.val &&
               isSame(a.left, b.left) &&
               isSame(a.right, b.right);
    }

    // 輔助：中序走訪印樹
    public static void printInOrder(TreeNode root) {
        if (root == null) return;
        printInOrder(root.left);
        System.out.print(root.val + " ");
        printInOrder(root.right);
    }
}
