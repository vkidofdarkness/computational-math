import java.io.File;
import java.io.FileNotFoundException;
import java.security.SecureRandom;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Input {
    public static double[][] userInput() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Выберите способ задания матрицы: ");
        System.out.println("1 - Ввод с консоли");
        System.out.println("2 - Считать с файла");
        System.out.println("3 - Случайная матрица");

        String typeInput = sc.nextLine();
        if (typeInput.equals("1")) {
            return getMatrixFromConsole();
        } else if (typeInput.equals("2")) {
            return getMatrixFromFile();
        } else if (typeInput.equals("3")) {
            return randomMatrix();
        } else {
            System.out.println("Неверный ввод. Повторите снова.\n");
            return userInput();
            }
        }

    public static double[][] randomMatrix() {
        System.out.println("Введите n (размерность квадратной матрицы n*n):");
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        SecureRandom random = new SecureRandom();
        double[][] matrix = new double[n][n+1];
        System.out.println("Исходная матрица: ");

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n + 1; j++) {
                matrix[i][j] = (int)random.nextInt(15);
                System.out.printf("%.2f\t", matrix[i][j]);
            }
            System.out.println();
        }
        return matrix;
        }
    
    public static double[][] getMatrixFromConsole() {
        System.out.println("Введите размерность матрицы: ");
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        double[][] matrix = new double[n][n+1];
        Scanner in = new Scanner(System.in);
        System.out.println("Введите элементы матрицы (через пробел). После ввода строки, нажмите Enter: ");

        for (int i = 0; i < n; i++) {
            String[] matrixRow = in.nextLine().split(" ");
            if (matrixRow.length != n) {
                System.out.println("Ошибка ввода, количество элементов не соответствует размерности матрицы. Попробуйте еще раз.");         
                return getMatrixFromConsole();
            } 
            for (int j = 0; j < n; j++) {
                matrix[i][j] = Double.parseDouble(matrixRow[j]);
            }
        }   

        System.out.println("Введите столбец свободных членов (через пробел): ");
        String columnB = in.nextLine();
        String[] columnRow = columnB.split(" ");
        if (columnRow.length != n) {
            System.out.println("Ошибка ввода, количество элементов не соответствует размерности матрицы. Попробуйте еще раз.");
            return getMatrixFromConsole();
        }
        for (int i = 0; i < n; i++) {
            matrix[i][n] = Double.parseDouble(columnRow[i]);
        }
        return matrix;
    }

    public static double[][] getMatrixFromFile() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("\nВведите имя файла: ");
            String filename = scanner.nextLine();
            File file = new File(filename);
            scanner = new Scanner(file);
            int n = 0;

            while (scanner.hasNextLine()) {
                n++;
                scanner.nextLine();
            }

            scanner = new Scanner(file);
            double[][] matrix = new double[n][n+1];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n+1; j++) {
                    matrix[i][j] = scanner.nextDouble();
                }
            }
            System.out.println("Прочитана матрица:");
            Util.printMatrix(matrix);
            return matrix;

        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден. Попробуйте еще раз.");
            return getMatrixFromFile();            
        } catch (NoSuchElementException e) {
            System.out.println("Неверный формат данных. Попробуйте другой файл.");
            return getMatrixFromFile();   
        }
    }
}
