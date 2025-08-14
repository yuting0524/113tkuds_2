public class AVLNode {
    int data;
    AVLNode left, right;
    int height;
    
    public AVLNode(int data) {
        this.data = data;
        this.height = 1;
    }
    
    // 計算平衡因子
    public int getBalance() {
        int leftHeight = (left != null) ? left.height : 0;
        int rightHeight = (right != null) ? right.height : 0;
        return leftHeight - rightHeight;
    }
    
    // 更新節點高度
    public void updateHeight() {
        int leftHeight = (left != null) ? left.height : 0;
        int rightHeight = (right != null) ? right.height : 0;
        this.height = Math.max(leftHeight, rightHeight) + 1;
    }

    // 測試用 main
    public static void main(String[] args) {
        // 建立三個節點
        AVLNode root = new AVLNode(10);
        AVLNode node5 = new AVLNode(5);
        AVLNode node15 = new AVLNode(15);
        
        // 手動連接節點
        root.left = node5;
        root.right = node15;
        
        // 更新高度
        node5.updateHeight();
        node15.updateHeight();
        root.updateHeight();
        
        // 印出資訊
        System.out.println("Root Data: " + root.data);
        System.out.println("Root Height: " + root.height);
        System.out.println("Root Balance Factor: " + root.getBalance());
        
        System.out.println("Left Child Data: " + root.left.data);
        System.out.println("Right Child Data: " + root.right.data);
    }
}
