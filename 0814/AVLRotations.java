public class AVLRotations {
    
    // 右旋操作
    public static AVLNode rightRotate(AVLNode y) {
        AVLNode x = y.left;
        AVLNode T2 = x.right;
        
        // 執行旋轉
        x.right = y;
        y.left = T2;
        
        // 更新高度
        y.updateHeight();
        x.updateHeight();
        
        return x; // 新的根節點
    }
    
    // 左旋操作
    public static AVLNode leftRotate(AVLNode x) {
        AVLNode y = x.right;
        AVLNode T2 = y.left;
        
        // 執行旋轉
        y.left = x;
        x.right = T2;
        
        // 更新高度
        x.updateHeight();
        y.updateHeight();
        
        return y; // 新的根節點
    }
    
    // 測試用 main
    public static void main(String[] args) {
        // 測試右旋
        System.out.println("===== 測試右旋 =====");
        AVLNode rootRight = new AVLNode(30);
        rootRight.left = new AVLNode(20);
        rootRight.left.left = new AVLNode(10);
        
        // 更新高度
        rootRight.left.left.updateHeight();
        rootRight.left.updateHeight();
        rootRight.updateHeight();
        
        System.out.println("右旋前 Root: " + rootRight.data + " 高度: " + rootRight.height);
        rootRight = rightRotate(rootRight);
        System.out.println("右旋後 Root: " + rootRight.data + " 高度: " + rootRight.height);
        System.out.println("Root 左子節點: " + (rootRight.left != null ? rootRight.left.data : "null"));
        System.out.println("Root 右子節點: " + (rootRight.right != null ? rootRight.right.data : "null"));
        
        // 測試左旋
        System.out.println("\n===== 測試左旋 =====");
        AVLNode rootLeft = new AVLNode(10);
        rootLeft.right = new AVLNode(20);
        rootLeft.right.right = new AVLNode(30);
        
        // 更新高度
        rootLeft.right.right.updateHeight();
        rootLeft.right.updateHeight();
        rootLeft.updateHeight();
        
        System.out.println("左旋前 Root: " + rootLeft.data + " 高度: " + rootLeft.height);
        rootLeft = leftRotate(rootLeft);
        System.out.println("左旋後 Root: " + rootLeft.data + " 高度: " + rootLeft.height);
        System.out.println("Root 左子節點: " + (rootLeft.left != null ? rootLeft.left.data : "null"));
        System.out.println("Root 右子節點: " + (rootLeft.right != null ? rootLeft.right.data : "null"));
    }
}
