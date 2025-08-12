import java.util.*;

public class MeetingRoomScheduler {

    // Part 1: 最少會議室數量
    public int minMeetingRooms(int[][] meetings) {
        if (meetings == null || meetings.length == 0) return 0;

        Arrays.sort(meetings, (a, b) -> Integer.compare(a[0], b[0])); // 按開始時間排序
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(); // 存結束時間

        for (int[] m : meetings) {
            if (!minHeap.isEmpty() && minHeap.peek() <= m[0]) {
                minHeap.poll(); // 會議室釋放
            }
            minHeap.offer(m[1]);
        }

        return minHeap.size();
    }

    // Part 2: N=1 最大化總會議時間（加權區間排程）
    public int maxMeetingTimeOneRoom(int[][] meetings) {
        if (meetings == null || meetings.length == 0) return 0;

        Arrays.sort(meetings, (a, b) -> Integer.compare(a[1], b[1])); // 按結束時間排序
        int n = meetings.length;
        int[] dp = new int[n];
        int[] p = new int[n];

        // 預處理 p(i)（二分搜尋）
        for (int i = 0; i < n; i++) {
            p[i] = -1;
            for (int j = i - 1; j >= 0; j--) {
                if (meetings[j][1] <= meetings[i][0]) {
                    p[i] = j;
                    break;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            int include = meetings[i][1] - meetings[i][0];
            if (p[i] != -1) {
                include += dp[p[i]];
            }
            int exclude = (i > 0) ? dp[i - 1] : 0;
            dp[i] = Math.max(include, exclude);
        }

        return dp[n - 1];
    }

    public static void main(String[] args) {
        MeetingRoomScheduler mrs = new MeetingRoomScheduler();

        // Part 1 測試
        System.out.println(mrs.minMeetingRooms(new int[][]{{0,30},{5,10},{15,20}})); // 2
        System.out.println(mrs.minMeetingRooms(new int[][]{{9,10},{4,9},{4,17}})); // 2
        System.out.println(mrs.minMeetingRooms(new int[][]{{1,5},{8,9},{8,9}})); // 2

        // Part 2 測試 (N=1)
        System.out.println(mrs.maxMeetingTimeOneRoom(new int[][]{{1,4},{2,3},{4,6}})); // 5
    }
}
