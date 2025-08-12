import java.util.*;

public class StockMaximizer {
    
    public int maxProfit(int[] prices, int k) {
        if (prices == null || prices.length == 0 || k == 0) return 0;

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        int n = prices.length;
        int buy = 0; // 買入點
        int sell = 0; // 賣出點

        while (buy < n) {
            // 找到下一個低點作為買入點
            while (buy < n - 1 && prices[buy] >= prices[buy + 1]) {
                buy++;
            }
            sell = buy + 1;

            // 找到對應的高點作為賣出點
            while (sell < n && prices[sell] >= prices[sell - 1]) {
                sell++;
            }

            // 如果有獲利，放進 Heap
            if (sell - 1 < n && prices[sell - 1] > prices[buy]) {
                maxHeap.offer(prices[sell - 1] - prices[buy]);
            }

            buy = sell; // 繼續尋找下一筆交易
        }

        // 從 Heap 中取出前 K 筆最大利潤
        int profit = 0;
        for (int i = 0; i < k && !maxHeap.isEmpty(); i++) {
            profit += maxHeap.poll();
        }

        return profit;
    }

    public static void main(String[] args) {
        StockMaximizer sm = new StockMaximizer();

        System.out.println(sm.maxProfit(new int[]{2,4,1}, 2)); // 2
        System.out.println(sm.maxProfit(new int[]{3,2,6,5,0,3}, 2)); // 7
        System.out.println(sm.maxProfit(new int[]{1,2,3,4,5}, 2)); // 4
    }
}
