public class GaussMethod {
    public static double[][] swapLines(double[][] matrix) {
        double[] column = new double[matrix.length];
        int index = -1;
        double notNull = 0;
        for (int j = 0; j < matrix.length; j++) {
            column[j] = matrix[j][0];
        }
        for (double x : column) {
            index++;
            if (x != 0) {
                notNull = x;
                break;
            }                                                                
        }
        if (notNull == 0) {
            System.out.println("Решений нет.");
            System.exit(0);
        }
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length + 1; j++) {
                double temp = 0;
                temp = matrix[0][j];
                matrix[0][j] = matrix[index][j];
                matrix[index][j] = temp;
            }
        }    
        return matrix;
        }
    
    public static double[] gaussMethodSolution(double[][] matrix) {
        int n = matrix.length;
        double[][] matrix_clone = matrix.clone();
        double[] roots = new double[n];

        // Прямой ход
        if (matrix[0][0] == 0) {
                matrix = swapLines(matrix);
        }    
        for (int k = 1; k < n; k++) {  //приводим к треугольной форме
            for (int i = k; i < n; i++) {
                double c = matrix[i][k-1] / matrix[k-1][k-1];
                for (int j = 0; j < n + 1; j++) { 
                    matrix[i][j] = matrix[i][j] - c * matrix[k-1][j];
                }
            }
        }

        for (int m = 0; m < n - 1; m++) {
            if (matrix[m][m] == 0) {
                System.out.println("Решений нет.");
                System.exit(0);   
            }
        }

        System.out.println("Определитель: ");
        System.out.printf("%.2f\t", getDeterminant(matrix));
        System.out.println();
        System.out.println("Получена треугольная матрица: ");
        Util.printMatrix(matrix);

        //Обратный ход
        for (int k = n - 1; k >= 0; k--) { //зануляем правый верхний угол
            roots[k] = matrix[k][n] / matrix[k][k];
            for (int i = n - 1; i > k; i--) {
                roots[k] = roots[k] - matrix[k][i] * roots[i] / matrix[k][k];; 
            }
        }
        Util.printDiscrepancy(getDiscrepancy(matrix_clone, roots));
        System.out.println();
        return roots;
    }    
        
    public static double getDeterminant(double[][] matrix) {
        double det = 1;
        int size = matrix.length;

        for (int i = 0; i < size; i++)
            det *= matrix[i][i];
        return det;
    }

    public static double[] getDiscrepancy(double[][] matrix, double[] roots) {
        int n = matrix.length;
        double[] dis = new double[n];

        for(int i = 0; i < n; i++){
            double r = matrix[i][n];
            for(int j = 0; j < n; j++){
                r -= matrix[i][j] * roots[j];
            }

            dis[i] = r;
        }
        return dis;
    }
}

