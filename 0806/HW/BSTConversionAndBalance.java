public class BSTConversionAndBalance {

    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    // 用於雙向鏈結串列
    static class DoublyListNode {
        int val;
        DoublyListNode prev, next;
        DoublyListNode(int val) { this.val = val; }
    }

    public static void main(String[] args) {
        /*
                10
              /    \
             5     15
            / \      \
           2   7     20
        */
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(15);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(7);
        root.right.right = new TreeNode(20);

        System.out.println("1️⃣ BST → 雙向鏈結串列 (中序排序)：");
        DoublyListNode head = bstToDoublyList(root);
        printDoublyList(head);

        System.out.println("\n2️⃣ 排序陣列轉平衡 BST（中序印出）：");
        int[] sorted = {1, 2, 3, 4, 5, 6, 7};
        TreeNode balanced = sortedArrayToBST(sorted);
        printInOrder(balanced);
        System.out.println();

        System.out.println("3️⃣ 是否為平衡 BST？ " + isBalanced(root));

        System.out.println("\n4️⃣ 節點更新為所有大於等於自身的總和（中序）：");
        convertToGreaterSumTree(root);
        printInOrder(root);
    }

    // 1. BST → 排序的雙向鏈結串列
    static TreeNode prevNode = null;
    static DoublyListNode dllHead = null;

    public static DoublyListNode bstToDoublyList(TreeNode root) {
        prevNode = null;
        dllHead = null;
        convertToDLL(root);
        return dllHead;
    }

    private static void convertToDLL(TreeNode node) {
        if (node == null) return;
        convertToDLL(node.left);
        DoublyListNode curr = new DoublyListNode(node.val);
        if (prevNode == null) {
            dllHead = curr;
        } else {
            DoublyListNode prev = dllHead;
            while (prev.next != null) prev = prev.next;
            prev.next = curr;
            curr.prev = prev;
        }
        prevNode = node;
        convertToDLL(node.right);
    }

    public static void printDoublyList(DoublyListNode head) {
        DoublyListNode curr = head;
        while (curr != null) {
            System.out.print(curr.val + " ");
            curr = curr.next;
        }
        System.out.println();
    }

    // 2. 排序陣列 → 平衡 BST
    public static TreeNode sortedArrayToBST(int[] arr) {
        return buildBalancedBST(arr, 0, arr.length - 1);
    }

    private static TreeNode buildBalancedBST(int[] arr, int l, int r) {
        if (l > r) return null;
        int mid = (l + r) / 2;
        TreeNode root = new TreeNode(arr[mid]);
        root.left = buildBalancedBST(arr, l, mid - 1);
        root.right = buildBalancedBST(arr, mid + 1, r);
        return root;
    }

    // 3. 是否為平衡樹
    public static boolean isBalanced(TreeNode root) {
        return checkHeight(root) != -1;
    }

    private static int checkHeight(TreeNode node) {
        if (node == null) return 0;
        int lh = checkHeight(node.left);
        int rh = checkHeight(node.right);
        if (lh == -1 || rh == -1 || Math.abs(lh - rh) > 1) return -1;
        return 1 + Math.max(lh, rh);
    }

    // 4. 節點更新為「所有 ≥ 該值」的總和（反中序）
    static int sum = 0;

    public static void convertToGreaterSumTree(TreeNode root) {
        sum = 0;
        reverseInOrder(root);
    }

    private static void reverseInOrder(TreeNode node) {
        if (node == null) return;
        reverseInOrder(node.right);
        sum += node.val;
        node.val = sum;
        reverseInOrder(node.left);
    }

    // 輔助：中序印樹
    public static void printInOrder(TreeNode node) {
        if (node == null) return;
        printInOrder(node.left);
        System.out.print(node.val + " ");
        printInOrder(node.right);
    }
}
