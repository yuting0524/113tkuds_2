import java.util.Arrays;

public class SelectionSortImplementation {
    public static void main(String[] args) {
        int[] data = {64, 25, 12, 22, 11};

        System.out.println("原始資料：" + Arrays.toString(data));
        selectionSort(data.clone()); // 複製一份做選擇排序
        System.out.println();

        bubbleSort(data.clone()); // 複製一份做氣泡排序
    }

    // 1. 選擇排序
    public static void selectionSort(int[] arr) {
        int compareCount = 0;
        int swapCount = 0;

        System.out.println("🔽 選擇排序過程：");
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;

            for (int j = i + 1; j < arr.length; j++) {
                compareCount++;
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }

            if (minIndex != i) {
                int temp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = temp;
                swapCount++;
            }

            System.out.printf("第 %d 輪排序結果：%s\n", i + 1, Arrays.toString(arr));
        }

        System.out.println("總比較次數：" + compareCount);
        System.out.println("總交換次數：" + swapCount);
    }

    // 2. 氣泡排序（比較用）
    public static void bubbleSort(int[] arr) {
        int compareCount = 0;
        int swapCount = 0;

        System.out.println("🔼 氣泡排序過程：");
        for (int i = 0; i < arr.length - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < arr.length - i - 1; j++) {
                compareCount++;
                if (arr[j] > arr[j + 1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                    swapped = true;
                    swapCount++;
                }
            }
            System.out.printf("第 %d 輪排序結果：%s\n", i + 1, Arrays.toString(arr));
            if (!swapped) break; // 提前結束
        }

        System.out.println("總比較次數：" + compareCount);
        System.out.println("總交換次數：" + swapCount);
    }
}
