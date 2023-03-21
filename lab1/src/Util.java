public class Util {
    public static void printMatrix(double[][] a) {
        int size = a.length;
        for (double[] doubles : a) {
            for (int j = 0; j < size + 1; j++) {
                System.out.printf("%.2f\t", doubles[j]);
            }
            System.out.println();
        }
        System.out.println();
    }
    
    public static void printRoots(double[] roots) {
        System.out.println("Корни СЛАУ: ");
        int i = 0;
        for (double x : roots) {
            i++;
            System.out.println("x" + i + " = " + x);
        }
    }

    public static void printDiscrepancy(double[] dis) {
        System.out.println("\nВектор невязки: ");
        int i = 0;
        for (double x : dis) {
            i++;
            System.out.println("x" + i + " = " + x);
        }
    }
}
