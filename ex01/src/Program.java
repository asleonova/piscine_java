import java.io.*;
import java.util.Scanner;

public class Program {

    public static void main(String args[]) {

        int num;
        int numOfSteps = 1;

        Scanner userInput = new Scanner(System.in);
        if (!userInput.hasNextInt()) {
            System.err.println("Illegal Argument");
            userInput.close();
            System.exit(-1);
        }
        num = userInput.nextInt();
        if (num <= 1) {
            System.out.println("Illegal Argument");
            userInput.close();
            System.exit(-1);
        } else if (num == 2) {
            System.out.println("true " + numOfSteps);
        } else {

            for (int i = 2; i * i <= num; ++i) {
                if (num % i == 0) {
                    System.out.println("false "+ numOfSteps);
                    return;
                }
                numOfSteps++;
            }
            System.out.println("true " + numOfSteps);
        }
    }

}
