import java.util.*;

public class MultiWayTreeAndDecisionTree {

    // 1. 任意子節點的多路樹結構
    static class MultiNode {
        String value;
        List<MultiNode> children;

        MultiNode(String val) {
            this.value = val;
            this.children = new ArrayList<>();
        }
    }

    public static void main(String[] args) {
        // 建立一棵多路樹
        MultiNode root = new MultiNode("A");
        MultiNode B = new MultiNode("B");
        MultiNode C = new MultiNode("C");
        MultiNode D = new MultiNode("D");
        MultiNode E = new MultiNode("E");
        MultiNode F = new MultiNode("F");
        MultiNode G = new MultiNode("G");

        root.children.add(B);
        root.children.add(C);
        B.children.add(D);
        B.children.add(E);
        C.children.add(F);
        C.children.add(G);

        System.out.println("1️⃣ DFS 深度優先走訪：");
        dfs(root);

        System.out.println("\n2️⃣ BFS 廣度優先走訪：");
        bfs(root);

        System.out.println("\n3️⃣ 樹的高度：" + getHeight(root));
        System.out.println("4️⃣ 每個節點的度數：");
        printDegrees(root);

        System.out.println("\n5️⃣ 🧠 簡單決策樹模擬（猜數字）");
        MultiNode decisionRoot = buildDecisionTree();
        simulateDecisionTree(decisionRoot, new Scanner(System.in));
    }

    // 1. 深度優先走訪
    public static void dfs(MultiNode node) {
        if (node == null) return;
        System.out.print(node.value + " ");
        for (MultiNode child : node.children) {
            dfs(child);
        }
    }

    // 2. 廣度優先走訪
    public static void bfs(MultiNode root) {
        if (root == null) return;
        Queue<MultiNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            MultiNode node = queue.poll();
            System.out.print(node.value + " ");
            for (MultiNode child : node.children) {
                queue.offer(child);
            }
        }
    }

    // 3. 計算高度
    public static int getHeight(MultiNode node) {
        if (node == null) return 0;
        int max = 0;
        for (MultiNode child : node.children) {
            max = Math.max(max, getHeight(child));
        }
        return 1 + max;
    }

    // 4. 印出每個節點的度數（子節點數）
    public static void printDegrees(MultiNode root) {
        if (root == null) return;
        System.out.println(root.value + " 的度數為：" + root.children.size());
        for (MultiNode child : root.children) {
            printDegrees(child);
        }
    }

    // 5. 簡單決策樹（猜數字遊戲）
    public static MultiNode buildDecisionTree() {
        /*
            你想到的是偶數還是奇數？
                → 偶數：小於10還是大於等於10？
                    → 小：你想到的是 2！
                    → 大：你想到的是 12！
                → 奇數：小於10還是大於等於10？
                    → 小：你想到的是 7！
                    → 大：你想到的是 13！
        */
        MultiNode root = new MultiNode("你想到的是偶數還是奇數？");
        MultiNode even = new MultiNode("偶數");
        MultiNode odd = new MultiNode("奇數");

        root.children.add(even);
        root.children.add(odd);

        MultiNode evenSmall = new MultiNode("你想到的是 2！");
        MultiNode evenLarge = new MultiNode("你想到的是 12！");
        even.children.add(new MultiNode("小於10嗎？"));
        even.children.get(0).children.add(evenSmall);
        even.children.get(0).children.add(evenLarge);

        MultiNode oddSmall = new MultiNode("你想到的是 7！");
        MultiNode oddLarge = new MultiNode("你想到的是 13！");
        odd.children.add(new MultiNode("小於10嗎？"));
        odd.children.get(0).children.add(oddSmall);
        odd.children.get(0).children.add(oddLarge);

        return root;
    }

    public static void simulateDecisionTree(MultiNode node, Scanner sc) {
        while (!node.children.isEmpty()) {
            System.out.println(node.value);
            for (int i = 0; i < node.children.size(); i++) {
                System.out.println((i + 1) + "）" + node.children.get(i).value);
            }
            System.out.print("請輸入選項（數字）：");
            int choice = sc.nextInt() - 1;
            if (choice < 0 || choice >= node.children.size()) {
                System.out.println("無效選項，請重新輸入。");
            } else {
                node = node.children.get(choice);
            }
        }
        System.out.println("👉 結果：" + node.value);
    }
}
