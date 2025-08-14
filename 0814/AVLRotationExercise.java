public class AVLRotationExercise {

    // 節點類別
    static class Node {
        int key;
        Node left, right;
        int height;

        Node(int key) {
            this.key = key;
            this.height = 1; // 初始高度設為 1
        }
    }

    Node root;

    // 計算節點高度
    int height(Node n) {
        if (n == null) return 0;
        return n.height;
    }

    // 計算平衡因子
    int getBalance(Node n) {
        if (n == null) return 0;
        return height(n.left) - height(n.right);
    }

    // 1. 右旋轉
    Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        // 旋轉
        x.right = y;
        y.left = T2;

        // 更新高度
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        return x; // 新的根
    }

    // 2. 左旋轉
    Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        // 旋轉
        y.left = x;
        x.right = T2;

        // 更新高度
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        return y; // 新的根
    }

    // 插入並自動平衡
    Node insert(Node node, int key) {
        // 1. 普通 BST 插入
        if (node == null) return new Node(key);
        if (key < node.key) {
            node.left = insert(node.left, key);
        } else if (key > node.key) {
            node.right = insert(node.right, key);
        } else {
            return node; // 相等不插入
        }

        // 2. 更新高度
        node.height = 1 + Math.max(height(node.left), height(node.right));

        // 3. 檢查平衡因子
        int balance = getBalance(node);

        // Case 1: 左左
        if (balance > 1 && key < node.left.key) {
            return rightRotate(node);
        }

        // Case 2: 右右
        if (balance < -1 && key > node.right.key) {
            return leftRotate(node);
        }

        // Case 3: 左右
        if (balance > 1 && key > node.left.key) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // Case 4: 右左
        if (balance < -1 && key < node.right.key) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    // 中序遍歷 (檢查用)
    void inorder(Node node) {
        if (node != null) {
            inorder(node.left);
            System.out.print(node.key + " ");
            inorder(node.right);
        }
    }

    // 測試
    public static void main(String[] args) {
        AVLRotationExercise tree = new AVLRotationExercise();

        // 測試四種情況
        // 左左
        tree.root = tree.insert(tree.root, 30);
        tree.root = tree.insert(tree.root, 20);
        tree.root = tree.insert(tree.root, 10); // 觸發右旋
        tree.inorder(tree.root);
        System.out.println("\n------");

        // 右右
        tree = new AVLRotationExercise();
        tree.root = tree.insert(tree.root, 10);
        tree.root = tree.insert(tree.root, 20);
        tree.root = tree.insert(tree.root, 30); // 觸發左旋
        tree.inorder(tree.root);
        System.out.println("\n------");

        // 左右
        tree = new AVLRotationExercise();
        tree.root = tree.insert(tree.root, 30);
        tree.root = tree.insert(tree.root, 10);
        tree.root = tree.insert(tree.root, 20); // 觸發左右旋
        tree.inorder(tree.root);
        System.out.println("\n------");

        // 右左
        tree = new AVLRotationExercise();
        tree.root = tree.insert(tree.root, 10);
        tree.root = tree.insert(tree.root, 30);
        tree.root = tree.insert(tree.root, 20); // 觸發右左旋
        tree.inorder(tree.root);
        System.out.println("\n------");
    }
}
