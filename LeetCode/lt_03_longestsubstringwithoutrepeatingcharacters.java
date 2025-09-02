// 題目：Longest Substring Without Repeating Characters
// 給定字串 s，回傳「最長且不含重複字元」子字串的長度。

import java.util.HashMap;
import java.util.Map;

class Solution {
    public int lengthOfLongestSubstring(String s) {
        // 邊界情況：空字串直接回傳 0
        if (s == null || s.length() == 0) return 0;

        // 使用 HashMap 紀錄「字元最後一次出現的索引」
        // key: 字元；value: 該字元上一次出現的位置
        Map<Character, Integer> last = new HashMap<>();

        int left = 0;   // 視窗左邊界（包含）
        int ans  = 0;   // 目前找到的最大長度

        // 以 right 作為視窗右邊界，逐一掃過字串
        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);

            // 若 c 之前出現過，且其位置在當前視窗內，
            // 則需要把左邊界 left 移到「上次位置 + 1」之後，避免重複
            if (last.containsKey(c)) {
                // 取最大值，避免 left 往回退（例如 "abba" 的情況）
                left = Math.max(left, last.get(c) + 1);
            }

            // 以當前 [left, right] 的視窗更新答案
            ans = Math.max(ans, right - left + 1);

            // 更新字元 c 的最後出現位置
            last.put(c, right);
        }

        return ans;
    }
}

/*
解題思路（滑動視窗 / 雙指標）：
1. 我們維護一個「不含重複字元」的視窗 [left, right]。
2. 右指標 right 由左到右掃描字串；當遇到已在視窗中的字元 c 時，
   將 left 移到「c 上次出現位置 + 1」，確保視窗內沒有重複字元。
   - 使用 HashMap<Character, Integer> 記錄每個字元最後出現的索引。
   - left = max(left, last.get(c) + 1) 可避免像 "abba" 使 left 倒退。
3. 每一步用視窗大小 (right - left + 1) 更新答案。
4. 時間複雜度 O(n)：每個字元至多被 left/right 指到一次。
   空間複雜度 O(k)：k 為字元種類數（本題可視為至多 128/256）。
*/
