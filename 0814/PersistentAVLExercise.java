import java.util.*;

public class PersistentAVLExercise {

    // 不可變節點
    static class Node {
        final int key;
        final Node left, right;
        final int height;

        Node(int key, Node left, Node right) {
            this.key = key;
            this.left = left;
            this.right = right;
            this.height = Math.max(height(left), height(right)) + 1;
        }
    }

    // 儲存各版本的根節點
    List<Node> versions = new ArrayList<>();

    public PersistentAVLExercise() {
        versions.add(null); // 初始空版本
    }

    // 計算高度
    static int height(Node n) {
        return (n == null) ? 0 : n.height;
    }

    // 平衡因子
    static int getBalance(Node n) {
        return (n == null) ? 0 : height(n.left) - height(n.right);
    }

    // 右旋
    static Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;
        return new Node(x.key, x.left, new Node(y.key, T2, y.right));
    }

    // 左旋
    static Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;
        return new Node(y.key, new Node(x.key, x.left, T2), y.right);
    }

    // 插入（不可變）
    static Node insert(Node node, int key) {
        if (node == null) return new Node(key, null, null);

        if (key < node.key) {
            node = new Node(node.key, insert(node.left, key), node.right);
        } else if (key > node.key) {
            node = new Node(node.key, node.left, insert(node.right, key));
        } else {
            return node; // 不允許重複
        }

        int balance = getBalance(node);

        // LL
        if (balance > 1 && key < node.left.key)
            return rightRotate(node);

        // RR
        if (balance < -1 && key > node.right.key)
            return leftRotate(node);

        // LR
        if (balance > 1 && key > node.left.key) {
            Node newLeft = leftRotate(node.left);
            return rightRotate(new Node(node.key, newLeft, node.right));
        }

        // RL
        if (balance < -1 && key < node.right.key) {
            Node newRight = rightRotate(node.right);
            return leftRotate(new Node(node.key, node.left, newRight));
        }

        return node;
    }

    // 建立新版本
    public void insert(int key) {
        Node newRoot = insert(versions.get(versions.size() - 1), key);
        versions.add(newRoot);
    }

    // 查詢某版本是否有某值
    public boolean search(int version, int key) {
        Node curr = versions.get(version);
        while (curr != null) {
            if (key < curr.key) curr = curr.left;
            else if (key > curr.key) curr = curr.right;
            else return true;
        }
        return false;
    }

    // 中序列印某版本
    public void printVersion(int version) {
        List<Integer> result = new ArrayList<>();
        inorder(versions.get(version), result);
        System.out.println("版本 " + version + ": " + result);
    }

    static void inorder(Node node, List<Integer> list) {
        if (node == null) return;
        inorder(node.left, list);
        list.add(node.key);
        inorder(node.right, list);
    }

    public static void main(String[] args) {
        PersistentAVLExercise tree = new PersistentAVLExercise();

        tree.insert(10);
        tree.insert(20);
        tree.insert(5);
        tree.insert(6);

        tree.printVersion(0); // []
        tree.printVersion(1); // [10]
        tree.printVersion(2); // [10, 20]
        tree.printVersion(3); // [5, 10, 20]
        tree.printVersion(4); // [5, 6, 10, 20]

        System.out.println("版本 2 是否有 5? " + tree.search(2, 5)); // false
        System.out.println("版本 4 是否有 5? " + tree.search(4, 5)); // true
    }
}
