
public class Main {
/** 
 * Метод гаусса
 * @autor Nikita Zheleznov P32212
 * @version 2.0
*/
    public static void main(String[] args) {
        double[][] matrix = Input.userInput();
        double[] roots = GaussMethod.gaussMethodSolution(matrix);
        Util.printRoots(roots);
    }
}            
