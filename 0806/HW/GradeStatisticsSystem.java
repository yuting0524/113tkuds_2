public class GradeStatisticsSystem {
    public static void main(String[] args) {
        int[] grades = {85, 92, 78, 96, 87, 73, 89, 94, 82, 90};

        int sum = 0, max = grades[0], min = grades[0];
        int[] levelCount = new int[5]; // A~F 分別存放人數 [A, B, C, D, F]
        int aboveAverageCount = 0;

        // 計算總和、最大、最小
        for (int grade : grades) {
            sum += grade;
            if (grade > max) max = grade;
            if (grade < min) min = grade;

            // 等第判定
            if (grade >= 90) levelCount[0]++;     // A
            else if (grade >= 80) levelCount[1]++; // B
            else if (grade >= 70) levelCount[2]++; // C
            else if (grade >= 60) levelCount[3]++; // D
            else levelCount[4]++;                  // F
        }

        double average = (double) sum / grades.length;

        // 統計高於平均的學生數
        for (int grade : grades) {
            if (grade > average) aboveAverageCount++;
        }

        // 輸出報表
        System.out.println("=== 成績統計報表 ===");
        System.out.printf("平均成績：%.2f\n", average);
        System.out.println("最高成績：" + max);
        System.out.println("最低成績：" + min);
        System.out.println("等第人數：");
        System.out.println("A (90~100)： " + levelCount[0] + " 人");
        System.out.println("B (80~89)：  " + levelCount[1] + " 人");
        System.out.println("C (70~79)：  " + levelCount[2] + " 人");
        System.out.println("D (60~69)：  " + levelCount[3] + " 人");
        System.out.println("F (<60)：    " + levelCount[4] + " 人");
        System.out.println("高於平均成績的人數：" + aboveAverageCount + " 人");

        System.out.println("\n完整成績報表：");
        for (int i = 0; i < grades.length; i++) {
            char level = getGradeLevel(grades[i]);
            System.out.printf("學生 %2d：%3d 分，等第 %c\n", i + 1, grades[i], level);
        }
    }

    // 傳回成績對應的等第（A~F）
    public static char getGradeLevel(int grade) {
        if (grade >= 90) return 'A';
        else if (grade >= 80) return 'B';
        else if (grade >= 70) return 'C';
        else if (grade >= 60) return 'D';
        else return 'F';
    }
}
