public class ValidMaxHeapChecker {

    public static boolean isValidMaxHeap(int[] arr) {
        int n = arr.length;
        if (n <= 1) return true; // 空陣列或單一元素一定符合

        // 最後一個非葉子節點的索引
        int lastParent = (n - 2) / 2;

        for (int i = 0; i <= lastParent; i++) {
            int left = 2 * i + 1;
            int right = 2 * i + 2;

            if (left < n && arr[i] < arr[left]) {
                System.out.println("違反規則: 索引 " + left + " 的值 " + arr[left] + " 大於父節點索引 " + i + " 的值 " + arr[i]);
                return false;
            }
            if (right < n && arr[i] < arr[right]) {
                System.out.println("違反規則: 索引 " + right + " 的值 " + arr[right] + " 大於父節點索引 " + i + " 的值 " + arr[i]);
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[][] testCases = {
            {100, 90, 80, 70, 60, 75, 65}, // true
            {100, 90, 80, 95, 60, 75, 65}, // false
            {50}, // true
            {} // true
        };

        for (int[] testCase : testCases) {
            System.out.println("陣列: " + java.util.Arrays.toString(testCase));
            boolean result = isValidMaxHeap(testCase);
            System.out.println("是否為有效 Max Heap? " + result);
            System.out.println();
        }
    }
}
