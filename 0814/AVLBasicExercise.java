// 檔名：AVLBasicExercise.java
public class AVLBasicExercise {

    // 樹的節點定義
    static class Node {
        int key;
        Node left, right;

        Node(int key) {
            this.key = key;
            this.left = null;
            this.right = null;
        }
    }

    Node root; // 樹的根節點

    // 1. 插入節點（當作普通 BST 插入）
    public Node insert(Node node, int key) {
        if (node == null) {
            return new Node(key);
        }
        if (key < node.key) {
            node.left = insert(node.left, key);
        } else if (key > node.key) {
            node.right = insert(node.right, key);
        }
        // 相等的值不插入
        return node;
    }

    // 2. 搜尋節點
    public boolean search(Node node, int key) {
        if (node == null) {
            return false;
        }
        if (key == node.key) {
            return true;
        } else if (key < node.key) {
            return search(node.left, key);
        } else {
            return search(node.right, key);
        }
    }

    // 3. 計算樹的高度（遞迴）
    public int height(Node node) {
        if (node == null) return 0;
        return 1 + Math.max(height(node.left), height(node.right));
    }

    // 4. 檢查是否為有效 AVL 樹
    public boolean isAVL(Node node) {
        if (node == null) return true;

        int balanceFactor = height(node.left) - height(node.right);

        // 檢查當前節點的平衡因子是否在 [-1, 1]
        if (Math.abs(balanceFactor) > 1) {
            return false;
        }

        // 遞迴檢查左右子樹
        return isAVL(node.left) && isAVL(node.right);
    }

    // 測試用主程式
    public static void main(String[] args) {
        AVLBasicExercise tree = new AVLBasicExercise();

        // 插入一些節點
        tree.root = tree.insert(tree.root, 10);
        tree.root = tree.insert(tree.root, 5);
        tree.root = tree.insert(tree.root, 15);
        tree.root = tree.insert(tree.root, 3);
        tree.root = tree.insert(tree.root, 7);

        // 搜尋測試
        System.out.println("搜尋 7: " + tree.search(tree.root, 7)); // true
        System.out.println("搜尋 20: " + tree.search(tree.root, 20)); // false

        // 樹的高度
        System.out.println("高度: " + tree.height(tree.root));

        // 是否為 AVL
        System.out.println("是否為 AVL 樹: " + tree.isAVL(tree.root));
    }
}
