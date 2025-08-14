// AVLNode.java
class AVLNode {
    int data;
    AVLNode left, right;
    int height;

    AVLNode(int data) {
        this.data = data;
        this.height = 1; // 葉節點高度 = 1
    }

    void updateHeight() {
        int leftHeight = (left != null) ? left.height : 0;
        int rightHeight = (right != null) ? right.height : 0;
        height = 1 + Math.max(leftHeight, rightHeight);
    }

    int getBalance() {
        int leftHeight = (left != null) ? left.height : 0;
        int rightHeight = (right != null) ? right.height : 0;
        return leftHeight - rightHeight;
    }
}

// AVLRotations.java
class AVLRotations {
    // 右旋
    public static AVLNode rightRotate(AVLNode y) {
        AVLNode x = y.left;
        AVLNode T2 = x.right;

        x.right = y;
        y.left = T2;

        y.updateHeight();
        x.updateHeight();

        return x;
    }

    // 左旋
    public static AVLNode leftRotate(AVLNode x) {
        AVLNode y = x.right;
        AVLNode T2 = y.left;

        y.left = x;
        x.right = T2;

        x.updateHeight();
        y.updateHeight();

        return y;
    }
}

// AVLTree.java
class AVLTree {
    private AVLNode root;

    public void insert(int data) {
        root = insertNode(root, data);
    }

    private AVLNode insertNode(AVLNode node, int data) {
        if (node == null) return new AVLNode(data);

        if (data < node.data) {
            node.left = insertNode(node.left, data);
        } else if (data > node.data) {
            node.right = insertNode(node.right, data);
        } else {
            return node; // 不插入重複值
        }

        node.updateHeight();
        int balance = node.getBalance();

        // LL
        if (balance > 1 && data < node.left.data)
            return AVLRotations.rightRotate(node);

        // RR
        if (balance < -1 && data > node.right.data)
            return AVLRotations.leftRotate(node);

        // LR
        if (balance > 1 && data > node.left.data) {
            node.left = AVLRotations.leftRotate(node.left);
            return AVLRotations.rightRotate(node);
        }

        // RL
        if (balance < -1 && data < node.right.data) {
            node.right = AVLRotations.rightRotate(node.right);
            return AVLRotations.leftRotate(node);
        }

        return node;
    }

    public boolean search(int data) {
        return searchNode(root, data);
    }

    private boolean searchNode(AVLNode node, int data) {
        if (node == null) return false;
        if (data == node.data) return true;
        if (data < node.data) return searchNode(node.left, data);
        return searchNode(node.right, data);
    }

    public void printTree() {
        printInOrder(root);
        System.out.println();
    }

    private void printInOrder(AVLNode node) {
        if (node != null) {
            printInOrder(node.left);
            System.out.print(node.data + "(" + node.getBalance() + ") ");
            printInOrder(node.right);
        }
    }
}

// AVLInsertExample.java
public class AVLInsertExample {
    public static void main(String[] args) {
        AVLTree tree = new AVLTree();

        System.out.println("=== AVL 樹插入演示 ===");

        int[] values = {10, 20, 30, 40, 50, 25};

        for (int value : values) {
            System.out.println("插入: " + value);
            tree.insert(value);
            System.out.print("當前樹狀態: ");
            tree.printTree();
            System.out.println();
        }

        System.out.println("=== 搜尋測試 ===");
        System.out.println("搜尋 25: " + tree.search(25));
        System.out.println("搜尋 35: " + tree.search(35));
    }
}
