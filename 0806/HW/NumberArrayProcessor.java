import java.util.*;

public class NumberArrayProcessor {
    public static void main(String[] args) {
        int[] array1 = {1, 2, 3, 4, 5, 2, 3, 6};
        int[] array2 = {2, 4, 6, 8};

        // 移除重複
        int[] unique = removeDuplicates(array1);
        System.out.println("移除重複後的陣列：" + Arrays.toString(unique));

        // 合併已排序陣列
        int[] sorted1 = {1, 3, 5, 7};
        int[] sorted2 = {2, 4, 6, 8};
        int[] merged = mergeSortedArrays(sorted1, sorted2);
        System.out.println("合併後的排序陣列：" + Arrays.toString(merged));

        // 找出出現最頻繁的元素
        int mode = findMostFrequentElement(array1);
        System.out.println("出現最頻繁的元素是：" + mode);

        // 分割陣列為兩個總和近似的子陣列
        System.out.println("將陣列分割成總和接近的兩組：");
        splitArrayBalanced(new int[]{1, 2, 3, 4, 5, 6, 7});
    }

    // 1. 移除重複元素
    public static int[] removeDuplicates(int[] arr) {
        Set<Integer> set = new LinkedHashSet<>(); // 保留順序
        for (int num : arr) {
            set.add(num);
        }
        int[] result = new int[set.size()];
        int i = 0;
        for (int num : set) {
            result[i++] = num;
        }
        return result;
    }

    // 2. 合併兩個已排序陣列
    public static int[] mergeSortedArrays(int[] a, int[] b) {
        int[] merged = new int[a.length + b.length];
        int i = 0, j = 0, k = 0;
        while (i < a.length && j < b.length) {
            if (a[i] < b[j]) merged[k++] = a[i++];
            else merged[k++] = b[j++];
        }
        while (i < a.length) merged[k++] = a[i++];
        while (j < b.length) merged[k++] = b[j++];
        return merged;
    }

    // 3. 找出出現頻率最高的元素（眾數）
    public static int findMostFrequentElement(int[] arr) {
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int num : arr) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }

        int maxFreq = 0;
        int mostFrequent = arr[0];
        for (Map.Entry<Integer, Integer> entry : freqMap.entrySet()) {
            if (entry.getValue() > maxFreq) {
                maxFreq = entry.getValue();
                mostFrequent = entry.getKey();
            }
        }
        return mostFrequent;
    }

    // 4. 分割陣列為兩個總和接近的子陣列（貪婪法）
    public static void splitArrayBalanced(int[] arr) {
        Arrays.sort(arr);
        List<Integer> group1 = new ArrayList<>();
        List<Integer> group2 = new ArrayList<>();
        int sum1 = 0, sum2 = 0;

        for (int i = arr.length - 1; i >= 0; i--) {
            if (sum1 <= sum2) {
                group1.add(arr[i]);
                sum1 += arr[i];
            } else {
                group2.add(arr[i]);
                sum2 += arr[i];
            }
        }

        System.out.println("Group 1: " + group1 + " -> 總和 = " + sum1);
        System.out.println("Group 2: " + group2 + " -> 總和 = " + sum2);
    }
}
