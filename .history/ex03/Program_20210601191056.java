import java.io. *;
import java.util.Scanner;

public class Program {


    public void printTable(int count, long res)
    {
        int i = 1;
        int remainder = 1;

        for (int j = 1; j < count; ++j)
            remainder = remainder * 10;

        while (i <= count)
        {
            System.out.println("Week " + count + " ")
            for (int j = 0; j < remainder; ++j)
                System.out.println("=")
            System.out.println(">");
        }
    }


    public int minNumber(int a, int b)
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