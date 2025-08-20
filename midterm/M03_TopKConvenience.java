/*
 * Time Complexity: O(n log K)
 * 說明：每筆資料進/出堆各 O(log K)，共 n 筆 → O(n log K)；
 *       最後 K 筆排序 O(K log K)，K ≤ n，主導項仍為 O(n log K)。
 */
import java.util.*;
public class M03_TopKConvenience {
    static class Item {
        String name;
        int qty;
        Item(String n, int q){ name = n; qty = q; }
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), k = sc.nextInt();
        PriorityQueue<Item> pq = new PriorityQueue<>(new Comparator<Item>() {
            @Override public int compare(Item a, Item b){
                if (a.qty != b.qty) return Integer.compare(a.qty, b.qty); // 小在前（最差在頂）
                return b.name.compareTo(a.name); // 同量時字典序大的在前（優先被踢）
            }
        });

        for(int i=0;i<n;i++){
            String name = sc.next();
            int qty = sc.nextInt();
            pq.offer(new Item(name, qty));
            if (pq.size() > k) pq.poll(); // 只留 K 個最好的
        }

        List<Item> ans = new ArrayList<>(pq);
        // 最終輸出需 qty↓, name↑
        ans.sort((a,b) -> {
            if (a.qty != b.qty) return Integer.compare(b.qty, a.qty);
            return a.name.compareTo(b.name);
        });

        for(Item it : ans){
            System.out.println(it.name + " " + it.qty);
        }
    }
}
