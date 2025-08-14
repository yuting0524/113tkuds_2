import java.util.*;

public class AVLLeaderboardSystem {

    // 節點類別
    static class Node {
        int score;
        String player;
        Node left, right;
        int height;
        int subtreeSize; // 子樹節點數量

        Node(String player, int score) {
            this.player = player;
            this.score = score;
            this.height = 1;
            this.subtreeSize = 1;
        }
    }

    Node root;
    Map<String, Integer> playerScores = new HashMap<>(); // 玩家 → 分數

    // 計算高度
    int height(Node n) {
        return (n == null) ? 0 : n.height;
    }

    // 計算子樹大小
    int size(Node n) {
        return (n == null) ? 0 : n.subtreeSize;
    }

    // 更新節點資訊
    void updateNode(Node n) {
        if (n != null) {
            n.height = Math.max(height(n.left), height(n.right)) + 1;
            n.subtreeSize = size(n.left) + size(n.right) + 1;
        }
    }

    // 平衡因子
    int getBalance(Node n) {
        return (n == null) ? 0 : height(n.left) - height(n.right);
    }

    // 右旋
    Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        x.right = y;
        y.left = T2;

        updateNode(y);
        updateNode(x);

        return x;
    }

    // 左旋
    Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        y.left = x;
        x.right = T2;

        updateNode(x);
        updateNode(y);

        return y;
    }

    // 比較排序：分數高的在前，如果分數相同，名字小的在前
    int compare(String player, int score, Node node) {
        if (score != node.score) {
            return (score > node.score) ? -1 : 1; // 高分在左
        }
        return player.compareTo(node.player); // 同分字母序
    }

    // 插入或更新節點
    Node insert(Node node, String player, int score) {
        if (node == null) return new Node(player, score);

        int cmp = compare(player, score, node);
        if (cmp < 0) {
            node.left = insert(node.left, player, score);
        } else if (cmp > 0) {
            node.right = insert(node.right, player, score);
        } else {
            node.score = score; // 更新分數
        }

        updateNode(node);

        int balance = getBalance(node);

        // LL
        if (balance > 1 && compare(player, score, node.left) < 0)
            return rightRotate(node);

        // RR
        if (balance < -1 && compare(player, score, node.right) > 0)
            return leftRotate(node);

        // LR
        if (balance > 1 && compare(player, score, node.left) > 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // RL
        if (balance < -1 && compare(player, score, node.right) < 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    // 新增或更新玩家分數
    public void addOrUpdate(String player, int score) {
        if (playerScores.containsKey(player)) {
            int oldScore = playerScores.get(player);
            root = delete(root, player, oldScore);
        }
        root = insert(root, player, score);
        playerScores.put(player, score);
    }

    // 刪除節點（用於更新分數）
    Node delete(Node node, String player, int score) {
        if (node == null) return null;

        int cmp = compare(player, score, node);
        if (cmp < 0) {
            node.left = delete(node.left, player, score);
        } else if (cmp > 0) {
            node.right = delete(node.right, player, score);
        } else {
            if (node.left == null || node.right == null) {
                node = (node.left != null) ? node.left : node.right;
            } else {
                Node successor = minValueNode(node.right);
                node.player = successor.player;
                node.score = successor.score;
                node.right = delete(node.right, successor.player, successor.score);
            }
        }

        if (node == null) return null;

        updateNode(node);

        int balance = getBalance(node);

        if (balance > 1 && getBalance(node.left) >= 0)
            return rightRotate(node);

        if (balance > 1 && getBalance(node.left) < 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        if (balance < -1 && getBalance(node.right) <= 0)
            return leftRotate(node);

        if (balance < -1 && getBalance(node.right) > 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    // 找最小值節點
    Node minValueNode(Node node) {
        Node current = node;
        while (current.left != null) current = current.left;
        return current;
    }

    // 查詢玩家排名
    public int getRank(String player) {
        if (!playerScores.containsKey(player)) return -1;
        int score = playerScores.get(player);
        return getRankHelper(root, player, score) + 1; // 排名從 1 開始
    }

    // 計算排名（左子樹大小 + 右遞迴）
    int getRankHelper(Node node, String player, int score) {
        if (node == null) return 0;

        int cmp = compare(player, score, node);
        if (cmp < 0) {
            return getRankHelper(node.left, player, score);
        } else if (cmp > 0) {
            return size(node.left) + 1 + getRankHelper(node.right, player, score);
        } else {
            return size(node.left);
        }
    }

    // 查詢前 K 名
    public List<String> topK(int k) {
        List<String> result = new ArrayList<>();
        topKHelper(root, k, result);
        return result;
    }

    void topKHelper(Node node, int k, List<String> result) {
        if (node == null || result.size() >= k) return;
        topKHelper(node.left, k, result);
        if (result.size() < k) result.add(node.player + " (" + node.score + ")");
        topKHelper(node.right, k, result);
    }

    // 測試
    public static void main(String[] args) {
        AVLLeaderboardSystem leaderboard = new AVLLeaderboardSystem();

        leaderboard.addOrUpdate("Alice", 90);
        leaderboard.addOrUpdate("Bob", 95);
        leaderboard.addOrUpdate("Charlie", 85);
        leaderboard.addOrUpdate("David", 95);
        leaderboard.addOrUpdate("Eve", 88);

        System.out.println("前 3 名: " + leaderboard.topK(3));
        System.out.println("Bob 排名: " + leaderboard.getRank("Bob"));
        System.out.println("Charlie 排名: " + leaderboard.getRank("Charlie"));

        leaderboard.addOrUpdate("Bob", 80); // 更新分數
        System.out.println("更新後前 3 名: " + leaderboard.topK(3));
    }
}
