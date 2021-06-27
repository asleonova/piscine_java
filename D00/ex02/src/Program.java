import java.util.Scanner;

public class Program {

    public static int sumOfDigits(int num) {
        int res = 0;
        int remainder = 0;
        while (num > 0) {
            remainder = num % 10;
            res = res + remainder;
            num = num / 10;
        }
        return res;
    }

    public static boolean checkIfPrime(int num) {

        int n = sumOfDigits(num);
        for (int i = 2; i * i <= n; ++i) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }


    public static void main(String args[]) {

        int num = 0;
        boolean query = true;
        int count = 0;
        while (query) {
            Scanner userInput = new Scanner(System.in);
            num = userInput.nextInt();
            if (checkIfPrime(num) == true)
                count++;
            if (num == 42)
                query = false;
        }
        System.out.println("Count of coffee-request - " + count);
    }
}
