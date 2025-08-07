import java.util.Arrays;

public class AdvancedArrayRecursion {
    public static void main(String[] args) {
        int[] arr = {7, 2, 1, 6, 8, 5, 3, 4};
        System.out.println("原始陣列：" + Arrays.toString(arr));
        quickSort(arr, 0, arr.length - 1);
        System.out.println("快速排序後：" + Arrays.toString(arr));

        int[] sorted1 = {1, 3, 5};
        int[] sorted2 = {2, 4, 6, 8};
        int[] merged = mergeRecursive(sorted1, sorted2, 0, 0);
        System.out.println("合併後的排序陣列：" + Arrays.toString(merged));

        int[] arr2 = {9, 3, 7, 1, 4, 6};
        int k = 3;
        System.out.println("第 " + k + " 小元素為：" + kthSmallest(arr2, k));

        int[] arr3 = {3, 34, 4, 12, 5, 2};
        int target = 9;
        System.out.println("是否存在子序列總和為 " + target + "？ " + subsetSum(arr3, target, 0));
    }

    // 1. 遞迴快速排序
    public static void quickSort(int[] arr, int left, int right) {
        if (left < right) {
            int pivotIndex = partition(arr, left, right);
            quickSort(arr, left, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, right);
        }
    }

    private static int partition(int[] arr, int left, int right) {
        int pivot = arr[right];
        int i = left - 1;
        for (int j = left; j < right; j++) {
            if (arr[j] <= pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, right);
        return i + 1;
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    // 2. 遞迴合併兩個已排序陣列
    public static int[] mergeRecursive(int[] a, int[] b, int i, int j) {
        if (i == a.length) return Arrays.copyOfRange(b, j, b.length);
        if (j == b.length) return Arrays.copyOfRange(a, i, a.length);

        if (a[i] < b[j]) {
            return concat(a[i], mergeRecursive(a, b, i + 1, j));
        } else {
            return concat(b[j], mergeRecursive(a, b, i, j + 1));
        }
    }

    private static int[] concat(int val, int[] rest) {
        int[] result = new int[rest.length + 1];
        result[0] = val;
        System.arraycopy(rest, 0, result, 1, rest.length);
        return result;
    }

    // 3. 遞迴找第 k 小元素（簡化版 QuickSelect）
    public static int kthSmallest(int[] arr, int k) {
        return quickSelect(arr, 0, arr.length - 1, k - 1);
    }

    private static int quickSelect(int[] arr, int left, int right, int k) {
        if (left == right) return arr[left];
        int pivotIndex = partition(arr, left, right);
        if (k == pivotIndex) return arr[k];
        else if (k < pivotIndex) return quickSelect(arr, left, pivotIndex - 1, k);
        else return quickSelect(arr, pivotIndex + 1, right, k);
    }

    // 4. 遞迴檢查子序列總和是否等於目標值
    public static boolean subsetSum(int[] arr, int target, int index) {
        if (target == 0) return true;
        if (index >= arr.length || target < 0) return false;
        // 包含當前元素 or 不包含
        return subsetSum(arr, target - arr[index], index + 1) ||
               subsetSum(arr, target, index + 1);
    }
}
