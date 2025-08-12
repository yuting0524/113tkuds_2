import java.util.*;

public class MultiLevelCacheSystem {

    // 快取項目類別
    private static class CacheItem {
        int key;
        String value;
        int freq;
        long timestamp;
        int level; // 1: L1, 2: L2, 3: L3

        CacheItem(int key, String value, int level, long timestamp) {
            this.key = key;
            this.value = value;
            this.freq = 1; // 初始頻率為1
            this.level = level;
            this.timestamp = timestamp;
        }
    }

    // 容量與存取成本
    private final int[] capacity = {2, 5, 10};
    private final int[] cost = {1, 3, 10};

    // 三層快取資料結構：用 LinkedHashMap 方便 LRU 管理與快速查找
    @SuppressWarnings("unchecked")
    private Map<Integer, CacheItem>[] caches = new Map[3];

    // 三層的優先佇列，排序依據：score = freq / cost，score小者優先淘汰，分數相同比較timestamp
    @SuppressWarnings("unchecked")
    private PriorityQueue<CacheItem>[] heaps = new PriorityQueue[3];

    private long globalTimestamp = 0;

    public MultiLevelCacheSystem() {
        for (int i = 0; i < 3; i++) {
            caches[i] = new LinkedHashMap<>();
            final int idx = i;
            heaps[i] = new PriorityQueue<>((a, b) -> {
                double scoreA = (double) a.freq / cost[a.level - 1];
                double scoreB = (double) b.freq / cost[b.level - 1];
                if (scoreA != scoreB) {
                    return Double.compare(scoreA, scoreB);
                }
                return Long.compare(a.timestamp, b.timestamp);
            });
        }
    }

    // 計算評分
    private double score(CacheItem item) {
        return (double) item.freq / cost[item.level - 1];
    }

    // 更新項目優先權
    private void updateItem(CacheItem item) {
        heaps[item.level - 1].remove(item);
        heaps[item.level - 1].offer(item);
    }

    // 嘗試升級該項目到更高層快取
   private void tryPromote(CacheItem item) {
    while (item.level > 1) {
        int higherLevel = item.level - 1;
        double currScore = score(item);
        CacheItem worst = heaps[higherLevel - 1].peek();

        if (worst == null) {
            moveItem(item, item.level, higherLevel);
        } else if (score(worst) < currScore) {
            moveItem(worst, higherLevel, higherLevel + 1);
            moveItem(item, item.level, higherLevel);
        } else {
            break;
        }

        // 防呆：從升級後的 cache 裡再找一次
        item = caches[higherLevel - 1].get(item.key);
        if (item == null) {
            break;
        }
    }
}


    // 移動 CacheItem 從一層到另一層
    private void moveItem(CacheItem item, int fromLevel, int toLevel) {
        caches[fromLevel - 1].remove(item.key);
        heaps[fromLevel - 1].remove(item);
        item.level = toLevel;
        caches[toLevel - 1].put(item.key, item);
        heaps[toLevel - 1].offer(item);
    }

    // 新增項目到指定層級，若滿了就先淘汰
    private void addToLevel(CacheItem item, int levelIdx) {
        if (caches[levelIdx].size() >= capacity[levelIdx]) {
            evictFromLevel(levelIdx);
        }
        item.level = levelIdx + 1;
        caches[levelIdx].put(item.key, item);
        heaps[levelIdx].offer(item);
    }

    // 從指定層級淘汰分數最低的項目
    private void evictFromLevel(int levelIdx) {
        CacheItem evicted = heaps[levelIdx].poll();
        if (evicted != null) {
            caches[levelIdx].remove(evicted.key);
            if (levelIdx < 2) {
                // 往下層放
                addToLevel(evicted, levelIdx + 1);
            }
            // L3淘汰即丟棄
        }
    }

    // get 操作
    public String get(int key) {
        for (int lvl = 0; lvl < 3; lvl++) {
            if (caches[lvl].containsKey(key)) {
                CacheItem item = caches[lvl].get(key);
                item.freq++;
                item.timestamp = ++globalTimestamp;
                updateItem(item);
                tryPromote(item);
                return item.value;
            }
        }
        return null;
    }

    // put 操作
    public void put(int key, String value) {
        CacheItem item = null;
        // 若存在，更新
        for (int lvl = 0; lvl < 3; lvl++) {
            if (caches[lvl].containsKey(key)) {
                item = caches[lvl].get(key);
                item.value = value;
                item.freq++;
                item.timestamp = ++globalTimestamp;
                updateItem(item);
                tryPromote(item);
                return;
            }
        }
        // 新增，預設放L3
        item = new CacheItem(key, value, 3, ++globalTimestamp);
        addToLevel(item, 2);
        tryPromote(item);
    }

    // 測試主程式
    public static void main(String[] args) {
        MultiLevelCacheSystem cache = new MultiLevelCacheSystem();

        cache.put(1, "A");
        cache.put(2, "B");
        cache.put(3, "C");

        System.out.println("L1 keys: " + cache.caches[0].keySet());
        System.out.println("L2 keys: " + cache.caches[1].keySet());
        System.out.println("L3 keys: " + cache.caches[2].keySet());

        cache.get(1);
        cache.get(1);
        cache.get(2);

        System.out.println("After some gets:");
        System.out.println("L1 keys: " + cache.caches[0].keySet());
        System.out.println("L2 keys: " + cache.caches[1].keySet());
        System.out.println("L3 keys: " + cache.caches[2].keySet());

        cache.put(4, "D");
        cache.put(5, "E");
        cache.put(6, "F");

        System.out.println("After more puts:");
        System.out.println("L1 keys: " + cache.caches[0].keySet());
        System.out.println("L2 keys: " + cache.caches[1].keySet());
        System.out.println("L3 keys: " + cache.caches[2].keySet());
    }
}
