import java.util.ArrayList;
import java.util.List;

public class AVLRangeQueryExercise {

    static class Node {
        int key;
        Node left, right;
        int height;

        Node(int key) {
            this.key = key;
            this.height = 1;
        }
    }

    Node root;

    // 高度
    int height(Node n) {
        return (n == null) ? 0 : n.height;
    }

    // 平衡因子
    int getBalance(Node n) {
        if (n == null) return 0;
        return height(n.left) - height(n.right);
    }

    // 右旋
    Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        x.right = y;
        y.left = T2;

        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        return x;
    }

    // 左旋
    Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        y.left = x;
        x.right = T2;

        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        return y;
    }

    // 插入（AVL）
    Node insert(Node node, int key) {
        if (node == null) return new Node(key);

        if (key < node.key)
            node.left = insert(node.left, key);
        else if (key > node.key)
            node.right = insert(node.right, key);
        else
            return node;

        node.height = 1 + Math.max(height(node.left), height(node.right));

        int balance = getBalance(node);

        // LL
        if (balance > 1 && key < node.left.key)
            return rightRotate(node);

        // RR
        if (balance < -1 && key > node.right.key)
            return leftRotate(node);

        // LR
        if (balance > 1 && key > node.left.key) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // RL
        if (balance < -1 && key < node.right.key) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    // 範圍查詢
    public List<Integer> rangeQuery(int min, int max) {
        List<Integer> result = new ArrayList<>();
        rangeQueryHelper(root, min, max, result);
        return result;
    }

    // 遞迴搜尋（帶剪枝）
    private void rangeQueryHelper(Node node, int min, int max, List<Integer> result) {
        if (node == null) return;

        // 如果當前節點大於 min → 左子樹可能有符合條件的節點
        if (node.key > min) {
            rangeQueryHelper(node.left, min, max, result);
        }

        // 如果當前節點在範圍內 → 加入結果
        if (node.key >= min && node.key <= max) {
            result.add(node.key);
        }

        // 如果當前節點小於 max → 右子樹可能有符合條件的節點
        if (node.key < max) {
            rangeQueryHelper(node.right, min, max, result);
        }
    }

    // 測試
    public static void main(String[] args) {
        AVLRangeQueryExercise tree = new AVLRangeQueryExercise();

        int[] nums = {20, 10, 30, 5, 15, 25, 35, 2, 7, 13, 17};
        for (int n : nums) {
            tree.root = tree.insert(tree.root, n);
        }

        System.out.println("範圍查詢 [7, 25]: " + tree.rangeQuery(7, 25));
        System.out.println("範圍查詢 [0, 100]: " + tree.rangeQuery(0, 100));
        System.out.println("範圍查詢 [14, 16]: " + tree.rangeQuery(14, 16));
    }
}
