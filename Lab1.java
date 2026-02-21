import java.util.Random;

public class Lab1 {

    public static int[][] generatRandomMatrix(int rows, int cols, int min, int max) {
        int[][] matrix = new int[rows][cols];
        Random random = new Random();
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = random.nextInt(max - min + 1) + min;
            }
        }
        return matrix;
    }

    public static int[] findMaxInRows(int[][] matrix) {
        int rows = matrix.length;
        int[] maxArray = new int[rows];

        for (int i = 0; i < rows; i++) {
            int max = matrix[i][0]; 
            
            for (int j = 1; j < matrix[i].length; j++) {
                if (matrix[i][j] > max) {
                    max = matrix[i][j];
                }
            }
            maxArray[i] = max;
        }
        return maxArray;
    }

    public static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int value : row) {
                System.out.printf("%4d ", value);
            }
            System.out.println();
        }
    }

    public static void printArray(int[] array) {
        for (int value : array) {
            System.out.printf("%4d ", value);
        }
        System.out.println();
    }

    public static void main(String[] args) {
    int rows = 5;
    int cols = 4;

    int[][] matrixA = generatRandomMatrix(rows, cols, 1, 99);

    System.out.println("Матриця A до обробки:");
    printMatrix(matrixA);
    }
}