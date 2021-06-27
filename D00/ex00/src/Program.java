import java.io.*;

public class Program {

    public static void main(String args[]) {
        int num = 479598;
        int res = 0;
        int remainder = 0;

        remainder = num % 10;
        res = res + remainder;
        num /= 10;

        remainder = num % 10;
        res = res + remainder;
        num /= 10;

        remainder = num % 10;
        res = res + remainder;
        num /= 10;

        remainder = num % 10;
        res = res + remainder;
        num /= 10;

        remainder = num % 10;
        res = res + remainder;
        num /= 10;

        remainder = num % 10;
        res = res + remainder;
        num /= 10;

        System.out.println(res);
    }
}