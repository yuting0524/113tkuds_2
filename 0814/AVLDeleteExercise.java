public class AVLDeleteExercise {

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

    // 計算高度
    int height(Node n) {
        return (n == null) ? 0 : n.height;
    }

    // 計算平衡因子
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

    // 插入
    Node insert(Node node, int key) {
        if (node == null) return new Node(key);
        if (key < node.key) {
            node.left = insert(node.left, key);
        } else if (key > node.key) {
            node.right = insert(node.right, key);
        } else {
            return node; // 相等不插入
        }

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

    // 找最小值節點（用於找後繼）
    Node minValueNode(Node node) {
        Node current = node;
        while (current.left != null)
            current = current.left;
        return current;
    }

    // 刪除
    Node delete(Node root, int key) {
        // 1. 普通 BST 刪除
        if (root == null) return root;

        if (key < root.key) {
            root.left = delete(root.left, key);
        } else if (key > root.key) {
            root.right = delete(root.right, key);
        } else {
            // 找到要刪的節點
            if (root.left == null || root.right == null) {
                // 只有一個子節點或沒有子節點
                Node temp = (root.left != null) ? root.left : root.right;
                if (temp == null) { // 無子節點
                    root = null;
                } else { // 一個子節點
                    root = temp;
                }
            } else {
                // 有兩個子節點 → 找後繼
                Node temp = minValueNode(root.right);
                root.key = temp.key; // 用後繼值取代
                root.right = delete(root.right, temp.key); // 刪除後繼
            }
        }

        // 如果刪掉後樹是空的，直接回傳
        if (root == null) return root;

        // 2. 更新高度
        root.height = Math.max(height(root.left), height(root.right)) + 1;

        // 3. 檢查平衡
        int balance = getBalance(root);

        // LL
        if (balance > 1 && getBalance(root.left) >= 0)
            return rightRotate(root);

        // LR
        if (balance > 1 && getBalance(root.left) < 0) {
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }

        // RR
        if (balance < -1 && getBalance(root.right) <= 0)
            return leftRotate(root);

        // RL
        if (balance < -1 && getBalance(root.right) > 0) {
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }

        return root;
    }

    // 中序遍歷
    void inorder(Node node) {
        if (node != null) {
            inorder(node.left);
            System.out.print(node.key + " ");
            inorder(node.right);
        }
    }

    // 測試
    public static void main(String[] args) {
        AVLDeleteExercise tree = new AVLDeleteExercise();

        int[] nums = {20, 10, 30, 5, 15, 25, 35};
        for (int n : nums) {
            tree.root = tree.insert(tree.root, n);
        }

        System.out.println("初始樹 (中序):");
        tree.inorder(tree.root);
        System.out.println();

        // 刪除葉子節點
        tree.root = tree.delete(tree.root, 5);
        System.out.println("刪除 5 (葉子):");
        tree.inorder(tree.root);
        System.out.println();

        // 刪除只有一個子節點的節點
        tree.root = tree.delete(tree.root, 35);
        System.out.println("刪除 35 (一個子節點):");
        tree.inorder(tree.root);
        System.out.println();

        // 刪除有兩個子節點的節點
        tree.root = tree.delete(tree.root, 20);
        System.out.println("刪除 20 (兩個子節點):");
        tree.inorder(tree.root);
        System.out.println();
    }
}
