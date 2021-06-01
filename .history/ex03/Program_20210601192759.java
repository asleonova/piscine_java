import java.io. *;
import java.util.Scanner;

public class Program {

    public static String printEquals(long count)
    {
        String s = "";
        for (long i = 0; i < count; ++i)
            s += "=";
        return s;
    }

    public static void printTable(int count, long res)
    {
        int i = 1;
        long remainder = 1;

        for (int j = 1; j < count - 1; ++j)
            remainder = remainder * 10;

        while (i < count)
        {
            long sign = res / remainder;
            String s = printEquals(sign);
            System.out.println("Week " + i + " " + s + ">");                
            remainder /= 10;
            i++;
        }
    }


    public static int minNumber(int a, int b)
    {
        return (a > b) ? a : b;
    }
    public static void main(String args[])
    {
        int weekCount = 1;
        int gradesCount = 1;
        int minNum = 0;
        long res = 0;
        Scanner userInput = new Scanner(System.in);

        while (weekCount <= 18)
        {
            String s = userInput.next();
            if ("42".equals(s) == true)
            {
                System.out.println("res is: " + res);
                printTable(weekCount, res);
                System.exit(0);
            }
            if ("Week".equals(s) == false)
            {
                System.err.println("Wrong input");
                System.exit(-1);
            }
            int tmp = userInput.nextInt();
            if (weekCount != tmp)
            {
                System.err.println("Wrong num in input");
                System.exit(-1); 
            }
            while (gradesCount <= 5)
            {
                int inputNum = userInput.nextInt();
                if (gradesCount == 1)
                    minNum = inputNum;
                else
                    minNum = minNumber(minNum, inputNum);
                gradesCount++;
            }
            userInput.nextLine();
            gradesCount = 1;
            res = res * 10 + minNum;
            weekCount++;
        }
    }
}