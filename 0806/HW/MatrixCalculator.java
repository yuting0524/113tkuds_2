import java.util.Arrays;

public class MatrixCalculator {
    public static void main(String[] args) {
        int[][] matrixA = {
            {1, 2, 3},
            {4, 5, 6}
        };

        int[][] matrixB = {
            {7, 8, 9},
            {10, 11, 12}
        };

        int[][] matrixC = {
            {1, 2},
            {3, 4},
            {5, 6}
        };

        // 矩陣加法 (A + B)
        System.out.println("矩陣加法 A + B：");
        printMatrix(addMatrices(matrixA, matrixB));

        // 矩陣乘法 (A * C)
        System.out.println("矩陣乘法 A * C：");
        printMatrix(multiplyMatrices(matrixA, matrixC));

        // 矩陣轉置 (A^T)
        System.out.println("矩陣 A 的轉置：");
        printMatrix(transposeMatrix(matrixA));

        // 最大最小值 (A)
        int[] maxMin = findMaxMin(matrixA);
        System.out.println("矩陣 A 的最大值：" + maxMin[0]);
        System.out.println("矩陣 A 的最小值：" + maxMin[1]);
    }

    // 矩陣加法
    public static int[][] addMatrices(int[][] a, int[][] b) {
        if (a.length != b.length || a[0].length != b[0].length) {
            throw new IllegalArgumentException("矩陣維度不相同，無法加總。");
        }
        int[][] result = new int[a.length][a[0].length];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                result[i][j] = a[i][j] + b[i][j];
            }
        }
        return result;
    }

    // 矩陣乘法
    public static int[][] multiplyMatrices(int[][] a, int[][] b) {
        if (a[0].length != b.length) {
            throw new IllegalArgumentException("矩陣維度不符，無法相乘。");
        }
        int[][] result = new int[a.length][b[0].length];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b[0].length; j++) {
                for (int k = 0; k < b.length; k++) {
                    result[i][j] += a[i][k] * b[k][j];
                }
            }
        }
        return result;
    }

    // 矩陣轉置
    public static int[][] transposeMatrix(int[][] matrix) {
        int[][] transposed = new int[matrix[0].length][matrix.length];
        for (int i = 0; i < matrix[0].length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                transposed[i][j] = matrix[j][i];
            }
        }
        return transposed;
    }

    // 找最大與最小值
    public static int[] findMaxMin(int[][] matrix) {
        int max = matrix[0][0];
        int min = matrix[0][0];
        for (int[] row : matrix) {
            for (int val : row) {
                if (val > max) max = val;
                if (val < min) min = val;
            }
        }
        return new int[]{max, min};
    }

    // 輸出矩陣
    public static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
        System.out.println();
    }
}
